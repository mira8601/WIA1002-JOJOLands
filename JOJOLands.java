/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jojolands;

import java.io.BufferedReader;
import java.io.File;
import java.io.*;
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
        
        //access file
        String residents = "C:\\Users\\hp\\Downloads\\residents.csv";
        String stands = "C:\\Users\\hp\\Downloads\\stands.csv";

        //need to pass location from task 1.
        location = "Joestar Mansion";
        //location needed to check the residents available at the location
        
        //after viewing resident info (task 2), user needs to choose [1] View Resident's Profile
        System.out.print("Select: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        if(choice==1){
            //after selecting [1] View Resident's Profile, enter name to view data
            System.out.print("Enter the resident's name: ");
            String name = sc.nextLine();  
            //System.out.println(name);
            System.out.println("=================================================================");

            profile profile = new profile(name);
            profile.residentProfile();
            //TheJoestars tj = new TheJoestars(name);
            //tj.profile(residents, stands);
            //tj.OrderHistory();
        }
        else{ //test only when wrong input
            System.out.println("Wrong input");
        }
        
        
    }
    
}
