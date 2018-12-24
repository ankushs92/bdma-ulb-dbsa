package streams;

import streams.read.FReadStream;
import streams.read.MemoryMappedReadStream;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        String fileLocation = "src/main/resources/inputToy.data";
        FReadStream fReadStream = new FReadStream();
        fReadStream.open(fileLocation);
        while(!fReadStream.endOfStream()) {
            System.out.println(fReadStream.readNext());
        }
        System.out.println("0--------");
        int bufferSize = 256;
        MemoryMappedReadStream r = new MemoryMappedReadStream(bufferSize);
        r.open(fileLocation);
//        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());

        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());
        System.out.println(r.readNext());




    }
}
