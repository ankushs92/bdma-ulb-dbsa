package streams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.interfaces.AbstractWriteStream;

import java.io.*;

public class WriteStream implements AbstractWriteStream {

    private static final Logger logger = LoggerFactory.getLogger(FileGenerator.class);
    private DataOutputStream ds;

    public void create(final String fileLocation) {
        try {
            if(ds!=null) {
                logger.debug("Closing stream before opening new location.");
                this.close();
            }
            final OutputStream outFile = new FileOutputStream( new File(fileLocation));
            ds = new DataOutputStream(outFile);
        } catch (final IOException e) {
            logger.error("", e);
        }

    }

    public void write(final Integer value) {
        try {
            ds.writeInt(value);
        } catch (IOException e) {
            System.out.println("Problem writing Integer to output stream.");
        }
    }

    public void close() {
        try {
            logger.debug("Closing stream");
            ds.flush();
            ds.close();
        } catch (IOException e) {
            System.out.println("Error closing output stream.");
            e.printStackTrace();
        }
    }

    public void flush()
    {
        try {
            ds.flush();
        } catch (IOException e) {
            System.out.println("Error flushing output stream.");
            e.printStackTrace();
        }
    }
}
