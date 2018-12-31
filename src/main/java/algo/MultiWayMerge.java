package algo;

import streams.interfaces.AbstractReadStream;
import streams.read.MemMapReadStream;
import streams.read.ReadStream;
import streams.write.MemoryMappedWriteStream;

import java.io.IOException;
import java.util.*;

import static algo.Constants.SORTED_DIR;
import static algo.Constants.SORTED_EXT;

public class MultiWayMerge {

    private final Queue<String> streamsLocations;
    private final int bufferSize;
    private final int d;
    private Queue<StreamsPriorityQueue> removeStreams = new PriorityQueue<>();

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
        System.out.println(streamsLocations);
        while(!streamsLocations.isEmpty() && streamsLocations.size() > 1) {
            Queue<StreamsPriorityQueue> streamsQueue = new PriorityQueue<>();
            String mergedFileKey = "";
            for (int i = 0; i < d && i <= streamsLocations.size(); i++) {
                MemMapReadStream stream = new MemMapReadStream(bufferSize);
                    StreamsPriorityQueue streamManager = new StreamsPriorityQueue(0, stream);
                    try {
                        String fileKey = streamsLocations.remove();
                        streamManager.getStream().open(SORTED_DIR + fileKey + SORTED_EXT);
                        streamManager.readNext();
                        streamsQueue.add(streamManager);
                        mergedFileKey += fileKey + "_";

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            //We use the file key as name but in the last one we call it finalMergedFile
            if(streamsQueue.size()>2)
                mergedFileKey = mergedFileKey.substring(0, mergedFileKey.length()-1);
            else
                mergedFileKey = "finalMergedFile";


            System.out.println("We have " + streamsQueue.size() + " with name " + mergedFileKey);
            mergeSort(streamsQueue, SORTED_DIR + mergedFileKey + SORTED_EXT);
            streamsLocations.add(mergedFileKey);
        }
    }

    public void mergeSort(Queue<StreamsPriorityQueue> kStreams, String fileName) {
        //Create the ouput stream. We consider size 1 for output
        System.out.println(fileName);
        MemoryMappedWriteStream output = new MemoryMappedWriteStream(1);
        output.create(fileName);
        while(!kStreams.isEmpty()) {
            StreamsPriorityQueue streamMinValue = kStreams.remove();
            int minValue = streamMinValue.getIntValue();
            output.write(minValue);
            if(!streamMinValue.getStream().endOfStream())
            {
                streamMinValue.readNext();
                kStreams.add(streamMinValue);
            }
            else{
                //add the stream to be deleted with its file
                removeStreams.add(streamMinValue);
            }
        }
        //We finish the merge, close the output.
        output.close();


    }
    public void removeTemporalFiles(){
        //We merged the files, we can remove them.
        while(!removeStreams.isEmpty()) {
            StreamsPriorityQueue streamToRemove = removeStreams.remove();
            streamToRemove.deleteFile();
        }
    }
}

