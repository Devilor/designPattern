package com.ernesto.demo;

import java.io.*;

/**
 * @author Ernesto
 * @date 2019/12/23
 */
public class BufferReaderAndWriterDemo {
    private final static String FILE_PATH = "G:\\renjy\\designPattern\\src\\main\\resources\\log4j.xml";

    public static void main(String[] args) {
        try {
            //将指定文件读入程序中 in
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(FILE_PATH)));
            String line = null;
            String result = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
