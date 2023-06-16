/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jojoland;

/**
 *
 * @author hp
 */
public class Sale{
    private String food;
    private int quantity;
    private double price;
    private int dayNum;

    public Sale(String food, int quantity, double price, int dayNum){
        this.food = food;
        this.quantity = quantity;
        this.price = price;
        this.dayNum = dayNum;
    }

    public String getFood() {
        return food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(double newPrice){ //for Milagro Man
        price = newPrice;
    }

    public double getPrice(){
        return price;
    }

    public int getDayNum() {
        return dayNum;

    }
}
