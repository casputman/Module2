package hotel;
import hotel.Password;

public class Safe {
	
	public static void main(String[] args){
		Safe safe = new Safe("123456");
		safe.open("123456");
		safe.activate("123456");
		safe.deactivate();
	}
	
	private void activate(String password) {
		assert(isActive() == false);
		active = passInstance.testWord(password);
	}
	public void deactivate(){
		assert(isActive() == true);
		this.active = false;
		this.open = false;
	}

	private Password passInstance;
	private boolean active;
	private boolean open;
	
	public Safe(String password){
		passInstance = new Password();
		passInstance.setWord(Password.INITIAL, password);
		}
	
	public boolean open(String password){
		assert(isActive() == true);
		open = passInstance.testWord(password);
		return this.open;
	}
	
	public void close(){
		assert(isOpen() == true);
		this.open = false;
	}
	
	public boolean isActive(){
		return active;
	}
	public boolean isOpen(){
		return open;
	}
	public Password getPassword(){
		return passInstance;
	}
}
