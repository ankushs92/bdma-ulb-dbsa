package streams.interfaces;

public interface AbstractWriteStream extends AutoCloseable {

    void create(String fileLocation);

    void write(Integer value);

    void close();
}
