import java.awt.*;
public class Powerup {
		private int spawnTimer;
		private int spawn;
		private String type;
		private Boolean spawned;
		private int x;
		private int y;
		private int direction;
		private int speed;
		
		public Powerup(String s, int i) {
			spawnTimer = 0;
			spawn = i;
			type = s;
			x = (int)(Math.random()*1800 + 50);
			y = (int)(Math.random()*900 + 50);
			direction = 0;
			speed = 8;
			spawned = false;
		}
		
		public void spawnCheck() {
			if(!spawned) {
				spawnTimer++;
			}
			if(spawnTimer == spawn*40) {
				spawnThis();
				spawnTimer = 0;
			}
		}

		private void spawnThis() {
			// TODO Auto-generated method stub
			spawned = true;
			x = (int) (Math.random()*1900+50);
			y = (int) (Math.random()*900+50);
			direction = (int) (Math.random()*360);
		}
		
		
		public void draw(Graphics g) {
			if(spawned) {
				if (type.equals("rapid")) {
					g.setColor(Color.MAGENTA);
				}
				if (type.equals("invinc")) {
					g.setColor(Color.BLUE);
				}
				if (type.equals("shotgun")) {
					g.setColor(Color.YELLOW);
				}
				g.drawOval(x, y, 20, 20);
				g.fillOval(x, y, 20, 20);
			}
		}

		public void move() {
				// TODO Auto-generated method stub
				x += -speed*Math.sin(direction*(Math.PI/180));
				y -= speed*Math.cos(direction*(Math.PI/180));
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

		public void contains(Ship s) {
			// TODO Auto-generated method stub
			if(spawned) {
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
				Rectangle b = new Rectangle((int)x,(int)y,20,20);
				if (sh.intersects(b)) {
					spawned = false;
					if(type.equals("rapid")) {
						s.rfire();
					}
					if(type.equals("shotgun")) {
						s.shotgun();
					}
					if(type.equals("invinc")) {
						s.immune();
					}
				}
			}
		}
		
		
}
