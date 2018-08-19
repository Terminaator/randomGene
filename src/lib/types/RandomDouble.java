package lib.types;

import lib.GenerateRandomNumber;

import java.nio.ByteBuffer;
import java.util.Random;

public class RandomDouble implements GenerateRandomNumber {
    private Random rand = new Random();

    @Override
    public byte[] randomNumber() {
        return toByteArray(rand.nextDouble());
    }
    private byte[] toByteArray(double value) {
        byte[] bytes = ByteBuffer.wrap(new byte[9]).putDouble(value).array();
        bytes[8] = 32;
        return newArray(bytes);
    }
    private byte[] newArray(byte[] arr){
        byte[] bytes = new byte[arr.length+1];
        bytes[0] = 1;
        for (int a = 1; a < bytes.length; a++){
            bytes[a] = arr[a-1];
        }
        return bytes;
    }
}
