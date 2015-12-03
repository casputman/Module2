package hotel;

public class TimedPassword extends Password {
	public long validTime;
	public long startTime;

	public TimedPassword(long validTime){
		super(new BasicChecker());
		this.validTime = validTime;
		startTime = (System.currentTimeMillis() / 1000);
	}
	
	public boolean isExpired(){
		return (System.currentTimeMillis()/1000 - startTime > validTime);
	}
	
	public boolean setWord(String oldpass, String newpass){
		if (super.setWord(oldpass, newpass)) {
			startTime = (System.currentTimeMillis() / 1000);
			return true;
		}
		else return false;
	}
}

