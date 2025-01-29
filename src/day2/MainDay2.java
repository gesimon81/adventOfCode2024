package day2;

import java.util.ArrayList;

public class MainDay2 {
	private static boolean isDebug = true;
	
	private static ArrayList<ArrayList<Integer>> listAllReports;
	
	/**
	 * Transforme les données de l'input
	 * @throws Exception
	 */
	private static void convertInput() throws Exception {
		// Séparation de l'entrée en lignes
        String[] lines;
        if(isDebug) {
        	lines = InputDay2.INPUT_DAY2_TEST.split("\r\n");
        } else {
        	lines = InputDay2.INPUT_DAY2.split("\r\n");
        }
        	
    	listAllReports = new ArrayList<ArrayList<Integer>>(lines.length);
    	String[] values;
    	ArrayList<Integer> listReportLevels;
    	
    	for(int indexLine = 0; indexLine < lines.length; indexLine++) {
        	//Récupérer les valeurs de l'input
        	values = lines[indexLine].split(" ");
        	
        	listReportLevels = new ArrayList<Integer>();
        	
        	for(int valuesIndex = 0; valuesIndex < values.length; valuesIndex++) {
        		listReportLevels.addLast(Integer.parseInt(values[valuesIndex]));
        	}
        
        	listAllReports.add(listReportLevels);
    	}
	}
	
	private static boolean isReportSafe(ArrayList<Integer> report) {
		boolean test1 = isAllDecreasing(report);
		boolean test2 = isAllIncreasing(report);
		boolean test3 = isAdjacentLevelsDifferSafe(report);
		
		if( (test1 || test2) && test3) {
			System.out.println("Report safe");
			return true;
		} else {
			System.out.println("Report unsafe");
			return false;
		}
	}
	
	private static boolean isAllDecreasing(ArrayList<Integer> report) {
		boolean isSafe = true;
		
		int compareAdjacent;
		
		for(int indexLevel = 0; indexLevel < (report.size() - 1); indexLevel++) {
			compareAdjacent = Integer.compare(report.get(indexLevel), report.get(indexLevel + 1));
			if(compareAdjacent <= 0) {
				return false;
			} 
		}
		
		return isSafe;
	}
	
	private static boolean isAllIncreasing(ArrayList<Integer> report) {
		boolean isSafe = true;
		
		int compareAdjacent;
		
		for(int indexLevel = 0; indexLevel < (report.size() - 1); indexLevel++) {
			compareAdjacent = Integer.compare(report.get(indexLevel), report.get(indexLevel + 1));
			if(compareAdjacent >= 0) {
				return false;
			} 
		}
		
		return isSafe;
	}
	
	private static boolean isAdjacentLevelsDifferSafe(ArrayList<Integer> report) {
		boolean isSafe = true;
		
		int differ;
		for(int indexLevel = 0; indexLevel < (report.size() - 1); indexLevel++) {
			differ = Math.abs(report.get(indexLevel) - report.get(indexLevel + 1));
			if(differ < 1 || differ > 3) {
				return false;
			} 
		}
		
		return isSafe;
	}
	
	private static int countTotalReportsSafe() {
		int nbTotalReportsSafe = 0;
		
		for(ArrayList<Integer> report : listAllReports) {
			if(isReportSafe(report))
				nbTotalReportsSafe++;
		}
		
		return nbTotalReportsSafe;
	}
	
	public static void main(String[] args) throws Exception {
		// V1
		convertInput();
		
		int resultV1 = countTotalReportsSafe(); 
		System.out.println("Result final V1 = " + resultV1);
	}

}