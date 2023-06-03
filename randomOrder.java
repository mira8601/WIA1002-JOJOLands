/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jojolands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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
        //added comment here to check the preferences first before getting all residents' order
        /*String residentFilePath = "C:\\Users\\hp\\Downloads\\residents.csv";
        loadFile loadSystemFile = new loadFile();
        ArrayList<resident> resident = loadSystemFile.loadresidentFromFile(residentFilePath);*/
        
        Pair<Integer> pair;
        name = "Jotaro Kujo"; //pre-added the name to check each preference first
        while(Days<dayNum){ //loop to generate order for each day
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
    
    public Pair<Integer> jonathanOrder() { //works now
        /*frequency between the foods he eats most and least should not exceed 1.*/
        
        int totalDays;
        boolean loop = true;
        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order

        if (!orderList.isEmpty()) {
            int maxcount = 0;
            int mincount = Integer.MAX_VALUE;
            List<Integer> maxIndices = new ArrayList<>(); //stores indices of orders with maxcount
            int nameIndex = -1;

            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.get(i).getName().equals(name)) {
                    totalDays = orderList.get(i).getTotalDays();
                    //System.out.println("Days:" + totalDays);
                    nameIndex = i;
                    if (totalDays > 1) { //needs at least 2 items in order to compare
                        List<String> ifoodList = orderList.get(i).getFoodList();

                        for (int j = 0; j < totalDays; j++) {
                            int count = 0; //count for frequency

                            for (int k = 0; k < totalDays; k++) {
                                if (ifoodList.get(j).equals(ifoodList.get(k))) {
                                    count++;
                                }
                            }

                            if (count > maxcount) { //new maxcount found, clear previous maxIndices
                                maxcount = count;
                                maxIndices.clear();
                                maxIndices.add(j);
                            } 
                            else if (count == maxcount) { //multiple orders with same maxcount
                                maxIndices.add(j);
                            }

                            if (count < mincount || mincount == 0) { //get mincount
                                mincount = count;
                            }
                        }
                    }
                }
            }

            if (nameIndex != -1) {
                while (loop) { //loops until preferences are met
                    boolean foundMatch = false;
                    if ((maxcount - mincount) >= 1) { //should not exceed 1
                        List<Integer> iorderList = orderList.get(nameIndex).getIndexOrderList();
                        List<Integer> irestList = orderList.get(nameIndex).getIndexRestList();

                        for (int l = 0; l < maxIndices.size(); l++) {
                            //Get the corresponding maxElement and maxRest values
                            int selectedMaxElement = iorderList.get(maxIndices.get(l));
                            int selectedMaxRest = irestList.get(maxIndices.get(l));
                            /*System.out.println("maxE: " + selectedMaxElement);
                            System.out.println("indexE: " + indexOrder);
                            System.out.println("maxR: " + selectedMaxRest);
                            System.out.println("indexR1: " + indexRest);*/
                            //Check if selectedMaxElement and selectedMaxRest are the same as indexOrder and indexRest
                            if (selectedMaxElement == indexOrder && selectedMaxRest == indexRest) {
                                foundMatch = true;
                                System.out.println("found");
                                break;
                            }
                        }
                    }
                    if (foundMatch) {
                        indexRest = ran.nextInt(5);
                        indexOrder = ran.nextInt(getBound(indexRest));
                        /*System.out.println("new order");
                        System.out.println("new: " + indexOrder);
                        System.out.println("new Rest: " + indexRest);*/
                    } else {
                        loop = false;
                    }
                }
            }
        }

        return new Pair<>(indexRest, indexOrder);
    }
    
    public Pair<Integer> josephOrder(){ // !!This method does not work, it still generates same food twice
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
    
    public Pair<Integer> jotaroOrder(){ //working on this method now
        /*try every dish at one restaurant 
          before moving on to the next.*/
        
        int totalDays;
        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order
        
        if(!orderList.isEmpty()){
            for(int i=0;i<orderList.size();i++){
                if(orderList.get(i).getName().equals(name)){
                    totalDays = orderList.get(i).getTotalDays();
                    //for(int j=0;j<totalDays;j++){
                    int lastIndexRest = orderList.get(i).getIndexRest(totalDays-1); //get last indexRest from orderList
                    indexRest = lastIndexRest;
                    System.out.println("last index: " + indexRest);
                    //List<Integer> restTried = new ArrayList<>();
                    //restTried.add(indexRest);
                    List<Integer> iorderList = orderList.get(i).getIndexOrderList();
                    //List<Integer> irestList = orderList.get(i).getIndexRestList();
                    if(iorderList.size() < getBound(indexRest)){ //will fix this later
                        boolean loop = true;
                        while(loop){
                            System.out.println("loop");
                            indexOrder = ran.nextInt(getBound(indexRest)); //get new index for order
                            if (!iorderList.contains(indexOrder)) {
                                loop = false;
                                break;
                            }
                        }

                    }
                    else{
                        indexRest = (lastIndexRest + 1) % 5; // Move to the next restaurant (assuming 5 restaurants)
                        /*indexRest = ran.nextInt(5); //get index for restaurant
                        for(int k=0;k<restTried.size();k++){
                            boolean loop = true;
                            while(loop){
                                loop = false;
                                if(restTried.get(k) == indexRest){
                                    indexRest = ran.nextInt(5); //get new index for restaurant
                                    loop = true;
                                }
                            }
                        }*/
                        indexOrder = ran.nextInt(getBound(indexRest)); //get index for order
                        iorderList.clear();

                    }
                        
                    //}
                }
            }
        }
        
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
