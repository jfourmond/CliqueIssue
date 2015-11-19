
public class Main {

	public static void main(String[] args) {
		/*
		Graphe G = new Graphe(2);
		G.init();
		System.out.println(G);
		*/
		
		Graphe G = new Graphe("./src/petitGraphe.clq");
		System.out.println(G);
		
		System.out.print("La clique max de G est de taille : " + G.cliqueMax());
	}
}
