/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jojolands;

/**
 *
 * @author 22004818
 */

public class orderList {
    private String name;
    private int dayNum;
    private int[] day;
    private String[] food;
    private String[] restaurant;
    private int[] indexRest;
    private int[] indexOrder;
    
    public orderList(String name, int dayNum, String food, String restaurant, int indexRest, int indexOrder){
        this.name = name;
        this.dayNum = dayNum;
        this.food[dayNum] = food; //store food according to day
        this.restaurant[dayNum] = restaurant; //store restaurant according to day
        this.indexRest[dayNum] = indexRest;
        this.indexOrder[dayNum] = indexOrder;
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
    
    public String[] getFood() {
        return food;
    }
    
    public String[] getRestaurant() {
        return restaurant;
    }
    
    public int[] getIndexRest() { 
        return indexRest;
    }
    
    public int[] getIndexOrder() { 
        return indexOrder;
    }
}

