package lib;

import lib.types.RandomDouble;
import lib.types.RandomFloat;
import lib.types.RandomInt;
import lib.types.RandomLong;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import java.util.Random;


public class GenerateRandomNumericFile {
    private Random random = new Random();
    private long size = 1024*1024*64;
    private void fileExist(String fileName) throws IOException {
        File file = new File(fileName);
        if (file.exists()) file.delete();
    }
    public void GenerateFile(String fileName, int sz) throws IOException{
        this.size = 1024*1024*sz;
        generate(fileName);
    }
    public void GenerateFile(String fileName) throws IOException {
        generate(fileName);
    }
    private void generate(String fileName) throws IOException{
        fileExist(fileName);
        FileChannel rwChannel = new RandomAccessFile(fileName, "rw").getChannel();
        ByteBuffer rwBuf = rwChannel.map(FileChannel.MapMode.READ_WRITE, 0,size);
        while (size >= 10) {
            writeToFile(rwBuf);
        }
        if (size >= 6) {
            rwBuf.put(finalBytes());
        }
        rwChannel.close();
    }
    private byte[] finalBytes(){
        int choice = random.nextInt(2);
        byte[] value = null;
        if (choice == 0){
            value = new RandomInt().randomNumber();
        }
        else if (choice == 1){
            value = new RandomFloat().randomNumber();
        }
        return value;
    }
    //igale muutujale tuleb 2 byte juurde, tühik ja liik, kus esimene on määrab tüübi ja viimane on tühik
    private void writeToFile(ByteBuffer rwBuf) throws IOException {
        int choice = random.nextInt(4);
        byte[] value = null;
        if (choice == 0){
            value = new RandomInt().randomNumber();
            size-=6;
        }
        else if (choice == 1){
            value = new RandomFloat().randomNumber();
            size-=6;
        }
        else if (choice == 2){
            value = new RandomDouble().randomNumber();
            size-=10;
        }
        else if (choice == 3){
            value = new RandomLong().randomNumber();
            size-=10;
        }
        rwBuf.put(value);
    }
}
