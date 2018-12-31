package util;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Util {

    public static void write(final File outputFile, final List<String[]> records) throws IOException {
        final CSVWriter writer = new CSVWriter(new FileWriter(outputFile.getPath()));
        writer.writeAll(records);
        writer.close();
    }

}
