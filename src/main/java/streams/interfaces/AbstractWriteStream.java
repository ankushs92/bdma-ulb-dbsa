package streams.interfaces;

import java.io.IOException;

public interface AbstractWriteStream extends AutoCloseable {

    void create(String fileLocation);

    void write(Integer value);

    void close() ;
}
