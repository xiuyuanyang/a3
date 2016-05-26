package beans;

public class Mi {
	private double x;
	private double y;
	private double r;
	private String yanse;
	
	public Mi(double x, double y, double r, String yanse) {
		super();
		this.x = x;
		this.y = y;
		this.r = r;
		this.yanse = yanse;
	}
	
	public Mi(){
		
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getR() {
		return r;
	}
	public void setR(double r) {
		this.r = r;
	}
	public String getYanse() {
		return yanse;
	}
	public void setYanse(String yanse) {
		this.yanse = yanse;
	}
	
}
