
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
		
		// Argument possible : -graph=file.txt -limit=10000 (en ms)	-method=1
		try {
			new Arguments(args);
		} catch (ArgumentException AE) {
			System.out.println(AE.getArgument() + " isn\'t a valid argument");
			Arguments.showValidArguments();
			return;
		}
		
		if(Arguments.help == true) {
			Arguments.showHelp();
			return;
		}
		
		if(Arguments.graph.equals("")) {
			System.out.println("Pas de graphe, pas de clique.");
			return;
		}
		
		try {
			Graphe G = new Graphe(Arguments.graph);
			Graphe.launchChrono();
			switch(Arguments.method) {
				case 0:
					// On exécute la méthode shlag
					System.out.println("Méthode Shlag ");
					Graphe aux = Graphe.getClique(G);
					System.out.println("Taille de la clique : " + aux.getNbSommets());
					break;
				case 1:
					// On exécute la méthode BK
					System.out.println("Méthode BK ");
					G.showCliquesBK(null, G.getSommets(), null);
					break;
				case 2:
					// On exécute Tomita
					System.out.println("Méthode Tomita ");
					G.showCliquesTomita(null, G.getSommets(), null);
					break;
				default:
					break;
			}
			Graphe.stopChrono();
		} catch(Exception E) {
			E.printStackTrace();
		}
		
		
		// Graphe G = new Graphe("./src/petitGraphe.clq");
		
		
		
		
	}

}
