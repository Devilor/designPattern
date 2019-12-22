package com.ernesto.property;

import com.ernesto.utils.DBUtils;
import com.ernesto.utils.LoadObjectByDB;

import java.io.Serializable;

/**
 * @author Ernesto
 * @date 2019/12/22
 */
public class Order implements Cloneable, Serializable {
    private String orderId;
    private String userId;
    private String location;
    private double price;
    private String telephone;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Order() {
    }

    public Order(String orderId, String userId, String location, double price, String telephone) {
        this.orderId = orderId;
        this.userId = userId;
        this.location = location;
        this.price = price;
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId='" + orderId + '\'' + ", userId='" + userId + '\'' + ", location='" + location + '\''
            + ", price=" + price + ", telephone='" + telephone + '\'' + '}';
    }

    @Override
    protected Order clone() {
        Order order = null;
        try {
            order = (Order)super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("该对象不允许克隆！");
            e.printStackTrace();
        }
        return order;
    }

    public static Order orderClone(String userName) {
        Order order = LoadObjectByDB.loadOrder(userName);
        order.setOrderId("");
        return order;
    }

    public static void main(String[] args) {
        Order order = Order.orderClone("Allen");
        System.out.println("初始对象：" + order.toString());
        order.setLocation("New-济南市高新区");
        order.setOrderId("10005");
        System.out.println("需要的对象：" + order.toString());
    }
}
