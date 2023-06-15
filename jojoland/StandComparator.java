package jojoland;

import java.util.*;

public class StandComparator {
	
    private int compareStandParameter(String param1, String param2) {
        int value1 = getStandParameterValue(param1);
        int value2 = getStandParameterValue(param2);

        return Integer.compare(value1, value2);
    }

    private int getStandParameterValue(String parameter) {
        if (parameter == null) {
            return 0; // Null has the highest
        } else if (parameter.equals("?")) {
            return 1;
        } else if (parameter.equals("E")) {
            return 2;
        } else if (parameter.equals("D")) {
            return 3;
        } else if (parameter.equals("C")) {
            return 4;
        } else if (parameter.equals("B")) {
            return 5;
        } else if (parameter.equals("A")) {
            return 6;
        } else if (parameter.equals("Infinity")) {
            return 7;
        } else {
            return -1; // Unknown parameter
        }
    }
    
    public ArrayList<stand> sorting(ArrayList<stand> StandsList, int[] order, int[] orderUpDown) {
    	
    	for (int i = 0; i < StandsList.size(); i++) {
    		for (int j = i + 1; j < StandsList.size(); j++) {
    			
    			stand stand1 = StandsList.get(i);
    			stand stand2 = StandsList.get(j);
    			
    			
    			for (int b = 0; b < order.length; b++) {
        			if(order[b] == 0) {
        				int result = compareStandParameter(stand1.getDestructivePower(), stand2.getDestructivePower());
        		        if (result != 0) {
        		        	if (result < 0 && orderUpDown[b] == 1) {
        		        		StandsList.set(i, stand2);
        		        		StandsList.set(j, stand1);
        		        		
        		        	} else if (result > 0 && orderUpDown[b] == 0) {
        		        		StandsList.set(i, stand2);
        		        		StandsList.set(j, stand1);
        		        		
        		        	}
        		            break;
        		        }
        			}
        			if(order[b] == 1) {
        				int result = compareStandParameter(stand1.getSpeed(), stand2.getSpeed());
        		        if (result != 0) {
        		        	if (result < 0 && orderUpDown[b] == 1) {
        		        		StandsList.set(i, stand2);
        		        		StandsList.set(j, stand1);
        		        		
        		        	} else if (result > 0 && orderUpDown[b] == 0) {
        		        		StandsList.set(i, stand2);
        		        		StandsList.set(j, stand1);
        		        		
        		        	}
        		            break;
        		        }
        			}
        			if(order[b] == 2) {
        				int result = compareStandParameter(stand1.getRange(), stand2.getRange());
        		        if (result != 0) {
        		        	if (result < 0 && orderUpDown[b] == 1) {
        		        		StandsList.set(i, stand2);
        		        		StandsList.set(j, stand1);
        		        		
        		        	} else if (result > 0 && orderUpDown[b] == 0) {
        		        		StandsList.set(i, stand2);
        		        		StandsList.set(j, stand1);
        		        		
        		        	}
        		            break;
        		        }
        			}
        			if(order[b] == 3) {
        				int result = compareStandParameter(stand1.getStamina(), stand2.getStamina());
        		        if (result != 0) {
        		        	if (result < 0 && orderUpDown[b] == 1) {
        		        		StandsList.set(i, stand2);
        		        		StandsList.set(j, stand1);
        		        		
        		        	} else if (result > 0 && orderUpDown[b] == 0) {
        		        		StandsList.set(i, stand2);
        		        		StandsList.set(j, stand1);
        		        		
        		        	}
        		            break;
        		        }
        			}
        			if(order[b] == 4) {
        				int result = compareStandParameter(stand1.getPrecision(), stand2.getPrecision());
        				if (result != 0) {
        					if (result < 0 && orderUpDown[b] == 1) {
        		        		StandsList.set(i, stand2);
        		        		StandsList.set(j, stand1);
        		        		
        		        	} else if (result > 0 && orderUpDown[b] == 0) {
        		        		StandsList.set(i, stand2);
        		        		StandsList.set(j, stand1);
        		        		
        		        	}
        		            break;
        		        }
        			}
        			if(order[b] == 5) {
        				int result = compareStandParameter(stand1.getDevelopmentPotential(), stand2.getDevelopmentPotential());
        				if (result != 0) {
        					if (result < 0 && orderUpDown[b] == 1) {
        		        		StandsList.set(i, stand2);
        		        		StandsList.set(j, stand1);
        		        		
        		        	} else if (result > 0 && orderUpDown[b] == 0) {
        		        		StandsList.set(i, stand2);
        		        		StandsList.set(j, stand1);
        		        		
        		        	}
        		            break;
        		        }
        			}
        			if(order[b] == 6) {
        				int result = Character.compare(stand1.getStand().charAt(0), stand2.getStand().charAt(0));
        				if (result != 0) {
        					if (result < 0 && orderUpDown[b] == 0) {
        		        		StandsList.set(i, stand2);
        		        		StandsList.set(j, stand1);
        		        		
        		        	} else if (result > 0 && orderUpDown[b] == 1) {
        		        		StandsList.set(i, stand2);
        		        		StandsList.set(j, stand1);
      
        		        	}
        		            break;
        		        }
        			}

        		}
    		}
		}
    	return StandsList;
    }
    
    public ArrayList<resident> sort(ArrayList<resident> residents) {
    	Scanner scanner = new Scanner(System.in);
    	System.out.print("Enter the sorting order: ");
    	String order = scanner.nextLine();
    	System.out.println();
    	String[] orders = order.split(";");
    	
    	int[] orderHeader = new int [orders.length];
    	int[] orderOrder = new int [orders.length];
    	
    	int i = 0;
    	for (String String : orders) {
    		if (String.contains("Destructive Power")) {
    			orderHeader[i] = 0;
    		} else if(String.contains("Speed")) {
    			orderHeader[i] = 1;
    		} else if(String.contains("Range")) {
    			orderHeader[i] = 2;
    		} else if(String.contains("Stamina")) {
    			orderHeader[i] = 3;
    		} else if(String.contains("Precision")) {
    			orderHeader[i] = 4;
    		} else if(String.contains("Development Potential")) {
    			orderHeader[i] = 5;
    		} else {
				orderHeader[i] = 6;
			}
    		
    		if (String.contains("DESC")) {
    			orderOrder[i] = 0;
    		} else {
    			orderOrder[i] = 1;
			}
    		i++;
    	}
    	
    	
    	ArrayList<stand> standsToOrder = new ArrayList<stand>();
    	ArrayList<resident> residentWithStands = new ArrayList<resident>();
    	ArrayList<resident> sortedResidents = new ArrayList<resident>();
    	
    	for (resident resident: residents) {
    		if (resident.getStand() != null) {
    			standsToOrder.add(resident.getStand());
    			residentWithStands.add(resident);
    			
    		}
    	}
    	
    	standsToOrder = sorting(standsToOrder, orderHeader, orderOrder);
    	
    	/*
    	System.out.println(" ");
    	for(stand stand: standsToOrder) {
    		System.out.println(stand.getStand());
    	}
    	System.out.println(" ");
    	*/
    	
    	for (int j = standsToOrder.size() - 1; j > -1; j--) {
    		for (resident resident : residentWithStands) {
    			if (resident.getStand().equals(standsToOrder.get(j))){
    				residents.remove(resident);
    				residents.add(0, resident);
    				break;
    			}
    		}
    	}
    	System.out.println(" ");
    	return residents;
    }
    
    
}
