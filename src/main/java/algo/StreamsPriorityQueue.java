package algo;

import streams.interfaces.AbstractReadStream;

import java.io.File;
import java.util.Objects;


public class StreamsPriorityQueue implements Comparable<StreamsPriorityQueue>{

    private int intValue;
    private final AbstractReadStream readStream;

    StreamsPriorityQueue(
            final int value, AbstractReadStream readStream
    )
    {
        this.intValue = value;
        this.readStream = readStream;
    }

    public int readNext()
    {
        this.intValue = this.readStream.readNext();
        return this.intValue;
    }

    public int deleteFile()
    {
        try {
            System.out.println("Delete file " + this.readStream.getFileLocation());
            this.readStream.close();
            new File(this.readStream.getFileLocation()).delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StreamsPriorityQueue that = (StreamsPriorityQueue) o;
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
