
public class Main {

	public static void main(String[] args) {
		/*
		Graphe G = new Graphe(2);
		G.init();
		System.out.println(G);
		*/
		
		Graphe G1 = new Graphe("./src/petitGraphe.clq");
		System.out.println(G1);
		
		System.out.print("La clique max de G1 est de taille : "+G1.cliqueMax());
	}
}
