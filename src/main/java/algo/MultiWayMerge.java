package algo;

import streams.read.MemMapReadStream;
import streams.write.MemoryMappedWriteStream;

import java.io.IOException;
import java.util.*;

import static algo.Constants.*;

public class MultiWayMerge {

    private final Queue<String> streamsLocations;
    private final int bufferSize;
    private final int d;
    private Queue<Integer> windowQueue;

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
//         currentSublists;
        while(!streamsLocations.isEmpty() && streamsLocations.size() > 1){
            List<MemMapReadStream> currentSubLists = new ArrayList<>();
            List<Integer> window = new ArrayList<>();
            windowQueue = new LinkedList<>();
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

            mergeSort(currentSubLists, window, SORTED_DIR + mergedFileKey + SORTED_EXT);
            streamsLocations.add(mergedFileKey);
            System.out.println(streamsLocations);
        }
    }

    public void mergeSort(List<MemMapReadStream> kStreams, List<Integer> window, String fileName) {
        MemoryMappedWriteStream output = new MemoryMappedWriteStream(1);
        output.create(fileName);
        System.out.println("Creating file " + fileName);
        int minimumIndex;
        int indexOfMinimum;
        for (int i = 0; i < kStreams.size(); i++) {
            while (window.size()>0){
                minimumIndex = window.indexOf(Collections.min(window));
                indexOfMinimum = windowQueue.peek();
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
