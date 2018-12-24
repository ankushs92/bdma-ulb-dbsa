import java.io.*;

public class StreamWriter {
    
    DataOutputStream ds;

    public void create(String fileLocation) throws FileNotFoundException {
        try {
            if(ds!=null) {
                System.out.println("Closing stream before opening new location.");
                this.close();
            }
            OutputStream outFile = new FileOutputStream( new File(fileLocation));
            ds = new DataOutputStream(outFile);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Can't create file!" + fileLocation);
        } catch (IOException e) {
            System.out.println("Error: Can't write file!" + fileLocation);
        }

    }

    public void write(Integer value) {
        try {
            ds.writeInt(value);
        } catch (IOException e) {
            System.out.println("Problem writing Integer to output stream.");
        }
    }

    public boolean close() {
        try {

            System.out.println("Closing stream");
            ds.flush();
            ds.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error closing output stream.");
            e.printStackTrace();
        }
        return false;
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
