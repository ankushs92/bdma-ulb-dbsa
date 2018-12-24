import java.io.*;
import java.util.Random;

public class FileGenerator {

    private static Random r = new Random();




    private static int rand(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min and min > 0");
        }
        int number = r.nextInt((max - min) + 1) + min;
        return number;
    }


    public static void FileGenerator(String fileLocation, int size, int max) {

        WriteStream outStream = new WriteStream();
        outStream.create(fileLocation);

        //FileOutputStream fOut = new FileOutputStream(fileLocation);
        for (int i = 0; i < size; i++) {
            Integer idx = rand(0, max);
            outStream.write(idx);
            if (i % 10000 == 0)
                outStream.flush();
            System.out.println(i + " is " + idx);
        }
        outStream.close();

    }


    public static void main(String[] args) throws IOException {

        String fileLocation = "src/main/resources/inputToy.data";

        FileGenerator(fileLocation, ((int) Math.pow(2, 5)), 15);

        FReadStream stReader = new FReadStream();
        stReader.open(fileLocation);

        for (int i = 0; i < 32; i++) {

            System.out.println(i + " is " + stReader.readNext());

        }
    }
}

