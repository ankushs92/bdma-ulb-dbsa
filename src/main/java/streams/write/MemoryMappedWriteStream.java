package streams.write;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.interfaces.AbstractWriteStream;

import java.io.FileNotFoundException;
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


    @Override
    public void create(String fileLocation) {
        int bufferSize = 4;
        if(bufferSize * UNIT_SIZE < UNIT_SIZE){
            logger.warn("Buffer smaller than UNIT_SIZE. Setting to min write size = " + UNIT_SIZE + " Bytes.");
            bufferSize = 1; //One unit size
        }
        this.bufferSize = bufferSize * UNIT_SIZE;

        try {
            randomAccessFile = new RandomAccessFile(fileLocation, WRITE_MODE);
            fileChannel = randomAccessFile.getChannel();
            fileSize = fileChannel.size();
            currentPosition = 0;
            mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, this.bufferSize);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void write(Integer value) {
        if (mappedByteBuffer.remaining() < UNIT_SIZE) {
            System.out.println("Buffer is full, remaining" + mappedByteBuffer.hasRemaining());
            try {
                mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, currentPosition, this.bufferSize);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mappedByteBuffer.putInt(value);
        currentPosition += UNIT_SIZE;
    }

    @Override
    public void close() {
        mappedByteBuffer.clear();


    }
}



