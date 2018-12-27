package algo;

import streams.read.MemMapReadStream;
import streams.write.MemoryMappedWriteStream;

import java.io.IOException;
import java.util.*;

public class MultiWayMerge {

    private final Queue<String> streamsLocations;
    private final int bufferSize;
    private final int d;
    private final String rootDir = "./src/main/resources/sorted/";
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
                    mergedFileKey += names.get(i) + "_";
                    currentSublists.add(bufferManager);
                    window.add(bufferManager.readNext());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            mergedFileKey = rootDir + mergedFileKey + ".data";
            mergeSort(currentSublists, window, mergedFileKey);
            streamsLocations.add(mergedFileKey);
        }
    }

    public String mergeSort(List<MemMapReadStream> kStreams, List<Integer> window, String fileName) {
        MemoryMappedWriteStream output = new MemoryMappedWriteStream(1);
        output.create(fileName);

        System.out.println("Kstreams " + kStreams.size());
        int minimumIndex;
        for (int i = 0; i < kStreams.size(); i++) {
            while (window.size()>0){
                minimumIndex = window.indexOf(Collections.min(window));
                System.out.println("Minimum " + minimumIndex + " with value " + window.get(minimumIndex));
                output.write(window.get(minimumIndex));
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
