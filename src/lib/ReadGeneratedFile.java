package lib;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.*;

public class ReadGeneratedFile {
    private String fileName;
    private List<Object> arr = new ArrayList<>();

    public Map<Object, Long> getMap() {
        return map;
    }

    public long getPrime() {
        return prime;
    }

    public long getArmstrong() {
        return armstrong;
    }

    private Map<Object,Long> map = new LinkedHashMap<>();
    private long prime = 0;
    private long armstrong = 0;

    public ReadGeneratedFile(String fileName) {
        this.fileName = fileName;
    }

    private void sorting(Map<Object,Long> mp1, Map<Object,Long> mp2){
        for (Map.Entry<Object,Long> entry : mp1.entrySet()){
            Object object = entry.getKey();
            long value = entry.getValue();
            if (mp2.containsKey(object)){
                mp2.replace(object,value+mp2.get(object));
            }
            else mp2.put(object,value);
        }
        Map<Object, Long> result = new LinkedHashMap<>();
        mp2.entrySet().stream()
                .sorted(Map.Entry.<Object, Long>comparingByValue().reversed())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
        int a = 0;
        for (Map.Entry<Object,Long> entry : result.entrySet()){
            if (a > 10) break;
            else {
                a++;
                map.put(entry.getKey(),entry.getValue());
            }
        }
    }

    private void threads() throws InterruptedException {
        int size = arr.size()/2;
        Data data1 = new Data(arr.subList(0, size));
        Data data2 = new Data(arr.subList(size, arr.size()));
        Thread thrd1 = new Thread(data1);
        Thread thrd2 = new Thread(data2);
        thrd1.start();
        thrd2.start();
        thrd1.join();
        thrd2.join();
        prime = data1.getPrime() + data2.getPrime();
        armstrong = data1.getArmstrong() + data2.getArmstrong();
        sorting(data1.getValues(),data2.getValues());

    }
    public void read() throws Exception {
        RandomAccessFile f = new RandomAccessFile(fileName, "r");
        byte[] b = new byte[(int)f.length()];
        f.readFully(b);
        bytesToTypes(b);
        threads();


    }
    private void bytesToTypes(byte[] bytes){
        int pos = 0, size = bytes.length;
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        while (true){
            if (pos >=size) {
                break;
            }
            byte type = bytes[pos];
            if (type == 1){
                double value = byteBuffer.getDouble(pos+1);
                pos+=10;
                arr.add(value);
            }
            else if (type == 2){
                float value = byteBuffer.getFloat(pos+1);
                pos+=6;
                arr.add(value);
            }
            else if (type == 3){ //prime ja armstrong
                int value = byteBuffer.getInt(pos+1);
                pos+=6;
                arr.add(value);
            }
            else if (type == 4) { //prime ja armstrong
                long value = byteBuffer.getLong(pos+1);
                pos+=10;
                if (Integer.MAX_VALUE >= value && Integer.MIN_VALUE <= value) arr.add((int) value);
                else arr.add(value);
            }
            else if (type == 0){
                break;
            }
        }
    }
}
