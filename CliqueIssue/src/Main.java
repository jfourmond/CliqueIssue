
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
		
		//Graphe G = new Graphe("./src/petitGraphe.clq");
		Graphe G = new Graphe("./src/C125.9.clq");
		System.out.println(G);
		
		if(G.isClique())
			System.out.println("G est une clique");
		else System.out.println("G n'est pas une clique");
		
		System.out.println("La clique max de G est de taille : " + G.cliqueMax());
		System.out.println("Sommet avec le moins d'arcs : " + (G.getWithLessArcs()+1));
		
		
		Graphe aux = Graphe.getClique(G);
		System.out.println("\n" + aux);
		if(aux.isClique())
			System.out.println("Aux est une clique");
		else System.out.println("Aux n'est pas une clique");
		
		/*
		
		// System.out.println(G1);
		System.out.println(G1);
		
		if(G1.isClique())
			System.out.println("G1 est une clique");
		else System.out.println("G1 n'est pas une clique");
		
		System.out.print("La clique max de G1 est de taille : " + G1.cliqueMax());
		*/
	}
}
