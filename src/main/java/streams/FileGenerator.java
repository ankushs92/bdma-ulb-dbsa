package streams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.interfaces.AbstractReadStream;
import streams.interfaces.AbstractWriteStream;
import streams.read.BufferedReadStream;
import streams.read.FReadStream;
import streams.read.MemMapReadStream;
import streams.read.ReadStream;
import streams.write.BufferedWriteStream;
import streams.write.FWriteStream;
import streams.write.MemoryMappedWriteStream;
import streams.write.WriteStream;

import java.io.File;
import java.io.IOException;
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


    public static void generateFile(String fileLocation, int numberOfIntegers, int streamType, int bufferSize) {
        AbstractWriteStream outStream;

        if(streamType==1)
            outStream = new WriteStream();
        else if(streamType==2)
            outStream = new FWriteStream();
        else if(streamType==3)
            outStream = new BufferedWriteStream(bufferSize);
        else
            outStream = new MemoryMappedWriteStream(bufferSize);

        outStream.create(fileLocation);

        final long start = System.currentTimeMillis();
        for (int i = 0; i < numberOfIntegers; i++) {
            final Integer idx = (int) (Math.random() * Integer.MAX_VALUE);
            outStream.write(idx);
//            System.out.println(idx);
//            logger.debug("Index file generated : {}", idx);
        }
        final long stop = System.currentTimeMillis();

        final long timeTaken = stop - start;
        logger.info("Type: {} Write: {} ms ", streamType, timeTaken);
        outStream.close();

    }


    public static void readFile(String fileLocation, int streamType, int bufferSize) {
        AbstractReadStream inStream;
        if(streamType==1)
            inStream = new ReadStream();
        else if(streamType==2)
            inStream = new FReadStream();
        else if(streamType==3)
            inStream = new BufferedReadStream(bufferSize);
        else
            inStream = new MemMapReadStream(bufferSize);
        //ReadStream readSt = new ReadStream();
        //BufferedReadStream readSt = new BufferedReadStream(128);
        //FReadStream readSt = new FReadStream();
        //MemMapReadStream readSt = new MemMapReadStream(1002);
        try {
            inStream.open(fileLocation);
            final long start = System.currentTimeMillis();
            while(!inStream.endOfStream()){
                inStream.readNext();
            }
            final long stop = System.currentTimeMillis();

            final long timeTaken = stop - start;
            logger.info("Type: {} Read: {} ms ", streamType, timeTaken);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {

//        String fileLocation = "./src/main/resources/benchmark/implementation_1_2/64mb_16777216_integers";
//        new File(fileLocation).delete();
//        generateMappedFile(fileLocation, 16777216);
//        16:15:59.760 [main] INFO  streams.FileGenerator - Time for writing is 314 ms
//        16:16:01.469 [main] INFO  streams.FileGenerator - Time reading is 1682 ms

        String fileLocation1 = "./src/main/resources/benchmark/4mb_integers.data";
        int numberOfIntegers = 1000000;
        int bufferSize = 81920;
//        new File(fileLocation1).delete();
//        generateFile(fileLocation1, numberOfIntegers, 1, 0);
//        readFile(fileLocation1, 1, 0);
//        System.out.println("______________");

//        new File(fileLocation1).delete();
//        generateFile(fileLocation1, numberOfIntegers, 2, bufferSize);
//        readFile(fileLocation1, 2, bufferSize);
//        System.out.println("______________");

        new File(fileLocation1).delete();
        generateFile(fileLocation1, numberOfIntegers, 4, bufferSize);
        readFile(fileLocation1, 4, bufferSize);
        System.out.println("______________");

        //
//        new File(fileLocation1).delete();
//        generateFile(fileLocation1, numberOfIntegers, 4, bufferSize);
//        readFile(fileLocation1, 4, bufferSize);
//        System.out.println("______________");




        //Don't delete this, is for reading isolated files

//        ReadStream readSt = new ReadStream();
//        //BufferedReadStream readSt = new BufferedReadStream(128);
//        //FReadStream readSt = new FReadStream();
//        //MemMapReadStream readSt = new MemMapReadStream(1002);
//        try {
//            readSt.open(fileLocation1);
//            final long start = System.currentTimeMillis();
//            while(!readSt.endOfStream()){
//                readSt.readNext();
//            }
//            final long stop = System.currentTimeMillis();
//
//            final long timeTaken = stop - start;
//            logger.info("Time reading is {} ms ", timeTaken);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}

