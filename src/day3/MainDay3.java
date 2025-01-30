package day3;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainDay3 {
	private static boolean isDebug = false;
	
	private static int calcResultAllMulInstructions() {
		int total = 0;
		int totalMatcherGroup = 0;
		
		//Extraire toutes les instructions mul correctes
		ArrayList<String> mulInstructions;
		if(isDebug) {
			mulInstructions = extractMulInstructions(InputDay3.INPUT_DAY3_V1_TEST);
			totalMatcherGroup = extractMulInstructionsAndCalc(InputDay3.INPUT_DAY3_V1_TEST);
        } else {
        	mulInstructions = extractMulInstructions(InputDay3.INPUT_DAY3);
        	totalMatcherGroup = extractMulInstructionsAndCalc(InputDay3.INPUT_DAY3);
        }
		
		//Pour chaque instruction, extraire les deux opérandes et faire le calcul
		//Ajouter le résultat au total
		for(String mulInstruction : mulInstructions) {
			total += calcMulInstruction(mulInstruction);
		}
		
		System.out.println("total V1 par extraction = " + total);
		System.out.println("total V1 par matcher.group = " + totalMatcherGroup);
		
		return total;
	}
	
	private static int calcMulInstruction(String mulInstruction) {
		//mul(---,---)
		String mulInstructionNumbers = mulInstruction.substring(4, mulInstruction.length() - 1);
		
		//---,---
		String[] numbersString = mulInstructionNumbers.split(",");
		
		//Calcul		
		return Integer.parseInt(numbersString[0]) * Integer.parseInt(numbersString[1]);
	}

	private static ArrayList<String> extractMulInstructions(String input) {
		ArrayList<String> listMulInstructions = new ArrayList<String>();
		
		// Séparation de l'entrée en lignes
        String[] lines;
        if(isDebug) {
        	lines = input.split("\r\n");
        } else {
        	lines = input.split("\r\n");
        }
		
        
        //Extraire les instructions mul
        String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
        
        // Création du Pattern et Matcher
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher; 
        
        for(int indexLine = 0; indexLine < lines.length; indexLine++) {
        	matcher = pattern.matcher(lines[indexLine]);
        	
        	while (matcher.find()) {
                // Extraire les groupes (les deux nombres)
                String instruction = "mul(" + matcher.group(1) + "," + matcher.group(2) + ")";
                listMulInstructions.add(instruction);
            }
        }
        
		return listMulInstructions;
	}
	
	/**
	 * Version utilisant matcher.group() pour directement extraire les nombres et faire le calcul
	 * @param input
	 * @return
	 */
	private static int extractMulInstructionsAndCalc(String input) {
		
		int total = 0;
		
		// Séparation de l'entrée en lignes
        String[] lines;
        if(isDebug) {
        	lines = input.split("\r\n");
        } else {
        	lines = input.split("\r\n");
        }
		
        
        //Extraire les instructions mul
        String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
        
        // Création du Pattern et Matcher
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher; 
        
        for(int indexLine = 0; indexLine < lines.length; indexLine++) {
        	matcher = pattern.matcher(lines[indexLine]);
        	
        	while (matcher.find()) {
                // Extraire les groupes (les deux nombres)     
                total += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
            }
        }
        
		return total;
	}
	
	public static void main(String[] args) {
		// V1
		int resultV1 = calcResultAllMulInstructions(); 
		System.out.println("Result final V1 = " + resultV1);
 
	}

}
