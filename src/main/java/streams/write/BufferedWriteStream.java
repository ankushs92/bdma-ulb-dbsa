package streams.write;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.FileGenerator;
import streams.interfaces.AbstractWriteStream;
import util.WriteUtil;

import java.io.*;

public class BufferedWriteStream implements AbstractWriteStream {

    private static final Logger logger = LoggerFactory.getLogger(FileGenerator.class);
    private DataOutputStream ds;

    private final int bufferSize;

    public BufferedWriteStream(final int bufferSize) {
        this.bufferSize = bufferSize;
    }


    @Override
    @SuppressWarnings("Duplicates")
    public void create(final String fileLocation) {
        try {
            if(ds!=null) {
                logger.debug("Closing stream before opening new location.");
                this.close();
            }
            final OutputStream outFile = new FileOutputStream( new File(fileLocation));
            final BufferedOutputStream bis = new BufferedOutputStream(outFile, bufferSize); // The only change is here
            ds = new DataOutputStream(bis);

        } catch (final FileNotFoundException e) {
            logger.error("Error: Can't create file!" + fileLocation);
        }
    }

    @Override
    public void write(final Integer value) {
        try {
            ds.writeInt(value);
        } catch (IOException e) {
            logger.error("Problem writing Integer to output stream : ", e);
        }
    }

    @Override
    public void close() {
        WriteUtil.closeAndFlush(ds);
    }
}
