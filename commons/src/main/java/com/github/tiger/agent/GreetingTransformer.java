package com.github.tiger.agent;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.Date;

public class GreetingTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        if ("com/zhaopin/agent/Dog".equals(className)) {
            System.out.println("Dog's method invoke at\t" + new Date());
        }
        return null;
    }

}
