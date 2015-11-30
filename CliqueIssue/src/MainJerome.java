
public class MainJerome {

	public static void main(String[] args) {
		/**
		 * Une clique dans un graphe G est un graphe partiel C de G tel que les sommets de C sont deux-à-deux adjacents.
		 * Une clique maximale est une clique qui n’est contenue dans une autre clique. Une clique maximum est une
		 * clique de cardinalité maximale, c’est à dire la plus grande clique de G. Une clique maximum est une clique maximale,
		 * mais l’inverse n’est pas vrai.
		 * Etant donné un graphe G, le problème de clique maximum (MaxClique) consiste à déterminer une clique maximum de G
		 * (la solution optimale).
		 */
		 
		for(String s : args) {
			System.out.print("Argument : ");
			System.out.print(s);
			System.out.println("");
		}
		
		// Graphe G = new Graphe("./src/petitGraphe.clq");
		Graphe G = new Graphe("./src/C125.9.clq");
		Graphe.launchChrono();
		G.showCliquesTomita(null, G.getSommets(), null);
		Graphe.stopChrono();
	}

}
