import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Graphe {
	private int nbSommets;
	private boolean arcs[][];
	private ArrayList<ArrayList<Integer>> listeCliques;
	
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
	
	public ArrayList<ArrayList<Integer>> getListeCliques(){
		return listeCliques;
	};
	
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
	
	public void setListeCliques(ArrayList<ArrayList<Integer>> liste_cliques){
		listeCliques = liste_cliques;
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
	public ArrayList<Integer> getSommets() {
		ArrayList<Integer> result = new ArrayList<>();
		for(int i=0 ; i<nbSommets ; i++)
			result.add(i);
		return result;
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
	
	//Retourne true si le graphe composé des colonnes et lignes ayant leurs indices contenus dans sommets est une clique
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
	
	public static void traitement(Graphe G) {
		// Liste des sommets déjà compris comme partie de la clique
		ArrayList<Integer> compsub = new ArrayList<>(); 
		// Liste des sommets candidats, connectés avec tous les noeuds de compsub
		ArrayList<Integer> candidats = new ArrayList<>();
		// Liste des sommets déjà analysés, menant à une extension valide de compsub et qui ne devraient pas être utilisées
		ArrayList<Integer> not = new ArrayList<>();
		// On prend le premier sommet avec le plus de voisin
		int sommet;
		
		sommet = G.getWithMostArcs();
		System.out.println("Sommet avec le plus de voisins : " + sommet);
		// On l'ajoute à compsub
		compsub.add(sommet);
		
		// Voisins du sommet courant
		ArrayList<Integer> neightbors;
		neightbors = G.getNeightbors(sommet);
		System.out.println("Voisins de " + sommet + " : " + neightbors);
		// Les candidats susceptibles de former une clique sont les voisins du sommet
		candidats = neightbors;
		
		/**
		 * Une clique est trouvée si :
		 * 	il n'y a pas plus de candidats ET
		 * 	il n'y a plus de sommets dans not (sinon ce n'est pas une clique maximale)
		 */
		while(true) {
			sommet = G.getWithMostArcs(candidats);
			System.out.println("Sommet avec le plus de voisins de " + candidats + " : " + sommet);
			
			compsub.add(sommet);
			
			neightbors = G.getNeightbors(sommet);
			candidats = intersection(candidats, neightbors);
			System.out.println("Voisins de " + sommet + " : " + neightbors);
			System.out.println("Intersection : " + candidats);
			
			if(candidats.isEmpty() && not.isEmpty()) break;
		}
		System.out.println("La clique : " + compsub);
		System.out.println("Clique : " + compsub.size());
		
		
	}

	public static void traitement_recursif(Graphe G, ArrayList<Integer> P, ArrayList<Integer> X) {
		// Liste des sommets déjà compris comme partie de la clique
		ArrayList<Integer> compsub = new ArrayList<>(); 
		// Liste des sommets candidats, connectés avec tous les noeuds de compsub
		ArrayList<Integer> candidats = new ArrayList<>();
		// Liste des sommets déjà analysés, menant à une extension valide de compsub et qui ne devraient pas être utilisées
		ArrayList<Integer> not = new ArrayList<>();
		
		int sommet;
		
		if(P == null && X == null) {		// Premier tour de procédure	
			sommet = G.getWithMostArcs(); 	// On prend le sommet avec le plus de voisins
			System.out.println("Sommet avec le plus de voisins : " + sommet);
			// On l'ajoute à compsub
			compsub.add(sommet);
			
			// Voisins du sommet courant
			candidats = G.getNeightbors(sommet);
			System.out.println("Voisins de " + sommet + " : " + candidats);
			return;
		}
		
		if(P.isEmpty() && X.isEmpty()) {
			System.out.println("Clique Maximal");
			return;
		}
		return;
			
	}
	
	/**
	 * Effectue l'intersection de deux {@link ArrayList}
	 * @param A : {@link ArrayList}
	 * @param B : {@link ArrayList}
	 * @return {@link ArrayList}
	 */
	private static ArrayList<Integer> intersection(ArrayList<Integer> A, ArrayList<Integer> B) {
		ArrayList<Integer> result = new ArrayList();
        for (int x : A) {
            if((B).contains(x)) {
                result.add(x);
            }
        }
        return result;
	}

	//retourne la liste d'entiers sans le i-ème
	ArrayList<Integer> getListWithoutI(ArrayList<Integer> indices, int i){
		ArrayList<Integer> res = new ArrayList<Integer>();
		for(int n = 0 ; n < indices.size(); ++n){
			if(n != i) res.add(indices.get(n));
		}
		
		return res;
	}
	
	
	//Retourne les cliques de taille n-1 à partir d'une clique de taille n
	ArrayList<ArrayList<Integer>> getSousCliques(ArrayList<Integer> indices){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if(isClique(indices)){
			if(indices.size() >= 2){
				//alors parcours de toutes les sous-cliques
				for(int i = 0; i < indices.size(); ++i){
					ArrayList<Integer> sousClique = getListWithoutI(indices, i);
					res.add(sousClique);
				}
			}
		}
		return res;
	}
	
	//ajoute une Clique à listeCliques
	void ajoutClique(ArrayList<Integer> clique){
		listeCliques.add(clique);
		
	}
	
	//Retourne toutes les sous cliques
	void getEverySingleClique(ArrayList<Integer> indices_clique){
		if(!indices_clique.isEmpty()){
			for(ArrayList<Integer> sous_clique : getSousCliques(indices_clique)){
				ajoutClique(sous_clique);
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
			for(ArrayList<Integer> clique : getListeCliques()){
				for(Integer i : clique){
					System.out.print(i);
				}
				System.out.println("");
			}
		} else {
			System.out.println("ListeCliques est vide...");
		}
	}
	
	
}