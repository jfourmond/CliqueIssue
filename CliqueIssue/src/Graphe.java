import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Graphe {
	private int nbSommets;
	private boolean arcs[][];
	private ArrayList<ArrayList<Integer>> listeCliques;
	
	public static ArrayList<Integer> maximumClique = new ArrayList<>();
	public static ArrayList<Integer> sommetExclus = new ArrayList<>();
	public static long chrono;
	
	/**
	 * Constructeur vide d'un {@link Graphe}
	 */
	public Graphe() {
		nbSommets = 0;
		arcs = new boolean[nbSommets][nbSommets];
		listeCliques = new ArrayList<ArrayList<Integer>>();
	}
	
	/**
	 * Constructeur d'un {@link Graphe} à partir du nombre de de sommets
	 * @param nbSommets : int
	 */
	public Graphe(int nbSommets) {
		this.nbSommets = nbSommets;
		arcs = new boolean[nbSommets][nbSommets];
		listeCliques = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < nbSommets; ++i){
			ArrayList<Integer> l = new ArrayList<Integer>();
			listeCliques.add(l);
		}
	}
	
	/**
	 * Constructeur d'un {@link Graphe} à partir d'un fichier
	 * @param filename : String
	 */
	public Graphe(String filename) throws Exception {
		int nbSommets = 0;
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
		
		//listeClique à créér aussi
		listeCliques = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < nbSommets; ++i){
			ArrayList<Integer> l = new ArrayList<Integer>();
			listeCliques.add(l);
		}
		
		
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
	}
	
	/*	GETTERS	*/
	public int getNbSommets() {
		return nbSommets;
	}
	
	public boolean[][] getArcs() {
		return arcs;
	}
	
	public boolean[] getArcs(int sommet) {
		return this.arcs[sommet];
	}
	
	public ArrayList<ArrayList<Integer>> getListeCliques(){
		return listeCliques;
	};
	
	/*	SETTERS	*/
	public void setNbSommets(int nbSommets) {
		this.nbSommets = nbSommets;
	}
	
	public void setArcs(boolean[][] arcs) {
		this.arcs = arcs;
	}
	
	public void setArcs(boolean[] arcs, int sommet) {
		this.arcs[sommet] = arcs;
	}
	
	public void setListeCliques(ArrayList<ArrayList<Integer>> liste_cliques){
		listeCliques = liste_cliques;
	}
	
	@Override
	public String toString() {
		super.toString();
		String ch = Stats();
		ch += Graph();
		return ch;
	}
	
	/*	METHODES AFFICHAGE	*/
	public String Stats() {
		String ch = "Sommets  : " + nbSommets + "\n" +
				"Arcs : " + getNbArcs() + "\n";
		return ch;
	}
	
	public String Graph() {
		String ch = "";
		
		for(int i=0 ; i<nbSommets ; i++) {
			ch += "| ";
			for(int j=0 ; j<nbSommets ; j++) {
				ch += (arcs[i][j] ? 1 : 0) + " | ";
			}
			ch += "\n";
		}
		
		return ch;
	}
	
	/*	METHODES	*/
	
	/**
	 * Initialise la matrice d'arcs à false
	 */
	public void init() {
		for(int i=0 ; i<nbSommets ; i++) {
			Arrays.fill(arcs[i], false);
		}
	}

	/**
	 * Retouche une liste des sommets du {@link Graphe} courant
	 * @return {@link ArrayList}
	 */
	public ArrayList<Integer> getSommets() {
		ArrayList<Integer> result = new ArrayList<>();
		for(int i=0 ; i<nbSommets ; i++)
			result.add(i);
		return result;
	}
	
	/**
	 * A utiliser après l'exécution de la methode classique getClique
	 * Retourne une liste des sommets du {@link Graphe} courant sans les sommets exclus
	 * C'est à dire la clique maximale
	 * @return ArrayList<Integer> : sommets
	 */
	public ArrayList<Integer> getSommetsNonExclus() {
		return difference(this.getSommets(), Graphe.sommetExclus);
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
	public Graphe getGrapheWithout(int sommet) {
		Graphe G = new Graphe(this.nbSommets-1);
		boolean new_arcs[][] = new boolean[G.nbSommets][G.nbSommets];
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
	 * Retourne le sommet ayant le plus d'arcs parmi la liste de sommets passée en paramètre
	 * @param sommets : ArrayList<Integer>
	 * @return sommet : int
	 */
	public int getWithMostArcs(ArrayList<Integer> sommets) {
		int sommet = -1;
		int max = 0;
		for(int s : sommets) {
			int nbArcs = getNbArcsFrom(s);
			if(nbArcs > max) {
				max = nbArcs;
				sommet = s;
			}
		}
		return sommet;
	}
	
	/**
	 * Retourne les sommets voisins du sommet passé en paramètre
	 * @param sommet : int
	 * @return ArrayList<Integer>
	 */
	public ArrayList<Integer> getNeightbors(int sommet) {
		ArrayList<Integer> voisins = new ArrayList<>();
		for(int i=0 ; i<nbSommets ; i++) {
			if(arcs[sommet][i] == true) voisins.add(i);
		}
		return voisins;
	}
	
	/**
	 * Retourne le sommet, dans la liste, ayant le plus de voisin
	 * @param sommets : {@link ArrayList}
	 * @return int
	 */
	public int getMostNeightbors(ArrayList<Integer> sommets) {
		int sommet = -1;
		int max = -1;
		for(int s : sommets) {
			int nbArcs = getNbArcsFrom(s);
			if(nbArcs > max) {
				max = nbArcs;
				sommet = s;
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
	
	/**
	 * Retourne VRAI si le graphe composé des colonnes et lignes ayant leurs indices contenus dans la liste en paramètre
	 * est une clique
	 * @param sommets : {@link ArrayList}
	 * @return {@link Boolean}
	 */
	public boolean isClique(ArrayList<Integer> sommets){
		for(Integer s1 : sommets){
			for(Integer s2 : sommets){
				if((s1 != s2 && arcs[s1][s2] == false)) 
					return false;
			}
		}
		return true;
	}
	
	/**
	 * Traitement Recursif d'une recherche de clique maximale
	 * Algorithme de Bron-Kerbosch récursif
	 * S'arrêtera au bout de cinq minutes
	 * @param Result : {@link ArrayList}
	 * @param Candidates : {@link ArrayList}
	 * @param Exclude : {@link ArrayList}
	 */
	public void showCliquesBK(ArrayList<Integer> Result, ArrayList<Integer> Candidates, ArrayList<Integer> Exclude) {
		if(Arguments.limit != -1 && getChrono() > Arguments.limit) return;
		if(Result == null) Result = new ArrayList<>();
		if(Exclude == null) Exclude = new ArrayList<>();
		
		if(Candidates.isEmpty() && Exclude.isEmpty()) {
			if(Result.size() > maximumClique.size()) {
				maximumClique = Result;
				Collections.sort(maximumClique);
				System.out.println("Nouvelle Clique Maximale : " + maximumClique + "(" + maximumClique.size() + ")");
				System.out.println("----------\t" + getChrono() + " ms");
			}
			return;
		}
		while(!Candidates.isEmpty()) {
			int x = Candidates.get(0);
			showCliquesBK(union(Result, x), intersection(Candidates, getNeightbors(x)), intersection(Exclude, getNeightbors(x)));
			Candidates.remove(0);
			Exclude = union(Exclude, x);
		}
	}
	
	/**
	 * Traitement Recursif d'une recherche de clique maximale
	 * Algorithme de Tomita
	 * S'arrêtera au bout de cinq minutes
	 * @param Result : {@link ArrayList}
	 * @param Candidates : {@link ArrayList}
	 * @param Exclude : {@link ArrayList}
	 */
	public void showCliquesTomita(ArrayList<Integer> Result, ArrayList<Integer> Candidates, ArrayList<Integer> Exclude) {
		if(Arguments.limit != -1 && getChrono() > Arguments.limit) return;
		if(Result == null)
			Result = new ArrayList<>();
		if(Exclude == null)
			Exclude = new ArrayList<>();
		
		if(Candidates.isEmpty() && Exclude.isEmpty()) {
			if(Result.size() > maximumClique.size()) {
				maximumClique = Result;
				Collections.sort(maximumClique);
				System.out.println("Nouvelle Clique Maximale : " + maximumClique + "(" + maximumClique.size() + ")");
				System.out.println("----------\t" + getChrono() + " ms");
			}
			return;
		}
		ArrayList<Integer> aux = union(Candidates, Exclude);
		int most_neighbors = getMostNeightbors(aux);
		ArrayList<Integer> toCompute = difference(Candidates, getNeightbors(most_neighbors));
		for(int sommet : toCompute) {
			showCliquesTomita(union(Result, sommet), intersection(Candidates, getNeightbors(sommet)), intersection(Exclude, getNeightbors(sommet)));
			Candidates.remove(Candidates.indexOf(sommet));
			Exclude = union(Exclude, sommet);
		}
	}
	
	/**
	 * Effecture l'union de deux {@link ArrayList}
	 * @param A : {@link ArrayList}
	 * @param B : {@link ArrayList}
	 * @return {@link ArrayList}
	 */
	private static ArrayList<Integer> union(ArrayList<Integer> A, ArrayList<Integer> B) {
		ArrayList<Integer> result = new ArrayList<>();
		for(int x : A)
			result.add(x);
		for(int x : B)
			if(!result.contains(x))
				result.add(x);
		return result;
	}
	
	/**
	 * Effectue l'intersection entre un {@link ArrayList} et un {@link Integer}
	 * @param A : {@link ArrayList}
	 * @param x : {@link Integer}
	 * @return {@link ArrayList}
	 */
	private static ArrayList<Integer> union(ArrayList<Integer> A, int x) {
		ArrayList<Integer> result = new ArrayList<>(A);
		if(!result.contains(x)) result.add(x);
		return result;
	}
	
	/**
	 * Effectue l'intersection de deux {@link ArrayList}
	 * @param A : {@link ArrayList}
	 * @param B : {@link ArrayList}
	 * @return {@link ArrayList}
	 */
	private static ArrayList<Integer> intersection(ArrayList<Integer> A, ArrayList<Integer> B) {
		ArrayList<Integer> result = new ArrayList<>();
        for (int x : A)
            if((B).contains(x))
                result.add(x);
        return result;
	}

	/**
	 * Supprime les éléments de B dans A
	 * @param A : {@link ArrayList}
	 * @param B : {@link ArrayList}
	 * @return {@link ArrayList}
	 */
	private static ArrayList<Integer> difference(ArrayList<Integer> A, ArrayList<Integer> B) {
		ArrayList<Integer> result = new ArrayList<>(A);
		for(int x : B)
			if(result.contains(x))
				result.remove(result.indexOf(x));
		return result;
	}
	
	/**
	 * Initialise le chrono
	 */
	public static void launchChrono() {
		chrono = System.currentTimeMillis();
	}
	
	/**
	 * Affiche le temps écoulé
	 */
	public static void stopChrono() {
		long chrono_now = System.currentTimeMillis();
		System.out.println("Temps écoulé : " + (chrono_now - chrono) + " ms");
	}
	
	/**
	 * Récupère le temps écoulé
	 * @return {@link Long}
	 */
	public static long getChrono() {
		long chrono_now = System.currentTimeMillis();
		return (chrono_now - chrono);
	}
	
	//retourne la liste d'entiers sans le i-ème
	/**
	 * Retourne la liste d'entiers sans le i-ème
	 * @param indices {@link ArrayList}
	 * @param i {@link Integer}
	 * @return {@link ArrayList}
	 */
	ArrayList<Integer> getListWithoutI(ArrayList<Integer> indices, int i){
		ArrayList<Integer> res = new ArrayList<Integer>();
		for(int n = 0 ; n < indices.size(); ++n){
			if(n != i) res.add(indices.get(n));
		}
		
		return res;
	}
	
	
	//Retourne les cliques de taille n-1 à partir d'une clique de taille n
	/**
	 * Retourne les cliques de taille n-1 à partir d'une clique de taille n
	 * @param indices {@link ArrayList}
	 * @return {@link ArrayList<ArrayList>}
	 */
	ArrayList<ArrayList<Integer>> getSousCliques(ArrayList<Integer> indices){
		int taille_min = 2;
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if(indices.size() >= taille_min){
			//alors parcours de toutes les sous-cliques
			for(int i = 0; i < indices.size(); ++i){
				ArrayList<Integer> sousClique = getListWithoutI(indices, i);
				if(sousClique.size() >= taille_min) res.add(sousClique);
			}
		}
		return res;
	}
	
	/**
	 * Teste si les deux cliques sont identiques
	 * @param clique1 : {@link ArrayList}
	 * @param clique2 : {@link ArrayList}
	 * @return {@link Boolean}
	 */
	boolean sameClique(ArrayList<Integer> clique1, ArrayList<Integer> clique2){
		boolean res = true;
		if(clique1.size() != clique2.size()) return false;
		for(Integer i : clique1){
			if(!clique2.contains(i)) return false;
		}
		
		return res;
	}
	
	/**
	 * Teste si la clique est déjà dans la liste de Cliques
	 * @param clique : {@link ArrayList}
	 * @return {@link Boolean}
	 */
	boolean isPresent(ArrayList<Integer> clique){
		boolean res = false;
		for(ArrayList<Integer> clique_stockee : getListeCliques()){
			if(sameClique(clique, clique_stockee)) return true;
		}
		
		return res;
	}

	/**
	 * Ajoute une clique à la liste de Cliques
	 * @param clique : {@link ArrayList}
	 */
	void ajoutClique(ArrayList<Integer> clique){
		if(!isPresent(clique)) listeCliques.add(clique);
		
	}
	
	/**
	 * Retourne toutes les sous-cliques
	 * @param indices_clique : {@link ArrayList}
	 */
	void getEverySingleClique(ArrayList<Integer> indices_clique){
		if(!indices_clique.isEmpty()){
			for(ArrayList<Integer> sous_clique : getSousCliques(indices_clique)){
				if(isClique(sous_clique)) ajoutClique(sous_clique);
				getEverySingleClique(sous_clique);
			}
		}
	}
	
	//Retourne une liste de listes d'indices où une liste d'entiers correspond aux colonnes et lignes d'une clique
	void start_getting_cliques(){
		ArrayList<Integer> indices_pionniers = new ArrayList<Integer>();
		for(int i = 0; i < getNbSommets(); ++i){
			indices_pionniers.add(i);
		}
		System.out.println("");
		getEverySingleClique(indices_pionniers);
	}
	
	//Affiche les cliques contenues dans listeCliques
	void afficheListeCliques(){
		if(!getListeCliques().isEmpty()){
			System.out.println("Nombre de cliques stockées : "+getListeCliques().size());
			for(ArrayList<Integer> clique : getListeCliques()){
				for(Integer i : clique){
					System.out.print(i+" ");
				}
				System.out.println("");
			}
		} else {
			System.out.println("ListeCliques est vide...");
		}
	}
	
	//Affiche les cliques contenues dans listeCliques
	void afficheListeCliques(ArrayList<ArrayList<Integer>> listeClique){
		if(!listeClique.isEmpty()){
			for(ArrayList<Integer> clique : listeClique){
				for(Integer i : clique){
					System.out.print(i+" ");
				}
				System.out.println("");
			}
		} else {
			System.out.println("ListeCliques est vide...");
		}
	}
	
	/**
	 * Retourne la taille de la plus grand clique de la liste de Cliques
	 * @return {@link Integer}
	 */
	int getBiggestClique(){
		int res = 0;
			for(ArrayList<Integer> clique : getListeCliques()){
				if(res < clique.size()) res = clique.size();
			}
		
		return res;
	}
	
	/**
	 * Teste si clique1 est inclus dans clique2
	 * @param clique1 : {@link ArrayList}
	 * @param clique2 : {@link ArrayList}
	 * @return {@link Boolean}
	 */
	boolean isIncludedClique(ArrayList<Integer> clique1, ArrayList<Integer> clique2){
		for(Integer i : clique1){
			if(!clique2.contains(i)) return false;
		}
		
		return true;
	}
	
	/**
	 * Teste si une clique est déjà incluse dans une liste de cliques
	 * @param clique : {@link ArrayList}
	 * @param listeCliques : {@link ArrayList<ArrayList>}
	 * @return {@link Boolean}
	 */
	boolean isIncludedListeCliques(ArrayList<Integer> clique, ArrayList<ArrayList<Integer>> listeCliques){
		for(ArrayList<Integer> l_clique : listeCliques){
			if(!sameClique(clique, l_clique)){
				if(clique.size() < l_clique.size()){
					if(isIncludedClique(clique, l_clique)) return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Trie la liste des cliques par taille
	 * @return {@link ArrayList<ArrayList>}
	 */
	ArrayList<ArrayList<Integer>> tri_listeCliques(){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		for(int i = getBiggestClique(); i > 0; --i){
			for(ArrayList<Integer> clique : getListeCliques()){
				if(clique.size() == i) res.add(clique);
			}
		}
		return res;
	}
	
	//retourne les cliques maximales parmis les cliques de listeCliques
	//On regarde (DESC) les cliques qui ne sont pas déjà inclues dans res, pour éliminer les "inclusions"
	ArrayList<ArrayList<Integer>> getAllCliquesMax(){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		for(ArrayList<Integer> clique : tri_listeCliques()){
			if(!isIncludedListeCliques(clique, res)) res.add(clique);
		}
		
		return res;
	}
	
}