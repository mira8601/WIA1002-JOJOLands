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
    private int totalDays=0;
    private String food;
    private String restaurant;
    private List<String> foodList = new ArrayList<>();
    private List<String> restList = new ArrayList<>();
    private List<Integer> indexOrderList = new ArrayList<>();
    private List<Integer> indexRestList = new ArrayList<>();
     private List<Integer> dayList = new ArrayList<>();
    private int indexRest;
    private int indexOrder;
    
    public orderList(String name, int dayNum, String food, String restaurant, int indexRest, int indexOrder){
        this.name = name;
        dayList.add(dayNum);
        foodList.add(food);
        restList.add(restaurant);
        indexOrderList.add(indexOrder);
        indexRestList.add(indexRest);
    }
    
    public String getName() {
        return name;
    }
    
    public void addDay(int dayNum){
        dayList.add(dayNum);
    } 
    
    public int getTotalDays(){
        int last = dayList.size();
        totalDays = dayList.get(last-1);
        return totalDays;
    }
    
    public void addFood(String newFood){
        foodList.add(newFood);
    }
    
    public void addRest(String newRest){
        restList.add(newRest);
    }
    
    public void addIndexOrder(int newIndexOrder){
        indexOrderList.add(newIndexOrder);
    }
    
    public void addIndexRest(int newIndexRest){
        indexRestList.add(newIndexRest);
    }
    
    public String getFood(int dayNum) {
        this.food = foodList.get(dayNum-1);
        return food;
    }
    
    public String getRestaurant(int dayNum) {
        this.restaurant = restList.get(dayNum-1);
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
    
    public List<Integer> getIndexRestList() {
        return indexRestList;
    }
    
    public List<Integer> getIndexOrderList() {
        return indexOrderList;
    }
    
    public List<String> getFoodList() {
        return foodList;
    }
    
    public void printOrderHistory(){
        System.out.println("Order History");
        System.out.println("+------+-------------------------------+--------------+");
        System.out.println("| Day  | Food                          | Restaurant   |");
        System.out.println("+------+-------------------------------+--------------+");
        for (int i = 0; i < foodList.size(); i++) {
            System.out.printf("| %d   | %-30s | %-12s |%n", i+1, foodList.get(i), restList.get(i));
        }
        System.out.println("+------+-------------------------------+--------------+");
    }
}

