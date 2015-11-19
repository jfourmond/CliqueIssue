
public class Main {

	public static void main(String[] args) {
		/*
		Graphe G = new Graphe(2);
		G.init();
		System.out.println(G);
		*/
		
		Graphe G = new Graphe("./src/petitGraphe.clq");
		System.out.println(G);
		
		if(G.isClique())
			System.out.println("G est une clique");
		else System.out.println("G n'est pas une clique");
		
		System.out.println("La clique max de G est de taille : " + G.cliqueMax());
		
		Graphe G1 = new Graphe("./src/C125.9.clq");
		// System.out.println(G1);
		
		if(G1.isClique())
			System.out.println("G1 est une clique");
		else System.out.println("G1 n'est pas une clique");
		
		System.out.print("La clique max de G1 est de taille : " + G1.cliqueMax());
	}
}
