package lib;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Data implements Runnable{
    private Map<Object,Long> map;
    private Map<Object,Long> values = new HashMap<>();

    private long prime = 0;
    private long armstrong = 0;
    private long count = 0;
    public Data(List<Object> list) {
        this.map = list.stream().parallel().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
    }

    private void isPrime(long number, Long value) {
        BigInteger bigInt = BigInteger.valueOf(number);
        if (bigInt.isProbablePrime(100)) prime+=value;

    }
    private void isArmstrongNumber(long n, Long value){
        long sum = 0, temp, remainder, digits = 0;
        temp = n;
        while (temp != 0) {
            digits++;
            temp = temp/10;
        }
        temp = n;
        while (temp != 0) {
            remainder = temp%10;
            sum = sum + power(remainder, digits);
            temp = temp/10;
        }
        if (n == sum) armstrong+=value;
    }
    private long power(long n, long r) {
        long c, p = 1;
        for (c = 1; c <= r; c++)
            p = p*n;
        return p;
    }
    private long top10(Object key, long value, long minMax){
        if (count > 10){
            long smallest = value;
            Object object = null;
            for (Map.Entry<Object,Long> entry : values.entrySet()){
                long temp = entry.getValue();
                if (temp < smallest) {
                    smallest = temp;
                    object = entry.getKey();
                }
            }
            if (object != null) {
                values.remove(object);
                values.put(key,value);
            }
            return value;
        }
        else if (count <= 10) {
            values.put(key,value);
            count++;
        }
        return minMax;
    }
    @Override
    public void run() {
        long minMax = 0;
        for (Map.Entry<Object,Long> entry : map.entrySet()) {
            Object object = entry.getKey();
            Long value = entry.getValue();
            if (object instanceof Long){
                Long obj = (long) object;
                if (obj > 0) {
                    isPrime(obj,value);
                    isArmstrongNumber(obj,value);
                }
                if (value > minMax){
                    minMax = top10(object,value,minMax);
                }
            }
            else if (object instanceof Integer){
                int obj = (int) object;
                if (obj > 0) {
                    isPrime(obj, value);
                    isArmstrongNumber(obj, value);
                }
                if (value > minMax){
                    minMax = top10(object,value,minMax);
                }
            }
            else if (object instanceof Float){
                float obj = (float) object;
                if (value > minMax){
                    minMax = top10(object,value,minMax);
                }
            }
            else if (object instanceof Double){
                double obj = (double) object;
                if (value > minMax){
                    minMax = top10(object,value,minMax);
                }
            }
        }
    }
    public Map<Object, Long> getValues() {
        return values;
    }

    public long getPrime() {
        return prime;
    }

    public long getArmstrong() {
        return armstrong;
    }
}
