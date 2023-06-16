# WIA1002-JOJOLands

The jojoland folder is the latest version. The folder already combines Heaven's Door (basic feature 2), Pearl Jam (basic feature 3), The Joestars (basic feature 4), Moody Blues (basic feature 5), and Milagro Man (basic feature 6) in StartInterface. All completed.

Changes: (better to dl the other classes(except StartInterface) again since I fixed a few things in other basic features as well)
1. StartInterface:
  - to call heaven's door:
    
      //jump to Heaven's Door (basic feature 2)
      heavensDoor(dayNum, currDay, residentOrderLists);
    
  - heaven's door method:
    
      public static void heavensDoor(int dayNum, int currDay, ArrayList<ArrayList<orderList>> residentOrderLists){
        loadFile loadSystemFile = new loadFile();
        ArrayList<resident> resident = loadSystemFile.loadresidentFromFile("resources/residents.csv");
        ArrayList<stand> stand = loadSystemFile.loadstandFromFile("resources/stands.csv");
        ArrayList<resident> residentsInArea = loadSystemFile.getResidentsInArea(currentLocation);
        loadSystemFile.printTable(residentsInArea);
        boolean loop = true;
        while(loop){
            System.out.println("");
            System.out.println("[1] View Resident's Profile");
            System.out.println("[2] Sort");
            System.out.println("[3] Exit");
            System.out.println("");

            System.out.print("Select: ");
                String choice = sc.nextLine();
                switch(choice){
                    case "1":
                        //jump to The Joestars (Basic Feature 4)
                        TheJoestars joestars = new TheJoestars(currentLocation, dayNum, currDay, residentOrderLists);
                        break;
                    case "2":
                        // Prompt for the sorting order
                        // Sort the residents
                        ResidentComparator sorter = new ResidentComparator();
                        StandComparator sorter2 = new StandComparator();
                        sorter.sort(residentsInArea);
                        residentsInArea = sorter2.sort(residentsInArea);

                        // Print the sorted table
                        System.out.println("Sorted Table:");
                        loadSystemFile.printTable(residentsInArea);
                        break;
                    case "3":
                        loop = false;
                        break;
                    default:
                        System.out.println("Wrong input, please select again.");
                        break;
                }
        }
    }
    
  - to call moody blues and milagro man inside MissionThree():
    
            case 4:
                //jump to Moody Blue (basic feature 5)
                residentOrderLists = ro.randomOrderGenerator(dayNum, currDay);
                currDay = dayNum;
                MoodyBlues moodyBlues= new MoodyBlues(dayNum, currentLocation, residentOrderLists);
                boolean milagro = false; //not in Milagro Man mode
                List<List<Sale>> salesRecord = new ArrayList<>(); //empty list
                moodyBlues.salesInfo(milagro, salesRecord);
                break;
            case 5:
                //jump to Milagro Man (basic feature 6)
                residentOrderLists = ro.randomOrderGenerator(dayNum, currDay);
                currDay = dayNum;
                MilagroMan milagroMan = new MilagroMan(dayNum, currentLocation, residentOrderLists);
                milagroMan.milagroManMode();
                break;
