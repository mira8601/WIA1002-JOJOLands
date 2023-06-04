/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jojolands;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 22004818
 */

public class orderList {
    private String name;
    private int dayNum;
    private int totalDays=0;
    private String food;
    private String restaurant;
    private double price;
    private int indexRest;
    private int indexOrder;
    
    public orderList(String name, int dayNum, String food, String restaurant, int indexRest, int indexOrder, double price){
        this.name = name;
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

}

