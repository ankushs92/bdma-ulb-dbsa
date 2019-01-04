package benchmark;

public class StreamsBenchmarkRunner1 {

//    private static final File OUTPUT_CSV = new File("./src/main/resources/benchmark/csv/streamsBenchmark.csv/");
//    static {
//        OUTPUT_CSV.delete();
//        try {
//            OUTPUT_CSV.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    // N = 1mb  , 32mb 128mb 512mb
//    // K = 1, 10, 20, 30
//    // M =  4096 Bytes (size of the block), 1048576, 32*1048576, 128*1048576, 536870912,
//    public static void main(String[] args) throws IOException {
//        List<String[]> csv = new ArrayList<>();
////        File input1Mb_1 = new File("./src/main/resources/benchmark/implementation_1_2/1Mb/1mb_262144_integers_1");
////        int fileSize1Mb = 1024 * 1024 / 4;
////        String fileSize1MbStr = valueOf(fileSize1Mb);
////        int index = 0;
//////        t,n,numInt,k,t1,t2,t3
////        String[] headers = new String[]{"index", "t", "n", "numInt", "k", "bufferSize", "t1", "t2", "t3"};
////        csv.add(headers);
////        Util.write(OUTPUT_CSV, csv);
////        int k = 1;
////        String kString = valueOf(k);
////        List<File> oneStream1mbFiles = getK1MbFiles( input1Mb_1);
////        index++;
////        String[] record = new String[]{valueOf(index), "1","1MB", fileSize1MbStr, kString, "1", null, null, null};
////        appendToCsvFor1mbFiles(record, oneStream1mbFiles, k);
////        csv.add(record);
////        Util.write(OUTPUT_CSV,csv);
////        k = 10;
////        kString = valueOf(k);
////        index++;
////        record = new String[]{valueOf(index), "1","1MB", fileSize1MbStr, kString, "1", null, null, null};
////        appendToCsvFor1mbFiles(record, oneStream1mbFiles, k);
////        csv.add(record);
////        Util.write(OUTPUT_CSV,csv);
////        k = 20;
////        kString = valueOf(k);
////        index++;
////        record = new String[]{valueOf(index), "1","1MB", fileSize1MbStr, kString, "1", null, null, null};
////        appendToCsvFor1mbFiles(record, oneStream1mbFiles, k);
////        csv.add(record);
////        Util.write(OUTPUT_CSV,csv);
////
////        k = 30;
////        kString = valueOf(k);
////        index++;
////        record = new String[]{valueOf(index),"1","1MB", fileSize1MbStr, kString, "1", null, null, null};
////        appendToCsvFor1mbFiles(record, oneStream1mbFiles, k);
////        csv.add(record);
////        Util.write(OUTPUT_CSV,csv);
////
////        index++;
////        String[] record2 = new String[]{valueOf(index), "2","1MB", fileSize1MbStr, kString, "8192", null, null, null};
////        appendCsv2ndImpl(record2, oneStream1mbFiles, k);
////        csv.add(record2);
////        Util.write(OUTPUT_CSV, csv);
////        k = 10;
////        kString = valueOf(k);
////        index++;
////        record2 = new String[]{valueOf(index), "1","1MB", fileSize1MbStr, kString, "1", null, null, null};
////        appendCsv2ndImpl(record2, oneStream1mbFiles, k);
////        csv.add(record2);
////        Util.write(OUTPUT_CSV, csv);
////        k = 20;
////        kString = valueOf(k);
////        index++;
////        record2 = new String[]{valueOf(index), "1","1MB", fileSize1MbStr, kString, "1", null, null, null};
////        appendCsv2ndImpl(record2, oneStream1mbFiles, k);
////        csv.add(record2);
////        Util.write(OUTPUT_CSV, csv);
////
////        k = 30;
////        kString = valueOf(k);
////        index++;
////        record2 = new String[]{valueOf(index), "1","1MB", fileSize1MbStr, kString, "1", null, null, null};
////        appendCsv2ndImpl(record2, oneStream1mbFiles, k);
////        csv.add(record2);
////        Util.write(OUTPUT_CSV, csv);
////
////        k = 1;
////        int bufferSize = 4096;
////        String bufferSizeStr = String.valueOf(bufferSize);
////        index++;
////        String[] record3 = new String[]{valueOf(index), "3","1MB", fileSize1MbStr, kString, "4096", null, null, null};
////        appendCsv3rdImpl(record3, oneStream1mbFiles, k, bufferSize);
////        csv.add(record3);
////        Util.write(OUTPUT_CSV, csv);
////        k = 10;
////        kString = valueOf(k);
////        index++;
////        record3 = new String[]{valueOf(index), "3","1MB", fileSize1MbStr, kString, "4096", null, null, null};
////        appendCsv3rdImpl(record3, oneStream1mbFiles, k, bufferSize);
////        csv.add(record3);
////        Util.write(OUTPUT_CSV, csv);
////        k = 20;
////        kString = valueOf(k);
////        index++;
////        record3 = new String[]{valueOf(index), "3","1MB", fileSize1MbStr, kString, "4096", null, null, null};
////        appendCsv3rdImpl(record3, oneStream1mbFiles, k, bufferSize);
////        csv.add(record3);
////        Util.write(OUTPUT_CSV, csv);
////        k = 30;
////        kString = valueOf(k);
////        index++;
////        record3 = new String[]{valueOf(index), "3","1MB", fileSize1MbStr, kString, "4096", null, null, null};
////        appendCsv3rdImpl(record3, oneStream1mbFiles, k, bufferSize);
////        csv.add(record3);
////        Util.write(OUTPUT_CSV, csv);
////
////
////        k = 1;
////        kString = valueOf(k);
////        bufferSize = 1048576;
////        index++;
////        String[] record4 = new String[]{valueOf(index), "3","1MB", fileSize1MbStr, kString, "1048576", null, null, null};
////        appendCsv3rdImpl(record4, oneStream1mbFiles, k, bufferSize);
////        csv.add(record4);
////        Util.write(OUTPUT_CSV, csv);
////
////        k = 10;
////        kString = valueOf(k);
////        index++;
////        record4 = new String[]{valueOf(index), "3","1MB", fileSize1MbStr, kString, "1048576", null, null, null};
////        appendCsv3rdImpl(record4, oneStream1mbFiles, k, bufferSize);
////        csv.add(record4);
////        Util.write(OUTPUT_CSV, csv);
////
////        k = 20;
////        kString = valueOf(k);
////        index++;
////         record4 = new String[]{valueOf(index), "3","1MB", fileSize1MbStr, kString, "1048576", null, null, null};
////        appendCsv3rdImpl(record4, oneStream1mbFiles, k, bufferSize);
////        csv.add(record4);
////        Util.write(OUTPUT_CSV, csv);
////
////        k = 30;
////        kString = valueOf(k);
////        index++;
////         record4 = new String[]{valueOf(index), "3","1MB", fileSize1MbStr, kString, "1048576", null, null, null};
////        appendCsv3rdImpl(record4, oneStream1mbFiles, k, bufferSize);
////        csv.add(record4);
////        Util.write(OUTPUT_CSV, csv);
////
////
////
////        k = 1;
////        kString = valueOf(k);
////        bufferSize = 32*1048576;
////        index++;
////        String[] record5 = new String[]{valueOf(index), "3","1MB", fileSize1MbStr, kString, "32*1048576", null, null, null};
////        appendCsv3rdImpl(record5, oneStream1mbFiles, k, bufferSize);
////        csv.add(record5);
////        Util.write(OUTPUT_CSV, csv);
////
////
////        bufferSize = 128*1048576;
////        index++;
////        String[] record6 = new String[]{valueOf(index), "3","1MB", fileSize1MbStr, kString, "128*1048576", null, null, null};
////        appendCsv3rdImpl(record6, oneStream1mbFiles, k, bufferSize);
////        csv.add(record6);
////        Util.write(OUTPUT_CSV, csv);
////
////        bufferSize = 512 * 1048576;
////        index++;
////        String[] record7 = new String[]{valueOf(index), "3","1MB", fileSize1MbStr, kString, "512*1048576", null, null, null};
////        appendCsv3rdImpl(record7, oneStream1mbFiles, k, bufferSize);
////        csv.add(record7);
////        Util.write(OUTPUT_CSV, csv);
////
////
////        // 4 MB
////
////        File fourMb = new File("./src/main/resources/benchmark/4mb_1048576_integers.data");
////        int fileSize4Mb = 1024 * 1024 * 4;
////        String fileSize4MbStr = valueOf(fileSize4Mb);
////        k = 1;
////        kString = valueOf(k);
////        List<File> fourMbFiles = getK1MbFiles(fourMb);
////        index++;
////        record = new String[]{valueOf(index), "1","4MB", fileSize4MbStr, kString, "1", null, null, null};
////        appendToCsvFor1mbFiles(record, fourMbFiles, k);
////        csv.add(record);
////        Util.write(OUTPUT_CSV,csv);
////        k = 10;
////        kString = valueOf(k);
////        index++;
////        record = new String[]{valueOf(index), "1","4MB", fileSize4MbStr, kString, "1", null, null, null};
////        appendToCsvFor1mbFiles(record, fourMbFiles, k);
////        csv.add(record);
////        Util.write(OUTPUT_CSV,csv);
////        k = 20;
////        kString = valueOf(k);
////        index++;
////        record = new String[]{valueOf(index), "1","4MB", fileSize4MbStr, kString, "1", null, null, null};
////        appendToCsvFor1mbFiles(record, fourMbFiles, k);
////        csv.add(record);
////        Util.write(OUTPUT_CSV,csv);
////
////        k = 30;
////        kString = valueOf(k);
////        index++;
////        record = new String[]{valueOf(index),"1","4MB", fileSize4MbStr, kString, "1", null, null, null};
////        appendToCsvFor1mbFiles(record, fourMbFiles, k);
////        csv.add(record);
////        Util.write(OUTPUT_CSV,csv);
////
////        index++;
////        record2 = new String[]{valueOf(index), "2","4MB", fileSize4MbStr, kString, "8192", null, null, null};
////        appendCsv2ndImpl(record2, fourMbFiles, k);
////        csv.add(record2);
////        Util.write(OUTPUT_CSV, csv);
////        k = 10;
////        kString = valueOf(k);
////        index++;
////        record2 = new String[]{valueOf(index), "1","4MB", fileSize4MbStr, kString, "1", null, null, null};
////        appendCsv2ndImpl(record2, fourMbFiles, k);
////        csv.add(record2);
////        Util.write(OUTPUT_CSV, csv);
////        k = 20;
////        kString = valueOf(k);
////        index++;
////        record2 = new String[]{valueOf(index), "1","4MB", fileSize4MbStr, kString, "1", null, null, null};
////        appendCsv2ndImpl(record2, fourMbFiles, k);
////        csv.add(record2);
////        Util.write(OUTPUT_CSV, csv);
////
////        k = 30;
////        kString = valueOf(k);
////        index++;
////        record2 = new String[]{valueOf(index), "1","4MB", fileSize4MbStr, kString, "1", null, null, null};
////        appendCsv2ndImpl(record2, fourMbFiles, k);
////        csv.add(record2);
////        Util.write(OUTPUT_CSV, csv);
////
////
////         bufferSize = 4096;
////        index++;
////         record3 = new String[]{valueOf(index), "3","4MB", fileSize4MbStr, kString, "4096", null, null, null};
////        appendCsv3rdImpl(record3, fourMbFiles, k, bufferSize);
////        csv.add(record3);
////        Util.write(OUTPUT_CSV, csv);
////        k = 10;
////        kString = valueOf(k);
////        index++;
////        record3 = new String[]{valueOf(index), "3","4MB", fileSize4MbStr, kString, "4096", null, null, null};
////        appendCsv3rdImpl(record3, fourMbFiles, k, bufferSize);
////        csv.add(record3);
////        Util.write(OUTPUT_CSV, csv);
////        k = 20;
////        kString = valueOf(k);
////        index++;
////        record3 = new String[]{valueOf(index), "3","4MB", fileSize4MbStr, kString, "4096", null, null, null};
////        appendCsv3rdImpl(record3, fourMbFiles, k, bufferSize);
////        csv.add(record3);
////        Util.write(OUTPUT_CSV, csv);
////        k = 30;
////        kString = valueOf(k);
////        index++;
////        record3 = new String[]{valueOf(index), "3","4MB", fileSize4MbStr, kString, "4096", null, null, null};
////        appendCsv3rdImpl(record3, fourMbFiles, k, bufferSize);
////        csv.add(record3);
////        Util.write(OUTPUT_CSV, csv);
////
////
////        k = 1;
////        kString = valueOf(k);
////        bufferSize = 1048576;
////        index++;
////         record4 = new String[]{valueOf(index), "3","4MB", fileSize4MbStr, kString, "1048576", null, null, null};
////        appendCsv3rdImpl(record4, fourMbFiles, k, bufferSize);
////        csv.add(record4);
////        Util.write(OUTPUT_CSV, csv);
////
////        k = 10;
////        kString = valueOf(k);
////        bufferSize = 32*1048576;
////        index++;
////        record5 = new String[]{valueOf(index), "3","4MB", fileSize4MbStr, kString, "32*1048576", null, null, null};
////        appendCsv3rdImpl(record5, fourMbFiles, k, bufferSize);
////        csv.add(record5);
////        Util.write(OUTPUT_CSV, csv);
////
////
////        k = 10;
////        kString = valueOf(k);
////        bufferSize = 128*1048576;
////        index++;
////        record6 = new String[]{valueOf(index), "3","4MB", fileSize4MbStr, kString, "128*1048576", null, null, null};
////        appendCsv3rdImpl(record6, fourMbFiles, k, bufferSize);
////        csv.add(record6);
////        Util.write(OUTPUT_CSV, csv);
//
//
//
//        // 32 MB
//        File thirtyTwoMb = new File("./src/main/resources/benchmark/32mb_32000000_integers.data");
//        int fileSize32mb = 1024 * 1024 * 16;
//        String fileSize32MbStr = valueOf(fileSize32mb);
//
//        int k = 1;
//        String kString = valueOf(k);
//        List<File> thirtyTwoMbFiles = getK1MbFiles(thirtyTwoMb);
//        int index = 0;
//        index++;
//        String[] record = new String[]{valueOf(index), "2","32MB", fileSize32MbStr, kString, "8192", null, null, null};
//        appendCsv2ndImpl(record, thirtyTwoMbFiles, k);
//        csv.add(record);
//        Util.write(OUTPUT_CSV, csv);
//
//
//        k = 10;
//        kString = valueOf(k);
//        index++;
//        record = new String[]{valueOf(index), "2","32MB", fileSize32MbStr, kString, "8192", null, null, null};
//        appendCsv2ndImpl(record, thirtyTwoMbFiles, k);
//        csv.add(record);
//        Util.write(OUTPUT_CSV, csv);
//
//
//        k = 20;
//        kString = valueOf(k);
//        index++;
//        record = new String[]{valueOf(index), "2","32MB", fileSize32MbStr, kString, "8192", null, null, null};
//        appendCsv2ndImpl(record, thirtyTwoMbFiles, k);
//        csv.add(record);
//        Util.write(OUTPUT_CSV, csv);
//
//        k = 30;
//        kString = valueOf(k);
//        index++;
//        record = new String[]{valueOf(index), "2","32MB", fileSize32MbStr, kString, "8192", null, null, null};
//        appendCsv2ndImpl(record, thirtyTwoMbFiles, k);
//        csv.add(record);
//        Util.write(OUTPUT_CSV, csv);
//
//
//        k = 1;
//        kString = valueOf(k);
//        index++;
//        int bufferSize = 4096;
//        String bufferSizeStr = valueOf(bufferSize);
//        record = new String[]{valueOf(index), "2","32MB", fileSize32MbStr, kString, bufferSizeStr, null, null, null};
//        appendCsv3rdImpl(record, thirtyTwoMbFiles, k, bufferSize);
//        csv.add(record);
//        Util.write(OUTPUT_CSV, csv);
//
//        k = 1;
//        kString = valueOf(k);
//        index++;
//        bufferSize = 1048576;
//        bufferSizeStr = valueOf(bufferSize);
//        record = new String[]{valueOf(index), "2","32MB", fileSize32MbStr, kString, bufferSizeStr, null, null, null};
//        appendCsv3rdImpl(record, thirtyTwoMbFiles, k, bufferSize);
//        csv.add(record);
//        Util.write(OUTPUT_CSV, csv);
//
//        // M =  4096 Bytes (size of the block), 1048576, 32*1048576, 128*1048576, 512*1048576,
//
//        k = 1;
//        kString = valueOf(k);
//        index++;
//        bufferSize = 32*1048576;
//        bufferSizeStr = valueOf(bufferSize);
//        record = new String[]{valueOf(index), "2","32MB", fileSize32MbStr, kString, bufferSizeStr, null, null, null};
//        appendCsv3rdImpl(record, thirtyTwoMbFiles, k, bufferSize);
//        csv.add(record);
//        Util.write(OUTPUT_CSV, csv);
//
//        k = 1;
//        kString = valueOf(k);
//        index++;
//        bufferSize = 128*1048576;
//        bufferSizeStr = valueOf(bufferSize);
//        record = new String[]{valueOf(index), "2","32MB", fileSize32MbStr, kString, bufferSizeStr, null, null, null};
//        appendCsv3rdImpl(record, thirtyTwoMbFiles, k, bufferSize);
//        csv.add(record);
//        Util.write(OUTPUT_CSV, csv);
//
//
//        k = 1;
//        kString = valueOf(k);
//        index++;
//        bufferSize = 512*1048576;
//        bufferSizeStr = valueOf(bufferSize);
//        record = new String[]{valueOf(index), "2","32MB", fileSize32MbStr, kString, bufferSizeStr, null, null, null};
//        appendCsv3rdImpl(record, thirtyTwoMbFiles, k, bufferSize);
//        csv.add(record);
//        Util.write(OUTPUT_CSV, csv);
//
//
//        k = 10;
//        kString = valueOf(k);
//        index++;
//        bufferSize = 4096;
//        bufferSizeStr = valueOf(bufferSize);
//        record = new String[]{valueOf(index), "2","32MB", fileSize32MbStr, kString, bufferSizeStr, null, null, null};
//        appendCsv3rdImpl(record, thirtyTwoMbFiles, k, bufferSize);
//        csv.add(record);
//        Util.write(OUTPUT_CSV, csv);
//
//        k = 10;
//        kString = valueOf(k);
//        index++;
//        bufferSize = 1048576;
//        bufferSizeStr = valueOf(bufferSize);
//        record = new String[]{valueOf(index), "2","32MB", fileSize32MbStr, kString, bufferSizeStr, null, null, null};
//        appendCsv3rdImpl(record, thirtyTwoMbFiles, k, bufferSize);
//        csv.add(record);
//        Util.write(OUTPUT_CSV, csv);
//
//        // M =  4096 Bytes (size of the block), 1048576, 32*1048576, 128*1048576, 512*1048576,
//
//        k = 10;
//        kString = valueOf(k);
//        index++;
//        bufferSize = 32*1048576;
//        bufferSizeStr = valueOf(bufferSize);
//        record = new String[]{valueOf(index), "2","32MB", fileSize32MbStr, kString, bufferSizeStr, null, null, null};
//        appendCsv3rdImpl(record, thirtyTwoMbFiles, k, bufferSize);
//        csv.add(record);
//        Util.write(OUTPUT_CSV, csv);
//
//        k = 10;
//        kString = valueOf(k);
//        index++;
//        bufferSize = 128*1048576;
//        bufferSizeStr = valueOf(bufferSize);
//        record = new String[]{valueOf(index), "2","32MB", fileSize32MbStr, kString, bufferSizeStr, null, null, null};
//        appendCsv3rdImpl(record, thirtyTwoMbFiles, k, bufferSize);
//        csv.add(record);
//        Util.write(OUTPUT_CSV, csv);
//
//
//        k = 10;
//        kString = valueOf(k);
//        index++;
//        bufferSize = 512*1048576;
//        bufferSizeStr = valueOf(bufferSize);
//        record = new String[]{valueOf(index), "2","32MB", fileSize32MbStr, kString, bufferSizeStr, null, null, null};
//        appendCsv3rdImpl(record, thirtyTwoMbFiles, k, bufferSize);
//        csv.add(record);
//        Util.write(OUTPUT_CSV, csv);
//
//
//        k = 30;
//        kString = valueOf(k);
//        index++;
//        bufferSize = 4096;
//        bufferSizeStr = valueOf(bufferSize);
//        record = new String[]{valueOf(index), "2","32MB", fileSize32MbStr, kString, bufferSizeStr, null, null, null};
//        appendCsv3rdImpl(record, thirtyTwoMbFiles, k, bufferSize);
//        csv.add(record);
//        Util.write(OUTPUT_CSV, csv);
//
//        k = 30;
//        kString = valueOf(k);
//        index++;
//        bufferSize = 1048576;
//        bufferSizeStr = valueOf(bufferSize);
//        record = new String[]{valueOf(index), "2","32MB", fileSize32MbStr, kString, bufferSizeStr, null, null, null};
//        appendCsv3rdImpl(record, thirtyTwoMbFiles, k, bufferSize);
//        csv.add(record);
//        Util.write(OUTPUT_CSV, csv);
//
//        k = 30;
//        kString = valueOf(k);
//        index++;
//        bufferSize = 32*1048576;
//        bufferSizeStr = valueOf(bufferSize);
//        record = new String[]{valueOf(index), "2","32MB", fileSize32MbStr, kString, bufferSizeStr, null, null, null};
//        appendCsv3rdImpl(record, thirtyTwoMbFiles, k, bufferSize);
//        csv.add(record);
//        Util.write(OUTPUT_CSV, csv);
//
//        k = 30;
//        kString = valueOf(k);
//        index++;
//        bufferSize = 128*1048576;
//        bufferSizeStr = valueOf(bufferSize);
//        record = new String[]{valueOf(index), "2","32MB", fileSize32MbStr, kString, bufferSizeStr, null, null, null};
//        appendCsv3rdImpl(record, thirtyTwoMbFiles, k, bufferSize);
//        csv.add(record);
//        Util.write(OUTPUT_CSV, csv);
//
//
//        k = 30;
//        kString = valueOf(k);
//        index++;
//        bufferSize = 512*1048576;
//        bufferSizeStr = valueOf(bufferSize);
//        record = new String[]{valueOf(index), "2","32MB", fileSize32MbStr, kString, bufferSizeStr, null, null, null};
//        appendCsv3rdImpl(record, thirtyTwoMbFiles, k, bufferSize);
//        csv.add(record);
//        Util.write(OUTPUT_CSV, csv);
//
//
//        k = 30;
//        kString = valueOf(k);
//        index++;
//        bufferSize = 512*1048576;
//        bufferSizeStr = valueOf(bufferSize);
//        record = new String[]{valueOf(index), "2","32MB", fileSize32MbStr, kString, bufferSizeStr, null, null, null};
//        appendCsv3rdImpl(record, thirtyTwoMbFiles, k, bufferSize);
//        csv.add(record);
//        Util.write(OUTPUT_CSV, csv);
//
//
//        // 128 MB
//
//        File oneTwentyEightMb = new File("./src/main/resources/benchmark/128mb_32000000_integers.data");
//        int fileSize128mb = 1024 * 1024 * 16;
//        String fileSize128MbStr = valueOf(fileSize128mb);
//
//        k = 1;
//        kString = valueOf(k);
//        List<File> oneTwentyEightMbFiles = getK1MbFiles(oneTwentyEightMb);
//        index = 0;
//        index++;
//        record = new String[]{valueOf(index), "2","128", fileSize128MbStr, kString, "8192", null, null, null};
//        appendCsv2ndImpl(record, oneTwentyEightMbFiles, k);
//        csv.add(record);
//        Util.write(OUTPUT_CSV, csv);
//
//    }
//
//
//    private static List<File> getK1MbFiles( File file) {
//        List<File> result = new ArrayList<>();
//        for(int i = 0 ; i < 100 ; i++) {
//            result.add(file);
//        }
//        return result;
//    }
//
//    private static void appendToCsvFor1mbFiles(String[] record, List<File> files, int k) throws FileNotFoundException {
//        for(int i = 0 ; i < 3; i++) {
//            StreamsBenchmark readStreamOneMb = new StreamsBenchmark(files, null, 0, k, 0);
//            long timeTaken = readStreamOneMb.getTimeTakenToExecuteKReadStreams();
//            String timeTakenString = valueOf(timeTaken);
//            if(i == 0) {
//                record[6] = timeTakenString;
//            }
//            if( i ==1 ) {
//                record[7] = timeTakenString;
//
//            }
//            if( i ==2 ) {
//                record[8] = timeTakenString;
//            }
//        }
//    }
//
//    private static void appendCsv2ndImpl(String[] record, List<File> files, int k) throws FileNotFoundException {
//        for(int i = 0 ; i < 3; i++) {
//            StreamsBenchmark readStreamOneMb = new StreamsBenchmark(files, null, 0, k, 0);
//            long timeTaken = readStreamOneMb.getTimeTakenToExecuteKFReadStreams();
//            String timeTakenString = valueOf(timeTaken);
//            if(i == 0) {
//                record[6] = timeTakenString;
//            }
//            if( i ==1 ) {
//                record[7] = timeTakenString;
//
//            }
//            if( i ==2 ) {
//                record[8] = timeTakenString;
//            }
//        }
//    }
//
//
//    private static void appendCsv3rdImpl(String[] record, List<File> files, int k, int bufferSize) throws FileNotFoundException {
//        for(int i = 0 ; i < 3; i++) {
//            StreamsBenchmark readStreamOneMb = new StreamsBenchmark(files, null, 0, k, bufferSize);
//            long timeTaken = readStreamOneMb.getTimeTakenToExecuteKBufferedReadStreams();
//            String timeTakenString = valueOf(timeTaken);
//            if(i == 0) {
//                record[6] = timeTakenString;
//            }
//            if( i ==1 ) {
//                record[7] = timeTakenString;
//
//            }
//            if( i ==2 ) {
//                record[8] = timeTakenString;
//            }
//        }
//    }
//

}
