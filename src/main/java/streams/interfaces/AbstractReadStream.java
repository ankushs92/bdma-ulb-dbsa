package streams.interfaces;

import java.io.FileNotFoundException;

public interface AbstractReadStream {

    void open(String fileLocation) throws FileNotFoundException;

    Integer readNext();

    boolean endOfStream();
}
