/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jojolands;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author 22004818
 */
public class randomOrder {
    private String name;
    private int dayNum;
    private String restaurant;
    //random food selection algorithm
    Random ran = new Random();
    public static Menu menu = new Menu();
    ArrayList<orderList> orderList = new ArrayList<>();
    
    public randomOrder(String name, int dayNum){
        this.name = name;
        this.dayNum = dayNum;
    }
    
    public ArrayList<orderList> randomOrderGenerator(){
        //has to match with restaurant sales record and order list from task 2
        //check for preferences
        Pair<Integer> pair;
        switch (name) {
            case "Jonathan Joestar":
                pair = jonathanOrder();
                break;
            case "Joseph Joestar":
                break;
            case "Jotaro Kujo":
                pair = jotaroOrder();
                break;
            case "Josuke Higashikata":
                pair = josukeOrder();
                break;
            case "Giorno Giovanna":
                pair = giornoOrder();
                break;
            case "Jolyne Cujoh":
                pair = jolyneOrder();
                break;
            default:
                break;
        }
        return orderList;
    }
    
    public Pair<Integer> jonathanOrder(){
        int indexRest = ran.nextInt(); //get index for restaurant
        int indexOrder = ran.nextInt(); //get index for order
        //store order history in list 
        //orderList.add();
        return new Pair<>(indexRest, indexOrder);
    }
    
    public Pair<Integer> josephOrder(){
        
        int indexRest = ran.nextInt(); //get index for restaurant
        int indexOrder = ran.nextInt(); //get index for order
        //store order history in list 
        //orderList.add();
        return new Pair<>(indexRest, indexOrder);
    }
    
    public Pair<Integer> jotaroOrder(){
        int indexRest = ran.nextInt(); //get index for restaurant
        int indexOrder = ran.nextInt(); //get index for order
        //store order history in list 
        //orderList.add();
        return new Pair<>(indexRest, indexOrder);
    }
    
    public Pair<Integer> josukeOrder(){
        int indexRest = ran.nextInt(); //get index for restaurant
        int indexOrder = ran.nextInt(); //get index for order
        //store order history in list 
        //orderList.add();
        return new Pair<>(indexRest, indexOrder);
    }
    
    public Pair<Integer> giornoOrder(){
        int indexRest = ran.nextInt(); //get index for restaurant
        int indexOrder = ran.nextInt(); //get index for order
        //store order history in list 
        //orderList.add();
        return new Pair<>(indexRest, indexOrder);
    }
    
    public Pair<Integer> jolyneOrder(){
        int indexRest = ran.nextInt(); //get index for restaurant
        int indexOrder = ran.nextInt(); //get index for order
        //store order history in list 
        //orderList.add();
        return new Pair<>(indexRest, indexOrder);
    }
    
    public class Pair<T> { //generic class to pair index values
        private final T first;
        private final T second;
        
        public Pair(T first, T second){
            this.first = first;
            this.second = second;
        }
        
        public T first(){
            return first;
        }
        
        public T second(){
            return second;
        }
    }
       
}
