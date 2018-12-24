package streams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.interfaces.AbstractWriteStream;

import java.io.*;

public class FWriteStream implements AbstractWriteStream {

    private static final Logger logger = LoggerFactory.getLogger(FileGenerator.class);
    private DataOutputStream ds;

    public void create(String fileLocation) {
        try {
            if(ds!=null) {
                logger.debug("Closing stream before opening new location.");
                this.close();
            }

            final OutputStream outFile = new FileOutputStream( new File(fileLocation));
            final BufferedOutputStream bis = new BufferedOutputStream( outFile );
            ds = new DataOutputStream(bis);

        } catch (final FileNotFoundException e) {
            logger.error("Error: Can't create file!" + fileLocation);
        }

    }

    public void write(final Integer value) {
        try {
            ds.writeInt(value);
        } catch (IOException e) {
            logger.error("Problem writing Integer to output stream : ", e);
        }
    }

    public void close() {
        try {
            logger.debug("Closing stream : FWriteStream");
            ds.flush();
            ds.close();

        } catch (final IOException e) {
            logger.error("Error closing output stream : ", e);
        }
    }

    public void flush() {
        try {
            ds.flush();
        } catch (final IOException e) {
            logger.error("Error flushing output stream : ", e);
        }
    }
}
