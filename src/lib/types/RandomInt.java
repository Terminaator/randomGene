package lib.types;


import lib.GenerateRandomNumber;

import java.nio.ByteBuffer;
import java.util.Random;

public class RandomInt implements GenerateRandomNumber {
    private Random rand = new Random();

    @Override
    public byte[] randomNumber() {
        return toByteArray(rand.nextInt());
    }
    private byte[] toByteArray(int value) {
        byte[] bytes = ByteBuffer.wrap(new byte[5]).putInt(value).array();
        bytes[4] = 32;
        return newArray(bytes);
    }
    private byte[] newArray(byte[] arr){
        byte[] bytes = new byte[arr.length+1];
        bytes[0] = 3;
        for (int a = 1; a < bytes.length; a++){
            bytes[a] = arr[a-1];
        }
        return bytes;
    }
}
