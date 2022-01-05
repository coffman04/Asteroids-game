
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Game extends JPanel{
	private static final long serialVersionUID = 3735710087969997389L;
	private Ship shipone;
	private Ship shiptwo;
	private HashMap<Integer, Laser> s1lasers;
	private HashMap<Integer, Laser> s2lasers;
	private int p1Lives;
	private int p2Lives;
	private int laserNum;
	private boolean gameOver;
	private ArrayList<Powerup> powers;
	
	public Game(Ship one, Ship two) {
		shipone = one;
		shiptwo = two;
		s1lasers = new HashMap<Integer, Laser>();
		s2lasers = new HashMap<Integer, Laser>();
		p1Lives = 5;
		p2Lives = 5;
		laserNum = 0;
		gameOver = false;
		powers = new ArrayList<Powerup>();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(!gameOver) {
			g.setColor(Color.green);
			shipone.draw(g);
			g.setColor(Color.red);
			shiptwo.draw(g);
			g.setColor(Color.green);
			if(s1lasers.size()>0) {
				Integer[] a= s1lasers.keySet().toArray(new Integer[s1lasers.size()]);
				for(Integer key: a) {
					Laser l = s1lasers.get(key);
					l.draw(g);
				}
			}
			g.setColor(Color.red);
			if(s2lasers.size()>0) {
				Integer[] a= s2lasers.keySet().toArray(new Integer[s2lasers.size()]);
				for(Integer key: a) {
					Laser l = s2lasers.get(key);
					l.draw(g);
				}
			}
			g.setColor(Color.red);
			g.setFont(new Font("Sans Serif", Font.PLAIN, 20));
			g.drawString("Lives: " + p2Lives, 20, 30);
			if(shiptwo.getImmune()) {
				g.drawString("IMMUNE", 20, 60);
			}
			if(shiptwo.getRfire()) {
				g.drawString("RFIRE", 20, 90);
			}
			if(shiptwo.getShotgun()) {
				g.drawString("SHOTGUN", 20, 120);
			}
			
			g.setColor(Color.green);
			g.setFont(new Font("Sans Serif", Font.PLAIN, 20));
			g.drawString("Lives: " + p1Lives, 1800, 30);
			if(shipone.getImmune()) {
				g.drawString("IMMUNE", 1800, 60);
			}
			if(shipone.getRfire()) {
				g.drawString("RFIRE", 1800, 90);
			}
			if(shipone.getShotgun()) {
				g.drawString("SHOTGUN", 1800, 120);
			}
			for(Powerup p: powers) {
				p.draw(g);
			}
		}
		else {
			if(p1Lives>0) {
				g.setColor(Color.green);
				g.setFont(new Font("Sans Serif", Font.PLAIN, 100));
				g.drawString("P2 Wins!", 750, 450);
			}
			else {
				g.setColor(Color.red);
				g.setFont(new Font("Sans Serif", Font.PLAIN, 100));
				g.drawString("P1 Wins!", 750, 450);
			}
		}
		
	}

	public Ship getShipOne() {
		// TODO Auto-generated method stub
		return shipone;
	}
	public Ship getShipTwo() {
		return shiptwo;
	}

	public void shipOneShoot() {
		// TODO Auto-generated method stub
		if((shipone.getShotgun())) {
			if(shipone.getRfire()) {
				s1lasers.put((Integer)laserNum, new Laser(shipone.getDir(), shipone));
				laserNum++;
				s1lasers.put((Integer)laserNum, new Laser((shipone.getDir()+15), shipone));
				laserNum++;
				s1lasers.put((Integer)laserNum, new Laser((shipone.getDir()-15), shipone));
				laserNum++;
			}
			else if(s1lasers.size()<=15) {
				s1lasers.put((Integer)laserNum, new Laser(shipone.getDir(), shipone));
				laserNum++;
				s1lasers.put((Integer)laserNum, new Laser((shipone.getDir()+15), shipone));
				laserNum++;
				s1lasers.put((Integer)laserNum, new Laser((shipone.getDir()-15), shipone));
				laserNum++;
			}
		}
		else if(!(shipone.getRfire())) {
			if(s1lasers.size()>=5) {
				
			}
			else {
				s1lasers.put((Integer)laserNum, new Laser(shipone.getDir(), shipone));
				laserNum++;
			}
		}
		else {
			s1lasers.put((Integer)laserNum, new Laser(shipone.getDir(), shipone));
			laserNum++;
		}
	}
	public void shipTwoShoot() {
		// TODO Auto-generated method stub
		if((shiptwo.getShotgun())) {
			if(shiptwo.getRfire()) {
				s2lasers.put((Integer)laserNum, new Laser(shiptwo.getDir(), shiptwo));
				laserNum++;
				s2lasers.put((Integer)laserNum, new Laser((shiptwo.getDir()+15), shiptwo));
				laserNum++;
				s2lasers.put((Integer)laserNum, new Laser((shiptwo.getDir()-15), shiptwo));
				laserNum++;
			}
			else if(s2lasers.size()<=15) {
				s2lasers.put((Integer)laserNum, new Laser(shiptwo.getDir(), shiptwo));
				laserNum++;
				s2lasers.put((Integer)laserNum, new Laser((shiptwo.getDir()+15), shiptwo));
				laserNum++;
				s2lasers.put((Integer)laserNum, new Laser((shiptwo.getDir()-15), shiptwo));
				laserNum++;
			}
		}
		else if(!(shiptwo.getRfire())) {
			if(s2lasers.size()>=5) {
				
			}
			else {
				s2lasers.put((Integer)laserNum, new Laser(shiptwo.getDir(), shiptwo));
				laserNum++;
			}
		}
		else {
			s2lasers.put((Integer)laserNum, new Laser(shiptwo.getDir(), shiptwo));
			laserNum++;
		}
	}

	public void objMove() {
		// TODO Auto-generated method stub
		if(s1lasers.size()>0) {
			Integer[] a= s1lasers.keySet().toArray(new Integer[s1lasers.size()]);
			for(Integer k: a) {
				Laser l = s1lasers.get(k);
				l.move();
				if(l.getDistance() < 0) {
					s1lasers.remove(k);
				}
			}
			
		}
		if(s2lasers.size()>0) {
			Integer[] a= s2lasers.keySet().toArray(new Integer[s2lasers.size()]);
			for(Integer k: a) {
				Laser l = s2lasers.get(k);
				l.move();
				if(l.getDistance() < 0) {
					s2lasers.remove(k);
				}
			}
			
		}
		hitDetection();
		
	}

	private void hitDetection() {
		// TODO Auto-generated method stub
		/*
		 * loop through laser arrays looking for the other ship. see contains method from the asteroid class in 
		 * the other game 
		 */
		if(shipone.contains(s2lasers)) {
			p1Lives--;
			shipone.reset();
		}
		if(shiptwo.contains(s1lasers)) {
			p2Lives--;
			shiptwo.reset();
		}

		
	}
	
	public void gameOver() {
		gameOver = true;
	}

	public int getP1Lives() {
		// TODO Auto-generated method stub
		return p1Lives;
	}

	public int getP2Lives() {
		// TODO Auto-generated method stub
		return p2Lives;
	}
	
	public void addPower(Powerup p) {
		powers.add(p);
	}


	public void runPowers() {
		// TODO Auto-generated method stub
		for(Powerup p: powers) {
			p.spawnCheck();
			p.move();
			p.contains(shipone);
			p.contains(shiptwo);
		}
	}

	
	
}

