/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jojolands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

/**
 *
 * @author 22004818
 */
public class TheJoestars {
    private String name;
    
    public TheJoestars(String name){
        this.name = name;
    }
    
   public void profile(String residents, String stands){
       BufferedReader reader = null;
       BufferedReader reader2 = null;
       String line = "";
       try{
           //read file residents
           reader = new BufferedReader(new FileReader(residents));
            
           while((line = reader.readLine()) != null) {
               //split values according to collumn
               String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
               if((values[0]).equals("George Joestar II")
                       ||(values[0]).equals("Giorno Giovanna") 
                       ||(values[0]).equals("Holy Kujo")
                       ||(values[0]).equals("Jonathan Joestar")
                       ||(values[0]).equals("Joseph Joestar")
                       ||(values[0]).equals("Josuke Higashikata")
                       ||(values[0]).equals("Jotaro Kujo")){
                   values[4] = values[4] + ", " + values[5];
               }
               //values[4] = values[4] + ", " + values[5];
               //search name
               if((values[0]).equals(name)){
                    System.out.println(name + "'s Profile");
                    System.out.println("Name\t: " + values[0] + "\n" 
                        + "Age\t: " + values[1] + "\n" 
                        + "Gender\t: " + values[2] + "\n"
                        + "Parents\t: " + values[4]);
               }
           }
           
           //read file stands
           reader2 = new BufferedReader(new FileReader(stands));
            
           while((line = reader2.readLine()) != null) {
               //split values according to collumn
               String[] values2 = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"); 
               //search name
               if((values2[1]).equals(name)){
                    System.out.println("Stand\t\t\t: " + values2[0] + "\n" 
                        + "Destructive Power\t: " + values2[2] + "\n" 
                        + "Speed\t\t\t: " + values2[3] + "\n"
                        + "Range\t\t\t: " + values2[4] + "\n" 
                        + "Stamina\t\t\t: " + values2[5] + "\n" 
                        + "Precision\t\t: " + values2[6] + "\n" 
                        + "Development Potential\t: " + values2[7]);
               }
           }
       }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
   }
   
   public void OrderHistory(){
       //has to match with restaurant sales record and order list from task 2
       System.out.println();
       System.out.println("Order History");
       
       System.out.println("+------+-------------------------------+--------------+");
   }
    
    
}
