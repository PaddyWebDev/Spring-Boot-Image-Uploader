package com.userservice.project.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class UserImgUtils {

    public static byte[] compressImage(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] temp = new byte[4 * 1024];
        while (!deflater.finished()) {
            int Size = deflater.deflate(temp);
            outputStream.write(temp, 0, Size);
        }
        try {
            outputStream.close();
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(data.length);
        byte[] temp = new byte[4 * 1024];
        try {
            while (!inflater.finished()) {
                int size = inflater.inflate(temp);
                byteArrayOutputStream.write(temp, 0, size);
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
}
