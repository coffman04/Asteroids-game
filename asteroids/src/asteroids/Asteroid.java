package asteroids;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

public class Asteroid {
	private int size;
	private int speed;
	private int direction;
	private double x;
	private double y;
	private int intSpeed;
	
	public Asteroid (int si, int sp, int isp) {
		size = si;
		speed = sp;
		direction = (int)(Math.random()*360+1);
		x = (Math.random()*1000+1);
		y = (Math.random()*1000+1);
		intSpeed = isp;
	}
	public Asteroid (int si, int sp, int d, double xpos, double ypos, int isp) {
		size = si;
		speed = sp;
		direction = d;
		x = xpos;
		y = ypos;
		intSpeed = isp;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getSize() {
		return size;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.green);
		g.drawOval((int)x, (int)y, size, size);
	}
	public void move() {
		// TODO Auto-generated method stub
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
	
	public boolean hit(HashMap<Integer, Laser> lasers) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean contains(Ship s) {
		int sx = s.getX();
		int sy = s.getY();
		int dir = s.getDir();
		double x1 = sx + 22.36*Math.sin((dir+26)*(Math.PI/180));
		double x2 = sx + 15*Math.sin(dir*(Math.PI/180));
		double x3 = sx + 22.36*Math.sin((dir-26)*(Math.PI/180));
		double y1 = sy + 22.36*Math.cos((dir+26)*(Math.PI/180));
		double y2 = sy + 15*Math.cos(dir*(Math.PI/180));
		double y3 = sy + 22.36*Math.cos((dir-26)*(Math.PI/180));
		int[] xship = new int[]{(int)sx+10,(int)x1, (int)x2, (int)x3};
		int[] yship = new int[] {(int) sy, (int)y1, (int)y2, (int)y3};
		Polygon sh = new Polygon(xship, yship, 4);
		Rectangle b = new Rectangle((int)x,(int)y,size,size);
		if (sh.intersects(b)) {
			return true;
		}
		return false;
	}
	
	public boolean contains(Laser l) {
		int lx = l.getX();
		int ly = l.getY();
		int dir = l.getDir();
		int lx2 = (int)(lx+25*(Math.sin(dir*(Math.PI/180))));
		int ly2 = (int)(ly+25*(Math.cos(dir*(Math.PI/180))));
		int[]lxarr = new int[] {lx, lx2};
		int[]lyarr = new int[] {ly, ly2};
		Polygon a = new Polygon(lxarr, lyarr, 2);
		Rectangle b = new Rectangle((int)x,(int)y,size,size);
		if (a.intersects(b)) {
			if(l.hitValid()) {
				l.hitValid(false);
				return true;
			}
		}
		return false;
		
	}
	
	public HashMap<Integer, Asteroid> destroy() {
		// TODO Auto-generated method stub
		HashMap<Integer, Asteroid> ret = new HashMap<Integer, Asteroid>();
		if(speed == intSpeed*4) {
			return ret;
		}
		Asteroid one = new Asteroid(size/2, speed*2, (direction+(int)(Math.random()*60+60)), x, y, intSpeed);
		Asteroid two = new Asteroid(size/2, speed*2, direction-(int)(Math.random()*60)-60, x, y, intSpeed);
		ret.put(1, one);
		ret.put(2, two);
		return ret;
	}
	public boolean contains(HashMap<Integer, Laser> beans) {
		if(beans.size()==0) {
			return false;
		}
		else {
			Integer[]cheese = beans.keySet().toArray(new Integer[beans.size()]);
			for(Integer icecream: cheese) {
				Laser l = beans.get(icecream);
				int lx = l.getX();
				int ly = l.getY();
				int dir = l.getDir();
				int lx2 = (int)(lx+25*(Math.sin(dir*(Math.PI/180))));
				int ly2 = (int)(ly+25*(Math.cos(dir*(Math.PI/180))));
				int[]lxarr = new int[] {lx, lx2};
				int[]lyarr = new int[] {ly, ly2};
				Rectangle b = new Rectangle((int)x,(int)y,size,size);
				Polygon a = new Polygon(lxarr, lyarr, 2);
				Rectangle2D c = a.getBounds2D();
				if (b.intersects(c)) {
					if(l.hitValid()) {
						l.hitValid(false);
						return true;
					}
				}
			}
		}
		return false;
	}
	
}
