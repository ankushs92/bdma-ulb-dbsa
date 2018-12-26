package streams.read;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.FileGenerator;
import streams.interfaces.AbstractReadStream;

import java.io.*;

public class ReadStream implements AbstractReadStream {

    private static final Logger logger = LoggerFactory.getLogger(FileGenerator.class);
    private DataInputStream ds;

    @Override
    public void open(String fileLocation) throws FileNotFoundException {
        final InputStream inputStream  = new FileInputStream( new File(fileLocation) );
        ds = new DataInputStream(inputStream);
    }

    @Override
    public Integer readNext() {
        try {
            return ds.readInt();
        } catch (final IOException e) {
            logger.error("Problem reading Integer from input stream {}", e);
            //e.printStackTrace();
            if(!endOfStream())
                logger.debug("Reached end of stream?");
            return null;
        }
    }

    @Override
    public boolean endOfStream() {
        try {
            if (ds.available() == 0)
                return true;
        } catch (final IOException e) {
            logger.error("Error checking endOfStream.");
            e.printStackTrace();
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
