/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jojoland;

import java.util.ArrayList;
import static jojoland.StartInterface.currentLocation;

public class orderHistory {
    private String name;
    private int dayNum;
    String residentFilePath = "resources/residents.csv";
    loadFile loadSystemFile = new loadFile();
    ArrayList<resident> resident = loadSystemFile.loadresidentFromFile(residentFilePath);
    
    public orderHistory(String name, int dayNum){
        this.name = name;
        this.dayNum = dayNum;
    }
    
    public void printOrderHistory(ArrayList<ArrayList<orderList>> residentOrderLists) {
        System.out.println("");
        System.out.println("Order History");
        System.out.println("+------+------------------------------------------+----------------------+");
        System.out.println("| Day  | Food                                     | Restaurant           |");
        System.out.println("+------+------------------------------------------+----------------------+");
        int residentIndex = -1;
        for (int i = 0; i < resident.size(); i++) {
            if (resident.get(i).getName().equals(name)) {
                residentIndex = i;
                break;
            }
        }
        ArrayList<orderList> orderList = residentOrderLists.get(residentIndex);
        for (int i = 0; i < orderList.size(); i++) {
            System.out.printf("| %-2d   | %-40s | %-20s |%n", i+1, orderList.get(i).getFood(), orderList.get(i).getRestaurant());
        }
        System.out.println("+------+------------------------------------------+----------------------+");
        System.out.println("===============================================================================");
    }
}
