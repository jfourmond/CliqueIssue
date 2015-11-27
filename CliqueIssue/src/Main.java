// Pour executer en ligne de commande : java -cp bin Main

public class Main {

	public static void main(String[] args) {
		/**
		 * Une clique dans un graphe G est un graphe partiel C de G tel que les sommets de C sont deux-à-deux adjacents.
		 * Une clique maximale est une clique qui n’est contenue dans une autre clique. Une clique maximum est une
		 * clique de cardinalité maximale, c’est à dire la plus grande clique de G. Une clique maximum est une clique maximale,
		 * mais l’inverse n’est pas vrai.
		 * Etant donné un graphe G, le problème de clique maximum (MaxClique) consiste à déterminer une clique maximum de G
		 * (la solution optimale).
		 */
		
		// Graphe G = new Graphe("./src/petitGraphe.clq");
		Graphe G = new Graphe("./src/C125.9.clq");
		
		// System.out.println("\n\nTRAITEMENT RECURSIF\n");
		// Graphe.traitement_recursif(G, null, null);
		
		// System.out.println("\n\nTRAITEMENT ITERATIF\n");
		// Graphe.traitement(G);
		// System.out.println("-----	Jérôme	-----");
		// G.showCliques(null, G.getSommets(), null);
		
		/*
		Graphe G1 = Graphe.getClique(G);
		if(G1.isClique()) {
			System.out.println("G1 est une clique de " + G1.getNbSommets());
		} else System.out.println("G1 n'est pas une clique" + G1.getNbSommets());
		*/
		
		System.out.println("-----	Antoine	-----");
		G.start_getting_cliques();
		G.afficheListeCliques();
		
		System.out.println("Affichage réussi !");
	}
}
