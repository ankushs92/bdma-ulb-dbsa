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

    @Override
    public void open(String fileLocation) throws FileNotFoundException {
        final InputStream inputStream = new FileInputStream(new File(fileLocation));
        final BufferedInputStream bis = new BufferedInputStream(inputStream, bufferSize);
        ds = new DataInputStream(bis);
    }

    @Override
    @SuppressWarnings("Duplicates")
    public Integer readNext() {
        try {
            return ds.readInt();
        } catch (IOException e) {
            logger.error("Problem reading Integer from input stream.");
            if(!endOfStream())
                System.out.println("Reached end of stream?");
        }
        return null;
    }

    @Override
    public boolean endOfStream() {
        try {
            if (ds.available() == 0)
                return true;
        } catch (final IOException e) {
            logger.error("", e);
        }
        return false;
    }

    @Override
    public void close() throws Exception {
        if(endOfStream()) {
            ds.close();
        }
    }
}
