package streams.interfaces;

public interface AbstractWriteStream {

    void create(String fileLocation);

    void write(Integer value);

    void close();
}
