package asteroids;
import java.awt.*;

public class Laser {
	private int direction;
	private double distance;
	private int speed;
	private int x;
	private int y;
	private boolean hit;
	
	public Laser(int d, Ship s) {
		direction = d;
		distance = 800;
		speed = 25;
		x = s.getX();
		y = s.getY();
		hit = true;
	}
	
	public void draw(Graphics g) {
		g.drawLine(x, y, (int)(x+10*(Math.sin(direction*(Math.PI/180)))), (int)(y+10*(Math.cos(direction*(Math.PI/180)))));
	}
	
	public void move() {
		distance -= speed;
		x += -speed*Math.sin(direction*(Math.PI/180));
		y -= speed*Math.cos(direction*(Math.PI/180));
		if(x>1000) {
			x = x-1000;
		}
		if(x<0) {
			x = x + 1000;
		}
		if(y<0) {
			y = y+ 1000;
		}
		if(y>1000) {
			y = y- 1000;
		}
	}
	public double getDistance() {
		return distance;
	}

	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	public int getDir() {
		// TODO Auto-generated method stub
		return direction;
	}

	public boolean hitValid() {
		return hit;
	}

	public void hitValid(boolean b) {
		// TODO Auto-generated method stub
		hit = b;
	}
}
