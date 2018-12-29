package benchmark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.interfaces.AbstractReadStream;
import streams.read.BufferedReadStream;
import streams.read.FReadStream;
import streams.read.MemMapReadStream;
import streams.read.ReadStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.Queue;


public class StreamsBenchmark {

    private static final Logger logger = LoggerFactory.getLogger(StreamsBenchmark.class);
    private final int k;
    private final File file;
    private final int bufferSize;

    public StreamsBenchmark(
            final File file,
            final int k,
            final int bufferSize
    )
    {
        this.file = file;
        this.k = k;
        this.bufferSize = bufferSize;
    }

    public long getTimeTakenToExecuteKReadStreams() throws FileNotFoundException {
        Queue<AbstractReadStream> readStreams = new LinkedList<>();
        for(int i = 1; i <= k; i++) {
            final ReadStream readStream = new ReadStream();
            readStream.open(file.getPath());
            readStreams.add(readStream);
        }
        return getTimeTaken(readStreams);
    }

    public long getTimeTakenToExecuteKFReadStreams() throws FileNotFoundException {
        Queue<AbstractReadStream> readStreams = new LinkedList<>();
        for(int i = 1; i <= k; i++) {
            final FReadStream readStream = new FReadStream();
            readStream.open(file.getPath());
            readStreams.add(readStream);
        }
        return getTimeTaken(readStreams);
    }

    public long getTimeTakenToExecuteKBufferedReadStreams() throws FileNotFoundException {
        Queue<AbstractReadStream> readStreams = new LinkedList<>();
        for(int i = 1; i <= k; i++) {
            final BufferedReadStream readStream = new BufferedReadStream(bufferSize);
            readStream.open(file.getPath());
            readStreams.add(readStream);
        }
        return getTimeTaken(readStreams);
    }

    public long getTimeTakenToExecuteKMemMappedByteStreams() throws IOException {
        Queue<AbstractReadStream> readStreams = new LinkedList<>();
        for(int i = 1; i <= k; i++) {
            final MemMapReadStream readStream = new MemMapReadStream(bufferSize);
            readStream.open(file.getPath());
            readStreams.add(readStream);
        }
        return getTimeTaken(readStreams);
    }


    public long getTimeTaken(final Queue<AbstractReadStream> readStreams) throws FileNotFoundException {
        long start = System.currentTimeMillis();
        int index = 0;
        while(!readStreams.isEmpty()) {
            final AbstractReadStream readStream = readStreams.remove();
            while(!readStream.endOfStream()) {
                index++;
                readStream.readNext();
                readStreams.add(readStream);
                if(index % 100000 ==0 ){
                    System.out.println("Index is " + index);
                }
            }
        }
        long stop = System.currentTimeMillis();
        logger.info("Time taken to Execute getTimeTakenToExecuteKReadStreams is {}", stop - start);
        return stop - start;
    }

    public static void main(String[] args) throws IOException {
        int bufferSize = 1073741;
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/ankushsharma/Desktop/code/dbsa/src/main/resources/benchmark/implementation_1_2/536870912_bytes_134217728_integers", "rw");
        File file = new File("/Users/ankushsharma/Desktop/code/dbsa/src/main/resources/benchmark/implementation_1_2/536870912_bytes_134217728_integers");
//        final StreamsBenchmark streamsBenchmark = new StreamsBenchmark(file, 50, bufferSize);
//        final StreamsBenchmark streamsBenchmark1 = new StreamsBenchmark(file, 50, bufferSize);
//        final StreamsBenchmark streamsBenchmark2 = new StreamsBenchmark(file, 50, bufferSize);
        final StreamsBenchmark streamsBenchmark3 = new StreamsBenchmark(file, 30, bufferSize);

//        System.out.println(streamsBenchmark.getTimeTakenToExecuteKReadStreams());
//        System.out.println(streamsBenchmark1.getTimeTakenToExecuteKFReadStreams());
//        System.out.println(streamsBenchmark2.getTimeTakenToExecuteKBufferedReadStreams());
        System.out.println(streamsBenchmark3.getTimeTakenToExecuteKMemMappedByteStreams());

    }



}
