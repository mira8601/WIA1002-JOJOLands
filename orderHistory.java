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
    
    public orderHistory(String name, int dayNum){
        this.name = name;
        this.dayNum = dayNum;
    }
    
    public void printOrderHistory(ArrayList<orderList> orderList){
        System.out.println();
        for(int i=0;i<orderList.size();i++){
            if((orderList.get(i).getName()).equals(name)){
                orderList.get(i).printOrderHistory();
            }
        }
        System.out.println("=================================================================");
    }
}
