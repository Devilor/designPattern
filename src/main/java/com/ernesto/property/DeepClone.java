package com.ernesto.property;

import java.io.*;

/**
 * @author Ernesto
 * @date 2019/12/22
 */
public class DeepClone implements Serializable {
    private int num;
    private String str;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public DeepClone deepClone() throws IOException {
        DeepClone deepClone = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(this);

            objectInputStream = new ObjectInputStream(new ByteArrayInputStream(outputStream.toByteArray()));

            deepClone = (DeepClone)objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            objectInputStream.close();
            objectOutputStream.close();
        }
        return deepClone;
    }

    @Override
    public String toString() {
        return "DeepClone{" + "num=" + num + ", str=" + str + '}';
    }

    public static void main(String[] args) {

        DeepClone deepClone = new DeepClone();
        /**
         * new String("AAAA") : 会创建2个对象，其中一个放到JVM字符常量池中，另个放到堆中，这个时候对这个值引用的指针指向堆中那个对象。
         * 如果是 deepClone.setStr("AAAA");则会直接引用JVM常量池中的字符串对象。
         */
        deepClone.setStr(new String("AAAA"));
        deepClone.setNum(1000000001);
        System.out.println("原始对象：");
        System.out.println(deepClone.toString());
        try {
            DeepClone clone = deepClone.deepClone();
            System.out.println("克隆对象：");
            System.out.println(clone.toString());

            System.out.println("++++++++++++++++++++++++++++++++++");
            System.out.println(deepClone.getNum() == clone.getNum());
            System.out.println(deepClone.getStr() == clone.getStr());
            System.out.println(deepClone.getStr().equals(clone.getStr()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
