package benchmark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.interfaces.AbstractReadStream;
import streams.interfaces.AbstractWriteStream;
import streams.read.BufferedReadStream;
import streams.read.FReadStream;
import streams.read.MemMapReadStream;
import streams.read.ReadStream;
import streams.write.BufferedWriteStream;
import streams.write.FWriteStream;
import streams.write.MemoryMappedWriteStream;
import streams.write.WriteStream;
import util.NumberUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class StreamsBenchmark {

    private static final Logger logger = LoggerFactory.getLogger(StreamsBenchmark.class);
    private final int k;
    private final List<File> inputFiles;
    private final int bufferSize;
    private final int outputFileSize;
    private final List<File> outputFiles;

    public StreamsBenchmark(
            final List<File> inputFiles,
            final List<File> outputFiles,
            final int outputFileSize,
            final int k,
            final int bufferSize
    )
    {
        this.inputFiles = inputFiles;
        this.outputFiles = outputFiles;
        this.outputFileSize = outputFileSize;

        this.k = k;
        this.bufferSize = bufferSize;
    }

    // ----- 1st Implementation
    public long getTimeTakenToExecuteKReadStreams() throws FileNotFoundException {
        final Queue<AbstractReadStream> readStreamsQueue = new LinkedList<>();
        for(int i = 0; i < k; i++) {
            final ReadStream readStream = new ReadStream();
            final File inputFile = inputFiles.get(i);
            readStream.open(inputFile.getPath());
            readStreamsQueue.add(readStream);
        }
        final long timeTaken = getTimeTakenForReadStreams(readStreamsQueue);
        closeReadStreams(readStreamsQueue);
        return timeTaken;
    }

    public long getTimeTakenToExecuteKWriteStreams() {
        final Queue<AbstractWriteStream> writeStreamsQueue = new LinkedList<>();
        final Queue<AbstractWriteStream> copy = new LinkedList<>();
        for(int i = 0; i < k; i++) {
            final WriteStream writeStream = new WriteStream();
            final File inputFile = outputFiles.get(i);
            writeStream.create(inputFile.getPath());
            writeStreamsQueue.add(writeStream);
            copy.add(writeStream);
        }
        final long timeTaken = getTimeTakenForWriteStreams(writeStreamsQueue);
        closeWriteStreams(copy);
        return timeTaken;
    }

    // ----- IInd Implementation
    public long getTimeTakenToExecuteKFReadStreams() throws FileNotFoundException {
        final Queue<AbstractReadStream> fReadStreamsQueue = new LinkedList<>();
        for(int i = 0; i < k; i++) {
            final FReadStream fReadStream = new FReadStream();
            final File inputFile = inputFiles.get(i);
            fReadStream.open(inputFile.getPath());
            fReadStreamsQueue.add(fReadStream);
        }
        final long timeTaken = getTimeTakenForReadStreams(fReadStreamsQueue);
        closeReadStreams(fReadStreamsQueue);
        return timeTaken;
    }

    public long getTimeTakenToExecuteKFWriteStreams() {
        final Queue<AbstractWriteStream> fWriteStreamsQueue = new LinkedList<>();
        final Queue<AbstractWriteStream> copy = new LinkedList<>();
        for(int i = 0; i < k; i++) {
            final FWriteStream fWriteStream = new FWriteStream();
            final File inputFile = outputFiles.get(i);
            fWriteStream.create(inputFile.getPath());
            fWriteStreamsQueue.add(fWriteStream);
            copy.add(fWriteStream);
        }
        final long timeTaken = getTimeTakenForWriteStreams(fWriteStreamsQueue);
        closeWriteStreams(copy);
        return timeTaken;
    }

    // ----- IIIrd Implementation
    public long getTimeTakenToExecuteKBufferedReadStreams() throws FileNotFoundException {
        final Queue<AbstractReadStream> bufferedReadStreamsQueue = new LinkedList<>();
        for(int i = 0; i < k; i++) {
            final BufferedReadStream readStream = new BufferedReadStream(bufferSize);
            final File inputFile = inputFiles.get(i);
            readStream.open(inputFile.getPath());
            bufferedReadStreamsQueue.add(readStream);
        }
        final long timeTaken = getTimeTakenForReadStreams(bufferedReadStreamsQueue);
        closeReadStreams(bufferedReadStreamsQueue);
        return timeTaken;
    }

    public long getTimeTakenToExecuteKBufferedWriteStreams() throws FileNotFoundException {
        final Queue<AbstractWriteStream> bufferedWriteStreamsQueue = new LinkedList<>();
        final Queue<AbstractWriteStream> copy = new LinkedList<>();
        for(int i = 0; i < k; i++) {
            final BufferedWriteStream bufferedWriteStream = new BufferedWriteStream(bufferSize);
            final File outputFile = outputFiles.get(i);
            bufferedWriteStream.create(outputFile.getPath());
            bufferedWriteStreamsQueue.add(bufferedWriteStream);
            copy.add(bufferedWriteStream);
        }
        final long timeTaken = getTimeTakenForWriteStreams(bufferedWriteStreamsQueue);
        closeWriteStreams(copy);
        return timeTaken;
    }

    // IVth Implementation
    public long getTimeTakenToExecuteKMemMappedByteReadStreams() throws IOException {
        final Queue<AbstractReadStream> memMappedReadStreamsQueue = new LinkedList<>();
        for(int i = 0; i < k; i++) {
            final MemMapReadStream readStream = new MemMapReadStream(bufferSize);
            final File inputFile = inputFiles.get(i);
            readStream.open(inputFile.getPath());
            memMappedReadStreamsQueue.add(readStream);
        }
        final long timeTaken = getTimeTakenForReadStreams(memMappedReadStreamsQueue);
        closeReadStreams(memMappedReadStreamsQueue);
        return timeTaken;
    }

    public long getTimeTakenToExecuteKMemMappedByteWriteStreams()  {
        final Queue<AbstractWriteStream> memMappedReadStreamsQueue = new LinkedList<>();
        final Queue<AbstractWriteStream> copy = new LinkedList<>();
        for(int i = 0; i < k; i++) {
            final MemoryMappedWriteStream writeStream = new MemoryMappedWriteStream(bufferSize);
            final File outputFile = outputFiles.get(i);
            writeStream.create(outputFile.getPath());
            memMappedReadStreamsQueue.add(writeStream);
            copy.add(writeStream);
        }
        final long timeTaken = getTimeTakenForWriteStreams(memMappedReadStreamsQueue);
        closeWriteStreams(copy);
        return timeTaken;
    }


    private long getTimeTakenForReadStreams(final Queue<AbstractReadStream> readStreams) {
        final long start = System.currentTimeMillis();
        while(!readStreams.isEmpty()) {
            final AbstractReadStream readStream = readStreams.remove();
            if(!readStream.endOfStream()) {
                readStream.readNext();
                readStreams.add(readStream);
            }
        }
        final long stop = System.currentTimeMillis();
        logger.info("Time taken to Execute getTimeTakenToExecuteKReadStreams is {}", stop - start);
        return stop - start;
    }

    private long getTimeTakenForWriteStreams(final Queue<AbstractWriteStream> writeStreams) {
        final long start = System.currentTimeMillis();
        long pos = 0;
        final long limit = (long) outputFileSize * (long) k / 4;

//        3589000000
//        3861225472
        //640000000
        while(!writeStreams.isEmpty()) {
            final AbstractWriteStream writeStream = writeStreams.remove();
            if(pos <= limit) {
                writeStream.write(NumberUtil.randomInt());
                pos++;
                writeStreams.add(writeStream);
            }
        }
        final long stop = System.currentTimeMillis();
        logger.info("Time taken to Execute getTimeTakenToExecuteKReadStreams is {}", stop - start);

        return stop - start;
    }

    private static void closeReadStreams(final Queue<AbstractReadStream> readStreams) {
        readStreams.forEach(stream -> {
            try {
                stream.close();
            }
            catch (final Exception ex) {
                logger.error("", ex);
            }
        });
    }

    private static void closeWriteStreams(final Queue<AbstractWriteStream> writeStreams) {
        writeStreams.forEach(stream -> {
            try {
                stream.close();
            }
            catch (final Exception ex) {
                logger.error("", ex);
            }
        });
    }

    public static void main(String[] args) throws IOException {
        int x = 128000000;
        int k = 20;
        long limit = (long) x * (long) k / 4;
        System.out.println(limit);

//        int bufferSize = 1073741;
//        File inputFile = new File("/Users/ankushsharma/Desktop/code/dbsa/src/main/resources/benchmark/implementation_1_2/4196_bytes_1024_integers");
//        int k = 3;
//        List<File> inputFiles = new ArrayList<>();
//        for(int i =0; i < k; i++) {
//            inputFiles.add(inputFile);
//        }
//
//
//        StreamsBenchmark readImpl1 = new StreamsBenchmark(inputFiles, null, 0, k, 0);
////        StreamsBenchmark readImpl2 = new StreamsBenchmark(inputFiles, null, 0, k, 0);
////        StreamsBenchmark readImpl3 = new StreamsBenchmark(inputFiles, null, 0, k, bufferSize);
////        StreamsBenchmark readImpl4 = new StreamsBenchmark(inputFiles, null, 0, k, bufferSize);
//
//        System.out.println(readImpl1.getTimeTakenToExecuteKReadStreams());
////        System.out.println(readImpl2.getTimeTakenToExecuteKFReadStreams());
////        System.out.println(readImpl3.getTimeTakenToExecuteKBufferedReadStreams());
////        System.out.println(readImpl4.getTimeTakenToExecuteKMemMappedByteReadStreams());

    }

}
