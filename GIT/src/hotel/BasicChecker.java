package hotel;

public class BasicChecker implements Checker {
	public boolean check(String password){
		return !(password.length() <= 5 && password.contains(" "));
	}
	public String generatePass(){
		return "basicpass";
	}
}
