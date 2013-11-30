package ru.regenix.jphp.runtime;

import ru.regenix.jphp.runtime.memory.BinaryMemory;
import ru.regenix.jphp.runtime.memory.DoubleMemory;
import ru.regenix.jphp.runtime.memory.LongMemory;
import ru.regenix.jphp.runtime.memory.support.Memory;

import java.util.Arrays;

public class OperatorUtils {

    public static boolean toBoolean(double value){
        return value != 0.0;
    }

    public static boolean toBoolean(long value){
        return value != 0;
    }

    public static boolean toBoolean(String value){
        return value != null && !value.equals("0") && !value.isEmpty();
    }

    // PLUS
    public static Memory plus(long o1, Memory value) {
        switch (value.type){
            case INT: return LongMemory.valueOf(o1 + value.toLong());
            case DOUBLE: return DoubleMemory.valueOf(o1 + value.toDouble());
        }
        return plus(o1, value.toNumeric());
    }
    public static Memory plus(double o1, Memory value) { return DoubleMemory.valueOf(o1 + value.toDouble()); }
    public static Memory plus(boolean o1, Memory value) {
        return o1 ? Memory.TRUE.plus(value) : Memory.FALSE.plus(value);
    }

    // MINUS
    public static Memory minus(long o1, Memory value) {
        switch (value.type){
            case INT: return LongMemory.valueOf(o1 - value.toLong());
            case DOUBLE: return DoubleMemory.valueOf(o1 - value.toDouble());
        }
        return minus(o1, value.toNumeric());
    }
    public static Memory minus(double o1, Memory value) { return DoubleMemory.valueOf(o1 - value.toDouble()); }
    public static Memory minus(boolean o1, Memory value) {
        return o1 ? Memory.TRUE.minus(value) : Memory.FALSE.minus(value);
    }

    // MUL
    public static Memory mul(long o1, Memory value) {
        switch (value.type){
            case INT: return LongMemory.valueOf(o1 * value.toLong());
            case DOUBLE: return DoubleMemory.valueOf(o1 * value.toDouble());
        }
        return mul(o1, value.toNumeric());
    }
    public static Memory mul(double o1, Memory value) { return DoubleMemory.valueOf(o1 * value.toDouble()); }
    public static Memory mul(boolean o1, Memory value) {
        return o1 ? Memory.TRUE.mul(value) : Memory.FALSE.mul(value);
    }

    // MOD
    public static Memory mod(long o1, Memory value) { return LongMemory.valueOf(o1 % value.toLong()); }
    public static Memory mod(double o1, Memory value) { return LongMemory.valueOf((long)o1 % value.toLong()); }
    public static Memory mod(boolean o1, Memory value) {
        return o1 ? Memory.TRUE.mod(value) : Memory.FALSE.mod(value);
    }

    public static char toChar(String o){
        return o.isEmpty() ? '\0' : o.charAt(0);
    }

    public static char toChar(long o){ return (char)o; }
    public static char toChar(int o){ return (char)o; }
    public static char toChar(short o){ return (char)o; }
    public static char toChar(byte o){ return (char)o; }
    public static char toChar(double o){ return (char)o; }
    public static char toChar(float o){ return (char)o; }
    public static char toChar(boolean o){ return (char)(o ? 0 : 1); }


    public static Memory binaryXor(Memory o1, Memory o2){
        byte[] bytes1 = o1.getBinaryBytes();
        byte[] bytes2 = o2.getBinaryBytes();

        byte[] result = bytes1.length <= bytes2.length
                ? Arrays.copyOf(bytes1, bytes1.length)
                : Arrays.copyOf(bytes2, bytes2.length);

        for(int i = 0; i < result.length; i++){
            result[i] = (byte)(bytes1[i] ^ bytes2[i]);
        }
        return new BinaryMemory(result);
    }

    public static Memory binaryAnd(Memory o1, Memory o2){
        byte[] bytes1 = o1.getBinaryBytes();
        byte[] bytes2 = o2.getBinaryBytes();

        byte[] result = bytes1.length <= bytes2.length
                ? Arrays.copyOf(bytes1, bytes1.length)
                : Arrays.copyOf(bytes2, bytes2.length);

        for(int i = 0; i < result.length; i++){
            result[i] = (byte)(bytes1[i] & bytes2[i]);
        }
        return new BinaryMemory(result);
    }

    public static Memory binaryOr(Memory o1, Memory o2){
        byte[] bytes1 = o1.getBinaryBytes();
        byte[] bytes2 = o2.getBinaryBytes();

        byte[] result = bytes1.length <= bytes2.length
                ? Arrays.copyOf(bytes1, bytes1.length)
                : Arrays.copyOf(bytes2, bytes2.length);

        for(int i = 0; i < result.length; i++){
            result[i] = (byte)(bytes1[i] | bytes2[i]);
        }
        return new BinaryMemory(result);
    }

    public static Memory binaryShr(Memory o1, Memory o2){
        byte[] bytes1 = o1.getBinaryBytes();
        byte[] bytes2 = o2.getBinaryBytes();

        byte[] result = bytes1.length <= bytes2.length
                ? Arrays.copyOf(bytes1, bytes1.length)
                : Arrays.copyOf(bytes2, bytes2.length);

        for(int i = 0; i < result.length; i++){
            result[i] = (byte)(bytes1[i] >> bytes2[i]);
        }
        return new BinaryMemory(result);
    }

    public static Memory binaryShl(Memory o1, Memory o2){
        byte[] bytes1 = o1.getBinaryBytes();
        byte[] bytes2 = o2.getBinaryBytes();

        byte[] result = bytes1.length <= bytes2.length
                ? Arrays.copyOf(bytes1, bytes1.length)
                : Arrays.copyOf(bytes2, bytes2.length);

        for(int i = 0; i < result.length; i++){
            result[i] = (byte)(bytes1[i] << bytes2[i]);
        }
        return new BinaryMemory(result);
    }

    public static Memory binaryNot(Memory o1){
        byte[] bytes = o1.getBinaryBytes();
        bytes = Arrays.copyOf(bytes, bytes.length);

        for(int i = 0; i < bytes.length; i++){
            bytes[i] = (byte)~bytes[i];
        }
        return new BinaryMemory(bytes);
    }
}