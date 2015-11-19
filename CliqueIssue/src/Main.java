
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
		
		System.out.print("La clique max de G est de taille : " + G.cliqueMax());
	}
}
