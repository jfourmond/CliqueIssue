
public class MainAntoine {

	public static void main(String[] args) {
		
		System.out.println("Main Antoine !");
		
		Graphe G = new Graphe("./src/petitGraphe.clq");
		
		G.start_getting_cliques();
		//G.afficheListeCliques();
		
		//System.out.println("Plus grande clique : "+G.getBiggestClique());
		
		G.afficheListeCliques(G.tri_listeCliques());
		
		System.out.println("Affichage réussi !");
		
	}
}
