package streams.write;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.FileGenerator;
import streams.interfaces.AbstractWriteStream;
import util.WriteUtil;

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
        } catch (final IOException e) {
            logger.error("Problem writing Integer to output stream.", e);
        }
    }

    public void close() {
        WriteUtil.closeAndFlush(ds);
    }


    public void flush() {
        WriteUtil.flush(ds);
    }

}
