import lib.GenerateRandomNumericFile;
import lib.ReadGeneratedFile;

import java.io.IOException;
import java.util.Map;

public class Main {
    private String fileName;
    private double time;
    private long prime;
    private long armstrong;
    private Map<Object,Long> map;

    public Main(String fileName,Integer size) throws Exception {
        this.fileName = fileName;
        generateFile(size);
        workWithData();
    }
    private void generateFile(Integer size) throws IOException {
        GenerateRandomNumericFile e = new GenerateRandomNumericFile();
        if (size == null) e.GenerateFile(fileName);
        else e.GenerateFile(fileName, size);
    }
    private void workWithData() throws Exception {
        ReadGeneratedFile readGeneratedFile = new ReadGeneratedFile(fileName);
        long lStartTime = System.nanoTime();
        readGeneratedFile.read();
        long lEndTime = System.nanoTime();
        long output = lEndTime - lStartTime;
        time = (double)output / 1000000000.0;
        prime = readGeneratedFile.getPrime();
        armstrong = readGeneratedFile.getArmstrong();
        map = readGeneratedFile.getMap();
    }

    public double getTime() {
        return time;
    }

    public long getPrime() {
        return prime;
    }

    public long getArmstrong() {
        return armstrong;
    }

    public Map<Object, Long> getMap() {
        return map;
    }
}
