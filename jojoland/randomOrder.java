/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jojoland;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import static jojoland.StartInterface.currentLocation;

/**
 *
 * @author 22004818
 */

public class randomOrder {
    private String name;
    private int dayNum;
    private String restaurant;
    private String food;
    private double price;
    private int residentIndex = -1;
    private int indexRestSat = Integer.MAX_VALUE;
    //random food selection algorithm
    Random ran = new Random();
    ArrayList<orderList> orderList = new ArrayList<>();
    String residentFilePath = "resources/residents.csv";
    loadFile loadSystemFile = new loadFile();
    ArrayList<resident> resident = loadSystemFile.loadresidentFromFile(residentFilePath);
    ArrayList<ArrayList<orderList>> residentOrderLists = new ArrayList<>(); //stores each residents' order
    
    /*public randomOrder(int dayNum){
        this.dayNum = dayNum;
    }*/
    
    public ArrayList<ArrayList<orderList>> randomOrderGenerator(int dayNum, int currDay){
        Pair<Integer> pair;
        //name = "Jonathan Joestar"; //pre-added the name to check each preference first
        if(residentOrderLists.size() != resident.size()){
            for (int i = 0; i < resident.size(); i++) { //create residentOrderLists for every resident
                residentOrderLists.add(new ArrayList<>());
            }
        }
        
        while(currDay!=dayNum){ //loop to generate order for each day
            for (int i = 0; i < resident.size(); i++) { //get name
                name = resident.get(i).getName();
                residentIndex = i;
                switch (name) {
                    case "Jonathan Joestar":
                        pair = jonathanOrder();
                        orderList = storeOrder(pair,name,currDay);
                        break;
                    case "Joseph Joestar":
                        pair = josephOrder();
                        orderList = storeOrder(pair,name,currDay);
                        break;
                    case "Jotaro Kujo":
                        pair = jotaroOrder(currDay);
                        if(pair.first != 3 && pair.first != 4){
                            indexRestSat = pair.first;
                        }else{
                            indexRestSat = Integer.MAX_VALUE; // resets
                        }
                        orderList = storeOrder(pair,name,currDay);
                        break;
                    case "Josuke Higashikata":
                        pair = josukeOrder();
                        orderList = storeOrder(pair,name,currDay);
                        break;
                    case "Giorno Giovanna":
                        pair = giornoOrder();
                        orderList = storeOrder(pair,name,currDay);
                        break;
                    case "Jolyne Cujoh":
                        pair = jolyneOrder(currDay);
                        if(indexRestSat == Integer.MAX_VALUE){
                            indexRestSat = pair.first;
                        }
                        orderList = storeOrder(pair,name,currDay);
                        break;
                    default:
                        pair = otherOrder();
                        orderList = storeOrder(pair,name,currDay);
                        break;
                }
            }
            currDay++;
        }
        return residentOrderLists;
    }
    
    public Pair<Integer> jonathanOrder() { 
        //frequency between the foods he eats most and least should not exceed 1.
        
        boolean loop = true;
        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order
        if (residentIndex != -1) { //orderList for Jonathan not empty
            orderList = residentOrderLists.get(residentIndex); //get the index for orderList
            int maxcount = 0;
            int mincount = Integer.MAX_VALUE;
            List<Integer> maxIndices = new ArrayList<>(); //stores indices of orders with maxcount

            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.size() > 1) { //needs at least 2 items in order to compare
                    int count = 0; //count for frequency

                    for (int j = 0; j < orderList.size(); j++) {
                        if (orderList.get(i).getFood().equals(orderList.get(j).getFood())) {
                            count++;
                        }

                        if (count > maxcount) { //new maxcount found, clear previous maxIndices
                            maxcount = count;
                            maxIndices.clear();
                            maxIndices.add(i);
                        } 
                        else if (count == maxcount) { //multiple orders with same maxcount
                            maxIndices.add(i);
                        }

                        if (count < mincount || mincount == 0) { //get mincount
                            mincount = count;
                        }
                    }
                }
            }
            while (loop) { //loops until preferences are met
                boolean foundMatch = false;
                if ((maxcount - mincount) >= 1) { //should not exceed 1
                    for (int l = 0; l < maxIndices.size(); l++) {
                        //Get the corresponding maxElement and maxRest values
                        int selectedMaxElement = orderList.get(maxIndices.get(l)).getIndexOrder();
                        int selectedMaxRest = orderList.get(maxIndices.get(l)).getIndexRest();
                        //Check if selectedMaxElement and selectedMaxRest are the same as indexOrder and indexRest
                        if (selectedMaxElement == indexOrder && selectedMaxRest == indexRest) {
                            foundMatch = true;
                            break;
                        }
                    }
                }
                if (foundMatch) {
                    indexRest = ran.nextInt(5);
                    indexOrder = ran.nextInt(getBound(indexRest));
                } else {
                    loop = false;
                }
            }
        }

        return new Pair<>(indexRest, indexOrder);
    }
    
    public Pair<Integer> josephOrder(){ 
        /*won’t eat the same food twice until he’s tried 
          everything currently available in JOJOLand’s*/
        
        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order
        if (residentIndex != -1) { //orderList for Joseph not empty
            orderList = residentOrderLists.get(residentIndex); //get the index for orderList
            if(orderList.size()<27){ //check if total days is less than total menu
                boolean isDuplicate;
                do{
                    isDuplicate = false;
                    for(int j=0;j<orderList.size();j++){
                        if(orderList.get(j).getIndexRest() == indexRest){
                            for(int k=0;k<orderList.size();k++){
                                if(orderList.get(j).getIndexOrder() == indexOrder){ //check if order is similar
                                    indexRest = ran.nextInt(5); //get new index for restaurant
                                    indexOrder = ran.nextInt(getBound(indexRest)); //get new index for order
                                    isDuplicate = true;
                                    break;
                                }
                            }
                        }
                    }
                }while(isDuplicate);
            }
        }
        return new Pair<>(indexRest, indexOrder);
    }
    
    public Pair<Integer> jotaroOrder(int currDay){ 
        /*try every dish at one restaurant 
          before moving on to the next.*/
        
        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order
        if (residentIndex != -1) { //orderList for Jotaro not empty
            orderList = residentOrderLists.get(residentIndex); //get the index for orderList
            if (!orderList.isEmpty()) {
                if(currDay%6!=0){
                    int lastIndexRest = orderList.get(orderList.size()-1).getIndexRest(); //get last indexRest from orderList
                    indexRest = lastIndexRest;
                    indexOrder = ran.nextInt(getBound(indexRest)); //get new index for order
                    int count = 0;
                    List<Integer> found = new ArrayList<>();
                    for(int i=0;i<orderList.size();i++){
                        if(orderList.get(i).getIndexRest() == indexRest){
                            found.add(i); //add index for indexRest
                            count++;
                        }
                    }
                    if(count > getBound(indexRest)){ //if second time to the restaurant
                        count = count - getBound(indexRest); //get balance
                    }
                    if(count < getBound(indexRest)){ //haven't tried every menu at the restaurant yet
                        boolean loop;
                        do{
                            loop = false;
                            for(int i=0;i<found.size();i++){
                                if (orderList.get(found.get(i)).getIndexOrder() == indexOrder) { //check if food already tried
                                    indexOrder = ran.nextInt(getBound(indexRest)); //get new index for order
                                    loop = true;
                                    break;
                                }
                            }
                        }while(loop);
                    }
                    else{
                        indexRest = (lastIndexRest + 1) % 5; // Move to the next restaurant (5 restaurants)
                        indexOrder = ran.nextInt(getBound(indexRest)); //get new index for order
                    }
                }else{
                    indexRest = indexRestSat; //
                    indexOrder = ran.nextInt(getBound(indexRest)); //get new index for order
                }
            }
        }
        return new Pair<>(indexRest, indexOrder);
    }
    
    public Pair<Integer> josukeOrder(){
        //tight weekly budget of $100, can borrow least amount of money if overspent
        
        double budget = 100;
        double moneySpent = 0;
        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order
        
        if (residentIndex != -1) { //orderList for Josuke not empty
            orderList = residentOrderLists.get(residentIndex); //get the index for orderList
            if (!orderList.isEmpty()) {
                int j = Math.min(orderList.size(), 7);
                for (int i = j; i < orderList.size(); i++) {
                    moneySpent += orderList.get(i).getPrice();
                }
                
                Menu menu = new Menu(indexRest);
                moneySpent += menu.getPrice(indexOrder); //get current index's price
                
                while(moneySpent>budget){
                    int[] restArr = {2, 3, 4}; //get the restaurant with cheapest menu
                    indexRest = restArr[ran.nextInt(restArr.length)]; //get index for restaurant
                    List<Integer> foodArr = new ArrayList<>();
                    switch(indexRest){ //get cheepest food from the restaurant
                        case 2:
                            foodArr.clear();
                            foodArr.add(1);
                            break;
                        case 3:
                            foodArr.clear();
                            foodArr.add(1);
                            foodArr.add(2);
                            foodArr.add(5);
                            break;
                        case 4:
                            foodArr.clear();
                            foodArr.add(0);
                            foodArr.add(2);
                            foodArr.add(4);
                            foodArr.add(5);
                            break;
                        default:
                            break;
                    }
                    indexOrder = foodArr.get(ran.nextInt(foodArr.size()));
                    moneySpent = orderList.get(j).getPrice();
                }
            }
        }
        return new Pair<>(indexRest, indexOrder);
    }
    
    public Pair<Integer> giornoOrder(){ 
        /*visits Trattoria Trussardi twice a week. orders
          different dish than last visit except when only
          1 option available*/
        
        int visited = 0;
        int indexVisited = -1;
        int weekVisits = 0;
        int count = 0;
        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order
        
        if (residentIndex != -1) { //orderList for Giorno not empty
            orderList = residentOrderLists.get(residentIndex); //get the index for orderList
            if (!orderList.isEmpty()) {
                for(int i=0;i<orderList.size();i++){
                    count++;
                    if(orderList.get(i).getIndexRest() == 2){ //if he visited Trattoria Trussardi
                        visited++; 
                        indexVisited = i; //get last index where indexRest = 2
                        weekVisits++;
                    }
                    if(count == 8){
                        count = 0;
                        weekVisits = 0;
                    }
                }
                
                //if it's not his first visit, indexOrder should be different than last visit
                if(indexRest == 2){
                    if(visited>=1){
                        boolean loop;
                        do {
                            loop = false;
                            if (indexOrder == orderList.get(indexVisited).getIndexOrder()) {
                                indexOrder = ran.nextInt(getBound(indexRest));
                                loop = true;
                            }
                        } while (loop);
                    }
                }
                
                count=0;
                int weekSize = 7; //number of days in each week
                int totalWeeks = (orderList.size() + weekSize - 1) / weekSize; //calculate the total number of weeks

                for (int week = 0; week < totalWeeks; week++) {
                    int startIndex = week * weekSize; //starting index of the week
                    int endIndex = Math.min(startIndex + weekSize, orderList.size()); //ending index of the week (considering the remaining days)

                    boolean isFinalWeek = (week==totalWeeks-1); //check to get most recent week
                    
                    if (isFinalWeek) {
                        //loop from the first day of the final week until the last day in orderList
                        for (int i = startIndex; i < orderList.size(); i++) {
                            count++;
                            if(count==5){ //fifth day but haven't visited Trattoria Trussardi yet
                                if(weekVisits==0){
                                    indexRest = 2; //last 2 days has to be a visit to Trattoria Trussardi
                                    indexOrder = ran.nextInt(getBound(indexRest));
                                }
                            }
                            if(count==6){
                                if(weekVisits==1){
                                    indexRest = 2; //last day has to be a visit to Trattoria Trussardi
                                    indexOrder = ran.nextInt(getBound(indexRest));
                                    boolean loop;
                                    do{
                                        loop = false;
                                        if(orderList.get(indexVisited).getIndexOrder() == indexOrder){
                                            indexOrder = ran.nextInt(getBound(indexRest));
                                            loop=true;
                                        }
                                    }while(loop);
                                }
                            }
                        }
                    }
                }
            }
        }
        return new Pair<>(indexRest, indexOrder);
    }
    
    public Pair<Integer> jolyneOrder(int currDay){
        /*avoid dining at the same restaurant twice in a row
          She and her father, Jotaro Kujo, always dine together 
          at same restaurant every Saturday.*/
        
        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order

	if (residentIndex != -1) { //orderList for Jolyne not empty
            orderList = residentOrderLists.get(residentIndex); //get the index for orderList
            if(currDay%6!=0){ //if the day is not Saturday
                //if the previous restaurant is same as the current generated one
                boolean loop;
                do{
                    loop = false;
                    if(orderList.get(orderList.size()-1).getIndexRest() == indexRest){
                        indexRest = ran.nextInt(5);
                        indexOrder = ran.nextInt(getBound(indexRest));
                        loop=true;
                    }
                }while(loop);
            }else{ //if the day is Saturday
                if(indexRestSat!=Integer.MAX_VALUE){
                    indexRest = indexRestSat;	//if there's a restaurant for Jotaro on Saturday, make it same for Jolyne
                    indexOrder = ran.nextInt(getBound(indexRest));
                }else{
                    indexRestSat = indexRest;   	//if restaurant for Jotaro hasn't been assigned, assign Jolyne's one to Jotaro so that both of them are same
                    indexOrder = ran.nextInt(getBound(indexRest));
                }
            }		
	}
        return new Pair<>(indexRest, indexOrder);
}
    
    public Pair<Integer> otherOrder(){
        int indexRest = ran.nextInt(5); //get index for restaurant
        int indexOrder = ran.nextInt(getBound(indexRest)); //get index for order
        return new Pair<>(indexRest, indexOrder);
    }
    
    public ArrayList<orderList> storeOrder(Pair<Integer> pair, String name, int days){ //gets items from Menu and store to orderList
        Menu menu = new Menu(pair.first);
        restaurant = menu.getRestaurant();
        food = menu.getFood(pair.second);
        price = menu.getPrice(pair.second);
        
        for (int i = 0; i < resident.size(); i++) {
            if (resident.get(i).getName().equals(name)) {
                residentIndex = i;
                break;
            }
        }
        if (residentIndex != -1) {
            orderList = residentOrderLists.get(residentIndex);
             // Generate a random arrival time between 1 and 30
            int arrivalTime = ran.nextInt(59) + 1;
            orderList.add(new orderList(name, resident.get(residentIndex).getAge(), resident.get(residentIndex).getGender(), arrivalTime, days+1, food, restaurant, pair.first, pair.second, price));
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
