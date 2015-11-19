import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Graphe {
	private int nbSommets;
	private boolean arcs[][];
	
	/**
	 * Constructeur vide d'un {@link Graphe}
	 */
	public Graphe() {
		nbSommets = 0;
		arcs = new boolean[nbSommets][nbSommets];
	}
	
	/**
	 * Constructeur d'un {@link Graphe} à partir du nombre de de sommets
	 * @param nbSommets : int
	 */
	public Graphe(int nbSommets) {
		this.nbSommets = nbSommets;
		arcs = new boolean[nbSommets][nbSommets];
	}
	
	/**
	 * Constructeur d'un {@link Graphe} à partir d'un fichier
	 * @param filename : String
	 */
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
					// System.out.print("From " + from + " To " + to + "\n");
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
	
	public boolean[] getArcs(int sommet) {
		return this.arcs[sommet];
	}
	
	//	SETTERS
	public void setNbSommets(int nbSommets) {
		this.nbSommets = nbSommets;
	}
	
	public void setArcs(boolean[][] arcs) {
		this.arcs = arcs;
	}
	
	public void setArcs(boolean[] arcs, int sommet) {
		this.arcs[sommet] = arcs;
	}
	
	@Override
	public String toString() {
		super.toString();
		String ch = "Sommets  : " + nbSommets + "\n" +
					"Arcs : " + getNbArcs() + "\n";
		for(int i=0 ; i<nbSommets ; i++) {
			ch += "| ";
			for(int j=0 ; j<nbSommets ; j++) {
				ch += (arcs[i][j] ? 1 : 0) + " | ";
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
			Arrays.fill(arcs[i], false);
		}
	}
	
	/**
	 * Teste si le {@link Graphe} est une clique
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
	
	public int getNbArcs() {
		int nbArcs = 0;
		for(int i=0 ; i<nbSommets ; i++) {
			for(int j=0 ; j<nbSommets ; j++) {
				if(arcs[i][j] == true) nbArcs++;
			}
		}
		return (nbArcs/2);
	}
	
	/**
	 * Retourne le nombre d'arcs partant du sommet passé en paramètre
	 * @param sommet : int
	 * @return int
	 */
	public int getNbArcsFrom(int sommet) {
		int nbArcs = 0;
		for(int i=0 ; i< nbSommets ; i++) {
			if(arcs[sommet][i] == true) nbArcs++;
		}
		return nbArcs;
	}
	
	/**
	 * Retoune un graphe sans le sommet passé en paramètre
	 * @param sommet : int
	 * @return {@link Graphe}
	 */
	public Graphe getGrapheWithout(int sommet) {
		Graphe G = new Graphe(this.nbSommets-1);
		boolean new_arcs[][] = new boolean[G.nbSommets][G.nbSommets];
		// On récupère la première partie
		int i;
		for(i=0 ; i<sommet ; i++) {
			new_arcs[i] = this.arcs[i];
		}
		// On saute le sommet et continue le traitement (si il y en a encore)
		for(i = sommet+1 ; i<this.nbSommets ; i++) {
			new_arcs[i-1] = this.arcs[i];
		}
		G.setArcs(new_arcs);
		return G;
	}
	
	/**
	 * Retourne le sommet ayant le moins d'arcs
	 * @return sommet : int
	 */
	public int getWithLessArcs() {
		int sommet = -1;
		int min = nbSommets;
		for(int i=0 ; i<nbSommets ; i++)  {
			int nbArcs = getNbArcsFrom(i);
			if(nbArcs < min) {
				min = nbArcs;
				sommet = i;
			}
		}
		return sommet;
	}
	
	/**
	 * Retoune un {@link Graphe} etant une clique à partir du {@link Graphe} G
	 * @param G : {@link Graphe}
	 * @return {@link Graphe}
	 */
	public static Graphe getClique(Graphe G) {
		System.out.println("Graphe de taille : " + G.nbSommets);
		if(G.isClique()) return G;
		else return getClique(G.getGrapheWithout(G.getWithLessArcs()));
	}
	
	public boolean isClique(ArrayList<Integer> sommets){
		for(Integer s1 : sommets){
			for(Integer s2 : sommets){
				if((s1 != s2 && arcs[s1][s2] == false)) 
					return false;
			}
		}
		return true;
	}
	
	// TODO Le traitement ne se fait que dans un seul sens, traiter les autres ?
	public boolean existingClique(int nbSom){
		System.out.println("Sommet : " + nbSom);
		for(int i = 0 ; i < nbSom ; ++i){ //i est le sommet qu'on ne comptabilise pas pour le moment
			ArrayList<Integer> sommets = new ArrayList<Integer>();
			for(int j = 0; j < nbSom; ++j){ // tous les sommets sauf i
				if(j != i) sommets.add(j);
			}
			if(isClique(sommets)) return true;
		}
		return false;
	}
	
	public int cliqueMax(){
		if(isClique()) return nbSommets; // Pas besoin de tester toute la matrice si le graphe d'origine est une clique
		for(int i = nbSommets ; i > 0 ; --i){	// Le traitement ne s'effectue pas sur tous les sommets ?
			if(existingClique(i)) return i;
			// TODO Attention on renvoie le sommet et non la taille de la Clique Maximale
		}
		return 0;
	}	
}