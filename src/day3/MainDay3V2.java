package day3;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainDay3V2 {
	private static boolean isDebug = false;
	
	private static int calcResultAllMulInstructions() throws IllegalArgumentException {
		int total = 0;
		
		//Extraire toutes les instructions mul correctes
		ArrayList<String> mulDoDontInstructions;
		if(isDebug) {
			mulDoDontInstructions = extractMulDoDontInstructions(InputDay3.INPUT_DAY3_V2_TEST);
        } else {
        	mulDoDontInstructions = extractMulDoDontInstructions(InputDay3.INPUT_DAY3);
        }
		
		//Pour chaque instruction, extraire les deux opérandes et faire le calcul
		//Ajouter le résultat au total
		boolean isDoActivated = true;
		
		for(String instruction : mulDoDontInstructions) {
			//System.out.println("instruction [" + instruction + "]");
			
			if(instruction.startsWith("mul(")) {
				if(isDoActivated)
					total += calcMulInstruction(instruction);
			} else if(instruction.startsWith("do()")) {
				isDoActivated = true;
			} else if(instruction.startsWith("don't()")) {
				isDoActivated = false;
			} else {
				throw new IllegalArgumentException("Instruction reçue incorrecte : " + instruction);
			}
			
		}
		
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
	

	private static ArrayList<String> extractMulDoDontInstructions(String input) {
		ArrayList<String> listMulDoDontInstructions = new ArrayList<String>();
		
		// Séparation de l'entrée en lignes
        String[] lines;
        if(isDebug) {
        	lines = input.split("\r\n");
        } else {
        	lines = input.split("\r\n");
        }
		
        
        //Extraire les instructions mul, do() et don't()
        String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don\\'t\\(\\)";
        
        // Création du Pattern et Matcher
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher; 
        
        for(int indexLine = 0; indexLine < lines.length; indexLine++) {
        	matcher = pattern.matcher(lines[indexLine]);
        	
        	while (matcher.find()) {
                listMulDoDontInstructions.add(matcher.group());
            }
        }
        
		return listMulDoDontInstructions;
	}
	
	public static void main(String[] args) {
		// V2
		int resultV2 = calcResultAllMulInstructions(); 
		System.out.println("Result final V2 = " + resultV2);

	}

}
