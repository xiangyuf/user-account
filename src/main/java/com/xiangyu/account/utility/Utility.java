package com.xiangyu.account.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;

public class Utility {
    private static final char PID_SEPERATOR = '@';

    private static String acquirePid() {
        String mxName = ManagementFactory.getRuntimeMXBean().getName();

        int index = mxName.indexOf(PID_SEPERATOR);

        String result;

        if (index != -1) {
            result = mxName.substring(0, index);
        } else {
            throw new IllegalStateException("Could not acquire pid using " + mxName);
        }

        return result;
    }

    public static String acquireJstack() throws Exception{
        String pid = acquirePid();
        String[] command = {"jstack", "-l", pid};
        Process process = Runtime.getRuntime().exec(command);

        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                process.getInputStream()))) {
            reader.lines().forEach(line -> stringBuilder.append(line + "\n"));
        }

        return stringBuilder.toString();
    }
}
