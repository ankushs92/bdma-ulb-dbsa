package algo;

import streams.read.MemMapReadStream;
import streams.write.MemoryMappedWriteStream;

import java.io.IOException;
import java.util.*;

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
//         currentSublists;
        while(!streamsLocations.isEmpty()){
            List<MemMapReadStream> currentSublists = new ArrayList<>();
            List<String> names = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");
            List<Integer> window = new ArrayList<>();
            String mergedFileKey = "";
            for (int i = 0; i < d; i++) {

                MemMapReadStream bufferManager = new MemMapReadStream(bufferSize);
                try {
                    String fileKey = streamsLocations.remove();
                    System.out.println(fileKey);
                    bufferManager.open(fileKey);
                    mergedFileKey += "_" + names.get(i);
                    currentSublists.add(bufferManager);
                    window.add(bufferManager.readNext());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(currentSublists);
            System.out.println(window);
            mergeSort(currentSublists, window, mergedFileKey);

            streamsLocations.add(mergedFileKey);
        }
    }

    public void mergeSort(List<MemMapReadStream> kStreams, List<Integer> window, String fileName) {
        MemoryMappedWriteStream output = new MemoryMappedWriteStream(1);
        output.create(fileName);

        System.out.println("Kstreams " + kStreams.size());
        int minimumIndex;
        for (int i = 0; i < kStreams.size(); i++) {
            while (window.size()>0){
                minimumIndex = window.indexOf(Collections.min(window));
                System.out.println("Minimum " + minimumIndex + " with value " + window.get(minimumIndex));
                Integer newValue = kStreams.get(minimumIndex).readNext();
                if(newValue == null) {
                    window.remove(minimumIndex);
                    kStreams.remove(minimumIndex);
                }
                else
                    window.set(minimumIndex, newValue);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,-1,0,-1);

        System.out.println(list.indexOf(-1));
    }
}
