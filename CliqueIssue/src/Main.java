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
		
		// TODO On admet que tous les graphes n'admettent pas de sommets récursif (d'un sommet à lui-même)
		
		Graphe G = new Graphe("./src/petitGraphe.clq");
		System.out.println(G);
		// Graphe G = new Graphe("./src/C125.9.clq");
		
		G.traitement();
		
		/*
		Graphe G1 = Graphe.getClique(G);
		if(G1.isClique()) {
			System.out.println("G1 est une clique de " + G1.getNbSommets());
		} else System.out.println("G1 n'est pas une clique" + G1.getNbSommets());
		*/
	}
}
