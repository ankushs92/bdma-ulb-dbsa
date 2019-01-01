package algo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.interfaces.AbstractReadStream;
import util.Assert;

import java.io.File;
import java.util.Objects;


public class StreamsPriorityQueue implements Comparable<StreamsPriorityQueue>{

    private static final Logger logger = LoggerFactory.getLogger(StreamsPriorityQueue.class);

    private int intValue;
    private final AbstractReadStream readStream;

    StreamsPriorityQueue(
            final int value,
            final AbstractReadStream readStream
    )
    {
        Assert.notNull(readStream, "readStream cannot be null");
        this.intValue = value;
        this.readStream = readStream;
    }

    public int readNext() {
        intValue = readStream.readNext();
        return intValue;
    }

    void deleteFile() {
        try {
            readStream.close();
            new File(readStream.getFileLocation()).delete();
        } catch (final Exception e) {
            logger.error("", e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final StreamsPriorityQueue that = (StreamsPriorityQueue) o;
        return intValue == that.intValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(intValue);
    }

    public int getIntValue() {
        return intValue;
    }

    public AbstractReadStream getStream() {
        return readStream;
    }

    @Override
    public int compareTo(StreamsPriorityQueue o) {
        return Integer.compare(this.intValue, o.intValue);
    }
}
