/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jojolands;

import java.util.ArrayList;

/**
 *
 * @author 22004818
 */

public class orderList {
    private String name;
    private int[] dayNum;
    private String[] food;
    private String[] restaurant;
    
    public orderList(String name, int[] dayNum, String[] food, String[] restaurant){
        this.name = name;
        this.dayNum = dayNum;
        this.food = food;
        this.restaurant = restaurant;
    }
    
    public String getName() {
        return name;
    }
    
    public int[] getDayNum() {
        return dayNum;
    }
    
    public String[] getFood() {
        return food;
    }
    
    public String[] getRestaurant() {
        return restaurant;
    }
}

