/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jojolands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author 22004818
 */
public class randomOrder {
    private String name;
    private int dayNum;
    private String restaurant;
    private String food;
    private int Days = 0;
    //random food selection algorithm
    Random ran = new Random();
    ArrayList<orderList> orderList = new ArrayList<>();
    
    public randomOrder(int dayNum){
        this.dayNum = dayNum;
    }
    
    public ArrayList<orderList> randomOrderGenerator(){
        /*String residentFilePath = "C:\\Users\\hp\\Downloads\\residents.csv";
        loadFile loadSystemFile = new loadFile();
        ArrayList<resident> resident = loadSystemFile.loadresidentFromFile(residentFilePath);*/
        Pair<Integer> pair;
        name = "Joseph Joestar";
        while(Days<dayNum){
            //for(int i=1;i<resident.size();i++){ //loop through every resident
               // name = resident.get(i).getName();
                switch (name) {
                    case "Jonathan Joestar":
                        pair = jonathanOrder();
                        orderList = storeOrder(pair,name,Days);
                        break;
                    case "Joseph Joestar":
                        pair = josephOrder();
                        orderList = storeOrder(pair,name,Days);
                        break;
                    case "Jotaro Kujo":
                        pair = jotaroOrder();
                        orderList = storeOrder(pair,name,Days);
                        break;
                    case "Josuke Higashikata":
                        pair = josukeOrder();
                        orderList = storeOrder(pair,name,Days);
                        break;
                    case "Giorno Giovanna":
                        pair = giornoOrder();
                        orderList = storeOrder(pair,name,Days);
                        break;
                    case "Jolyne Cujoh":
                        pair = jolyneOrder();
                        orderList = storeOrder(pair,name,Days);
                        break;
                    default:
                        pair = otherOrder();
                        orderList = storeOrder(pair,name,Days);
                        break;
                }
                Days++;
            //}
        }
        return orderList;
    }
    
    public Pair<Integer> jonathanOrder(){
        /*values fairness he doesn’t eat any food too frequently 
          or infrequently. Difference in frequency between the 
          foods he eats most and least should not exceed 1.*/
        int totalDays;
        int maxcount = 0;
        int mincount = 0;
        int maxElement;
        int minElement;
        boolean loop = true;
        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order
        if(!orderList.isEmpty()){
            while(loop == true){ //loops until match preferences
                for(int i=0;i<orderList.size();i++){
                    if(orderList.get(i).getName().equals(name)){
                        //indexCompareFood = orderList.get(i).getIndexOrder(); //store food index to compare
                        totalDays = orderList.get(i).getTotalDays();
                        if(totalDays > 1){ //needs at least 2 item in order to compare
                            for(int j=0;j<totalDays;j++){
                                int count = 0; //count for frequency
                                for(int k=0;k<totalDays;k++){
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
    
    public Pair<Integer> josephOrder(){ // !!This method does not work, it still generates same food twice. Tried using chatGPT and still same
        /*won’t eat the same food twice until he’s tried 
          everything currently available in JOJOLand’s*/
        int sameRest;
        int sameOrder;
        int totalDays;
        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order
        if(!orderList.isEmpty()){ 
            for(int i=0;i<orderList.size();i++){
                if(orderList.get(i).getName().equals(name)){
                    totalDays = orderList.get(i).getTotalDays();
                    System.out.println(totalDays);
                    if(totalDays<27){ //check if total days is less than total menu
                        List<Integer> irestList = orderList.get(i).getIndexRestList();
                        List<Integer> iorderList = orderList.get(i).getIndexOrderList();
                        boolean isDuplicate = true;
                        do{
                            isDuplicate = false;
                            for(int j=0;j<totalDays;j++){
                                System.out.println(j);
                                if(irestList.get(j) == indexRest){
                                    for(int k=0;k<totalDays;k++){
                                        if(iorderList.get(k) == indexOrder){ //check if order is similar
                                            sameOrder = iorderList.get(k);
                                            sameRest = iorderList.get(j);
                                            duplicates(sameOrder, sameRest);
                                            indexRest = ran.nextInt(5); //get new index for restaurant
                                            indexOrder = ran.nextInt(getBound(indexRest)); //get new index for order
                                            System.out.println("new: " + indexOrder);
                                            isDuplicate = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }while(isDuplicate);
                    }
                    
                }
            }
        }
        //System.out.println("indexRest= " + indexRest);
        //System.out.println("indexOrder= " + indexOrder);
        return new Pair<>(indexRest, indexOrder);
    }

    public void duplicates(int sameOrder, int sameRest){ //for josephOrder to store duplicates
        List<int[]> dupl = new ArrayList<>();
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
    
    public ArrayList<orderList> storeOrder(Pair<Integer> pair, String name, int days){
        Menu menu = new Menu(pair.first);
        restaurant = menu.getRestaurant();
        food = menu.getFood(pair.second);
        //System.out.println(food);
        /*for(int j=0;j<menuItem.size();j++){
            if((menuItem.get(j).getRestaurant()).equals(restaurant)){
                for(int k=0;k<menuItem.get(j).getIndexA();k++){
                    food[k] = menuItem.get(j).getName(k);
                }
                
            }
        }
        foodName = food[pair.second];*/
        if(orderList.isEmpty()){
            orderList.add(new orderList(name, 1, food, restaurant, pair.first, pair.second));
        }
        else{
            int length = orderList.size();
            boolean personExists = false;
            for(int i=0;i<length;i++){
                if((orderList.get(i).getName()).equals(name)){
                    personExists = true;
                    orderList.get(i).addDay(orderList.get(i).getTotalDays()+1);
                    orderList.get(i).addFood(food);
                    orderList.get(i).addRest(restaurant);
                    orderList.get(i).addIndexRest(pair.first);
                    orderList.get(i).addIndexOrder(pair.second);
                }
            }
            if(!personExists){
                orderList.add(new orderList(name, 1, food, restaurant, pair.first, pair.second));
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
