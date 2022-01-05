import java.util.HashMap;
import java.util.Timer;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.TimerTask;

public class Ship {
	private double momentumx;
	private double momentumy;
	private double direction;
	private double x;
	private double y;
	private double speedVar;
	private boolean invinc;
	private boolean accelerating;
	private int accelInt;
	private boolean rfire;
	private boolean shotgun;
	
	public Ship (double xpos, double ypos, double d) {
		momentumx = 0;
		momentumy = 0;
		direction = d;
		x=xpos;
		y=ypos;
		accelerating = false;
		accelInt = 1;
		speedVar = .4;
		rfire = false;
		shotgun = false;
		invinc = false;
	}
	
	public void draw(Graphics g) {
		double x1 = x + 22.36*Math.sin((direction+26)*(Math.PI/180));
		double x2 = x + 15*Math.sin(direction*(Math.PI/180));
		double x3 = x + 22.36*Math.sin((direction-26)*(Math.PI/180));
		double y1 = y + 22.36*Math.cos((direction+26)*(Math.PI/180));
		double y2 = y + 15*Math.cos(direction*(Math.PI/180));
		double y3 = y + 22.36*Math.cos((direction-26)*(Math.PI/180));
		int[] xship = new int[]{(int)x,(int)x1, (int)x2, (int)x3};
		int[] yship = new int[] {(int) y, (int)y1, (int)y2, (int)y3};
		g.drawPolygon(xship ,yship, 4);
		if(accelerating) {
			if(accelInt > 0 && accelInt < 4) {
				Color fakeBlue = new Color(204,255,255);
				g.setColor(fakeBlue);
			}
			if(accelInt > 3 && accelInt < 7) {
				Color lightRed = new Color(255,102,102);
				g.setColor(lightRed);
			}
			if(accelInt > 6 && accelInt < 10) {
				Color yellowish = new Color(243,246,228);
				g.setColor(yellowish);
			}
			double tx1 = (2*x1+x2)/3;
			double tx2 = x + 27*Math.sin((direction+10)*(Math.PI/180));
			double tx3 = x + 22*Math.sin((direction+5)*(Math.PI/180));
			double tx4 = x + 30*Math.sin(direction*(Math.PI/180));
			double tx5 = x + 22*Math.sin((direction-5)*(Math.PI/180));
			double tx6 = x + 27*Math.sin((direction-10)*(Math.PI/180));
			double tx7 = (x2+2*x3)/3;
			double ty1 = (2*y1+y2)/3;
			double ty2 = y + 27*Math.cos((direction+10)*(Math.PI/180));
			double ty3 = y + 22*Math.cos((direction+5)*(Math.PI/180));
			double ty4 = y + 30*Math.cos(direction*(Math.PI/180));
			double ty5 = y + 22*Math.cos((direction-5)*(Math.PI/180));
			double ty6 = y + 27*Math.cos((direction-10)*(Math.PI/180));
			double ty7 = (y2+2*y3)/3;
			g.drawLine((int)tx1, (int)ty1, (int)tx2, (int)ty2);
			g.drawLine((int)tx2, (int)ty2, (int)tx3, (int)ty3);
			g.drawLine((int)tx3, (int)ty3, (int)tx4, (int)ty4);
			g.drawLine((int)tx4, (int)ty4, (int)tx5, (int)ty5);
			g.drawLine((int)tx5, (int)ty5, (int)tx6, (int)ty6);
			g.drawLine((int)tx6, (int)ty6, (int)tx7, (int)ty7);
			if(accelInt == 9) {
				accelInt = 1;
			}
			else {
				accelInt++;
			}
		}
	}
	
	public void turnLeft() {
		if(accelerating) {
			direction += 1;
			if(direction >= 360) {
				direction = 0;
			}
		}
		direction += 1;
		direction += 1;
		if(direction >= 360) {
			direction = 0;
		}
	}
	public void turnRight() {
		if(accelerating) {
			direction -= 1;
			if (direction <= 0) {
				direction = 360;
			}
		}
		direction -= 1;
		direction -= 1;
		if (direction <= 0) {
			direction = 360;
		}
	}

	public void accelerate() {
		// TODO Auto-generated method stub
		if(direction == 0) {
			momentumy -= speedVar;
		}
		else if(direction == 90) {
			momentumx -= speedVar;
		}
		else if(direction == 180) {
			momentumy += speedVar;
		}
		else if(direction == 270) {
			momentumx += speedVar;
		}
		else if(direction < 90) {
			momentumx -= speedVar*(direction/90.0);
			momentumy -= speedVar*(1-(direction/90.0));
		}
		else if(direction < 180) {
			momentumx -= speedVar*(1-((direction-90)/90.0));
			momentumy += speedVar*((direction-90)/90.0);
		}
		else if(direction < 270) {
			momentumx += speedVar*((direction-180)/90.0);
			momentumy += speedVar*(1-(direction-180)/90.0);
		}
		else {
			momentumx += speedVar*(1-((direction-270)/90.0));
			momentumy -= speedVar*((direction-270)/90.0);
		}
	}
	
	public void move() {
		x+=momentumx;
		y+=momentumy;
		if(x>1900) {
			x = x-1900;
		}
		if(x<0) {
			x = x + 1900;
		}
		if(y<0) {
			y = y+ 1000;
		}
		if(y>1000) {
			y = y- 1000;
		}
	}


	public void decay() {
		// TODO Auto-generated method stub
		momentumx*=.99;
		momentumy*=.99;
	}

	public int getX() {
		// TODO Auto-generated method stub
		return (int)x;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return (int)y;
	}

	public int getDir() {
		// TODO Auto-generated method stub
		return (int) direction;
	}

	public void reset() {
		// TODO Auto-generated method stub
		x = Math.random()*1800+50;
		y = Math.random()*900+50;
		direction = 0;
		momentumx = 0;
		momentumy = 0;
	}
	
	public void immune() {
		invinc = true;
		TimerTask f = new TimerTask()
		{
			public void run() {
				invinc = false;
			}
		};
		Timer z = new Timer();
		z.schedule(f,5000);
	}

	public void isAccelerating() {
		// TODO Auto-generated method stub
		accelerating = true;
	}
	
	public void stopsAccelerating() {
		accelerating = false;
	}

	public boolean contains(HashMap<Integer, Laser> beans) {
		if(invinc) {
			return false;
		}
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
				double x1 = x + 22.36*Math.sin((direction+26)*(Math.PI/180));
				double x2 = x + 15*Math.sin(direction*(Math.PI/180));
				double x3 = x + 22.36*Math.sin((direction-26)*(Math.PI/180));
				double y1 = y + 22.36*Math.cos((direction+26)*(Math.PI/180));
				double y2 = y + 15*Math.cos(direction*(Math.PI/180));
				double y3 = y + 22.36*Math.cos((direction-26)*(Math.PI/180));
				int[] xship = new int[]{(int)x,(int)x1, (int)x2, (int)x3};
				int[] yship = new int[] {(int) y, (int)y1, (int)y2, (int)y3};
				Polygon a = new Polygon(lxarr, lyarr, 2);
				Polygon b = new Polygon(xship, yship, 4);
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
	
	public boolean getRfire(){
		return rfire;
	}

	public boolean getShotgun() {
		// TODO Auto-generated method stub
		return shotgun;
	}

	public void shotgun() {
		// TODO Auto-generated method stub
		shotgun = true;
		TimerTask f = new TimerTask()
		{
			public void run() {
				shotgun = false;
			}
		};
		Timer fork = new Timer();
		fork.schedule(f,15000);
	}

	public void rfire() {
		// TODO Auto-generated method stub
		rfire = true;
		TimerTask f = new TimerTask()
		{
			public void run() {
				rfire = false;
			}
		};
		Timer knife = new Timer();
		knife.schedule(f,10000);
	}

	public boolean getImmune() {
		// TODO Auto-generated method stub
		return invinc;
	}
	
	
}
