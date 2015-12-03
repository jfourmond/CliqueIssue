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
				}
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
					default:
						throw new ArgumentException(argument);
				}
			} else throw new ArgumentException(s);
		}
		computeMap(arguments);
	}
	 // METHODES
	private void computeMap(Map<String, Object> M) {
		if(M.get("graph") != null)
			graph = (String) M.get("graph");
		
		if(M.get("limit") != null)
			limit = (Long) M.get("limit");
		
		if(M.get("method") != null)
			method = (Integer) M.get("method");
	}
	
	public static void showValidArguments() {
		// TODO modifier le "script"
		System.out.println("Usage : script -graph=<file to graph> -method=<method 0, 1, 2> -limit=<time limit ms>");
	}
	
	public static void showHelp() {
		
	}
}
