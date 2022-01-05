package asteroids;
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
	        frame.setSize(1000,1000);
	        Game gamePanel = new Game(new Ship(0));
	        gamePanel.setBackground(Color.black);
	        Container z = frame.getContentPane();
	        z.add(gamePanel);
	        gamePanel.startLevel();
	        frame.setVisible(true);
	        StartStopTimer up = new StartStopTimer(gamePanel, "u");
	        StartStopTimer left = new StartStopTimer(gamePanel,"l");
	        StartStopTimer right = new StartStopTimer(gamePanel, "r");
	        
	        KeyListener a = new KeyListener() {
	        	// try moving timer task around to find where it will reset
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == 32) {
						gamePanel.shoot();
					}
	    			if (e.getKeyCode() == KeyEvent.VK_UP) {
	    				if(!(up.hasStarted())) {
	    					up.start();
	    					gamePanel.getShip().isAccelerating();
	    				}
	    			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	        			if(!(right.hasStarted())) {
	        				right.start();
	        			}
	    			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	        			if(!(left.hasStarted())) {
	    				left.start();
	        			}
	    			}
				}

				@Override
				public void keyReleased(KeyEvent e) {
	    			if (e.getKeyCode() == KeyEvent.VK_UP) {
	        			up.pause();
	        			gamePanel.getShip().stopsAccelerating();
	    			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	        			right.pause();
	    			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	        			left.pause();
	    		}
				}
				
				
	        		
	        };
	        frame.addKeyListener(a);
	        TimerTask timerTask = new TimerTask()
	        { 
	            public void run()  
	            { 
	            	
	            	if(gamePanel.getLives()>0) {
	    	        	gamePanel.repaint();
	    	        	gamePanel.getShip().move();
	    	        	gamePanel.getShip().decay();
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
