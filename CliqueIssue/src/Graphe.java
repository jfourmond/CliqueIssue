
public class Graphe {
	private int nbCol;
	private int nbRow;
	private boolean arcs[][];
	
	public Graphe() {
		nbCol = 0;
		nbRow = 0;
		arcs = new boolean[nbCol][nbRow];
	}
	
	public Graphe(int col, int row) {
		nbCol = col;
		nbRow = row;
		arcs = new boolean[nbCol][nbRow];
	}
	
	// TODO création d'un graphe à partir d'un fichier
	public Graphe(String filename) {
		
	}
	
	//	GETTERS
	public int getNbCol() {
		return nbCol;
	}
	
	public int getNbRow() {
		return nbRow;
	}
	
	public boolean[][] getArcs() {
		return arcs;
	}
	
	//	SETTERS
	public void setNbCol(int nbCol) {
		this.nbCol = nbCol;
	}
	
	public void setNbRow(int nbRow) {
		this.nbRow = nbRow;
	}
	
	public void setArcs(boolean[][] arcs) {
		this.arcs = arcs;
	}
	
	@Override
	public String toString() {
		super.toString();
		String ch = "Colonnes  : " + nbCol + "\n" +
					"Lignes : " + nbRow + "\n" + 
					"Arcs : \n";
		for(int i=0 ; i<nbCol ; i++) {
			ch += "| ";
			for(int j=0 ; j<nbRow ; j++) {
				ch += arcs[i][j] + " |";
			}
			ch += "\n";
		}
		return ch;
	}
	
	//	METHODES
	public void init() {
		for(int i=0 ; i<nbCol ; i++) {
			for(int j=0 ; j<nbRow ; j++) {
				arcs[i][j] = false;
			}
		}
	}
}
