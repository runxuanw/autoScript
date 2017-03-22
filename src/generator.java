
public class generator {
	
	public String generateDelay(int ms){
		return "Delay "+Integer.toString(ms)+"\n";
	}

	public String pressChar(char c){
		return "KeyPress \""+Character.toString(Character.toUpperCase(c))+"\""+", 1\n"+generateDelay(30);
	}
	
	public String getGenerateStr(String method, String key, int delay){
		String result = method+" \""+key+"\", 1\n";
		result += generateDelay(delay);
		return result;
	}
	
	public String pressUpperChar(char c){
		String res = getGenerateStr("KeyDown", "Shift", 30);
		res += getGenerateStr("KeyPress", Character.toString(c), 30);
		res += getGenerateStr("KeyUp", "Shift", 30);
		return res;
	}
	
	public String pressSpecialChar(char c){
		String res = "";
		//more cases here
		if(c == ' '){
			res += getGenerateStr("KeyPress", "Space", 30);
		}
		else{
			res += getGenerateStr("KeyPress", Character.toString(c), 30);
		}
		
		return res;
	}
	
	public void generateScript(String trigger, String input){
		//keyup the trigger
		String[] triggerList = trigger.split(" ");
		for(String str : triggerList)
			System.out.print(getGenerateStr("KeyUp", str, 30));
		
		//then generate the script
		for(int i = 0; i < input.length(); i++){
			if(input.charAt(i) - 'A' >= 0 && input.charAt(i) - 'A' < 26){
				System.out.print(pressUpperChar(input.charAt(i)));
			}
			else if(input.charAt(i) - 'a' >= 0 && input.charAt(i) - 'a' < 26){
				System.out.print(pressChar(input.charAt(i)));
			}
			else{
				System.out.print(pressSpecialChar(input.charAt(i)));
			}
		}
		
	}
	
	public static void main(String[]args)
	{
		//script trigger seperated by space
		String trigger = "Ctrl M";
		//script text
		String input = "Monterey Park";
		generator instance = new generator();
		instance.generateScript(trigger, input);
	}
	
}
