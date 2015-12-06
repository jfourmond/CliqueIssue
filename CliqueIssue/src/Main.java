import java.io.FileNotFoundException;

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
		
		if(args.length == 0) {
			Arguments.showValidArguments();
			return;
		}
		
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
			Graphe aux;
			
			if(G.getNbSommets() == 0) {
				System.out.println("Le fichier ne décrit pas un graphe");
				return;
			}
			
			switch(Arguments.show) {
				case "all":
					System.out.println(G);
					break;
				case "stats":
					System.out.println(G.Stats());
					break;
				case "graph":
					System.out.println(G.Graph());
					break;
				default:
					System.out.println(G);
					break;
			}
			
			Graphe.launchChrono();
			switch(Arguments.method) {
				case 0:
					// On exécute la méthode Classique
					System.out.println("Méthode Classique ");
					aux = Graphe.getClique(G);
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
				case 3:
					// On execute la méthode Classique n°2
					System.out.println("Méthode Classique n°2 ");
					G.start_getting_cliques();
					G.afficheListeCliques(G.getAllCliquesMax());
					break;
				default:
					// On exécute la méthode Classique
					System.out.println("Méthode Classique ");
					aux = Graphe.getClique(G);
					System.out.println("Taille de la clique : " + aux.getNbSommets());
					System.out.println(G.getSommetsNonExclus());
					break;
			}
			Graphe.stopChrono();
		} catch(FileNotFoundException FNFE) {
			System.out.println("Fichier non trouvé");
		} catch(Exception E) {
			E.printStackTrace();
		}
	}
}
