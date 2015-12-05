import java.util.Hashtable;
import java.util.Map;

/**
 * La classe {@link Arguments} permet de traiter les arguments lisibles
 * passés en paramètres du lancement du programme
 */
public class Arguments {
	static public String graph = "";
	static public long limit = -1;
	static public int method = 0;
	static public String show = "";
	static public boolean help = false;
	
	public Arguments(String[] args) throws ArgumentException {
		Map<String, Object> arguments = new Hashtable<>();
		for(String s : args) {
			if(s.startsWith("-")) {
				String arg;
				arg = s.substring(1);
				if(arg.equals("help")) {
					help = true;
					return;
				} else {
					int pos = arg.indexOf('=');
					if(pos == -1) throw new ArgumentException(s);
					String argument = arg.substring(0, pos);
					String value = arg.substring(pos+1);
					switch(argument) {
						case "graph" :
							arguments.put(argument, value);
							break;
						case "limit" : 
							arguments.put(argument, Long.valueOf(value));
							break;
						case "method" : 
							arguments.put(argument, Integer.valueOf(value));
							break;
						case "show" :
							arguments.put(argument, value);
							break;
						default:
							throw new ArgumentException(argument);
					}
				}
			} else throw new ArgumentException(s);
		}
		computeMap(arguments);
	}

	private void computeMap(Map<String, Object> M) {
		if(M.get("graph") != null)
			graph = (String) M.get("graph");
		
		if(M.get("limit") != null)
			limit = (Long) M.get("limit");
		
		if(M.get("method") != null)
			method = (Integer) M.get("method");
		
		if(M.get("show") != null)
			show = (String) M.get("show");
	}
	
	public static void showValidArguments() {
		System.out.println("Usage : clique_issue -graph=<file to graph> -method=<method 0, 1, 2> -limit=<limit time ms> -show=<all, stats, graph> ");
	}
	
	public static void showHelp() {
		System.out.println("-graph=<file to graph>	:	the file who contains the description of the graphe");
		System.out.println("-method=<method 0, 1, 2>	:	method to use");
		System.out.println("\t\t\t 0\t:\tShlag");
		System.out.println("\t\t\t 1\t:\tB-K");
		System.out.println("\t\t\t 2\t:\tTomita");
		System.out.println("-limit=<time limit ms>	:	the time limit of execution (ms)");
		System.out.println("show=<all, stats, graph>\t:\twhat to show");
		System.out.println("\t\t\t all\t:\tshow the stats of the graph and the graph (matrix)");
		System.out.println("\t\t\t graph\t:\tshow only the graph (matrix)");
		System.out.println("\t\t\t stats\t:\tshow only the stats");
	}
}
