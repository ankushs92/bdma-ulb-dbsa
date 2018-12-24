package streams.read;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.interfaces.AbstractReadStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MemoryMappedReadStream implements AbstractReadStream {

    private final static Logger logger = LoggerFactory.getLogger(MemoryMappedReadStream.class);

    private static final String READ_MODE = "r";
    private MappedByteBuffer mappedByteBuffer;
    private RandomAccessFile randomAccessFile;
    private FileChannel fileChannel;

    private final int bufferSize;

    public MemoryMappedReadStream(final int bufferSize) {
        this.bufferSize = bufferSize;
    }


    @Override
    public void open(final String fileLocation) throws IOException {
        randomAccessFile = new RandomAccessFile(fileLocation, READ_MODE);
        fileChannel = randomAccessFile.getChannel();
        mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, bufferSize);
    }

    @Override
    public Integer readNext() {
        //final int index = mappedByteBuffer.position();
//        mappedByteBuffer.position()
        return mappedByteBuffer.getInt();
       // return mappedByteBuffer.getInt();
    }

    @Override
    public boolean endOfStream() {
        final int remaining = mappedByteBuffer.remaining();
        if(remaining == 0) {
            return true;
        }
        return false;
    }
}
