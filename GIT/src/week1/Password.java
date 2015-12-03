package week1;

public class Password {

	private String password = INITIAL;
	public static final String INITIAL = "123456";

	public boolean acceptable(String suggestion){
		if (suggestion.length() < 6){
			if (suggestion.contains(" ")){
			}
			return false;
		} else { return true;
		}
	}

	public boolean testWord(String test){
		if (test.equals(password)){
			System.out.println("Correct!"); return true;
		} else { System.out.println("Incorrect, check again!"); return false;
		}
	}
	public boolean setWord(String oldpass, String newpass){
		boolean oldpassCheck = testWord(oldpass);
		System.out.println("OldpassCheck: " + oldpassCheck);
		boolean newpassCheck = acceptable(newpass);
		System.out.println("NewpassCheck: " + newpassCheck);
		if (oldpassCheck && newpassCheck){
			password = newpass; return true;
		} else { return false;

		}
	}
}