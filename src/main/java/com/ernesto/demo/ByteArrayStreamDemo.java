package com.ernesto.demo;

import java.io.*;

/**
 * ByteArrayInputStream 和 ByteArrayOutputStream
 *
 * @author Ernesto
 * @date 2019/12/23
 */
public class ByteArrayStreamDemo {
    public static void main(String[] args) {
        Demo demo = new Demo("ByteArray", 102);
        System.out.println(demo);
        System.out.println("-------------------------------");

        try {
            //将 Demo 对象写入输出流
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            //对象开始写入输出流中
            objectOutputStream.writeObject(demo);

            //从当前输出流中读入对象到程序中
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Demo demoRead = (Demo)objectInputStream.readObject();
            System.out.println(demoRead);
            objectInputStream.close();
            byteArrayInputStream.close();
            objectOutputStream.close();
            byteArrayOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}

class Demo implements Serializable {
    private String name;
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Demo(String name) {
        this(name, 0);
    }

    public Demo(int score) {
        this("", score);
    }

    public Demo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Demo{" + "name='" + name + '\'' + ", score=" + score + '}';
    }

}
