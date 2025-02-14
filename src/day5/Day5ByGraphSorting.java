package day5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Day5ByGraphSorting {
	
	private static boolean isDebug = false;
	
	/**
	 * Liste des instructions à vérifier sous la forme [75,47,61,53,29]
	 */
	private static ArrayList<String> listUpdates;

	/**
	 * Contient pour chaque noeud sa liste de noeuds successeurs
	 */
	//private static Map<String, List<String>> graph;
	
	/**
	 * Pour chaque noeud, indique son nombre de sucesseurs
	 * A update à chaque itération du parcours pour réduire les degrés progressivement
	 */
    //private static Map<String, Integer> inDegree;
    
    /**
     * Ordre complet des pages
     */
    //private static List<String> orderedPages;
    
	/**
	 * Liste des règles d'ordonnancement sous la forme "75|13"
	 */
    private static Set<String> listPageOrderingRules;
	
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
        listPageOrderingRules = new HashSet<String>();
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
		
		//remove du tri topologique pour l'utiliser en graphes partiels
	}
	
	/**
	 * Se base sur le tri topographique pour vérifier l'ordre des updates
	 * @param listUpdates sous la forme "75|29"
	 */
	private static ArrayList<String> generateOrderedPageList(ArrayList<String> listUpdates) {
		ArrayList<String> correctlyOrderedUpdates = new ArrayList<String>();
		
		for(String update : listUpdates) {
			String[] pagesUpdate = update.split(",");
			
			//Crée l'ordre des noeuds par tri topologique
			ArrayList<String> orderedPages = triTopologiqueSousGraphe(update);
			
			boolean isOrdered = true;
			
			//Pour chaque page de l'update, on vérifie si elle doit être placée avant chacune des suivantes
			outerLoop: // Label pour la boucle interne
			for(int indexPage = 0; indexPage < pagesUpdate.length - 1; indexPage++) {
				for(int indexPageToCompare = indexPage + 1; indexPageToCompare < pagesUpdate.length; indexPageToCompare++) {
					if(orderedPages.indexOf(pagesUpdate[indexPage]) > orderedPages.indexOf(pagesUpdate[indexPageToCompare])) {
						isOrdered = false;
						break outerLoop;
					}
				}
			}
			
			if(isOrdered) {
				correctlyOrderedUpdates.add(update);
			} else {
				isOrdered = true;
			}
		}
		
		return correctlyOrderedUpdates;
	}
	
	/**
	 * Créer un graphe en ne prenant en compte que les noeuds correspondants aux pages de l'update, pour éviter un graphe cyclique
	 * @param update sous la forme "75,97,47,61,53"
	 * @return liste ordonnée des numéros de page selon l'ordre d'update
	 */
	private static ArrayList<String> triTopologiqueSousGraphe(String update) {
		
		//Construire le graphe //TODO erreur ici (tous les noeuds finissent avec un degré 24
		HashMap<String, List<String>> graph = new HashMap<String, List<String>>();
		HashMap<String, Integer> inDegree = new HashMap<String, Integer>();
		
		ArrayList<String> pagesUpdate = new ArrayList<String>();
		Collections.addAll(pagesUpdate, update.split(","));
		
		for(String rule : listPageOrderingRules) {
			String[] pagesNumber = rule.split("\\|");
			
			String firstPage = pagesNumber[0];
			String nextPage = pagesNumber[1];
			
			//Créer un sous graphe en ne prenant en compte qu'une update à la fois
			if(pagesUpdate.contains(firstPage) && pagesUpdate.contains(nextPage)) {
				graph.putIfAbsent(firstPage, new ArrayList<String>());
				graph.putIfAbsent(nextPage, new ArrayList<String>());
				
				//Ajouter le successeur
				graph.get(firstPage).add(nextPage);
				
				//Déterminer le degré de chaque noeud (combien de successeurs)
				/*inDegree.put(nextPage, inDegree.getOrDefault(nextPage, 0) + 1);
	            inDegree.putIfAbsent(firstPage, 0); // Assure l'existence dans le mapping*/
				int oldValue = inDegree.getOrDefault(nextPage, 0);
			    inDegree.put(nextPage, oldValue + 1);
			    inDegree.putIfAbsent(firstPage, 0);
			}

		}
		
		
		//Identifier les sources (degré entrants = 0)
		Queue<String> queue = new LinkedList<String>();
        for (String page : inDegree.keySet()) {
            if (inDegree.get(page) == 0) {
                queue.add(page);
            }
        }
        
        // 3. Appliquer le tri topologique (Algorithme de Kahn)
        ArrayList<String> orderedPages = new ArrayList<String>();
        
        while (!queue.isEmpty()) {
            //Commencer par les sources, qui n'ont pas de prédecesseurs, donc degré 1
            String current = queue.poll();
            orderedPages.add(current);

            //Réduire les degrés des noeuds suivants après "suppression" de leurs prédecesseurs
            for (String next : graph.get(current)) {
            	
                inDegree.put(next, inDegree.get(next) - 1);
                if (inDegree.get(next) == 0) {
                    queue.add(next);
                }
            }
        }
        
		return orderedPages;
	}

	/**
	 * Pour les updates correctement ordonnées, la V1 demande de faire la somme des nombres intermédiaires
	 * @return
	 */
	private static int calcMiddlePagesNumberUpdates(ArrayList<String> updates) {
		int sumMiddlePagesNumber = 0;
		
		for(String update : updates) {
			String[] pagesUpdate = update.split(",");
			
			//check error
			if(pagesUpdate.length % 2 == 0) 
				System.err.println("Une update a une taille paire : " + update);
			
			int indexMiddle = pagesUpdate.length / 2;
			
			sumMiddlePagesNumber += Integer.parseInt(pagesUpdate[indexMiddle]);
		}
		
		return sumMiddlePagesNumber;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		convertInput();
		
		ArrayList<String> correctlyOrderedUpdates = generateOrderedPageList(listUpdates);
		System.out.println("Nombre d'updates correctes = " + correctlyOrderedUpdates.size() + " sur " + listUpdates.size() + " updates");
		
		int resultV1 = calcMiddlePagesNumberUpdates(correctlyOrderedUpdates);
		System.out.println("Resultat final V1 = " + resultV1);
	}

}
