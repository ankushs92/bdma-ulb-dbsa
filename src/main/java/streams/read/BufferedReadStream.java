package streams.read;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.interfaces.AbstractReadStream;

import java.io.*;

public class BufferedReadStream implements AbstractReadStream {

    private static final Logger logger = LoggerFactory.getLogger(BufferedReadStream.class);
    private final int bufferSize;

    public BufferedReadStream(final int bufferSize) {
        this.bufferSize = bufferSize;
    }

    private DataInputStream ds;
    private String fileLocation;

    @Override
    public void open(String fileLocation) throws FileNotFoundException {
        this.fileLocation = fileLocation;
        final InputStream inputStream = new FileInputStream(new File(fileLocation));
        final BufferedInputStream bis = new BufferedInputStream(inputStream, bufferSize);
        ds = new DataInputStream(bis);
    }

    @Override
    public Integer readNext() {
        try {
            return ds.readInt();
        }
        catch (final IOException e) {
            logger.error("Problem reading Integer from input stream.");
        }
        return null;
    }

    @Override
    public boolean endOfStream() {
        try {
            return ds.available() == 0;
        } catch (final IOException e) {
            logger.error("", e);
        }
        return false;
    }

    @Override
    public long getFileSize() throws IllegalAccessException {
        throw new IllegalAccessException("Please refer to MemMapReadStream implementation for file size");
    }

    @Override
    public String getFileLocation() {
        return fileLocation;
    }

    @Override
    public void close() throws Exception {
        if(endOfStream()) {
            ds.close();
        }
    }
}
