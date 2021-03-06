package benchmark;

import org.apache.commons.io.FileUtils;
import util.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.valueOf;

public class StreamsBenchmarkRunner {

    private static final File WRITE_OUTPUT_CSV = new File("./src/main/resources/benchmark/csv/writeBenchmark_NEW.csv/");
    private static final File OUTPUT_CSV = new File("./src/main/resources/benchmark/csv/streamsBenchmark_NEW.csv/");
    private static final int MAX_K = 30;
    private static final List<Integer> Ks = Arrays.asList(1, 10, 20, 30);
    private static final List<Integer> BUFFER_SIZES = Arrays.asList(4096, 8192, 16384, 1048576, 33554432);
    private static File FILE_DIRECTORY = new File("/Users/ankushsharma/Downloads/test");
    private static final File ONE_MB = new File("/Users/ankushsharma/Desktop/code/dbsa/src/main/resources/benchmark/implementation_1_2/1Mb/1mb_262144_integers_1");
    private static final File FOUR_MB = new File("./src/main/resources/benchmark/4mb_integers.data");
    private static final File THIRTY_TWO_MB = new File("./src/main/resources/benchmark/32mb_32000000_integers.data");
    private static final File ONE_TWENTY_EIGHT_MB = new File("./src/main/resources/benchmark/128mb_32000000_integers.data");


    private static final List<File> ONE_MB_FILES = new ArrayList<>();
    private static final List<File> FOUR_MB_FILES = new ArrayList<>();
    private static final List<File> THIRTY_TWO_MB_FILES = new ArrayList<>();
    private static final List<File> ONE_TWENTY_EIGHT_MB_FILES = new ArrayList<>();


    private static final String OUTPUT_FILE_DIRECTORY_PATH = "./src/main/resources/write/";
    private static final File OUTPUT_FILE_DIRECTORY = new File(OUTPUT_FILE_DIRECTORY_PATH);

    static {
        for(int i = 0 ; i < MAX_K; i ++) {
            ONE_MB_FILES.add(ONE_MB);
            FOUR_MB_FILES.add(FOUR_MB);
            THIRTY_TWO_MB_FILES.add(THIRTY_TWO_MB);
            ONE_TWENTY_EIGHT_MB_FILES.add(ONE_TWENTY_EIGHT_MB);
        }
    }


    public static void main(final String[] args) throws IOException {
        int index = 0;
//        final String[] headers = new String[]{"index", "t", "n", "numInt", "k", "bufferSize", "t1", "t2", "t3"};
////        BufferedWriteStream b = new BufferedWriteStream(1);
////        b.create("/Users/ankushsharma/Desktop/code/dbsa/src/main/resources/test");
//
//        FWriteStream fWriteStream = new FWriteStream();
//        fWriteStream.create("/Users/ankushsharma/Desktop/code/dbsa/src/main/resources/test");
//
//        for(int i = 0 ;i < 2045; i ++) {
//            fWriteStream.write(i);
//        }
//        fWriteStream.close();
//        Util.write(OUTPUT_CSV, singletonList(headers));
        final int fileSize1Mb = 1000000;
        final int fileSize4mb = 4000000;
        final int fileSize32Mb = 32000000;
        final int fileSize128Mb = 128000000;

//        saveCsvForRead1stImplementation(ONE_MB, index, fileSize1Mb, FileSize.ONE_MB);
//        saveCsvForRead1stImplementation(FOUR_MB, index, fileSize4mb, FileSize.FOUR_MB);
//        saveCsvForRead1stImplementation(THIRTY_TWO_MB, index, fileSize4mb, FileSize.THIRTY_TWO_MB);


//        saveCsvForRead2ndImplementation(ONE_MB, index, fileSize1Mb, FileSize.ONE_MB);
//        saveCsvForRead2ndImplementation(FOUR_MB, index, fileSize4mb, FileSize.FOUR_MB);
//        saveCsvForRead2ndImplementation(THIRTY_TWO_MB, index, fileSize32Mb, FileSize.THIRTY_TWO_MB);
//        saveCsvForRead2ndImplementation(ONE_TWENTY_EIGHT_MB, index, fileSize128Mb, FileSize.ONE_TWENTY_EIGHT_MB);
//
//        saveCsvForRead3rdImplementation(ONE_MB, index, fileSize1Mb, FileSize.ONE_MB);
//        saveCsvForRead3rdImplementation(FOUR_MB, index, fileSize4mb, FileSize.FOUR_MB);
//        saveCsvForRead3rdImplementation(THIRTY_TWO_MB, index, fileSize32Mb, FileSize.THIRTY_TWO_MB);
//        saveCsvForRead3rdImplementation(ONE_TWENTY_EIGHT_MB, index, fileSize128Mb, FileSize.ONE_TWENTY_EIGHT_MB);
//
//        saveCsvForRead4thImplementation(ONE_MB, index, fileSize1Mb, FileSize.ONE_MB);
//        saveCsvForRead4thImplementation(FOUR_MB, index, fileSize4mb, FileSize.FOUR_MB);
//        saveCsvForRead4thImplementation(THIRTY_TWO_MB, index, fileSize32Mb, FileSize.THIRTY_TWO_MB);
//        saveCsvForRead4thImplementation(ONE_TWENTY_EIGHT_MB, index, fileSize128Mb, FileSize.ONE_TWENTY_EIGHT_MB);
//        saveCsvForRead4thImplementation(FI, index, fileSize128Mb, FileSize.ONE_TWENTY_EIGHT_MB);

        saveCsvForWrite1stImplementation(index, fileSize1Mb, FileSize.ONE_MB);
        saveCsvForWrite1stImplementation(index, fileSize4mb, FileSize.FOUR_MB);
        saveCsvForWrite1stImplementation(index, fileSize32Mb, FileSize.THIRTY_TWO_MB);
//
//        saveCsvForWrite2ndImplementation(index, fileSize1Mb, FileSize.ONE_MB);
//        saveCsvForWrite2ndImplementation(index, fileSize4mb, FileSize.FOUR_MB);
//        saveCsvForWrite2ndImplementation(index, fileSize32Mb, FileSize.THIRTY_TWO_MB);
//        saveCsvForWrite2ndImplementation(index, fileSize128Mb, FileSize.ONE_TWENTY_EIGHT_MB);
//

//        saveCsvForWrite4thImplementation(index, fileSize1Mb, FileSize.ONE_MB);
//        saveCsvForWrite4thImplementation(index, fileSize4mb, FileSize.FOUR_MB);
//        saveCsvForWrite4thImplementation(index, fileSize32Mb, FileSize.THIRTY_TWO_MB);
//        saveCsvForWrite4thImplementation(index, fileSize128Mb, FileSize.ONE_TWENTY_EIGHT_MB);
////
//        saveCsvForWrite3rdImplementation(index, fileSize1Mb, FileSize.ONE_MB);
//        saveCsvForWrite3rdImplementation(index, fileSize4mb, FileSize.FOUR_MB);
//        saveCsvForWrite3rdImplementation(index, fileSize32Mb, FileSize.THIRTY_TWO_MB);
//        saveCsvForWrite3rdImplementation(index, fileSize128Mb, FileSize.ONE_TWENTY_EIGHT_MB);
//
    }

    @SuppressWarnings({"Duplicates"})
    private static void saveCsvForRead1stImplementation(
            final File file,
            int index,
            final int fileSize,
            final FileSize fileSizeType
    )
    throws IOException
    {
        final String fileSizeStr = valueOf(fileSize);
        final String fileSizeTypeStr = getReadableSizeString(fileSizeType);
        final List<String[]> records = new ArrayList<>();
        for(final int k : Ks) {
            String kString = valueOf(k);
            String[] record = new String[]{valueOf(index), "1", fileSizeTypeStr, fileSizeStr, kString, "1", null, null, null};
            for(int i = 0; i < 3; i++) {
                final List<File> files = createKFiles(file, k);
                index++;
                final StreamsBenchmark firstImplReadBenchmark = new StreamsBenchmark(files, null, 0, k, 0);
                final long timeTaken = firstImplReadBenchmark.getTimeTakenToExecuteKReadStreams();
                record[0] = valueOf(index);
                if(i == 0) {
                    record[6] = valueOf(timeTaken);
                }
                if(i == 1) {
                    record[7] = valueOf(timeTaken);
                }
                if(i == 2) {
                    record[8] = valueOf(timeTaken);
                }
                deleteTemporaryFiles(files);
            }
            records.add(record);
        }
        Util.write(OUTPUT_CSV, records);
    }


    @SuppressWarnings({"Duplicates"})
    private static void saveCsvForRead2ndImplementation(
            final File file,
            int index,
            final int fileSize,
            final FileSize fileSizeType
    )
            throws IOException
    {
        final int bufferSize = 8192;
        final String fileSizeStr = valueOf(fileSize);
        final List<String[]> records = new ArrayList<>();
        final String fileSizeTypeStr = getReadableSizeString(fileSizeType);
        for(final int k : Ks) {
            String kString = valueOf(k);
            String[] record = new String[]{valueOf(index), "2", fileSizeTypeStr, fileSizeStr, kString, "8192", null, null, null};
            for(int i = 0; i < 3; i++) {
                final List<File> files = createKFiles(file, k);
                index++;
                final StreamsBenchmark firstImplReadBenchmark = new StreamsBenchmark(files, null, 0, k, bufferSize);
                final long timeTaken = firstImplReadBenchmark.getTimeTakenToExecuteKFReadStreams();
                record[0] = valueOf(index);
                if(i == 0) {
                    record[6] = valueOf(timeTaken);
                }
                if(i == 1) {
                    record[7] = valueOf(timeTaken);
                }
                if(i == 2) {
                    record[8] = valueOf(timeTaken);
                }
                deleteTemporaryFiles(files);
            }
            records.add(record);
        }
        Util.write(OUTPUT_CSV, records);
    }




    @SuppressWarnings({"Duplicates"})
    private static void saveCsvForRead3rdImplementation(
            final File file,
            int index,
            final int fileSize,
            final FileSize fileSizeType
    )
            throws IOException
    {
        final String fileSizeStr = valueOf(fileSize);
        final List<String[]> records = new ArrayList<>();
        final String fileSizeTypeStr = getReadableSizeString(fileSizeType);
        for(final int k : Ks) {
            String kString = valueOf(k);
            for(final int bufferSize : BUFFER_SIZES) {
                String[] record = new String[]{valueOf(index), "3", fileSizeTypeStr, fileSizeStr, kString, valueOf(bufferSize), null, null, null};
                for(int i = 0; i < 1; i++) {
                    final List<File> files = createKFiles(file, k);
                    index++;
                    final StreamsBenchmark firstImplReadBenchmark = new StreamsBenchmark(files, null, 0, k, bufferSize);
                    final long timeTaken = firstImplReadBenchmark.getTimeTakenToExecuteKBufferedReadStreams();
                    record[0] = valueOf(index);
                    if(i == 0) {
                        record[6] = valueOf(timeTaken);
                    }
                    if(i == 1) {
                        record[7] = valueOf(timeTaken);
                    }
                    if(i == 2) {
                        record[8] = valueOf(timeTaken);
                    }
                    deleteTemporaryFiles(files);
                }
                records.add(record);
            }
        }
        Util.write(OUTPUT_CSV, records);
    }


    @SuppressWarnings({"Duplicates"})
    private static void saveCsvForRead4thImplementation(
            final File file,
            int index,
            final int fileSize,
            final FileSize fileSizeType
    )
            throws IOException
    {
        final String fileSizeStr = valueOf(fileSize);
        final List<String[]> records = new ArrayList<>();
        final String fileSizeTypeStr = getReadableSizeString(fileSizeType);
        for(final int k : Ks.subList(0, 4)) {
            String kString = valueOf(k);
            for(final int bufferSize : BUFFER_SIZES) {
                String[] record = new String[]{valueOf(index), "4", fileSizeTypeStr, fileSizeStr, kString, valueOf(bufferSize), null, null, null};
                for(int i = 0; i < 1; i++) {
                    final List<File> files = createKFiles(file, k);
                    index++;
                    final StreamsBenchmark firstImplReadBenchmark = new StreamsBenchmark(files, null, 0, k, bufferSize);
                    final long timeTaken = firstImplReadBenchmark.getTimeTakenToExecuteKMemMappedByteReadStreams();
                    record[0] = valueOf(index);
                    if(i == 0) {
                        record[6] = valueOf(timeTaken);
                    }
                    if(i == 1) {
                        record[7] = valueOf(timeTaken);
                    }
                    if(i == 2) {
                        record[8] = valueOf(timeTaken);
                    }
                    deleteTemporaryFiles(files);
                }
                records.add(record);
            }
        }
        Util.write(OUTPUT_CSV, records);
    }


    @SuppressWarnings({"Duplicates"})
    private static void saveCsvForWrite1stImplementation(
            int index,
            final int fileSize,
            final FileSize fileSizeType
    )
    throws IOException
    {
        final String fileSizeTypeStr = getReadableSizeString(fileSizeType);
        final List<String[]> records = new ArrayList<>();
        for(final int k : Ks) {
            String kString = valueOf(k);
            final List<File> outputFiles = createKEmptyFiles(k);
            index++;
            final StreamsBenchmark firstImplWriteBenchmark = new StreamsBenchmark(null, outputFiles, fileSize, k, 0);
            final long timeTaken = firstImplWriteBenchmark.getTimeTakenToExecuteKWriteStreams();
            String[] record = new String[]{valueOf(index), "wStreamA", fileSizeTypeStr, kString, "1", String.valueOf(timeTaken)};
            records.add(record);
            deleteTemporaryFiles(outputFiles);
        }
        Util.write(WRITE_OUTPUT_CSV, records);

    }

    @SuppressWarnings({"Duplicates"})
    private static void saveCsvForWrite2ndImplementation(
            int index,
            final int fileSize,
            final FileSize fileSizeType
    )
            throws IOException
    {
        final String fileSizeTypeStr = getReadableSizeString(fileSizeType);
        final List<String[]> records = new ArrayList<>();
        for(final int k : Ks) {
            String kString = valueOf(k);
            final List<File> outputFiles = createKEmptyFiles(k);
            index++;
            final StreamsBenchmark firstImplWriteBenchmark = new StreamsBenchmark(null, outputFiles, fileSize, k, 8192);
            final long timeTaken = firstImplWriteBenchmark.getTimeTakenToExecuteKFWriteStreams();
            String[] record = new String[]{valueOf(index), "wStreamB", fileSizeTypeStr, kString, "8192", String.valueOf(timeTaken)};
            records.add(record);
            deleteTemporaryFiles(outputFiles);
        }
        Util.write(WRITE_OUTPUT_CSV, records);
    }

    @SuppressWarnings({"Duplicates"})
    private static void saveCsvForWrite3rdImplementation(
            int index,
            final int fileSize,
            final FileSize fileSizeType
    )
    throws IOException
    {
        final String fileSizeTypeStr = getReadableSizeString(fileSizeType);
        final List<String[]> records = new ArrayList<>();
        for(final int k : Ks) {
            String kString = valueOf(k);
            index++;
            for(final int bufferSize : BUFFER_SIZES) {
                final List<File> outputFiles = createKEmptyFiles(k);
                final StreamsBenchmark firstImplWriteBenchmark = new StreamsBenchmark(null, outputFiles, fileSize, k, bufferSize);
                final long timeTaken = firstImplWriteBenchmark.getTimeTakenToExecuteKBufferedWriteStreams();
                String[] record = new String[]{valueOf(index), "wStreamC", fileSizeTypeStr, kString, valueOf(bufferSize), String.valueOf(timeTaken)};
                records.add(record);
                deleteTemporaryFiles(outputFiles);
            }
        }
        Util.write(WRITE_OUTPUT_CSV, records);
    }

    @SuppressWarnings({"Duplicates"})
    private static void saveCsvForWrite4thImplementation(
            int index,
            final int fileSize,
            final FileSize fileSizeType
    )
    throws IOException
    {
        final String fileSizeTypeStr = getReadableSizeString(fileSizeType);
        final List<String[]> records = new ArrayList<>();
        for(final int k : Ks) {
            String kString = valueOf(k);
            index++;
            for(final int bufferSize : BUFFER_SIZES) {
                final List<File> outputFiles = createKEmptyFiles(k);
                final StreamsBenchmark firstImplWriteBenchmark = new StreamsBenchmark(null, outputFiles, fileSize, k, bufferSize);
                final long timeTaken = firstImplWriteBenchmark.getTimeTakenToExecuteKMemMappedByteWriteStreams();
                String[] record = new String[]{valueOf(index), "wStreamD", fileSizeTypeStr, kString, valueOf(bufferSize), String.valueOf(timeTaken)};
                records.add(record);
                deleteTemporaryFiles(outputFiles);
            }
        }
        Util.write(WRITE_OUTPUT_CSV, records);
    }

    private static String getReadableSizeString(final FileSize fileSizeType) {
        String result = "";
        switch (fileSizeType) {
            case ONE_MB : result = "1MB"; break;
            case FOUR_MB :  result = "4MB"; break;
            case THIRTY_TWO_MB : result = "32MB"; break;
            case ONE_TWENTY_EIGHT_MB : result = "128MB"; break;
            case FIVE_HUNDRED_TWELVE_MB : result = "512MB"; break;
        }
        return result;
    }

    private static List<File> createKEmptyFiles(final int k) throws IOException {
        final List<File> files = new ArrayList<>();
        for(int i = 0 ; i < k ; i++) {
            final File copy = new File(FILE_DIRECTORY.getPath() + "_" + valueOf(i));
            if(!copy.exists()) {
                copy.createNewFile();
            }
            files.add(copy);
        }
        return files;
    }
    private static List<File> createKFiles(final File file, final int k) throws IOException {
        final List<File> files = new ArrayList<>();
        for(int i = 0 ; i < k ; i++) {
            final File copy = new File(FILE_DIRECTORY.getPath() + "_" + valueOf(i));
            if(!copy.exists()) {
                copy.createNewFile();
            }
            FileUtils.copyFile(file, copy);
            files.add(copy);
        }
        return files;
    }

    private static void deleteTemporaryFiles(final List<File> files) {
        files.forEach(File :: delete);
    }


}
