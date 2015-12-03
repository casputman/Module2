package week1;

public class ThreeWayLamp {
		public final static int OFF = 0;
		public final static int LOW  = 1;
		public final static int MID  = 2;
		public final static int HIGH = 3;
		
		public int setting;
		
	public ThreeWayLamp(){
			setting = OFF;
	}
	//@ensures getSetting() != \old(getSetting());
	public void switchSetting(){
			setting = (setting +1) % 4;
	}
	
	//@ pure
	public int getSetting(){
		return setting;
	}
}
