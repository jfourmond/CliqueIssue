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
	 * Teste si le {@link Graphe} courant est une clique
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
	
	/**
	 * Retourne le nombre d'arcs du {@link Graphe} courant
	 * @return int
	 */
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
	 * Retourne le nombre d'arcs absent du sommet passé en paramètre
	 * @param sommet : int
	 * @return int
	 */
	public int getNotNbArcsFrom(int sommet) {
		int notNbArcs = 0;
		for(int i=0 ; i< nbSommets ; i++) {
			if(i != sommet && arcs[sommet][i] == false) notNbArcs++;
		}
		return notNbArcs;
	}
	
	/**
	 * Retoune un graphe sans le sommet passé en paramètre
	 * @param sommet : int
	 * @return {@link Graphe}
	 */
	// TODO Le soucis vient de la suppression d'un sommet, on supprime la colonne et non la ligne (PAS BIEN)
	public Graphe getGrapheWithout(int sommet) {
		Graphe G = new Graphe(this.nbSommets-1);
		boolean new_arcs[][] = new boolean[G.nbSommets][G.nbSommets];
		/*
		int i;
		for(i=0 ; i<sommet ; i++) {
			new_arcs[i] = this.arcs[i];
		}
		// On saute le sommet et continue le traitement (si il y en a encore)
		for(i = sommet+1 ; i<this.nbSommets ; i++) {
			new_arcs[i-1] = this.arcs[i];
		} */
		for(int i=0 ; i<this.nbSommets ; i++) {
			for(int j=0 ; j<this.nbSommets ; j++) {
				if(i != sommet && j != sommet) {
					if (i > sommet) {
						if(j > sommet)
							new_arcs[i-1][j-1] = this.arcs[i][j];
						else 
							new_arcs[i-1][j] = this.arcs[i][j];
					} else {
						if(j > sommet)
							new_arcs[i][j-1] = this.arcs[i][j];
						else
							new_arcs[i][j] = this.arcs[i][j];
					}
				}
			}
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
		int min = 0;

		for(int i=0 ; i<nbSommets ; i++) {
			int nbNotArcs = getNotNbArcsFrom(i);
			if(nbNotArcs > min) {
				min = nbNotArcs;
				sommet = i;
			}
		}
		return sommet;
	}
	
	/**
	 * Retourne le sommet ayant le plus d'arcs
	 * @return sommet : int
	 */
	public int getWithMostArcs() {
		int sommet = -1;
		int max = 0;
		for(int i=0 ; i<nbSommets ; i++) {
			int nbArcs = getNbArcsFrom(i);
			if(nbArcs > max) {
				max = nbArcs;
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
		if(G.isClique()) return G;
		else {
			Graphe Gaux = getClique(G.getGrapheWithout(G.getWithLessArcs()));
			return Gaux;
		}
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