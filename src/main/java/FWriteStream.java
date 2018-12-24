import java.io.*;

public class FWriteStream {
    
    DataOutputStream ds;

    public void create(String fileLocation) {
        try {
            if(ds!=null) {
                System.out.println("Closing stream before opening new location.");
                this.close();
            }

            OutputStream outFile = new FileOutputStream( new File(fileLocation));
            BufferedOutputStream bis = new BufferedOutputStream( outFile );
            ds = new DataOutputStream(bis);

        } catch (FileNotFoundException e) {
            System.out.println("Error: Can't create file!" + fileLocation);
        }

    }

    public void write(Integer value) {
        try {
            ds.writeInt(value);
        } catch (IOException e) {
            System.out.println("Problem writing Integer to output stream.");
        }
    }

    public void close() {
        try {

            System.out.println("Closing stream");
            ds.flush();
            ds.close();

        } catch (IOException e) {
            System.out.println("Error closing output stream.");
            e.printStackTrace();
        }
    }

    public void flush()
    {
        try {
            ds.flush();
        } catch (IOException e) {
            System.out.println("Error flushing output stream.");
            e.printStackTrace();
        }
    }
}
