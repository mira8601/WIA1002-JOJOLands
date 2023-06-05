/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jojolands;

import java.io.BufferedReader;
import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hp
 */
public class JOJOLands {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String location;
        int dayNum;
        
        //access file
        String residentFilePath = "C:\\Users\\hp\\Downloads\\residents.csv";
        String standFilePath = "C:\\Users\\hp\\Downloads\\stands.csv";

        //need to pass location from task 1.
        location = "Joestar Mansion";
        //location needed to check the residents available at the location
        
        //need to pass day number from task 1.
        dayNum = 21; //pre-added to test coding only
        
        //after viewing resident info (task 2), user needs to choose [1] View Resident's Profile
        System.out.print("Select: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        if(choice==1){
            //after selecting [1] View Resident's Profile, enter name to view data
            boolean containName = false; //check if entered name is correct or not
            System.out.print("Enter the resident's name: ");
            String name = sc.nextLine();
            while(containName == false){
                System.out.println("===============================================================================");

                profile profile = new profile(name);
                containName = profile.residentProfile(residentFilePath, standFilePath);
                if(containName == false){
                    System.out.println("Invalid name");
                    System.out.print("Enter the resident's name: ");
                    name = sc.nextLine();
                }
            }
            randomOrder ro = new randomOrder(dayNum);
            ArrayList<ArrayList<orderList>> residentOrderLists = ro.randomOrderGenerator();
            orderHistory order = new orderHistory(name,dayNum);
            order.printOrderHistory(residentOrderLists);
            
        }
        else{ //test only when wrong input
            System.out.println("Wrong input");
        }
        
        
    }
    
}
