
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
		// TODO Soucis actuel : l'algorithme supprime tous les sommets qui ont un sommet en moins.
		
		
		// Graphe G = new Graphe("./src/petitGraphe.clq");
		Graphe G = new Graphe("./src/testGraphe25.txt");
		System.out.println("Sommets  : " + G.getNbSommets() + "\n" +
				"Arcs : " + G.getNbArcs() + "\n");
		
		System.out.println(G);
		
		int max = G.getWithMostArcs();
		int min = G.getWithLessArcs();
		
		System.out.println("Min : " + min);
		System.out.println("Max : " + max);
		
		Graphe G1 = Graphe.getClique(G);
		System.out.println(G1);
	}
}
