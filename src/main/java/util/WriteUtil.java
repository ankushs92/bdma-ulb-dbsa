package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.write.WriteStream;

import java.io.DataOutputStream;
import java.io.IOException;

public class WriteUtil {

    private static final Logger logger = LoggerFactory.getLogger(WriteUtil.class);

    public static void flush(final DataOutputStream ds) {
        try {
            ds.flush();
        } catch (final IOException e) {
            logger.error("Error flushing output stream : ", e);
        }
    }


    public static void closeAndFlush(final DataOutputStream ds) {
        try {
            flush(ds);
            ds.close();
        } catch (final IOException e) {
            logger.error("Error closing output stream.", e);
        }
    }

}
