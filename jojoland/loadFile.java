package jojoland;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class loadFile{
	private ArrayList<resident> listOfResidents;
	private String selectResident;
        Scanner sc = new Scanner(System.in);
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
                String parents = data[4].trim();

                residentList.add(new resident(name, age, gender, residentialArea, parents));
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


    public ArrayList<resident> getResidentsInArea(String residentialArea) {
        ArrayList<resident> residentsInArea = new ArrayList<>();
        boolean foundResidents = false;

        for (resident residents : this.listOfResidents) {
            if (residents.getResidentialArea().equalsIgnoreCase(residentialArea)) {
                residentsInArea.add(residents);
                foundResidents = true;
            }
        }

        if (!foundResidents) {
            System.out.println("No residents found in the given residential area.");
            return null;
        } else {
            return residentsInArea;
        }
    }

    public void printTable(ArrayList<resident> residents) {
            System.out.println("");
            System.out.println("Resident Information in " +  residents.get(0).getResidentialArea());
        System.out.println("+----+-----------------------+-----+--------+-----------------------+-------------------+-----------+-----------+---------+-----------+-----------------------+");
        System.out.println("| No | Name                  | Age | Gender | Stand                 | Destructive Power | Speed     | Range     | Stamina | Precision | Development Potential |");
        System.out.println("+----+-----------------------+-----+--------+-----------------------+-------------------+-----------+-----------+---------+-----------+-----------------------+");
        int counter = 1;
        for (resident resident1 : residents) {
            stand residentStand = resident1.getStand();
            String standName = residentStand != null ? residentStand.getStand() : "N/A";
            String destructivePower = residentStand != null ? residentStand.getDestructivePower() : "-";
            String speed = residentStand != null ? residentStand.getSpeed() : "-";
            String range = residentStand != null ? residentStand.getRange() : "-";
            String stamina = residentStand != null ? residentStand.getStamina() : "-";
            String precision = residentStand != null ? residentStand.getPrecision() : "-";
            String developmentPotential = residentStand != null ? residentStand.getDevelopmentPotential() : "-";

            System.out.printf("| %-2d | %-21s | %-3s | %-6s | %-21s | %-17s | %-9s | %-9s | %-7s | %-9s | %-21s |\n",
                    counter, resident1.getName(), resident1.getAge(), resident1.getGender(), standName, destructivePower,
                    speed, range, stamina, precision, developmentPotential);

            counter++;
        }
        System.out.println("+----+-----------------------+-----+--------+-----------------------+-------------------+-----------+-----------+---------+-----------+-----------------------+");
        System.out.println("");
    }

    public void sortTable(ArrayList<resident> residentsInArea){
        // Prompt for the sorting order
        // Sort the residents
        ResidentComparator sorter = new ResidentComparator();
        StandComparator sorter2 = new StandComparator();
        sorter.sort(residentsInArea);
        residentsInArea = sorter2.sort(residentsInArea);

        // Print the sorted table
        System.out.println("Sorted Table:");
        printTable(residentsInArea);
    }	     
	
}
