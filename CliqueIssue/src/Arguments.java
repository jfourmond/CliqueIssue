import java.util.Hashtable;
import java.util.Map;

public class Arguments {
	private long limit;
	private long method;
	
	public Arguments(String[] args) {
		Map<String, Long> arguments = new Hashtable<>();
		for(String s : args) {
			if(s.startsWith("-")) {
				String arg;
				arg = s.substring(1);
				int pos = arg.indexOf('=');
				String argument = arg.substring(0, pos);
				String value = arg.substring(pos+1);
				arguments.put(argument, Long.valueOf(value));
			}
		}
		computeMap(arguments);
	}
	 // METHODES
	private void computeMap(Map<String, Long> M) {
		if(M.get("limit") != null) {
			limit = M.get("limit");
			System.out.println("Limit : " + limit);
		}
		
		if(M.get("method") != null) {
			method = M.get("method");
			System.out.println("Method : " + method);
		}
		
		
	}
}
