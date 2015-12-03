package pw;

public class StrongChecker extends BasicChecker {

	public boolean extraChecker(String password){
		if (super.check(password) == true && Character.isLetter(password.charAt(0)) == true && Character.isDigit(password.charAt(password.length())) == true){
			return true;
		} else {
			return false;
		}
	}
	public String generatePass(){
		return "strongpass1";
	}
}


