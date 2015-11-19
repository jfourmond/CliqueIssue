import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Graphe {
	private int nbSommets;
	private boolean arcs[][];
	
	public Graphe() {
		nbSommets = 0;
		arcs = new boolean[nbSommets][nbSommets];
	}
	
	public Graphe(int nbSommets) {
		this.nbSommets = nbSommets;
		arcs = new boolean[nbSommets][nbSommets];
	}
	
	public Graphe(String filename) {
		int nbSommets = 0;
		try{
			FileInputStream fis 	=	new FileInputStream(filename); 
			InputStreamReader ipsr 	= 	new InputStreamReader(fis);
			BufferedReader br 		=	new BufferedReader(ipsr);
			String ligne;
			// Récupération du nombre de sommets
			while ((ligne=br.readLine())!=null){
				if(ligne.startsWith("p")) {
					String[] split_line = ligne.split(" ");
					nbSommets = Integer.parseInt(split_line[2]);
					break;
				}
			}
			this.nbSommets = nbSommets;
			arcs = new boolean[nbSommets][nbSommets];
			init();
			// Récupération des arcs
			while ((ligne=br.readLine())!=null){
				if(ligne.startsWith("e")) {
					String[] split_ligne = ligne.split(" ");
					int from = Integer.parseInt(split_ligne[1]);
					int to = Integer.parseInt(split_ligne[2]);
					System.out.print("From " + from + " To " + to + "\n");
					arcs[from-1][to-1] = true;
					arcs[to-1][from-1] = true;
				}
			}
			br.close();
			ipsr.close();
			fis.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	//	GETTERS
	public int getNbSommets() {
		return nbSommets;
	}
	
	public boolean[][] getArcs() {
		return arcs;
	}
	
	//	SETTERS
	public void setNbSommets(int nbSommets) {
		this.nbSommets = nbSommets;
	}
	
	public void setArcs(boolean[][] arcs) {
		this.arcs = arcs;
	}
	
	@Override
	public String toString() {
		super.toString();
		String ch = "Sommets  : " + nbSommets + "\n" +
					"Arcs : \n";
		for(int i=0 ; i<nbSommets ; i++) {
			ch += "| ";
			for(int j=0 ; j<nbSommets ; j++) {
				ch += arcs[i][j] + " |";
			}
			ch += "\n";
		}
		return ch;
	}
	
	//	METHODES
	/**
	 * Initialise la matrice d'arcs à false
	 */
	public void init() {
		for(int i=0 ; i<nbSommets ; i++) {
			for(int j=0 ; j<nbSommets ; j++) {
				arcs[i][j] = false;
			}
		}
	}
	
	/**
	 * Teste si le @link Graphe est une clique
	 * @return <code>true</code> si le Graphe est une clique, <code>false</code> sinon
	 */
	public boolean isClique() {
		for(int i=0 ; i<nbSommets ; i++) {
			for(int j=0 ; j<nbSommets ; j++) { 
				if((i != j && arcs[i][j] == false))
					return false;
			}
		}
		return true;
	}
	
	public boolean isCliqueBis(ArrayList<Integer> sommets){
		for(Integer s1 : sommets){
			for(Integer s2 : sommets){
				if(arcs[s1][s2] == false) 
					return false;
			}
		}
		return true;
	}
	
	public boolean existingClique(int nbSom){
		for(int i = 0; i < nbSom; ++i){ //i est le sommet qu'on ne comptabilise pas pour le moment
			ArrayList<Integer> sommets = new ArrayList<Integer>();
			for(int j = 0; j < nbSom; ++j){ // tous les sommets sauf i
				if(j != i) sommets.add(j);
			}
			if(isCliqueBis(sommets)) return true;
		}
		return false;
	}
	
	public int cliqueMax(){
		for(int i = nbSommets; i > 0; --i){
			if(existingClique(i)) return i;
		}
		return 0;
	}
	
}
