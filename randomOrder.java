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
    private String food;
    //random food selection algorithm
    Random ran = new Random();
    ArrayList<orderList> orderList = new ArrayList<>();
    
    public randomOrder(String name, int dayNum){
        this.name = name;
        this.dayNum = dayNum;
    }
    
    public ArrayList<orderList> randomOrderGenerator(){
        /*String residentFilePath = "C:\\Users\\hp\\Downloads\\residents.csv";
        loadFile loadSystemFile = new loadFile();
        ArrayList<resident> resident = loadSystemFile.loadresidentFromFile(residentFilePath);*/
        Pair<Integer> pair;
        if(orderList.isEmpty()){
            switch (name) {
                    case "Jonathan Joestar":
                            pair = jonathanOrder();
                            orderList = storeOrder(pair);
                        break;
                    case "Joseph Joestar":
                        pair = josephOrder();
                        orderList = storeOrder(pair);
                        //test run
                        System.out.println("Order= " + orderList.get(0).getFood());
                        break;
                    case "Jotaro Kujo":
                        while(orderList.size()<dayNum){
                            pair = jotaroOrder();
                            orderList = storeOrder(pair);
                        }
                        break;
                    case "Josuke Higashikata":
                        while(orderList.size()<dayNum){
                            pair = josukeOrder();
                            orderList = storeOrder(pair);
                        }
                        break;
                    case "Giorno Giovanna":
                        while(orderList.size()<dayNum){
                            pair = giornoOrder();
                            orderList = storeOrder(pair);
                        }
                        break;
                    case "Jolyne Cujoh":
                        while(orderList.size()<dayNum){
                            pair = jolyneOrder();
                            orderList = storeOrder(pair);
                        }
                        break;
                    default:
                        while(orderList.size()<dayNum){
                            pair = otherOrder();
                            orderList = storeOrder(pair);
                        }
                        break;
                }
        }
        else{
            while(orderList.get(0).getTotalDays()<dayNum){
            //for(int i=1;i<resident.size();i++){
                //name = resident.get(i).getName();
                switch (name) {
                    case "Jonathan Joestar":
                        while(orderList.size()<dayNum){
                            pair = jonathanOrder();
                            orderList = storeOrder(pair);
                        }
                        break;
                    case "Joseph Joestar":
                        pair = josephOrder();
                        orderList = storeOrder(pair);
                        //test run
                        for(int i=0;i<orderList.size();i++){
                            if(orderList.get(i).getName().equals(name)){
                                    System.out.println("Order= " + orderList.get(i).getFood());
                            }
                        }
                        break;
                    case "Jotaro Kujo":
                        while(orderList.size()<dayNum){
                            pair = jotaroOrder();
                            orderList = storeOrder(pair);
                        }
                        break;
                    case "Josuke Higashikata":
                        while(orderList.size()<dayNum){
                            pair = josukeOrder();
                            orderList = storeOrder(pair);
                        }
                        break;
                    case "Giorno Giovanna":
                        while(orderList.size()<dayNum){
                            pair = giornoOrder();
                            orderList = storeOrder(pair);
                        }
                        break;
                    case "Jolyne Cujoh":
                        while(orderList.size()<dayNum){
                            pair = jolyneOrder();
                            orderList = storeOrder(pair);
                        }
                        break;
                    default:
                        while(orderList.size()<dayNum){
                            pair = otherOrder();
                            orderList = storeOrder(pair);
                        }
                        break;
                }
            }
        }
        //}
        return orderList;
    }
    
    public Pair<Integer> jonathanOrder(){
        /*values fairness he doesn’t eat any food too frequently 
          or infrequently. Difference in frequency between the 
          foods he eats most and least should not exceed 1.*/
        int[] indexCompareFood;
        int[] totalDays;
        int maxcount = 0;
        int mincount = 0;
        int maxElement;
        int minElement;
        boolean loop = true;
        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order
        if(!orderList.isEmpty()){
            while(loop = true){ //loops until match preferences
                for(int i=0;i<orderList.size();i++){
                    if(orderList.get(i).getName().equals(name)){
                        //indexCompareFood = orderList.get(i).getIndexOrder(); //store food index to compare
                        totalDays = orderList.get(i).getDayNum();
                        if(totalDays.length > 1){ //needs at least 2 item in order to compare
                            for(int j=0;j<totalDays.length;j++){
                                int count = 0; //count for frequency
                                for(int k=0;k<totalDays.length;k++){
                                    if(orderList.get(i).getIndexOrder(j) == orderList.get(i).getIndexOrder(k)){
                                        count++;
                                    }
                                }
                                if(count > maxcount){ //get maxcount
                                    maxcount=count;
                                    maxElement = orderList.get(i).getIndexOrder(j);
                                }
                                if(count < mincount){ //get mincount
                                    mincount=count;
                                    minElement = orderList.get(i).getIndexOrder(j);
                                }
                            }
                        }
                        else{
                            break;
                        }

                    }
                }
                if((maxcount - mincount)>1){ //should not exceed 1
                    indexRest = ran.nextInt(5); //get new index for restaurant
                    indexOrder = ran.nextInt(getBound(indexRest)); //get new index for order
                }
                else{
                    loop = false; //if fits preferences, end loop
                }
            }
        }
        return new Pair<>(indexRest, indexOrder);
    }
    
    public Pair<Integer> josephOrder(){
        /*won’t eat the same food twice until he’s tried 
          everything currently available in JOJOLand’s*/
        int[] indexCompareFood;
        int[] indexCompareRest;
        int[] totalDays;
        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order
        if(dayNum < 26 && dayNum > 1){ //check if total days = total menu and total days more than 1
            for(int i=0;i<orderList.size();i++){
                if(orderList.get(i).getName().equals(name)){
                    //indexCompareFood = orderList.get(i).getIndexOrder();
                    //indexCompareRest = orderList.get(i).getIndexRest();
                    totalDays = orderList.get(i).getDayNum();
                    for(int j=0;j<totalDays.length;j++){
                        for(int k=0;k<totalDays.length;k++){
                            if(orderList.get(i).getIndexRest(j) == indexRest){
                                if(orderList.get(i).getIndexOrder(k) == indexOrder){ //check if order is similar
                                    indexRest = ran.nextInt(5); //get new index for restaurant
                                    indexOrder = ran.nextInt(getBound(indexRest)); //get new index for order
                                }
                            }
                        }
                    }
                }
            }
        }
        
        System.out.println("indexRest= " + indexRest);
        System.out.println("indexOrder= " + indexOrder);
        return new Pair<>(indexRest, indexOrder);
    }
    
    public Pair<Integer> jotaroOrder(){
        /*try every dish at one restaurant 
          before moving on to the next.*/
        
        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order
        //store order history in list 
        //orderList.add();
        return new Pair<>(indexRest, indexOrder);
    }
    
    public Pair<Integer> josukeOrder(){
        //tight weekly budget of $100
        
        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order
        //store order history in list 
        //orderList.add();
        return new Pair<>(indexRest, indexOrder);
    }
    
    public Pair<Integer> giornoOrder(){
        /*visits Trattoria Trussardi twice a week. orders
          different dish than last visit except when only
          1 option available*/
        
        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order
        //store order history in list 
        //orderList.add();
        return new Pair<>(indexRest, indexOrder);
    }
    
    public Pair<Integer> jolyneOrder(){
        /*avoid dining at the same restaurant twice in a row
          She and her father, Jotaro Kujo, always dine together 
          at same restaurant every Saturday.*/
        
        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order
        //store order history in list 
        //orderList.add();
        return new Pair<>(indexRest, indexOrder);
    }
    
    public Pair<Integer> otherOrder(){
        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order
        return new Pair<>(indexRest, indexOrder);
    }
    
    public ArrayList<orderList> storeOrder(Pair<Integer> pair){
        Menu menu = new Menu(pair.first);
        restaurant = menu.getRestaurant();
        food = menu.getFood(pair.second);
        System.out.println(food);
        /*for(int j=0;j<menuItem.size();j++){
            if((menuItem.get(j).getRestaurant()).equals(restaurant)){
                for(int k=0;k<menuItem.get(j).getIndexA();k++){
                    food[k] = menuItem.get(j).getName(k);
                }
                
            }
        }
        foodName = food[pair.second];*/
        if(orderList.isEmpty()){
            orderList.add(new orderList(name, dayNum, food, restaurant, pair.first, pair.second));
        }
        else{
            int length = orderList.size();
            for(int i=0;i<length;i++){
                if((orderList.get(i).getName()).equals(name)){
                    orderList.set(i, new orderList(name, dayNum, food, restaurant, pair.first, pair.second));
                }
                else{
                    orderList.add(new orderList(name, dayNum, food, restaurant, pair.first, pair.second));
                }
            }
        }
        return orderList;
    }
    
    public int getBound(int indexRest){ //get total menu for each restaurant for random bound num
        int bound = 0;
        switch (indexRest) {
            case 0:
                bound = 5;
                break;
            case 1:
                bound = 5;
                break;
            case 2:
                bound = 4;
                break;
            case 3:
                bound = 6;
                break;
            case 4:
                bound = 6;
                break;
            default:
                break;
        }
        return bound;
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
