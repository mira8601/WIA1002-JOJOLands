package jojoland;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author 22004818
 */

public class MoodyBlues {
    private int dayNum;
    private String currentLocation;
    private double totalSales = 0;
    private double price;
    Scanner sc = new Scanner(System.in);
    ArrayList<ArrayList<orderList>> residentOrderLists;
    List<Sale> sale = new ArrayList<>(); //stores sales by day
    List<List<Sale>> salesRecord = new ArrayList<>(); //stores the day index for sale
    
    public MoodyBlues(int dayNum, String currentLocation, ArrayList<ArrayList<orderList>> residentOrderLists){
        this.dayNum = dayNum;
        this.currentLocation = currentLocation;
        this.residentOrderLists = residentOrderLists;
    }
    
    public void salesInfo(boolean milagro, List<List<Sale>> salesRecord){
        if(milagro == true){
            this.salesRecord = salesRecord;
        }else{
            this.salesRecord = getSales();
        }
        
        boolean loop = true;
        while(loop){
            System.out.println("Restaurant: " + currentLocation);
            System.out.println("Sales Information");
            System.out.println("[1] View Sales");
            System.out.println("[2] View Aggregated Information");
            System.out.println("    [A] Minimum Sales");
            System.out.println("    [B] Maximum Sales");
            System.out.println("    [C] Top k Highest Sales");
            System.out.println("    [D] Total and Average Sales");
            System.out.println("[3] Exit");
            System.out.println("");
            System.out.print("Select: ");
            String choice = sc.nextLine();
            switch(choice){
                case "1":
                    sales();
                    break;
                case "2A":
                    minSales();
                    break;
                case "2B":
                    maxSales();
                    break;
                case "2C":
                    topK();
                    break;
                case "2D":
                    average();
                    break;
                case "3":
                    loop = false;
                    break;
                default:
                    System.out.println("Wrong input, please select again.");
                    break;
            }
        }
    }
    
    public List<List<Sale>> getSales(){ //get sales by day
        salesRecord = new ArrayList<>();
        int quantity = 1;
        if(salesRecord.size() != dayNum){
            for (int i = 0; i < dayNum; i++) { //create salesRecord for each day
                salesRecord.add(new ArrayList<>());
            }
        }
        
        for (int i = 0; i < residentOrderLists.size(); i++) {
            ArrayList<orderList> orderList = residentOrderLists.get(i);
            for(int j = 0; j < orderList.size(); j++){
                if(orderList.get(j).getRestaurant().equals(currentLocation)){
                    sale = salesRecord.get(j);
                    int found = 0;
                    if(!sale.isEmpty()){
                        for(int k = 0; k < sale.size(); k++){
                            if(sale.get(k).getFood().equals(orderList.get(j).getFood())){
                                quantity += sale.get(k).getQuantity();
                                price = orderList.get(j).getPrice() * quantity;
                                sale.set(k, (new Sale(orderList.get(j).getFood(), quantity, price, j+1)));
                                found = 1;
                            }
                        }
                    }
                    if(found == 0){
                        price = orderList.get(j).getPrice();
                        sale.add(new Sale(orderList.get(j).getFood(), quantity, price, j+1));
                    }
                    quantity = 1;
                }
            }    
        }
        return salesRecord;
    }
    
    public void sales(){
        System.out.print("Enter Day: ");
        int day = sc.nextInt();
        sc.nextLine();
        
        System.out.println("==========================================================================");
        System.out.println("Restaurant: " + currentLocation);
        System.out.println("Day " + day + " Sales");
        System.out.println("+------------------------------------------+------------+--------------+");
        System.out.println("| Food                                     | Quantity   | Total Price  |");
        System.out.println("+------------------------------------------+------------+--------------+");
        int found = 0;
        for (int i = 0; i < salesRecord.size(); i++) {
            sale = salesRecord.get(i);
            for(int j = 0; j < sale.size(); j++){
                if(sale.get(j).getDayNum() == day){
                    System.out.printf("| %-40s | %10d | $%11.2f |%n" ,sale.get(j).getFood(), sale.get(j).getQuantity(), sale.get(j).getPrice());
                    totalSales += sale.get(j).getPrice();
                    found = 1;
                }
            }
        }
        if(found == 0){
            System.out.println("| None                                     |          0 |           $0 |");
        }
        System.out.println("+------------------------------------------+------------+--------------+");
        System.out.printf("|                                           Total Sales | $%11.2f |%n", totalSales);
        System.out.println("+------------------------------------------+------------+--------------+");
        System.out.println("==========================================================================");
        totalSales = 0;
    }
    
    public void minSales(){
        double minSale = Integer.MAX_VALUE;
        int index = -1;
        
        System.out.print("Enter Start Day(min 1): ");
        int startDay = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Enter End Day(max " + dayNum + "): ");
        int endDay = sc.nextInt();
        sc.nextLine();
        
        List<Sale> min = aggregated(startDay, endDay);
                
        System.out.println("==========================================================================");
        System.out.println("Restaurant: " + currentLocation);
        System.out.println("Minimum Sales (Day  " + startDay + " - " + endDay + ")");
        System.out.println("+------------------------------------------+------------+--------------+");
        System.out.println("| Food                                     | Quantity   | Total Price  |");
        System.out.println("+------------------------------------------+------------+--------------+");
        
        for(int i = 0; i < min.size(); i++){
            if(min.get(i).getPrice() < minSale){
                minSale = min.get(i).getPrice();
                index = i;
            }
        }
        
        System.out.printf("| %-40s | %10d | $%11.2f |%n" ,min.get(index).getFood(), min.get(index).getQuantity(), min.get(index).getPrice());
        System.out.println("+------------------------------------------+------------+--------------+");
        System.out.printf("|                                         Minimum Sales | $%11.2f |%n", min.get(index).getPrice());
        System.out.println("+------------------------------------------+------------+--------------+");
        System.out.println("==========================================================================");
        
    }
    
    public void maxSales(){
        double maxSale = -1;
        int index = -1;
        
        System.out.print("Enter Start Day: ");
        int startDay = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Enter End Day: ");
        int endDay = sc.nextInt();
        sc.nextLine();
        
        List<Sale> max = aggregated(startDay, endDay);
        
        System.out.println("==========================================================================");
        System.out.println("Restaurant: " + currentLocation);
        System.out.println("Maximum Sales (Day  " + startDay + " - " + endDay + ")");
        System.out.println("+------------------------------------------+------------+--------------+");
        System.out.println("| Food                                     | Quantity   | Total Price  |");
        System.out.println("+------------------------------------------+------------+--------------+");
        
        for(int i = 0; i < max.size(); i++){
            if(max.get(i).getPrice() > maxSale){
                maxSale = max.get(i).getPrice();
                index = i;
            }
        }
        
        System.out.printf("| %-40s | %10d | $%11.2f |%n" ,max.get(index).getFood(), max.get(index).getQuantity(), max.get(index).getPrice());
        System.out.println("+------------------------------------------+------------+--------------+");
        System.out.printf("|                                         Minimum Sales | $%11.2f |%n", max.get(index).getPrice());
        System.out.println("+------------------------------------------+------------+--------------+");
        System.out.println("==========================================================================");
        
    }
    
    public void topK(){
        System.out.print("Enter Start Day(min 1): ");
        int startDay = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Enter End Day(max " + dayNum + "): ");
        int endDay = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Enter k: ");
        int k = sc.nextInt();
        sc.nextLine();
        
        List<Sale> topk = aggregated(startDay, endDay);
        // Sort the agg list based on the price field in descending order
        Collections.sort(topk, (s1, s2) -> Double.compare(s2.getPrice(), s1.getPrice()));

        System.out.println("==========================================================================");
        System.out.println("Restaurant: " + currentLocation);
        System.out.println("Top " + k + " Highest Sales (Day  " + startDay + " - " + endDay + ")");
        System.out.println("+------------------------------------------+------------+--------------+");
        System.out.println("| Food                                     | Quantity   | Total Price  |");
        System.out.println("+------------------------------------------+------------+--------------+");
        totalSales = 0;
        int count = Math.min(k, topk.size());
        for (int i = 0; i < count; i++) {
            System.out.printf("| %-40s | %10d | $%11.2f |%n" ,topk.get(i).getFood(), topk.get(i).getQuantity(), topk.get(i).getPrice());
            totalSales += topk.get(i).getPrice(); 
        }
        System.out.println("+------------------------------------------+------------+--------------+");
        System.out.printf("|                                   Top %d highest Sales | $%11.2f |%n", k,totalSales);
        System.out.println("+------------------------------------------+------------+--------------+");
        System.out.println("==========================================================================");
        totalSales = 0;
    }
    
    public void average(){
        double avgSales;
        totalSales = 0;
        
        System.out.print("Enter Start Day(min 1): ");
        int startDay = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Enter End Day(max " + dayNum + "): ");
        int endDay = sc.nextInt();
        sc.nextLine();
        
        List<Sale> avg = aggregated(startDay, endDay);
        
        System.out.println("==========================================================================");
        System.out.println("Restaurant: " + currentLocation);
        System.out.println("Total and Average Sales (Day  " + startDay + " - " + endDay + ")");
        System.out.println("+------------------------------------------+------------+--------------+");
        System.out.println("| Food                                     | Quantity   | Total Price  |");
        System.out.println("+------------------------------------------+------------+--------------+");
        
       
        for(int j = 0; j < avg.size(); j++){
            System.out.printf("| %-40s | %10d | $%11.2f |%n" ,avg.get(j).getFood(), avg.get(j).getQuantity(), avg.get(j).getPrice());
            totalSales += avg.get(j).getPrice();
        }
        
        avgSales = totalSales/(endDay-startDay+1);
                    
        System.out.println("+------------------------------------------+------------+--------------+");
        System.out.printf("|                                           Total Sales | $%11.2f |%n", totalSales);
        System.out.printf("|                                         Average Sales | $%11.2f |%n", avgSales);
        System.out.println("+------------------------------------------+------------+--------------+");
        System.out.println("==========================================================================");
        totalSales = 0;
    }
    
    public List<Sale> aggregated(int startDay, int endDay){
        List<Sale> agg = new ArrayList<>(); //stores sales for aggregated information
        int quantity;
        for (int i = startDay-1; i <= endDay-1; i++) {
            sale = salesRecord.get(i);
            for(int j = 0; j < sale.size(); j++){
                int found = 0;
                if(!agg.isEmpty()){
                    for(int k = 0; k < agg.size(); k++){
                        if(sale.get(j).getFood().equals(agg.get(k).getFood())){
                            quantity = sale.get(j).getQuantity() + agg.get(k).getQuantity();
                            price = sale.get(j).getPrice() + agg.get(k).getPrice();
                            agg.set(k, (new Sale(sale.get(j).getFood(), quantity, price, i+1)));
                            found = 1;
                        }
                    }
                }
                if(found == 0){
                    agg.add(sale.get(j));
                }
            }
        }
        return agg;
    }
}