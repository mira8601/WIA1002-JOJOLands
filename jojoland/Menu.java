/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jojoland;

public class Menu{
    private MenuItem[] menuItem;
    private String selectedRestaurant;
    private int indexRest;

    public Menu(int indexRest) {
        this.indexRest = indexRest;
        setMenu();
    }

    public void setMenu() {
        switch (indexRest) {
            case 0:
                selectedRestaurant = "Jade Garden";
                menuItem = new MenuItem[]{
                    new MenuItem("Braised Chicken in Black Bean Sauce", 15.00),
                    new MenuItem("Braised Goose Web with Vermicelli", 21.00),
                    new MenuItem("Deep-fried Hiroshima Oysters", 17.00),
                    new MenuItem("Poached Tofu with Dried Shrimps", 12.00),
                    new MenuItem("Scrambled Egg White with Milk", 10.00)
                };
                break;
            case 1:
                selectedRestaurant = "Cafe Deux Magots";
                menuItem = new MenuItem[]{
                    new MenuItem("Sampling Matured Cheese Platter", 23.00),
                    new MenuItem("Spring Lobster Salad", 35.00),
                    new MenuItem("Spring Organic Omelette", 23.00),
                    new MenuItem("Truffle-flavoured Poultry Supreme", 34.00),
                    new MenuItem("White Asparagus", 26.00)
                };
                break;
            case 2:
                selectedRestaurant = "Trattoria Trussardi";
                menuItem = new MenuItem[]{
                    new MenuItem("Caprese Salad", 10.00),
                    new MenuItem("Creme caramel", 6.50),
                    new MenuItem("Lamb Chops with Apple Sauce", 25.00),
                    new MenuItem("Spaghetti alla Puttanesca", 15.00)
                };
                break;
            case 3:
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
            case 4:
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
    
    public String getFood(int indexOrder){
        String food;
        MenuItem item = menuItem[indexOrder];
        food = item.getName();
        return food;
    }
    
    public double getPrice(int indexOrder){
        double price;
        MenuItem item = menuItem[indexOrder];
        price = item.getPrice();
        return price;
    }

    public void displayMenu() {
        if (menuItem != null && menuItem.length > 0) {
            System.out.println("Menu for " + selectedRestaurant + ":");

            for (int i = 0; i < menuItem.length; i++) {
                MenuItem item = menuItem[i];
                System.out.printf("%s (%.2f)\n" ,item.getName(),item.getPrice());
            }
        } else {
            System.out.println("Menu not available for the specified restaurant.");
        }
    }
}
//public class Menu extends PearlJam {
//    private MenuItem[] menuItem;
//
//    public Menu(String selectedRestaurant) {
//        super(selectedRestaurant);
//        setMenu();
//    }
//
//    public void setMenu() {
//        switch (selectedRestaurant) {
//            case "Jade Garden":
//                menuItem = new MenuItem[]{
//                    new MenuItem("Braised Chicken in Black Bean Sauce", 15.00),
//                    new MenuItem("Braised Goose Web with Vermicelli", 21.00),
//                    new MenuItem("Deep-fried Hiroshima Oysters", 17.00),
//                    new MenuItem("Poached Tofu with Dried Shrimps", 12.00),
//                    new MenuItem("Scrambled Egg White with Milk", 10.00)
//                };
//                break;
//            case "Cafe Deux Magots":
//                menuItem = new MenuItem[]{
//                    new MenuItem("Sampling Matured Cheese Platter", 23.00),
//                    new MenuItem("Spring Lobster Salad", 35.00),
//                    new MenuItem("Spring Organic Omelette", 23.00),
//                    new MenuItem("Truffle-flavoured Poultry Supreme", 34.00),
//                    new MenuItem("White Asparagus", 26.00)
//                };
//                break;
//            case "Trattoria Trussardi":
//                menuItem = new MenuItem[]{
//                    new MenuItem("Caprese Salad", 10.00),
//                    new MenuItem("Creme caramel", 6.50),
//                    new MenuItem("Lamb Chops with Apple Sauce", 25.00),
//                    new MenuItem("Spaghetti alla Puttanesca", 15.00)
//                };
//                break;
//            case "Libeccio":
//                menuItem = new MenuItem[]{
//                    new MenuItem("Formaggio", 12.50),
//                    new MenuItem("Ghiaccio", 1.01),
//                    new MenuItem("Melone", 5.20),
//                    new MenuItem("Prosciutto and Pesc", 20.23),
//                    new MenuItem("Risotto", 13.14),
//                    new MenuItem("Zucchero and Sale", 0.60),
//                };
//                break;
//            case "Savage Garden":
//                menuItem = new MenuItem[]{
//                    new MenuItem("Abbacchio’s Tea", 1.00),
//                    new MenuItem("DIO’s Bread", 36.14),
//                    new MenuItem("Giorno’s Donuts", 6.66),
//                    new MenuItem("Joseph’s Tequila", 35.00),
//                    new MenuItem("Kakyoin’s Cherry", 3.50),
//                    new MenuItem("Kakyoin’s Porridge", 4.44),
//                };
//                break;
//            default:
//                break;
//        }
//    }
//
//    private static class MenuItem {
//        private String name;
//        private double price;
//
//        public MenuItem(String name, double price) {
//            this.name = name;
//            this.price = price;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public double getPrice() {
//            return price;
//        }
//    }
//
//    public void displayMenu() {
//        if (menuItem != null && menuItem.length > 0) {
//            System.out.println("Menu for " + selectedRestaurant + ":");
//
//            for (int i = 0; i < menuItem.length; i++) {
//                MenuItem item = menuItem[i];
//                System.out.println(item.getName() + " (" + item.getPrice() + ")");        
//            }
//        } else {
//            System.out.println("Menu not available for the specified restaurant.");
//        }
//    }
//}
//
