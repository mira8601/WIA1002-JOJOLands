package jojoland;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MilagroMan {

    private int dayNum;
    private String currentLocation;
    private double totalSales = 0;
    private double price;
    private Scanner sc = new Scanner(System.in);
    private ArrayList<ArrayList<orderList>> residentOrderLists;
    private List<List<Sale>> salesRecord = new ArrayList<>(); // stores the day index for sale
    private List<List<Sale>> originalSalesRecord; // stores the original sales records
    MoodyBlues moodyBlues;

    public MilagroMan(int dayNum, String currentLocation, ArrayList<ArrayList<orderList>> residentOrderLists) {
        this.dayNum = dayNum;
        this.currentLocation = currentLocation;
        this.residentOrderLists = residentOrderLists;
        moodyBlues = new MoodyBlues(dayNum, currentLocation, residentOrderLists);
        // Create a backup of the original sales records
        originalSalesRecord = new ArrayList<>(salesRecord);
    }

    public void milagroManMode() {
        
        boolean milagro = false;
        boolean milagroManLoop = true;

        while (milagroManLoop) {
            System.out.println("======================================================================");
            System.out.println("Restaurant: " + currentLocation + " (Milagro Man Mode)");
            System.out.println("[1] Modify Food Prices");
            System.out.println("[2] View Sales Information");
            System.out.println("[3] Exit Milagro Man");
            System.out.println("");
            System.out.print("Select: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    salesRecord = modifyFoodPrices();
                    milagro = true;
                    break;
                case "2":
                    moodyBlues.salesInfo(milagro, salesRecord);
                    break;
                case "3":
                    restoreSalesRecords();
                    milagroManLoop = false;
                    break;
                default:
                    System.out.println("Wrong input, please select again.");
                    break;
            }
        }
    }

    private List<List<Sale>> modifyFoodPrices() {
        System.out.println("Enter food name: ");
        String foodName = sc.nextLine();

        System.out.println("Enter new price: ");
        double newPrice = Double.parseDouble(sc.nextLine());

        System.out.println("Enter start day: ");
        int startDay = Integer.parseInt(sc.nextLine());

        System.out.println("Enter end day: ");
        int endDay = Integer.parseInt(sc.nextLine());

        salesRecord = moodyBlues.getSales();
        for (List<Sale> daySales : salesRecord) {
            for (Sale sale : daySales) {
                int saleDayNum = sale.getDayNum();
                if (saleDayNum >= startDay && saleDayNum <= endDay) {
                    // Update the price for the corresponding sale
                    if (sale.getFood().equalsIgnoreCase(foodName)) {
                        double newTotal = sale.getQuantity() * newPrice;
                        sale.setPrice(newTotal);
                    }
                } else if (saleDayNum > endDay) {
                    // Break the loop as we have updated prices for all relevant records
                    break;
                }
            }
        }

        System.out.println("Food prices modified successfully!");
        return salesRecord;
    }

    private void restoreSalesRecords() {
        salesRecord.clear();
        salesRecord.addAll(originalSalesRecord);
    }
}


