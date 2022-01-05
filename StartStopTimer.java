import java.util.Timer;
import java.util.TimerTask;

public class StartStopTimer extends Timer{
		private Game game;
		private String key;
		private TimerTask u;
		private TimerTask l;
		private TimerTask r;
		private boolean started;
		
		public StartStopTimer (Game g, String a) {
			game = g;
			key = a;
			u = null;
			l = null;
			r = null;
			started = false;
			
		}
		
		public void start(){
			started = true;
			if(key.equals("u")) {
				u = new TimerTask() {
		        	public void run() {
		        		game.getShipOne().accelerate();
		        	}
		        };
		        this.scheduleAtFixedRate(u, 0, 25);
			}
			else if(key.equals("l")) {
				l = new TimerTask() {
		        	public void run() {
		        		game.getShipOne().turnLeft();
		        	}
		        };
		        this.scheduleAtFixedRate(l, 0, 25);
			}
			else if(key.equals("r")) {
				r = new TimerTask() {
		        	public void run() {
		        		game.getShipOne().turnRight();
		        	}
		        };
		        this.scheduleAtFixedRate(r, 0, 25);
			}
			else if(key.equals("u2")) {
				u = new TimerTask() {
		        	public void run() {
		        		game.getShipTwo().accelerate();
		        	}
		        };
		        this.scheduleAtFixedRate(u, 0, 25);
			}
			else if(key.equals("l2")) {
				l = new TimerTask() {
		        	public void run() {
		        		game.getShipTwo().turnLeft();
		        	}
		        };
		        this.scheduleAtFixedRate(l, 0, 25);
			}
			else if(key.equals("r2")) {
				r = new TimerTask() {
		        	public void run() {
		        		game.getShipTwo().turnRight();
		        	}
		        };
		        this.scheduleAtFixedRate(r, 0, 25);
			}

			
		}
		
		public void pause() {
			started = false;
			if(key.equals("u")) {
				u.cancel();
				this.purge();
			}
			else if(key.equals("l")) {
				l.cancel();
				this.purge();
			}
			else if(key.equals("r")) {
				r.cancel();
				this.purge();
			}
			else if(key.equals("u2")) {
				u.cancel();
				this.purge();
			}
			else if(key.equals("l2")) {
				l.cancel();
				this.purge();
			}
			else if(key.equals("r2")) {
				r.cancel();
				this.purge();
			}
		}
		public boolean hasStarted() {
			return started;
		}
}
