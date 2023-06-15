/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jojoland;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PearlJam {
    private List<orderList> waitingList;
    private List<orderList> orderProcessingList;
    private List<orderList> orders;
    private int dayNum;
    protected String selectedRestaurant;
    protected String[] menu;
    private HashMap<Customer, LocalDate> customerDiningHistory;
    String residentFilePath = "resources/residents.csv";
    loadFile loadSystemFile = new loadFile();
    ArrayList<resident> resident = loadSystemFile.loadresidentFromFile(residentFilePath);
    ArrayList<ArrayList<orderList>> residentOrderLists;
    //private ArrayList<orderList> orderList = new ArrayList<>();

    public PearlJam(String selectedRestaurant, int daynum, ArrayList<ArrayList<orderList>> residentOrderLists) {
        waitingList = new ArrayList<>();
        orderProcessingList = new ArrayList<>();
        orders = new ArrayList<>();
        this.selectedRestaurant = selectedRestaurant;
        customerDiningHistory = new HashMap<>();
        this.residentOrderLists = residentOrderLists;
        this.dayNum = daynum;
        sortOrdersWithinRestaurants();
    }
    
//    public void serveCustomers(Customer1[] customers) {
//        // Check if it's within the allowed serving time (13 PM to 14)
//        LocalTime currentTime = LocalTime.now();
//        LocalTime servingStartTime = LocalTime.of(22, 30);
//        LocalTime servingEndTime = LocalTime.of(23, 59);
//
//        if (currentTime.isBefore(servingStartTime) || currentTime.isAfter(servingEndTime)) {
//            System.out.println("Sorry, the restaurant is currently not serving customers.");
//            return;
//        } else {
//            System.out.println("Welcome!");
//        }
//            // Serve each customer
//        for (Customer1 customer : customers) {
//            // Check if the customer has already dined in today
//            if (hasDinedIn(customer)) {
//                System.out.println("Customer " + customer.getName() + " has already dined in today. Cannot serve again.");
//            } else {
//                // Add customer to the dining history
//                addDiningHistory(customer);
//                // Process the order for the customer
//                // Your code for processing the order goes here
//                customer.setHasDinedIn(true); // Set the hasDinedIn flag for the customer
//            }
//        }
//    }
//
//    private boolean hasDinedIn(Customer1 customer){
//        LocalDate today = LocalDate.now();
//        LocalDate diningDate = customerDiningHistory.get(customer);
//        return diningDate != null && diningDate.equals(today);
//    }
//    
//    private void addDiningHistory(Customer1 customer){
//        LocalDate today = LocalDate.now();
//        customerDiningHistory.put(customer, today);
//    }
    
    // Add customers to waiting list
    public List<orderList> getOrders(){
        for (int i = 0; i < resident.size(); i++) {
            ArrayList<orderList> orderList = residentOrderLists.get(i);
            //for(int j = 0; j < orderList.size(); j++){
                //if(orderList.get(orderList.size()).getDayNum() == dayNum){ //check for orders on current day
                    if(orderList.get(orderList.size()-1).getRestaurant().equals(selectedRestaurant)){ //only check for orders at selectedREstaurant
                        waitingList.add(orderList.get(orderList.size()-1));
                    }
                //}
            //}
        }
        return waitingList;
    }
    
    // Add a customer to the waiting list
    /*public void addToWaitingList(resident customer) {
            waitingList.add(customer);        
    }*/

    // Sort the waiting list by arrival time in ascending order
    public List<orderList> sortWaitingList() {
        waitingList = getOrders();
        Collections.sort(waitingList, Comparator.comparing(orderList::getArrivalTime));
        return waitingList;
    }
    
//    // Process the orders based on the restaurant's rule
//    public void setProcessOrders() {
//        orderProcessingList.clear();
//        orders.clear();
//
//        switch (selectedRestaurant) {
//            case "Jade Garden":
//                processJadeGardenOrders();
//                break;
//            case "Cafe Deux Magots":
//                processCafeDeuxMagotsOrders();
//                break;
//            case "Trattoria Trussardi":
//                processTrattoriaTrussardiOrders();
//                break;
//            case "Libeccio":
//                processLibeccioOrders();
//                break;
//            case "Savage Garden":
//                processSavageGardenOrders();
//                break;
//            default:
//                break;
//        }
//    }
    
    // Process orders for Jade Garden restaurant
    private void processJadeGardenOrders() {
        waitingList = sortWaitingList();
        int left = 0;
        int right = waitingList.size() - 1;

        while (left <= right) {
            orderProcessingList.add(waitingList.get(left));
            if (left != right) {
                orderProcessingList.add(waitingList.get(right));
            }
            left++;
            right--;
        }
    }

    // Process orders for Cafe Deux Magots restaurant
    private void processCafeDeuxMagotsOrders() {
        waitingList = sortWaitingList();
        List<orderList> orderedList = new ArrayList<>(waitingList);
        Collections.sort(orderedList, Comparator.comparing(orderList::getAge).reversed());

        int left = 0;
        int right = orderedList.size() - 1;

        while (left <= right) {
            orderProcessingList.add(orderedList.get(left));
            if (left != right) {
                orderProcessingList.add(orderedList.get(right));
            }
            left++;
            right--;
        }
    }

    // Process orders for Trattoria Trussardi restaurant
    private void processTrattoriaTrussardiOrders() {
        waitingList = sortWaitingList();
        List<orderList> males = new ArrayList<>();
        List<orderList> females = new ArrayList<>();
        List<orderList> unspecified = new ArrayList<>();

        // Categorize customers based on gender and age
        for (orderList customer : waitingList) {
            if (customer.getGender().equalsIgnoreCase("Male")) {
                males.add(customer);
            } else if (customer.getGender().equalsIgnoreCase("Female")) {
                females.add(customer);
            } else {
                unspecified.add(customer);
            }
        }

        // Process orders based on the rule
        while (!males.isEmpty() || !females.isEmpty()) {
            if (!males.isEmpty()) {
                orderList youngestMale = Collections.min(males, Comparator.comparing(orderList::getAge));
                orderList oldestMale = Collections.max(males, Comparator.comparing(orderList::getAge));

                orderProcessingList.add(youngestMale);
                orderProcessingList.add(oldestMale);

                males.remove(youngestMale);
                males.remove(oldestMale);
            }

            if (!females.isEmpty()) {
                orderList oldestFemale = Collections.max(females, Comparator.comparing(orderList::getAge));
                orderList youngestFemale = Collections.min(females, Comparator.comparing(orderList::getAge));

                orderProcessingList.add(oldestFemale);
                orderProcessingList.add(youngestFemale);

                females.remove(oldestFemale);
                females.remove(youngestFemale);
            }
        }

        // Add remaining unspecified customers to the order processing list
        orderProcessingList.addAll(unspecified);
    }

    // Process orders for Libeccio restaurant
    private void processLibeccioOrders() {
        waitingList = sortWaitingList();
        int index = dayNum - 1;

        while (!waitingList.isEmpty()) {
            int customerIndex = index % waitingList.size();
            orderList customer = waitingList.remove(customerIndex);
            orderProcessingList.add(customer);
        }
    }

    // Process orders for Savage Garden restaurant
    private void processSavageGardenOrders() {
        waitingList = sortWaitingList();
        int index = dayNum - 1;

        while (!waitingList.isEmpty()) {
            int customerIndex = index % waitingList.size();
            orderList customer = waitingList.remove(customerIndex);
            orderProcessingList.add(customer);
            index++;
        }
    }
    
    // Sort orders within each restaurant based on restaurant logic and arrival time
    public void sortOrdersWithinRestaurants() {
        waitingList = sortWaitingList();
        Map<String, List<orderList>> ordersByRestaurant = new HashMap<>();

        for (orderList order : waitingList) {
            String restaurant = order.getRestaurant();
            ordersByRestaurant.putIfAbsent(restaurant, new ArrayList<>());
            ordersByRestaurant.get(restaurant).add(order);
        }

        for (List<orderList> restaurantOrders : ordersByRestaurant.values()) {

            // Custom sorting based on restaurant logic
            String restaurantName = restaurantOrders.get(0).getRestaurant();
            switch (restaurantName) {
                case "Jade Garden":
                    // Sort Jade Garden orders based on specific logic
                    restaurantOrders.sort(Comparator.comparingInt(orderList -> orderList.getDayNum()));
                    processJadeGardenOrders();
                    break;
                case "Cafe Deux Magots":
                    // Sort Cafe Deux Magots orders based on specific logic
                    restaurantOrders.sort(Comparator.comparingInt(orderList -> orderList.getDayNum()));
                    processCafeDeuxMagotsOrders();
                    break;
                case "Trattoria Trussardi":
                    // Sort Trattoria Trussardi orders based on specific logic
                    restaurantOrders.sort(Comparator.comparingInt(orderList -> orderList.getDayNum()));
                    processTrattoriaTrussardiOrders();
                    break;
                case "Libeccio":
                    // Sort Libeccio orders based on specific logic
                    restaurantOrders.sort(Comparator.comparingInt(orderList -> orderList.getDayNum()));
                    processLibeccioOrders();
                    break;
                case "Savage Garden":
                    // Sort Savage Garden orders based on specific logic
                    restaurantOrders.sort(Comparator.comparingInt(orderList -> orderList.getDayNum()));
                    processSavageGardenOrders();
                    break;
                default:
                    // No specific sorting logic for unknown restaurants
                    break;
            }
        }
    }


    // Display the waiting list of the selected restaurant
    

    public void displayWaitingList() {
        waitingList = sortWaitingList();
        System.out.println("Waiting List for " + selectedRestaurant + ":");
        boolean found = false;
        for (orderList customer : waitingList) {
            if (customer.getRestaurant().equals(selectedRestaurant)) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("+----+--------------------+-----+--------+-");
            System.out.println("| No | Name               | Age | Gender |");
            System.out.println("+----+--------------------+-----+--------+-");
            int count = 1;
            for (orderList customer : waitingList) {
                if (customer.getRestaurant().equals(selectedRestaurant)) {
                    System.out.printf("| %-2d | %-18s | %-3s | %-6s |\n", count, customer.getName(), customer.getAge(), customer.getGender());
                    count++;
                }
            }
            System.out.println("+----+--------------------+-----+----------+-");
            System.out.println("-+----------------------------------------+");
            System.out.println("| Order                                  |");
            System.out.println("-+----------------------------------------+");
            for (orderList customer : waitingList) {
                System.out.printf("| %-38s |\n", customer.getFood());
            }
            System.out.println("-+-----------------------------------------+");
        } else {
            System.out.println("No customers in the waiting list for " + selectedRestaurant);
        }
    }

    // Display the order processing list of the selected restaurant
    //orderList customer = new customerPearlJam("John Doe", 30, "Male", 10, "Jade Garden", "Chicken Curry");

    public void displayOrderProcessingList() {
        System.out.println("Order Processing List for " + selectedRestaurant + ":");
        boolean found = false;
        for (orderList customer : orderProcessingList) {
            if (customer.getRestaurant().equals(selectedRestaurant)) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("+----+--------------------+-----+--------+-");
            System.out.println("| No | Name               | Age | Gender |");
            System.out.println("+----+--------------------+-----+--------+-");
            int count = 1;
            for (orderList customer : orderProcessingList) {
                if (customer.getRestaurant().equals(selectedRestaurant)) {
                    System.out.printf("| %-2d | %-18s | %-3s | %-6s |\n", count, customer.getName(), customer.getAge(), customer.getGender());
                    count++;
                }
            }
            System.out.println("+----+--------------------+-----+----------+-");
            System.out.println("-+----------------------------------------+");
            System.out.println("| Order                                  |");
            System.out.println("-+----------------------------------------+");
            for (orderList customer : orderProcessingList) {
                System.out.printf("| %-38s |\n", customer.getFood());
            }
            System.out.println("-+-----------------------------------------+");
        } else {
            System.out.println("No customers in the order processing list for " + selectedRestaurant);
        }
    }

}
