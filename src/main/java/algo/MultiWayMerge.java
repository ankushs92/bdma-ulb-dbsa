package algo;

import streams.read.MemMapReadStream;
import streams.write.MemoryMappedWriteStream;

import java.io.IOException;
import java.util.*;

import static algo.Constants.SORTED_DIR;
import static algo.Constants.SORTED_EXT;

public class MultiWayMerge {

    private final Queue<String> streamsLocations;
    private final int bufferSize;
    private final int d;

    public MultiWayMerge(
            final Queue<String> streamsLocations,
            final int bufferSize,
            final int d
    )
    {
        this.streamsLocations = streamsLocations;
        this.bufferSize = bufferSize;
        this.d = d;
    }

    public void merge() {
        while(!streamsLocations.isEmpty() && streamsLocations.size() > 1) {
            System.out.println(streamsLocations);
            List<MemMapReadStream> currentSubLists = new ArrayList<>();
            List<Integer> window = new ArrayList<>();
            Queue<Integer> windowQueue = new LinkedList<>();
            String mergedFileKey = "";
            for (int i = 0; i < d && i <= streamsLocations.size(); i++) {
                MemMapReadStream bufferManager = new MemMapReadStream(bufferSize);
                try {
                    String fileKey = streamsLocations.remove();
                    bufferManager.open(SORTED_DIR + fileKey + SORTED_EXT);
                    mergedFileKey += fileKey + "_";
                    int firstElement = bufferManager.readNext();
                    currentSubLists.add(bufferManager);
                    window.add(firstElement);
                    windowQueue.add(firstElement);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            mergedFileKey = mergedFileKey.substring(0, mergedFileKey.length()-1);

            mergeSort(currentSubLists, window, SORTED_DIR + mergedFileKey + SORTED_EXT, windowQueue);
            streamsLocations.add(mergedFileKey);
            System.out.println(streamsLocations);
        }
    }

    public void mergeSort(List<MemMapReadStream> kStreams, List<Integer> window, String fileName, Queue<Integer> windowQueue) {
        MemoryMappedWriteStream output = new MemoryMappedWriteStream(1);
        output.create(fileName);
        int minimumIndex;
        for (int i = 0; i < kStreams.size(); i++) {
            while(window.size() > 0) {
                minimumIndex = window.indexOf(Collections.min(window));
                output.write(window.get(minimumIndex));
                //Get the next Int in the stream or remove it if no more data
                if(!kStreams.get(minimumIndex).endOfStream())
                {
                    Integer newValue = kStreams.get(minimumIndex).readNext();
                    window.set(minimumIndex, newValue);
                }
                else {
                        //Stream exhausted, remove it.
                        window.remove(minimumIndex);
                        kStreams.remove(minimumIndex);
                }
            }
        }
    }


}
