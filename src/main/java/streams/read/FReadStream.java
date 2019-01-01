package streams.read;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.FileGenerator;
import streams.interfaces.AbstractReadStream;

import java.io.*;

public class FReadStream implements AbstractReadStream {

    private static final Logger logger = LoggerFactory.getLogger(FileGenerator.class);
    private DataInputStream ds;
    private String fileLocation;

    @Override
    public void open(final String fileLocation) throws FileNotFoundException {
        final InputStream inputStream = new FileInputStream(new File(fileLocation));
        this.fileLocation = fileLocation;
        final BufferedInputStream bis = new BufferedInputStream(inputStream);
        ds = new DataInputStream(bis);
    }

    @Override
    public Integer readNext() {
        try {
            return ds.readInt();
        } catch (final IOException e) {
            logger.error("Problem reading Integer from input stream.");
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
    public long getFileSize() throws Exception {
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
