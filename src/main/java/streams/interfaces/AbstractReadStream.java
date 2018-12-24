package streams.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface AbstractReadStream {

    void open(String fileLocation) throws IOException;

    Integer readNext();

    boolean endOfStream();
}
