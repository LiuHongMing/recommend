package com.github.tiger.agent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class DogTransformer implements ClassFileTransformer {

    public static byte[] getBytesFromFile(String filename) {

        File file = new File(filename);
        try(InputStream is = new FileInputStream(file)) {
            int length = (int) file.length();
            byte[] bytes = new byte[length];

            int offset = 0;

            int numRead;

            while(offset < length
                    && (numRead = is.read(bytes, offset,
                    bytes.length - offset)) > 0) {
                offset += numRead;
            }

            if (offset < bytes.length) {
                System.out.println("Could not completely read file " + filename);
            }
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("classname: " + className);
        if ("com/zhaopin/agent/Dog".equals(className)) {
            String filename = "/Users/liuhongming/Desktop/Dog.class";
            return getBytesFromFile(filename);
        }
        return new byte[0];
    }
}
