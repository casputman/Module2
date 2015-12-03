package week2;

public class Rectangle {
	private int length;
	private int width;

	public static void main(String[] args){
		new Rectangle(1,2);
	}

	public Rectangle(int length, int width){
		assert length > width;
		assert length > 0;
		assert width > 0;
		this.length = length;
		this.width = width;
	}
	public int length(){
		return this.length;
	}
	public int width(){
		return this.width;
	}
	public int area(){
		return this.width * this.length;
	}
	public int perimeter(){
		return 2*(this.width + this.length);
	}
}
