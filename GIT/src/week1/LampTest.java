package week1;

public class LampTest {
	public static void main(String[] args){
		ThreeWayLamp lamp = new ThreeWayLamp();
		lamp.switchSetting();
		if(lamp.getSetting() == ThreeWayLamp.LOW){
			System.out.println("Well done William!");
		} else { 
			System.out.println("You fucked up son.");
		}
	}
}