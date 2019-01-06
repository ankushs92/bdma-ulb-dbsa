package util;

import com.opencsv.CSVWriter;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static void write(final File outputFile, List<String[]> records) throws IOException {
        final boolean appendToEnd = true;
        final CSVWriter writer = new CSVWriter(new FileWriter(outputFile.getPath(), appendToEnd));
        writer.writeAll(records);
        writer.close();
    }

    public static int getOsBlockSize() throws IOException {
        final Process process = Runtime.getRuntime().exec("diskutil info /");
        final String result = new BufferedReader(new InputStreamReader(process.getInputStream()))
                                    .lines()
                                    .filter(line -> line.contains("Allocation Block Size"))
                                    .collect(Collectors.toList())
                                    .get(0);
        final String bytes = result.split(":")[1].trim().replace("Bytes","").trim();
        return Integer.valueOf(bytes);
    }

}
