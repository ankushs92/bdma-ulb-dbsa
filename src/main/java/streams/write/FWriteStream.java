package streams.write;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.FileGenerator;
import streams.interfaces.AbstractWriteStream;
import util.WriteUtil;

import java.io.*;

public class FWriteStream implements AbstractWriteStream {

    private static final Logger logger = LoggerFactory.getLogger(FileGenerator.class);
    private DataOutputStream ds;
    private OutputStream outputStream;
    private BufferedOutputStream bis;

    @Override
    public void create(final String fileLocation) {
        try {
            if(ds != null) {
                logger.debug("Closing stream before opening new location.");
                close();
            }
            outputStream = new FileOutputStream( new File(fileLocation));
            bis = new BufferedOutputStream(outputStream);
            ds = new DataOutputStream(bis);

        } catch (final FileNotFoundException e) {
            logger.error("Error: Can't create file!" + fileLocation);
        }

    }

    @Override
    public void write(final Integer value) {
        try {
            ds.writeInt(value);
        }
        catch (final IOException e) {
            logger.error("Problem writing Integer to output stream : ", e);
        }
    }

    @Override
    public void close() {
        try {
            WriteUtil.closeAndFlush(ds);
            outputStream.close();
            bis.close();
        } catch (final IOException e) {
            logger.error("", e);
        }
    }

}
