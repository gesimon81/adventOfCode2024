package day5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MainDay5 {
	private static boolean isDebug = true;

	private static HashMap<String, HashSet<String>> mapPageOrderingRules;
	private static ArrayList<String> listUpdates;
	
	/**
	 * Transforme les données de l'input
	 * @throws Exception
	 */
	private static void convertInput() throws Exception {
		// Séparation de l'entrée en lignes
        String[] lines;
        
        if(isDebug) {
        	lines = InputDay5.INPUT_DAY5_TEST.split("\r\n");
        } else {
        	lines = InputDay5.INPUT_DAY5.split("\r\n");
        }
        	
        //Séparer les instructions entre les deux listes
        ArrayList<String> listPageOrderingRules = new ArrayList<String>();
        listUpdates = new ArrayList<String>();
        
    	
        boolean isOrderingRule = true;
        
		for (int line = 0; line < lines.length; line++) {
			if(lines[line].contentEquals("")) {
				isOrderingRule = false;
			} else {
				if(isOrderingRule) {
					listPageOrderingRules.add(lines[line]);
				} else {
					listUpdates.add(lines[line]);
				}
			}
			  
	    }
		
		//Sorting the page ordering rules (format "53|13")
		mapPageOrderingRules = new HashMap<String, HashSet<String>>();
		String [] pagesNumbersOrderingRule;
		
		for(String orderingRule : listPageOrderingRules) {
			pagesNumbersOrderingRule = orderingRule.split("\\|");
			
			if(!mapPageOrderingRules.containsKey(pagesNumbersOrderingRule[0])) {
				mapPageOrderingRules.put(pagesNumbersOrderingRule[0], new HashSet<String>());
			}
			
			mapPageOrderingRules.get(pagesNumbersOrderingRule[0]).add(pagesNumbersOrderingRule[1]);
		}
		
	}
	
	
	private static int countUpdatesRightOrder() {
		int nbUpdatesRightOrder = 0;
		
		for(String update : listUpdates) {
			if(isUpdateInRightOrder(update)) {				
				//Add the middle number of the update
				nbUpdatesRightOrder++;
				//TODO
			}
		}
		
		return nbUpdatesRightOrder;
	}
	
	private static boolean isUpdateInRightOrder(String update) throws IllegalArgumentException {
		String[] tabPageNumbers = update.split(",");
				
		for (int currentPageNumberIndex = 0; currentPageNumberIndex < tabPageNumbers.length - 1; currentPageNumberIndex++) {
	        String currentPage = tabPageNumbers[currentPageNumberIndex];

	        // Vérifier si la page actuelle a des règles dans la map
	        if (mapPageOrderingRules.containsKey(currentPage)) {
	            

	            // Vérifier si l'une des pages suivantes ne devrait pas être avant la page actuelle
	            for (int testPageNumberIndex = currentPageNumberIndex + 1; testPageNumberIndex < tabPageNumbers.length; testPageNumberIndex++) {
	                String testPage = tabPageNumbers[testPageNumberIndex];

	                HashSet<String> restrictedPages = mapPageOrderingRules.get(testPage);
	                
	                System.out.println("Checking if " + currentPage + " should not be before " + testPage);
	                System.out.println("Restricted pages for " + currentPage + " : " + restrictedPages);
	                
	                if (restrictedPages != null && !restrictedPages.isEmpty() && restrictedPages.contains(currentPage)) {
	                    return false; // L'ordre est incorrect
	                }
	            }
	        }
	    }
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		//V1
		convertInput();
		 
		int resultV1 = countUpdatesRightOrder(); 
		System.out.println("Resultat final V1 = " + resultV1);

	}

}
