package streams;

import java.io.FileNotFoundException;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        String fileLocation = "/src/main/resources/inputToy.data";
        FReadStream fReadStream = new FReadStream();
        fReadStream.open(fileLocation);
        System.out.println(fReadStream.readNext());
        System.out.println(fReadStream.endOfStream());
        while(!fReadStream.endOfStream()) {
            System.out.println(fReadStream.readNext());
        }

    }
}
