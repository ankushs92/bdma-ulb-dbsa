package streams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.read.FReadStream;
import streams.read.MemMapReadStream;
import streams.read.ReadStream;
import streams.write.MemoryMappedWriteStream;
import streams.write.WriteStream;
import util.Assert;

import java.io.IOException;
import java.util.Random;

public class FileGenerator {

    private static final Logger logger = LoggerFactory.getLogger(FileGenerator.class);
    private static Random r = new Random();

    private static int rand(int min, int max) {
        Assert.isTrue(min >= 0, "min must be greater than equal to 0");
        Assert.isTrue(max > min, "Max must be greater than min");
        final int number = r.nextInt((max - min) + 1) + min;
        return number;
    }


    public static void generateFile(String fileLocation, int size, int max) {

        final WriteStream outStream = new WriteStream();
        outStream.create(fileLocation);

        for (int i = 0; i < size; i++) {
            final Integer idx = rand(0, max);
            outStream.write(idx);
            if (i % 10000 == 0)
                outStream.flush();
            logger.debug("Index file generated : {}", idx);
        }
        outStream.close();

    }


    public static void generateMappedFile(String fileLocation, int size, int max) {

        final MemoryMappedWriteStream outStream = new MemoryMappedWriteStream();
        outStream.create(fileLocation);

        for (int i = 0; i < size; i++) {
            final Integer idx = rand(0, max);
            outStream.write(idx);

            logger.debug("Index file generated : {}", idx);
        }
        outStream.close();

    }

    public static void main(String[] args) throws IOException {

        String fileLocation = "./src/main/resources/inputToy3.data";

        generateMappedFile(fileLocation, ((int) Math.pow(2, 7)), 15);

        MemMapReadStream stReader = new MemMapReadStream(10);
        stReader.open(fileLocation);

        for (int i = 0; i < 130; i++) {
            System.out.println(i + " is " + stReader.readNext());
        }
    }
}

