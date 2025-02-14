package day6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import day6.Guard.Orientation;

public class MainDay6 {
	private static boolean isDebug = false;
	
	private static ArrayList<Coordinate> mapObstructions;
	
	private static Guard guard;
	
	private static int lengthMap;
	
	private static int heightMap;
	
	/**
	 * Transforme les données de l'input
	 * @throws Exception
	 */
	private static void convertInput() throws Exception {
		// Séparation de l'entrée en lignes
        String[] lines;
        if(isDebug) {
        	lines = InputDay6.INPUT_DAY6_TEST.split("\r\n");
        } else {
        	lines = InputDay6.INPUT_DAY6.split("\r\n");
        }
        
        
        //Construction de la carte
        guard = new Guard(null, Orientation.UP);
        mapObstructions = new ArrayList<Coordinate>();
        lengthMap = lines[0].length();
        heightMap = lines.length;
        
        char[] line;
        
        for(int indexLine = 0; indexLine < lines.length; indexLine++) {
        	line = lines[indexLine].toCharArray();
        	
        	for(int indexColumn = 0; indexColumn < line.length; indexColumn++) {
        		if(line[indexColumn] == '#') {
        			mapObstructions.add(new Coordinate(indexColumn, indexLine));
        		} else if(line[indexColumn] == '^') {
        			guard.setGuardPosition(new Coordinate(indexColumn, indexLine));
        		}
        	}
        }
        
        if(guard.getGuardPosition().equals(null))
        	throw new IllegalArgumentException("Position du garde non détectée");	
	}
	
	private static int countPositionsGuard() {
		HashSet<Coordinate> listPositionsGuard = new HashSet<Coordinate>();
		
		listPositionsGuard.add(guard.getGuardPosition());
		
		Coordinate nextCoordinateGuard = guard.getCoordinateForward();
		
		System.out.println("Garde position = " + guard.getGuardPosition() + " et prochaine position = " + nextCoordinateGuard);
		
		while(isPositionPossible(nextCoordinateGuard)) {
			if(mapObstructions.contains(nextCoordinateGuard)) {
				//Un obstacle est devant le garde
				guard.turnRight();
				System.out.println("turn right");
			} else {
				//Le garde peut avancer
				guard.setGuardPosition(nextCoordinateGuard);
				listPositionsGuard.add(nextCoordinateGuard);
			}
			
			nextCoordinateGuard = guard.getCoordinateForward();
			System.out.println("Garde position = " + guard.getGuardPosition() + " et prochaine position = " + nextCoordinateGuard);
		}
		
		//Le garde est sorti de la carte
		
		return listPositionsGuard.size();
	}
	
	private static boolean isPositionPossible(Coordinate coordinate) {
		return coordinate.getCoordinateX() >= 0 && coordinate.getCoordinateX() < lengthMap
			&& coordinate.getCoordinateY() >= 0 && coordinate.getCoordinateY() < heightMap;
	}
	
	public static void main(String[] args) throws Exception {
		//V1 
		convertInput();
		 
		int resultV1 = countPositionsGuard(); 
		System.out.println("Result final V1 = " + resultV1);
	}

}
