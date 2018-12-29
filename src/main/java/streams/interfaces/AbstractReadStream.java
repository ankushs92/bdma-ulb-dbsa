package streams.interfaces;

import java.io.IOException;

public interface AbstractReadStream extends AutoCloseable {

    void open(String fileLocation) throws IOException;

    Integer readNext();

    boolean endOfStream();

    long getFileSize() throws Exception;

}
