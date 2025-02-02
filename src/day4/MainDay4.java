package day4;

public class MainDay4 {
	private static boolean isDebug = false;
	
	private static char[][] tabInput;
	
	/**
	 * Transforme les données de l'input
	 * @throws Exception
	 */
	private static void convertInput() throws Exception {
		// Séparation de l'entrée en lignes
        String[] lines;
        if(isDebug) {
        	lines = InputDay4.INPUT_DAY4_TEST.split("\r\n");
        } else {
        	lines = InputDay4.INPUT_DAY4.split("\r\n");
        }
        	
    	tabInput = new char[lines.length][lines[0].length()];
        
		for (int line = 0; line < lines.length; line++) {
	        tabInput[line] = lines[line].toCharArray(); // Utilisation de toCharArray pour une conversion efficace
	    }
	}
	
	/***
	 * Parcourir les charactères et tester si XMAS à partir des X pour éviter les doublons
	 * @return
	 */
	private static int countNbXMAS() {
		int nbXMAS = 0;
		
		for(int row = 0; row < tabInput.length; row++) {
			for(int col = 0; col < tabInput[0].length; col++) {
				if(tabInput[row][col] == 'X') {
					//tester XMAS vers le N
					if(row >= 3) {
						if(tabInput[row - 1][col] == 'M' && tabInput[row - 2][col] == 'A' && tabInput[row - 3][col] == 'S')
							nbXMAS++;
					}
					
					//tester XMAS vers le S
					if(row <= tabInput.length - 4) {
						if(tabInput[row + 1][col] == 'M' && tabInput[row + 2][col] == 'A' && tabInput[row + 3][col] == 'S')
							nbXMAS++;
					}
					
					//tester XMAS vers la W
					if(col >= 3) {
						if(tabInput[row][col - 1] == 'M' && tabInput[row][col - 2] == 'A' && tabInput[row][col - 3] == 'S')
							nbXMAS++;
					}
					
					//tester XMAS vers la E
					if(col <= tabInput[row].length - 4) {
						if(tabInput[row][col + 1] == 'M' && tabInput[row][col + 2] == 'A' && tabInput[row][col + 3] == 'S')
							nbXMAS++;
					}
					
					//tester XMAS vers la diagonale NW
					if(row >= 3 && col >= 3) {
						if(tabInput[row - 1][col - 1] == 'M' && tabInput[row - 2][col - 2] == 'A' && tabInput[row - 3][col - 3] == 'S')
							nbXMAS++;
					}
					
					//tester XMAS vers la diagonale NE
					if(row >= 3 && col <= tabInput[row].length - 4) {
						if(tabInput[row - 1][col + 1] == 'M' && tabInput[row - 2][col + 2] == 'A' && tabInput[row - 3][col + 3] == 'S')
							nbXMAS++;
					}
					
					//tester XMAS vers la diagonale SW
					if(row <= tabInput.length - 4 && col >= 3) {
						if(tabInput[row + 1][col - 1] == 'M' && tabInput[row + 2][col - 2] == 'A' && tabInput[row + 3][col - 3] == 'S')
							nbXMAS++;
					}
					
					//tester XMAS vers la diagonale SE
					if(row <= tabInput.length - 4 && col <= tabInput[row].length - 4) {
						if(tabInput[row + 1][col + 1] == 'M' && tabInput[row + 2][col + 2] == 'A' && tabInput[row + 3][col + 3] == 'S')
							nbXMAS++;
					}
				}
			}
		}
		
		return nbXMAS;
	}
	
	
	/***
	 * Parcourir les charactères et tester si deux MAS peuvent être placés en X
	 * 
	 * NW _ NE
	 * _  A _
	 * SW _ SE
	 * @return
	 */
	private static int countNbXShapedMAS() {
		int nbXShapedMAS = 0;
		
		int nbMAS;
		
		for(int row = 0; row < tabInput.length; row++) {
			for(int col = 0; col < tabInput[0].length; col++) {
				nbMAS = 0;
				
				if(tabInput[row][col] == 'A') {
					//Tester l'axe NW->SE
					if(row > 0 && col > 0 && row < tabInput.length - 1 && col < tabInput[row].length - 1) {
						if(tabInput[row - 1][col - 1] == 'M' && tabInput[row + 1][col + 1] == 'S')
						nbMAS++;
					}
										
					//Tester l'axe SE-->NW
					if(row < tabInput.length - 1 && col < tabInput[row].length - 1 && row > 0 && col > 0) {
						if(tabInput[row + 1][col + 1] == 'M' && tabInput[row - 1][col - 1] == 'S')
						nbMAS++;
					}				
					
					//Tester l'axe NE-->SW
					if(row > 0 && col < tabInput[row].length - 1 && row < tabInput.length - 1 && col > 0) {
						if(tabInput[row - 1][col + 1] == 'M' && tabInput[row + 1][col - 1] == 'S')
						nbMAS++;
					}				
					
					//Tester l'axe SW-->NE
					if(row < tabInput.length - 1 && col > 0 && row > 0 && col < tabInput[row].length - 1) {
						if(tabInput[row + 1][col - 1] == 'M' && tabInput[row - 1][col + 1] == 'S')
						nbMAS++;
					}
					
					//tester si 2 MAS sont en X
					if(nbMAS == 2)
						nbXShapedMAS++;
					
					nbMAS = 0;
				}
			}
		}
		
		return nbXShapedMAS;
	}
	
	
	public static void main(String[] args) throws Exception {	
		//V1
		convertInput();
		 
		int resultV1 = countNbXMAS(); 
		System.out.println("Result final V1 = " + resultV1);
		
		//V2
		int resultV2 = countNbXShapedMAS(); 
		System.out.println("Result final V2 = " + resultV2);
	}

	

}
