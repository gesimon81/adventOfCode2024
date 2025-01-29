package day2;

import java.util.ArrayList;

public class MainDay2V2 {
	private static boolean isDebug = false;
	
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
	
	private static int countTotalReportsSafe() {
		int nbTotalReportsSafe = 0;
		
		for(ArrayList<Integer> report : listAllReports) {
			if(isReportSafeV2_test3(report))
				nbTotalReportsSafe++;
		}
		
		System.out.println("Result before Problem Dampener = " + nbTotalReportsSafe);
		
		
		return nbTotalReportsSafe;
	}
	
	
	private static boolean isReportSafeV2_test3(ArrayList<Integer> reportInput) {
		ArrayList<Integer> report = new ArrayList<Integer>(reportInput);
		ArrayList<Integer> possibleReportByRemoval = new ArrayList<Integer>(reportInput);
		ArrayList<Integer> possibleReportByRemovalPlusOne = new ArrayList<Integer>(reportInput);
		ArrayList<Integer> possibleReportByRemovalMinusOne = new ArrayList<Integer>(reportInput);
		
		boolean result;
		
		//Test isAdjacentLevelsDifferSafe
		boolean isAdjacentLevelsDifferSafe = true;
		int differ;
		for(int indexLevel = 0; indexLevel < (report.size() - 1); indexLevel++) {
			differ = Math.abs(report.get(indexLevel) - report.get(indexLevel + 1));
			if(differ < 1 || differ > 3) {
				//isAllDecreasing || isAllIncreasing
				
				possibleReportByRemoval.remove(indexLevel);
				if(indexLevel > 0) 
					possibleReportByRemovalMinusOne.remove(indexLevel - 1);
				if(indexLevel < report.size() - 1) 
					possibleReportByRemovalPlusOne.remove(indexLevel + 1);
				
				isAdjacentLevelsDifferSafe = false;
				break;
			} 
		}
		
		//si test isAdjacentLevelsDifferSafe true ou false, on peut utiliser possibleReportByDecreasing
		if(isAdjacentLevelsDifferSafe) {
			//isAllDecreasing 
			int compareAdjacent;
			ArrayList<Integer> possibleReportDecreasing = new ArrayList<Integer>(reportInput);
			ArrayList<Integer> possibleReportDecreasingPlusOne = new ArrayList<Integer>(reportInput);
			ArrayList<Integer> possibleReportDecreasingMinusOne = new ArrayList<Integer>(reportInput);
			
			result = true;
			
			for(int indexLevel = 0; indexLevel < (reportInput.size() - 1); indexLevel++) {
				compareAdjacent = Integer.compare(reportInput.get(indexLevel), reportInput.get(indexLevel + 1));
				if(compareAdjacent <= 0) {
					
					possibleReportDecreasing.remove(indexLevel);
					if(indexLevel > 0) 
						possibleReportDecreasingMinusOne.remove(indexLevel - 1);
					if(indexLevel < report.size() - 1) 
						possibleReportDecreasingPlusOne.remove(indexLevel + 1);
					
					result = false;
					break;
				} 
			}
			
			if(result) {
				return true;
			}
			
			ArrayList<Integer> possibleReportIncreasing = new ArrayList<Integer>(reportInput);
			ArrayList<Integer> possibleReportIncreasingPlusOne = new ArrayList<Integer>(reportInput);
			ArrayList<Integer> possibleReportIncreasingMinusOne = new ArrayList<Integer>(reportInput);
			result = true;
			
			for(int indexLevel = 0; indexLevel < (reportInput.size() - 1); indexLevel++) {
				compareAdjacent = Integer.compare(reportInput.get(indexLevel), reportInput.get(indexLevel + 1));
				if(compareAdjacent >= 0) {
					
					possibleReportIncreasing.remove(indexLevel);
					if(indexLevel > 0) 
						possibleReportIncreasingMinusOne.remove(indexLevel - 1);
					if(indexLevel < report.size() - 1) 
						possibleReportIncreasingPlusOne.remove(indexLevel + 1);
					
					result = false;
					break;
				}
			}
			
			if(result) {
				return true;
			}
			
			//if(!isAllDecreasing && !isAllIncreasing) { //cas obligatoire grâce aux return true;
			result = (isAdjacentLevelsDifferSafe(possibleReportDecreasing) && (isAllDecreasing(possibleReportDecreasing) || isAllIncreasing(possibleReportDecreasing))
					|| (isAdjacentLevelsDifferSafe(possibleReportDecreasingMinusOne) && (isAllDecreasing(possibleReportDecreasingMinusOne) || isAllIncreasing(possibleReportDecreasingMinusOne)))
					|| (isAdjacentLevelsDifferSafe(possibleReportDecreasingPlusOne) && (isAllDecreasing(possibleReportDecreasingPlusOne) || isAllIncreasing(possibleReportDecreasingPlusOne)))
					|| (isAdjacentLevelsDifferSafe(possibleReportIncreasing) && (isAllDecreasing(possibleReportIncreasing) || isAllIncreasing(possibleReportIncreasing)))
					|| (isAdjacentLevelsDifferSafe(possibleReportIncreasingMinusOne) && (isAllDecreasing(possibleReportIncreasingMinusOne) || isAllIncreasing(possibleReportIncreasingMinusOne)))
					|| (isAdjacentLevelsDifferSafe(possibleReportIncreasingPlusOne) && (isAllDecreasing(possibleReportIncreasingPlusOne) || isAllIncreasing(possibleReportIncreasingPlusOne))));
			
		} else {
			//pas possible de supprimer un second niveau
			result =  (isAdjacentLevelsDifferSafe(possibleReportByRemoval) && (isAllDecreasing(possibleReportByRemoval) || isAllIncreasing(possibleReportByRemoval)))
					|| (isAdjacentLevelsDifferSafe(possibleReportByRemovalMinusOne) && (isAllDecreasing(possibleReportByRemovalMinusOne) || isAllIncreasing(possibleReportByRemovalMinusOne)))
					|| (isAdjacentLevelsDifferSafe(possibleReportByRemovalPlusOne) && (isAllDecreasing(possibleReportByRemovalPlusOne) || isAllIncreasing(possibleReportByRemovalPlusOne)));
		}
		
		return result;
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
	
	private static boolean isAllDecreasing(ArrayList<Integer> report, boolean aLevelIsRemovable) throws ALevelIsIncorrect {
		boolean isSafe = true;
		
		int compareAdjacent;
		
		for(int indexLevel = 0; indexLevel < (report.size() - 1); indexLevel++) {
			compareAdjacent = Integer.compare(report.get(indexLevel), report.get(indexLevel + 1));
			if(compareAdjacent <= 0) {
				if(aLevelIsRemovable) {
					report.remove(indexLevel);
					listReportsWithALevelRemoved.add(report);	//Si la modif est faite à cet endroit là, possible que le rapport soit ajouté en doublon sur la liste à check, si il est validé dans le second test.
					throw new ALevelIsIncorrect();
				} else {
					return false;
				}	
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
	
	public static void main(String[] args) throws Exception {
		// V2
		convertInput();
		
		int resultV2 = countTotalReportsSafe(); 
		System.out.println("Result final V2 = " + resultV2);
	}

}
