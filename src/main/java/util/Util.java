package util;

import com.opencsv.CSVWriter;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static void write(final File outputFile, final List<String[]> records) throws IOException {
        final CSVWriter writer = new CSVWriter(new FileWriter(outputFile.getPath()));
        writer.writeAll(records);
        writer.close();
    }

    public static int getOsBlockSize() throws IOException {
        final Process process = Runtime.getRuntime().exec("diskutil info /");
        final String result = new BufferedReader(new InputStreamReader(process.getInputStream()))
                .lines()
                .filter(line -> line.contains("Device Block Size")).collect(Collectors.toList()).get(0);
        final String bytes = result.split(":")[1].trim().replace("Bytes","").trim();

        return Integer.valueOf(bytes);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getOsBlockSize());
    }
}
