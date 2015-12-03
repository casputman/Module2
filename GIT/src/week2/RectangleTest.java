package week2;

public class RectangleTest {
	public Rectangle rectangle;

	public void start(){
		System.out.println("Test class: " + this.getClass());
		setUp();
		testRectangle();

	}

	public void setUp(){
		rectangle = new Rectangle(5,2);

	}
	public void testRectangle() {
		beginTest("rectangle");
		assertEquals("length.getLength()", "5", rectangle.length());
		assertEquals("width.getWidth()", "2", rectangle.width());
        assertEquals("perimeter.getPerimeter()", "14", rectangle.perimeter());
        assertEquals("area.getArea()", "10", rectangle.area());
    }
	
	private void assertEquals(String text, Object expected, Object result) {
		System.out.println("        " + text);
		System.out.println("            Expected:  " + expected);
		System.out.println("            Result: " + result);
	}
	private void beginTest(String text){
		System.out.println("    Test: " + text);
	}

	public static void main(String[] args) {
		System.out.println("Log of " + RectangleTest.class + 
				", " + new java.util.Date());
		new RectangleTest().start();
	}
}
