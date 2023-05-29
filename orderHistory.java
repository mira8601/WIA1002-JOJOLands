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
public class orderHistory {
    private String name;
    private int dayNum;
    private String[] food;
    private String[] restaurant;
    
    public orderHistory(String name, int dayNum){
        this.name = name;
        this.dayNum = dayNum;
    }
    
    public void printOrderHistory(){ //somehow when run, it stops here w/o error. Pls help T.T
        randomOrder ro= new randomOrder(dayNum);
        ArrayList<orderList> orderList = ro.randomOrderGenerator();
        System.out.println();
        System.out.println("Order History");
        System.out.println("+------+-------------------------------+--------------+");
        System.out.println("| Day\t| Food\t\t| Restaurant\t|");
        System.out.println("+------+-------------------------------+--------------+");
        for(int i=0;i<orderList.size();i++){
            if((orderList.get(i).getName()).equals(name)){
                for(int j=0;j<dayNum;j++){
                    food = orderList.get(i).getFood();
                    restaurant = orderList.get(i).getRestaurant();
                    System.out.println("|\t" + j+1 + " | " + food[j] + "\t| " 
                            + restaurant[j] + "\t|");
                }
            }
        }
        System.out.println("+------+-------------------------------+--------------+");
        System.out.println("=================================================================");
    }
}
