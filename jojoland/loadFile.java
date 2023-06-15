package jojoland;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class loadFile{
	private ArrayList<resident> listOfResidents;
	
	public loadFile() {
		this.listOfResidents = new ArrayList<resident>();
	}
	
	public ArrayList<resident> loadresidentFromFile(String filePath) {
            ArrayList<resident> residentList = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                boolean isFirstLine = true;

                while ((line = br.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;  // Skip the header line
                    }

                    String[] data = line.split(",");
                    String name = data[0].trim();
                    String age = data[1].trim();
                    String gender = data[2].trim();
                    String residentialArea = data[3].trim();
                    //combine the fourth and fifth collumn for the rows that have fifth collumn
                    if((data[0]).equals("George Joestar II")
                           ||(data[0]).equals("Giorno Giovanna") 
                           ||(data[0]).equals("Holy Kujo")
                           ||(data[0]).equals("Jonathan Joestar")
                           ||(data[0]).equals("Joseph Joestar")
                           ||(data[0]).equals("Josuke Higashikata")
                           ||(data[0]).equals("Jotaro Kujo")){
                        String parents = data[4].trim() + ", " + data[5].trim();
                        residentList.add(new resident(name, age, gender, residentialArea, parents));
                    }
                    else{
                        String parents = data[4].trim();
                        residentList.add(new resident(name, age, gender, residentialArea, parents));
                    }
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.listOfResidents = residentList;
            return listOfResidents;
        }
    
    public ArrayList<stand> loadstandFromFile(String filePath) {
        ArrayList<stand> standList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;  // Skip the header line
                }

                String[] data = line.split(",");

                String stand = data[0].trim();
                String standUser = data[1].trim();
                String destructivePower = data[2].trim();
                String speed = data[3].trim();
                String range = data[4].trim();
                String stamina = data[5].trim();
                String precision = data[6].trim();
                String developmentPotential = data[7].trim();
                stand currentStand = new stand(stand, standUser, destructivePower, speed, range, stamina, precision, developmentPotential);
                standList.add(currentStand);
                
                for (resident resident: listOfResidents) {
                	if (standUser.equals(resident.getName())) {
                		resident.setStand(currentStand);
                	}
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return standList;
    }


    public void displayResidentsInArea(String residentialArea) {
    		ArrayList<resident> sorted = new ArrayList<>();
    		ResidentComparator sorter = new ResidentComparator();
            boolean foundResidents = false;
            for (resident residents : this.listOfResidents) {
                if (residents.getResidentialArea().equalsIgnoreCase(residentialArea)) {
                	sorted.add(residents);
                    
                    foundResidents = true;
                }
            }
            if (!foundResidents) {
                System.out.println("No residents found in the given residential area.");
            } else {
            	sorter.sort(sorted);
            	for (resident residents : sorted) {
            		System.out.println(residents);
            	}
            }
          
        }

    public String selectResidentialArea() {
            System.out.println("Residential Areas:");
            System.out.println("1. Morioh Grand Hotel");
            System.out.println("2. Trattoria Trussardi");
            System.out.println("3. Town Hall");
            System.out.println("4. San Giorgio Maggiore");
            System.out.println("5. Green Dolphin Street Prison");
            System.out.println("6. Libeccio");
            System.out.println("7. Jade Garden");
            System.out.println("8. Angelo Rock");
            System.out.println("9. Cafe Deux Magots");
            System.out.println("10. DIO's Mansion");
            System.out.println("11. Joestar Mansion");
            System.out.println("12. Polnareff Land");
            System.out.println("13. Vineyard");
            System.out.println("14. Savage Garden");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the number corresponding to the residential area: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    return "Morioh Grand Hotel";
                case 2:
                    return "Trattoria Trussardi";
                case 3:
                    return "Town Hall";
                case 4:
                    return "San Giorgio Maggiore";
                case 5:
                    return "Green Dolphin Street Prison";
                case 6:
                    return "Libeccio";
                case 7:
                    return "Jade Garden";
                case 8:
                    return "Angelo Rock";
                case 9:
                    return "Cafe Deux Magots";
                case 10:
                    return "DIO's Mansion";
                case 11:
                    return "Joestar Mansion";
                case 12:
                    return "Polnareff Land";
                case 13:
                    return "Vineyard";
                case 14:
                    return "Savage Garden";
                default:
                    return null;
            }
        }
	
}
