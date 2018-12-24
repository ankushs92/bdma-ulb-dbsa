import java.io.*;

public class FReadStream {

    DataInputStream ds;

    public void open(String fileLocation) throws FileNotFoundException {

        InputStream inputStream = new FileInputStream( new File( fileLocation ) );
        BufferedInputStream bis = new BufferedInputStream( inputStream );
        ds = new DataInputStream( bis );
    }

    public Integer readNext() {
        try {
            return ds.readInt();
        } catch (IOException e) {
            System.out.println("Problem reading Integer from input stream.");
            //e.printStackTrace();
            if(!endOfStream())
                System.out.println("Reached end of stream?");
            return null;
        }
    }

    public boolean endOfStream() {
        try {
            if (ds.available()>=1)
                return true;
        } catch (IOException e) {
            System.out.println("Error checking endOfStream.");
            e.printStackTrace();
        }
        return false;
    }
}
