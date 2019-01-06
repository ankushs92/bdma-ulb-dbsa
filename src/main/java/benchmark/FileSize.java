package benchmark;

import streams.read.MemMapReadStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public enum FileSize {
    ONE_MB,
    FOUR_MB,
    THIRTY_TWO_MB,
    ONE_TWENTY_EIGHT_MB,
    FIVE_HUNDRED_TWELVE_MB;

    public static void main(String[] args) throws IOException {
        int n = (int) Math.ceil( 25000 / 3);
        System.out.println(n);

        MemMapReadStream r = new MemMapReadStream(1111);
        r.open("/Users/ankushsharma/Desktop/code/dbsa/src/main/resources/sorted/1.data");


        List<Integer> list = new ArrayList<>();
        while(!r.endOfStream()) {
            list.add(r.readNext());
        }


        for(int i : list) {
            System.out.println(i);
        }
    }
}
