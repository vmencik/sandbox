package cz.vmencik.sandbox;

public class JPoint {
	private final int x;
	private final int y;
	
	public JPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "Point(" + x + ", " + y + ")";
	}
}
