/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jojolands;

/**
 *
 * @author 22004818
 */
public class Menu {
    private int restaurant;
    private String selectedRestaurant;
    private MenuItem[] menuItem;
    
    public void Menu(int restaurant){ //pass index for restaurant and food from randomOrder class
        this.restaurant = restaurant;
        setMenu();
    }
    
     public void setMenu() {
        switch (restaurant) {
            case 1:
                selectedRestaurant = "Jade Garden";
                menuItem = new MenuItem[]{
                    new MenuItem("Braised Chicken in Black Bean Sauce", 15.00),
                    new MenuItem("Braised Goose Web with Vermicelli", 21.00),
                    new MenuItem("Deep-fried Hiroshima Oysters", 17.00),
                    new MenuItem("Poached Tofu with Dried Shrimps", 12.00),
                    new MenuItem("Scrambled Egg White with Milk", 10.00)
                };
                break;
            case 2:
                selectedRestaurant = "Cafe Deux Magots";
                menuItem = new MenuItem[]{
                    new MenuItem("Sampling Matured Cheese Platter", 23.00),
                    new MenuItem("Spring Lobster Salad", 35.00),
                    new MenuItem("Spring Organic Omelette", 23.00),
                    new MenuItem("Truffle-flavoured Poultry Supreme", 34.00),
                    new MenuItem("White Asparagus", 26.00)
                };
                break;
            case 3:
                selectedRestaurant = "Trattoria Trussardi";
                menuItem = new MenuItem[]{
                    new MenuItem("Caprese Salad", 10.00),
                    new MenuItem("Creme caramel", 6.50),
                    new MenuItem("Lamb Chops with Apple Sauce", 25.00),
                    new MenuItem("Spaghetti alla Puttanesca", 15.00)
                };
                break;
            case 4:
                selectedRestaurant = "Libeccio";
                menuItem = new MenuItem[]{
                    new MenuItem("Formaggio", 12.50),
                    new MenuItem("Ghiaccio", 1.01),
                    new MenuItem("Melone", 5.20),
                    new MenuItem("Prosciutto and Pesc", 20.23),
                    new MenuItem("Risotto", 13.14),
                    new MenuItem("Zucchero and Sale", 0.60),
                };
                break;
            case 5:
                selectedRestaurant = "Savage Garden";
                menuItem = new MenuItem[]{
                    new MenuItem("Abbacchio’s Tea", 1.00),
                    new MenuItem("DIO’s Bread", 36.14),
                    new MenuItem("Giorno’s Donuts", 6.66),
                    new MenuItem("Joseph’s Tequila", 35.00),
                    new MenuItem("Kakyoin’s Cherry", 3.50),
                    new MenuItem("Kakyoin’s Porridge", 4.44),
                };
                break;
            default:
                break;
        }
    }

    private static class MenuItem {
        private String name;
        private double price;

        public MenuItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }
    
    public String getRestaurant(){
        return selectedRestaurant;
    }
    
    public String getFood(int indexFood){
        MenuItem item = menuItem[indexFood];
        String food = item.getName();
        return food;
    }

    public void displayMenu() {
        if (menuItem != null && menuItem.length > 0) {
            System.out.println("Menu for " + selectedRestaurant + ":");

            for (int i = 0; i < menuItem.length; i++) {
                MenuItem item = menuItem[i];
                System.out.println(item.getName() + " (" + item.getPrice() + ")");
            }
        } else {
            System.out.println("Menu not available for the specified restaurant.");
        }
    }
}


