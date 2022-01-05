package asteroids;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Game extends JPanel{
	private static final long serialVersionUID = 3735710087969997389L;
	private Ship ship;
	private HashMap<Integer, Asteroid> asteroids;
	private HashMap<Integer, Laser> lasers;
	private int lives;
	private int score;
	private int laserNum;
	private int asteroidNum;
	private int lvl;
	private boolean gameOver;
	
	public Game(Ship s) {
		ship = s;
		asteroids = new HashMap<Integer, Asteroid>();
		lasers = new HashMap<Integer, Laser>();
		lives = 5;
		score = 0;
		laserNum = 0;
		asteroidNum = 0;
		lvl = 1;
		gameOver = false;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(!gameOver) {
			if(asteroids.size()>0) {
				Integer[] a= asteroids.keySet().toArray(new Integer[lasers.size()]);
				for(Integer key: a) {
					Asteroid b = asteroids.get(key);
					if(b!= null) {
					b.draw(g);
					}
				}
			}
			else {
				levelWin();
			}
			g.setColor(Color.white);
			ship.draw(g);
			g.setColor(Color.white);
			if(lasers.size()>0) {
				Integer[] a= lasers.keySet().toArray(new Integer[lasers.size()]);
				for(Integer key: a) {
					Laser l = lasers.get(key);
					l.draw(g);
				}
			}
			g.setColor(Color.green);
			g.setFont(new Font("Sans Serif", Font.PLAIN, 20));
			g.drawString("Lives: " + lives, 10, 30);
			g.drawString("Score: " + score, 10, 950);
		}
		else{
			g.setColor(Color.green);
			g.setFont(new Font("Sans Serif", Font.PLAIN, 40));
			g.drawString("GAME OVER", 400, 475);
			String s = String.valueOf(score);
			g.drawString("Score: " + score, 450-((s.length()-1)*12), 525);
		}
	}
	
	private void levelWin() {
		// TODO Auto-generated method stub
		ship.reset();
		lvl++;
		startLevel();
		score += 1000;
	}

	public void startLevel() {
		int numAsteroids = (int) (Math.pow(2, lvl)/lvl);
		for(int j = 0; j<numAsteroids; j++) {
			Asteroid a = new Asteroid(80, 2, 2);
			asteroids.put(asteroidNum, a);
			asteroidNum++;
		}
		
		
	}
	
	public int getLives() {
		return lives;
	}

	public Ship getShip() {
		// TODO Auto-generated method stub
		return ship;
	}

	public void shoot() {
		// TODO Auto-generated method stub
		if(lasers.size()==5) {
			
		}
		
		else {
			lasers.put((Integer)laserNum, new Laser(ship.getDir(), ship));
			laserNum++;
		}
	}

	public void objMove() {
		// TODO Auto-generated method stub
		if(lasers.size()>0) {
			Integer[] a= lasers.keySet().toArray(new Integer[lasers.size()]);
			for(Integer k: a) {
				Laser l = lasers.get(k);
				l.move();
				if(l.getDistance() < 0) {
					lasers.remove(k);
				}
			}
			
		}
		if(asteroids.size()>0) {
			Integer[] a= asteroids.keySet().toArray(new Integer[asteroids.size()]);
			for(Integer k: a) {
				Asteroid l = asteroids.get(k);
				l.move();
				if(l.hit(lasers)) {
					asteroids.remove(k);
				}
			}
			
		}
		hitDetection();
		
	}

	private void hitDetection() {
		// TODO Auto-generated method stub
		Integer[] a= asteroids.keySet().toArray(new Integer[asteroids.size()]);
		for(Integer key: a) {
			Asteroid z = asteroids.get(key);
			if(z.contains(ship)) {
				if(ship.isImmune() == false) {
					ship.reset();
					lives--;
					ship.immune(3);
				}
			}
			if (z.contains(lasers)) {
				HashMap<Integer,Asteroid> small = z.destroy();
				if (small.size()==2) {
					Integer[] zzz = small.keySet().toArray(new Integer[small.size()]);
					for(Integer x: zzz) {
					asteroids.put(asteroidNum, small.get(x));
					asteroidNum++;
					}
				}
				asteroids.remove(key);
				score+= 100;
			}
			
		}
	}
	
	public void gameOver() {
		gameOver = true;
	}

	
	
}
