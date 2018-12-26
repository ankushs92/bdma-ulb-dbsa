package streams.read;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.FileGenerator;
import streams.interfaces.AbstractReadStream;

import java.io.*;

public class FReadStream implements AbstractReadStream {

    private static final Logger logger = LoggerFactory.getLogger(FileGenerator.class);
    private DataInputStream ds;

    public void open(String fileLocation) throws FileNotFoundException {
        final InputStream inputStream = new FileInputStream(new File(fileLocation));
        final BufferedInputStream bis = new BufferedInputStream(inputStream);
        ds = new DataInputStream(bis);
    }

    @SuppressWarnings("Duplicates")
    public Integer readNext() {
        try {
            return ds.readInt();
        } catch (IOException e) {
            logger.error("Problem reading Integer from input stream.");
            //e.printStackTrace();
            if(!endOfStream())
                System.out.println("Reached end of stream?");
        }
        return null;
    }

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
    public long getFileSize() {
        return 0;
    }

    @Override
    public void close() throws Exception {
        if(endOfStream()) {
            ds.close();
        }
    }
}
