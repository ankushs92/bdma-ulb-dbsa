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
    private String fileLocation;
    private long fileSize = 0;
    private long bytesRead = 0;
    private int bufferSize;

    public MemMapReadStream(int bufferSize) {
        if(bufferSize < UNIT_SIZE){
            logger.warn("Buffer smaller than UNIT_SIZE. Setting to min read size = " + UNIT_SIZE + " Bytes.");
            bufferSize = 4; //One unit size
        }
        this.bufferSize = bufferSize;
    }

    @Override
    public void open(final String file) throws IOException {
        this.fileLocation = file;
        randomAccessFile = new RandomAccessFile(fileLocation, READ_MODE);
        fileChannel = randomAccessFile.getChannel();
        fileSize = fileChannel.size();
        if(bufferSize > fileSize)
            bufferSize = (int) fileSize;
        mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, bufferSize);
    }

    @Override
    public String getFileLocation(){
       return this.fileLocation;
    }

    @Override
    public Integer readNext() {
//        if(bytesRead < 0) {
//            return null;
//        }
        final Integer readNumber  = mappedByteBuffer.getInt();
        bytesRead += UNIT_SIZE;
        if(mappedByteBuffer.remaining() < UNIT_SIZE) {
//            if(bytesRead >= fileSize) {
//                bytesRead = -1;
//            }
           // else {
                try {
                    if(bufferSize > (fileSize - bytesRead)) {
                        bufferSize = (int) (fileSize - bytesRead);
                    }
                    mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, bytesRead, bufferSize);
                }
                catch (final Exception e) {
                    logger.error("", e);
                }
            //}
        }
        return readNumber;
    }

    @Override
    public void close(){
        try {
            fileChannel.close();
            randomAccessFile.close();
        } catch (final IOException e) {
            logger.error("", e);
        }
    }

    @Override
    public boolean endOfStream() {
        if(bytesRead < 0) {
            close();
            return true;
        }
        if(bytesRead < fileSize)
            return false;
        return true;
    }

    @Override
    public long getFileSize() {
        return fileSize;
    }

}
