/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jojoland;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author 22004818
 */

//this class will be called in StartInterface after user views resident info (Basic feature 2) 
public class TheJoestars {
    private String currentLocation;
    private int dayNum;
    private int currDay;
    String residentFilePath = "resources/residents.csv";
    String standFilePath = "C:\\Users\\hp\\Downloads\\stands.csv";
    loadFile loadSystemFile = new loadFile();
    ArrayList<resident> resident = loadSystemFile.loadresidentFromFile(residentFilePath);
    ArrayList<ArrayList<orderList>> residentOrderLists;
    Scanner sc = new Scanner(System.in);
    
    public TheJoestars(String currentLocation, int dayNum, int currDay, ArrayList<ArrayList<orderList>> residentOrderLists){ 
        this.currentLocation = currentLocation;
        this.dayNum = dayNum;
        this.currDay = currDay;
        this.residentOrderLists = residentOrderLists;
        
        //after selecting [1] View Resident's Profile, enter name to view data
        boolean containName = false; //check if entered name is correct or not
        System.out.print("Enter the resident's name: ");
        String name = sc.nextLine();
        while(containName == false){
            System.out.println("===============================================================================");

            //view profile
            profile profile = new profile(name, currentLocation);
            containName = profile.residentProfile(residentFilePath, standFilePath);
            if(containName == false){
                System.out.println("Invalid name");
                System.out.print("Enter the resident's name: ");
                name = sc.nextLine();
            }
        }
        
        //prints order history for selected resident
        orderHistory order = new orderHistory(name,dayNum);
        order.printOrderHistory(residentOrderLists);
    }
}
