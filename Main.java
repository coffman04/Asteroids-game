import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;
import java.util.Timer;

import javax.swing.*;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			JFrame frame = new JFrame("Asteroids");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(1900,1000);
	        Game gamePanel = new Game(new Ship(1375, 500, 0), new Ship(525,500,0));
	        gamePanel.setBackground(Color.black);
	        Container z = frame.getContentPane();
	        z.add(gamePanel);
	        frame.setVisible(true);
	        StartStopTimer up = new StartStopTimer(gamePanel, "u");
	        StartStopTimer left = new StartStopTimer(gamePanel,"l");
	        StartStopTimer right = new StartStopTimer(gamePanel, "r");
	        StartStopTimer upTwo = new StartStopTimer(gamePanel, "u2");
	        StartStopTimer leftTwo = new StartStopTimer(gamePanel, "l2");
	        StartStopTimer rightTwo = new StartStopTimer(gamePanel, "r2");
	        Powerup rapid = new Powerup("rapid", 53);
	        Powerup invinc = new Powerup("invinc", 37);
	        Powerup shotgun = new Powerup("shotgun", 24);
	        gamePanel.addPower(rapid);
	        gamePanel.addPower(invinc);
	        gamePanel.addPower(shotgun);
	        
	        KeyListener a = new KeyListener() {
	        	// try moving timer task around to find where it will reset
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == 80) {
						gamePanel.shipOneShoot();
					}
					if(e.getKeyCode() == 32) {
						gamePanel.shipTwoShoot();
					}
	    			if (e.getKeyCode() == 38) {
	    				if(!(up.hasStarted())) {
	    					up.start();
	    					gamePanel.getShipOne().isAccelerating();
	    				}
	    			} 
	    			else if (e.getKeyCode() == 39) {
	        			if(!(right.hasStarted())) {
	        				right.start();
	        			}
	    			} 
	    			else if (e.getKeyCode() == 37) {
	        			if(!(left.hasStarted())) {
	    				left.start();
	        			}
	    			}
	    			else if (e.getKeyCode() == 87) {
	    				if(!(upTwo.hasStarted())) {
	    					upTwo.start();
	    					gamePanel.getShipTwo().isAccelerating();
	    				}
	    			} 
	    			else if (e.getKeyCode() == 68) {
	        			if(!(rightTwo.hasStarted())) {
	        				rightTwo.start();
	        			}
	    			} 
	    			else if (e.getKeyCode() == 65) {
	        			if(!(leftTwo.hasStarted())) {
	    				leftTwo.start();
	        			}
	    			}
				}

				@Override
				public void keyReleased(KeyEvent e) {
	    			if (e.getKeyCode() == 38) {
	        			up.pause();
	        			gamePanel.getShipOne().stopsAccelerating();
	    			} 
	    			else if (e.getKeyCode() == 39) {
	        			right.pause();
	    			} 
	    			else if (e.getKeyCode() == 37) {
	        			left.pause();
	    			}
	    			else if (e.getKeyCode() == 87) {
	        			upTwo.pause();
	        			gamePanel.getShipTwo().stopsAccelerating();
	    			} 
	    			else if (e.getKeyCode() == 68) {
	        			rightTwo.pause();
	    			} 
	    			else if (e.getKeyCode() == 65) {
	        			leftTwo.pause();
	    			}
				}
				
				
	        		
	        };
	        frame.addKeyListener(a);
	        TimerTask timerTask = new TimerTask()
	        { 
	            public void run()  
	            { 
	            	
	            	if((gamePanel.getP1Lives()>0) && (gamePanel.getP2Lives()>0)) {
	    	        	gamePanel.repaint();
	    	        	gamePanel.getShipOne().move();
	    	        	gamePanel.getShipOne().decay();
	    	        	gamePanel.getShipTwo().move();
	    	        	gamePanel.getShipTwo().decay();
	    	        	gamePanel.runPowers();
	    	        	gamePanel.objMove();
	    	        	
	    	        }
	            	else {
	            		gamePanel.gameOver();
	            		gamePanel.repaint();
	            		this.cancel();
	            	}
	            } 
	        }; 

	        Timer t = new Timer(); 
	        t.scheduleAtFixedRate(timerTask, 0, 25);
	        
	        
	        
	}
	
}

