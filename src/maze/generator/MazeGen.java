package maze.generator;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class MazeGen extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 * 
	 * @param dimX width
	 * @param dimY height
	 */
	public MazeGen(int dimX, int dimY){
		new ArenaFrame(dimX, dimY, this);
		wonderer = new Path();
	}
	
	private static int DIMX=75, DIMY=45;
	private Thread thread;
	private boolean running = false;
	private Path wonderer = new Path();
	private Runner mazeRunner = new Runner(DIMX, DIMY, this.wonderer);

	public static void main(String[] args) {
		new MazeGen(DIMX, DIMY);
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{ 
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0, ns = 1000000000 / amountOfTicks, delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running) render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(frames);
				frames = 0;
			} 
		}
	}
	
	private void tick(){
		mazeRunner.takesAStep();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(4);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, DIMX*60, DIMY*60);
		
		mazeRunner.paintsAPic(g);
		
		g.dispose();
		bs.show();
	}
}
