package streams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.interfaces.AbstractWriteStream;
import streams.write.MemoryMappedWriteStream;

import java.io.File;
import java.util.Random;

public class FileGenerator {

    private static final Logger logger = LoggerFactory.getLogger(FileGenerator.class);
    private static Random r = new Random();

    private final File outputFile;
    private final AbstractWriteStream writeStream;
    private final int size;

    public FileGenerator(
            final File outputFile,
            final AbstractWriteStream writeStream,
            final int size
    )
    {
        this.outputFile = outputFile;
        this.writeStream = writeStream;
        this.size = size;
    }

    public void writeToFile() {
        writeStream.create(outputFile.getPath());
        for(int i = 0 ; i < size; i ++) {
            final int value = (int) (Math.random() * Integer.MAX_VALUE);
            writeStream.write(value);
        }
    }


    public static void generateMappedFile(String fileLocation, int size) {

        final MemoryMappedWriteStream outStream = new MemoryMappedWriteStream(10000);
        outStream.create(fileLocation);

        for (int i = 0; i < size; i++) {
            final Integer idx = (int) (Math.random() * Integer.MAX_VALUE);
            outStream.write(idx);
            System.out.println(idx);
            logger.debug("Index file generated : {}", idx);
        }
        outStream.close();

    }

    public static void main(String[] args) {

//        String fileLocation = "./src/main/resources/benchmark/implementation_1_2/64mb_16777216_integers";
//        new File(fileLocation).delete();
//        generateMappedFile(fileLocation, 16777216);
//

        String fileLocation1 = "./src/main/resources/benchmark/implementation_1_2/128mb_33554432_integers";
        new File(fileLocation1).delete();
        generateMappedFile(fileLocation1, 33554432);

    }
}

