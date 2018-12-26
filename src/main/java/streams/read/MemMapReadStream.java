package streams.read;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.interfaces.AbstractReadStream;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MemMapReadStream implements AbstractReadStream {

    private final static Logger logger = LoggerFactory.getLogger(MemMapReadStream.class);

    private static final String READ_MODE = "r";
    //All inputs are 32bit Integers UNIT_SIZE
    private static final Integer UNIT_SIZE = 4;
    private MappedByteBuffer mappedByteBuffer;
    private RandomAccessFile randomAccessFile;
    private FileChannel fileChannel;
    private long fileSize = 0;
    private long currentPosition = 0;


    private int bufferSize;

    public MemMapReadStream(int bufferSize) {
        if(bufferSize * UNIT_SIZE < UNIT_SIZE){
            logger.warn("Buffer smaller than UNIT_SIZE. Setting to min read size = " + UNIT_SIZE + " Bytes.");
            bufferSize = 1; //One unit size
        }
        this.bufferSize = bufferSize * UNIT_SIZE;
    }

    @Override
    public void open(final String fileLocation) throws IOException {
        randomAccessFile = new RandomAccessFile(fileLocation, READ_MODE);
        fileChannel = randomAccessFile.getChannel();
        fileSize = fileChannel.size();
        currentPosition = 0;
        mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, bufferSize);
    }

    @Override
    public Integer readNext() {
        if(currentPosition < 0)
            return null;
        Integer readNumber = null;
        readNumber = mappedByteBuffer.getInt();
        currentPosition += UNIT_SIZE;
        if(mappedByteBuffer.remaining() < UNIT_SIZE)
        {
            if(currentPosition >= fileSize) {
                currentPosition = -1;
            }
            else{
                try {
                    mappedByteBuffer.clear();
                    //Do no request more than required. We are reading.
                    if(bufferSize > fileSize - (currentPosition)) {
                        bufferSize = (int) (fileSize - currentPosition);
                    }
                    mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, currentPosition, bufferSize);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return readNumber;
    }

    public void close(){
        try {
            fileChannel.close();
            randomAccessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean endOfStream() {
        if(currentPosition < 0) {
            close();
            return true;
        }
        if(currentPosition < fileSize)
            return false;
        return true;
    }


    public long getFileSize() {

        try {
            return fileSize;
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}
