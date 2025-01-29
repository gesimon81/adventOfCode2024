package day1;

import java.util.ArrayList;
import java.util.Collections;

public class MainDay1 {
	private static boolean isDebug = false;
	
	private static ArrayList<Integer> listLocationIdsTeam1;
	private static ArrayList<Integer> listLocationIdsTeam2;
	
	
	/**
	 * Transforme les données de l'input
	 * @throws Exception
	 */
	private static void convertInput() throws Exception {
		// Séparation de l'entrée en lignes
        String[] lines;
        if(isDebug) {
        	lines = InputDay1.INPUT_DAY1_TEST.split("\r\n");
        } else {
        	lines = InputDay1.INPUT_DAY1.split("\r\n");
        }
        	
    	listLocationIdsTeam1 = new ArrayList<Integer>(lines.length);
    	listLocationIdsTeam2 = new ArrayList<Integer>(lines.length);
    	
		String[] values;
        
        for(int indexLine = 0; indexLine < lines.length; indexLine++) {
        	//Récupérer les valeurs de l'input
        	values = lines[indexLine].split("   ");
        	
        	listLocationIdsTeam1.add(Integer.parseInt(values[0]));
        	listLocationIdsTeam2.add(Integer.parseInt(values[1]));
        }
	}
	
	private static int calcTotalDistance() {
		//Trier les locationsIDs par ordre croissant
		sortListLocationsIds();
		
		//Calculer les distances entre chaque liste
		int resultTotalDistance = 0;
		
		for(int indexLocationId = 0; indexLocationId < listLocationIdsTeam1.size(); indexLocationId++) {
			resultTotalDistance += Math.abs(listLocationIdsTeam1.get(indexLocationId) - listLocationIdsTeam2.get(indexLocationId));
		}
		
		return resultTotalDistance;
	}
	
	private static void sortListLocationsIds() {
		//Trier les locationsIDs par ordre croissant
		Collections.sort(listLocationIdsTeam1);
		Collections.sort(listLocationIdsTeam2);
	}
	
	private static int calcSimilarityScoreVersionBruteForce() {
		int resultTotalSimilarity = 0;
		int resultSimilarity;
		
		for(int locationId: listLocationIdsTeam1) {
			resultSimilarity = Collections.frequency(listLocationIdsTeam2, locationId);
			resultTotalSimilarity += locationId * resultSimilarity;
			
			//System.out.println("locationId = " + locationId + ", resultSimilarity = " + resultSimilarity + ", resultTotalSimilarity = " + resultTotalSimilarity);
		}
		
		return resultTotalSimilarity;
	}
	
	public static void main(String[] args) throws Exception {
		/*
		 * //V1 convertInput();
		 * 
		 * int resultV1 = calcTotalDistance(); System.out.println("Result final V1 = " +
		 * resultV1);
		 */
		
		//V2
		convertInput();
		int resultV2 = calcSimilarityScoreVersionBruteForce();
		System.out.println("Result final V2 = " + resultV2);
	}

	

}
