/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jojoland;

import java.util.ArrayList;
import java.util.List;

public class orderList {
    private String name;
    private String age;
    private String gender;
    private int arrivalTime;
    private int dayNum;
    private int totalDays=0;
    private String food;
    private String restaurant;
    private double price;
    private int indexRest;
    private int indexOrder;
    
    public orderList(String name, String age, String gender, int arrivalTime, int dayNum, String food, String restaurant, int indexRest, int indexOrder, double price){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.arrivalTime = arrivalTime;
        this.dayNum = dayNum;
        this.food = food;
        this.restaurant = restaurant;
        this.indexRest = indexRest;
        this.indexOrder = indexOrder;
        this.price = price;
    }


    public String getName() {
        return name;
    }
    
    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
    
    public int getArrivalTime(){
        return arrivalTime;
    }
    
    public int getTotalDays(){
        return totalDays;
    }
    
    public String getFood() {
        return food;
    }
    
    public String getRestaurant() {
        return restaurant;
    }
    
    public int getIndexRest(){
        return indexRest;
    }
    
    public int getIndexOrder(){
        return indexOrder;
    }
    
    public double getPrice(){
        return price;
    }
    
    public int getDayNum(){
        return dayNum;
    }
    
    public static List<orderList> getOrdersByRestaurant(List<orderList> orderList, String restaurant) {
        List<orderList> orders = new ArrayList<>();

        for (orderList order : orderList) {
            if (order.getRestaurant().equalsIgnoreCase(restaurant)) {
                orders.add(order);
            }
        }

        return orders;
    }


}

