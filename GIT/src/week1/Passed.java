package week1;

public class Passed {
	public String passed = "The student has passed the test";
	public String failed = "The student has failed the test";
	public int score;

	public String result(){
		if (score >= 70){
			return passed;
		} else {
			return failed;
		}
	}
}
