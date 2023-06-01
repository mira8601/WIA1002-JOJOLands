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
    private int[] day;
    private int totalDays=0;
    private String food;
    private String restaurant;
    private List<String> foodList = new ArrayList<>();
    private List<String> restList = new ArrayList<>();
    private List<Integer> indexOrderList = new ArrayList<>();
    private List<Integer> indexRestList = new ArrayList<>();
    private int indexRest;
    private int indexOrder;
    
    public orderList(String name, int dayNum, String food, String restaurant, int indexRest, int indexOrder){
        this.name = name;
        this.dayNum = dayNum;
        this.totalDays = dayNum;
        foodList.add(food);
        restList.add(restaurant);
        indexOrderList.add(indexOrder);
        indexRestList.add(indexRest);
    }
    
    public String getName() {
        return name;
    }
    
    public int[] getDayNum() {
        for(int i=0;i<dayNum;i++){ //create array day from 1 until dayNum
            day[i] = i+1;
        }
        return day;
    }
    
    public int getTotalDays(){
        return totalDays;
    }
    
    private void setFood(){
        this.food = foodList.get(dayNum-1);
    }
    
    public String getFood() {
        return food;
    }
    
    private void setRestaurant(){
        this.restaurant = restList.get(dayNum-1);
    }
    
    public String getRestaurant() {
        return restaurant;
    }
    
    public int getIndexRest(int dayNum) { 
        this.indexRest= indexRestList.get(dayNum);
        return indexRest;
    }
    
    public int getIndexOrder(int dayNum) { 
        this.indexOrder = indexOrderList.get(dayNum);
        return indexOrder;
    }
}

