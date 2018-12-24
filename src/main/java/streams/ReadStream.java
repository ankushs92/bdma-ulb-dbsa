package streams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ReadStream {

    private static final Logger logger = LoggerFactory.getLogger(FileGenerator.class);
    private DataInputStream ds;

    public void open(String fileLocation) throws FileNotFoundException {
        //sourceFile = fileLocation;
        final InputStream inputStream;
        inputStream = new FileInputStream( new File(fileLocation) );
        //System.out.println("Reading numbers..");
        ds = new DataInputStream(inputStream);
    }

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
}
