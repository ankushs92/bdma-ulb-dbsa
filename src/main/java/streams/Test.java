package streams;

import streams.read.FReadStream;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        String fileLocation = "./src/main/resources/sorted/1_6";
        FReadStream fReadStream = new FReadStream();
        fReadStream.open(fileLocation);

        while(!fReadStream.endOfStream()) {
            System.out.println(fReadStream.readNext());
        }
        System.out.println("0--------");
    }
}
