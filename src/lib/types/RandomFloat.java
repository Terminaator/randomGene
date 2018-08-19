package lib.types;

import lib.GenerateRandomNumber;

import java.nio.ByteBuffer;
import java.util.Random;

public class RandomFloat implements GenerateRandomNumber {
    private Random rand = new Random();

    @Override
    public byte[] randomNumber() {
        return toByteArray(rand.nextFloat());
    }
    private byte[] toByteArray(float value) {
        byte[] bytes = ByteBuffer.wrap(new byte[5]).putFloat(value).array();
        bytes[4] = 32;
        return newArray(bytes);
    }
    private byte[] newArray(byte[] arr){
        byte[] bytes = new byte[arr.length+1];
        bytes[0] = 2;
        for (int a = 1; a < bytes.length; a++){
            bytes[a] = arr[a-1];
        }
        return bytes;
    }
}
