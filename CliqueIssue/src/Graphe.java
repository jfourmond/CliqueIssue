
public class Graphe {
	private int nb_col;
	private int nb_row;
	private boolean arcs[][];
	
	public Graphe() {
		nb_col = 0;
		nb_row = 0;
		arcs = new boolean[nb_col][nb_row];
	}
	
	public Graphe(int col, int row) {
		nb_col = col;
		nb_row = row;
		arcs = new boolean[nb_col][nb_row];
	}
	
	// TODO création d'un graphe à partir d'un fichier
	public Graphe(String filename) {
		
	}
}
