package streams.write;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.interfaces.AbstractWriteStream;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MemoryMappedWriteStream implements AbstractWriteStream {

    private final static Logger logger = LoggerFactory.getLogger(MemoryMappedWriteStream.class);

    private static final String WRITE_MODE = "rw";
    //All inputs are 32bit Integers UNIT_SIZE
    private static final Integer UNIT_SIZE = 4;
    private MappedByteBuffer mappedByteBuffer;
    private RandomAccessFile randomAccessFile;
    private FileChannel fileChannel;
    private long fileSize = 0;
    private long currentPosition = 0;
    private int bufferSize;


    public MemoryMappedWriteStream(int bufferSize) {
//        if(bufferSize * UNIT_SIZE < UNIT_SIZE){
//            logger.warn("Buffer smaller than UNIT_SIZE. Setting to min write size = " + UNIT_SIZE + " Bytes.");
//            bufferSize = 1; //One unit size
//        }
        this.bufferSize = bufferSize;
    }

    @Override
    public void create(final String fileLocation) {
        try {
            randomAccessFile = new RandomAccessFile(fileLocation, WRITE_MODE);
            fileChannel = randomAccessFile.getChannel();
            fileSize = fileChannel.size();
            currentPosition = 0;
            mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, bufferSize);
        }
        catch (final IOException e) {
            logger.error("", e);
        }

    }

    @Override
    public void write(final Integer value) {
        if (mappedByteBuffer.remaining() < UNIT_SIZE) {
            try {
                mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, currentPosition, bufferSize);
            } catch (final IOException e) {
                logger.error("", e);
            }
        }
        mappedByteBuffer.putInt(value);
        currentPosition += UNIT_SIZE;
    }

    @Override
    public void close() {
        try {
            System.out.println("File size " + fileSize);
            System.out.println("Current position " + currentPosition);
            System.out.println("Buffer size " + bufferSize);
            //Since we are not interested in append data.
            fileChannel.truncate(currentPosition);
            mappedByteBuffer.clear();
            randomAccessFile.close();
            fileChannel.close();
        } catch (final IOException e) {
            logger.error("", e);
        }

    }
}



