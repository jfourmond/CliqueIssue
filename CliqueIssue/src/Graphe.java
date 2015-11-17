import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Graphe {
	private int nbSommets;
	private boolean arcs[][];
	
	public Graphe() {
		nbSommets = 0;
		arcs = new boolean[nbSommets][nbSommets];
	}
	
	public Graphe(int nbSommets) {
		this.nbSommets = nbSommets;
		arcs = new boolean[nbSommets][nbSommets];
	}
	
	public Graphe(String filename) {
		int nbSommets = 0;
		try{
			FileInputStream fis 	=	new FileInputStream(filename); 
			InputStreamReader ipsr 	= 	new InputStreamReader(fis);
			BufferedReader br 		=	new BufferedReader(ipsr);
			String ligne;
			// Récupération du nombre de sommets
			while ((ligne=br.readLine())!=null){
				if(ligne.startsWith("p")) {
					String[] split_line = ligne.split(" ");
					nbSommets = Integer.parseInt(split_line[2]);
					break;
				}
			}
			this.nbSommets = nbSommets;
			arcs = new boolean[nbSommets][nbSommets];
			// Récupération des arcs
			while ((ligne=br.readLine())!=null){
				if(ligne.startsWith("e")) {
					String[] split_ligne = ligne.split(" ");
					int from = Integer.parseInt(split_ligne[1]);
					int to = Integer.parseInt(split_ligne[2]);
					System.out.print("From " + from + " To " + to + "\n");
					arcs[from-1][to-1] = true;
					arcs[to-1][from-1] = true;
				}
			}
			br.close();
			ipsr.close();
			fis.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	//	GETTERS
	public int getNbSommets() {
		return nbSommets;
	}
	
	public boolean[][] getArcs() {
		return arcs;
	}
	
	//	SETTERS
	public void setNbSommets(int nbSommets) {
		this.nbSommets = nbSommets;
	}
	
	public void setArcs(boolean[][] arcs) {
		this.arcs = arcs;
	}
	
	@Override
	public String toString() {
		super.toString();
		String ch = "Sommets  : " + nbSommets + "\n" +
					"Arcs : \n";
		for(int i=0 ; i<nbSommets ; i++) {
			ch += "| ";
			for(int j=0 ; j<nbSommets ; j++) {
				ch += arcs[i][j] + " |";
			}
			ch += "\n";
		}
		return ch;
	}
	
	//	METHODES
	public void init() {
		for(int i=0 ; i<nbSommets ; i++) {
			for(int j=0 ; j<nbSommets ; j++) {
				arcs[i][j] = false;
			}
		}
	}
	
	public boolean isClique() {
		for(int i=0 ; i<nbSommets ; i++) {
			for(int j=0 ; j<nbSommets ; j++) {
				if(arcs[i][j] == false)
					return false;
			}
		}
		return true;
	}
}
