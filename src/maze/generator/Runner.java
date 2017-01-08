package maze.generator;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * Runner class
 * Responsible for generating the maze and creating render objects
 * 
 * @author Mathias
 *
 */
public class Runner {
	
	private int dimX,
		dimY,
		currentX, 
		currentY, 
		lastX, 
		lastY, 
		startX, 
		startY,
		tempRand=0;

	Maze[][] maze;
	Path path;
	boolean isGenerated = false;
	
	/**
	 * Constructor
	 * 
	 * Sets arena dimensions and initializes maze object array
	 * 
	 * @param x x-dimension
	 * @param y y-dimension
	 */
	public Runner(int x, int y, Path path){
		dimX=x;
		dimY=y;
		currentX=0; 
		currentY=5;
		lastX=currentX; 
		lastY=currentY; 
		startX=currentX; 
		startY=currentY;
		
		maze = new Maze[dimX][dimY];
		this.path = path;
		
		arenaInit();
	}
	
	/**
	 * Generates objects into the object array and 
	 * initializes the first cell that is occupied
	 */
	public void arenaInit(){
		generateArena();
		initStartCell();
	}
	
	/**
	 * Generates arena for maze
	 */
	private void generateArena(){
		for(int i=0;i<dimY;i++){
			for(int j=0;j<dimX;j++){
				maze[j][i]= new Maze();
			}
		}
	}
	
	/**
	 * initialize first cell
	 */
	private void initStartCell(){
		maze[currentX][currentY].setNorth(false);
		maze[currentX][currentY].setEast(true);
		maze[currentX][currentY].setSouth(false);
		maze[currentX][currentY].setWest(false);
		maze[currentX][currentY].setSelf(false);
		
		
		maze[currentX][currentY].setSourceLoc(startX, startY);
	}
	
	Random rand = new Random();
	
	
	/**
	 * Blocks impossible paths
	 */
	private void blockUnviablePaths(){
		/* Check if end of arena or path */
		if(currentX==0) maze[currentX][currentY].setWest(false);
		else if(!maze[currentX-1][currentY].self) maze[currentX][currentY].setWest(false);
		if(currentX==dimX-1) maze[currentX][currentY].setEast(false);
		else if(!maze[currentX+1][currentY].self) maze[currentX][currentY].setEast(false);
		if(currentY==0) maze[currentX][currentY].setNorth(false);
		else if(!maze[currentX][currentY-1].self) maze[currentX][currentY].setNorth(false);
		if(currentY==dimY-1) maze[currentX][currentY].setSouth(false);
		else if(!maze[currentX][currentY+1].self) maze[currentX][currentY].setSouth(false);
	}
	
	/**
	 * moves the mazerunner
	 */
	public void takesAStep(){
		Maze mazeLoc = maze[currentX][currentY];
		
		/* If there are viable cells next to current, move there */
		if(mazeLoc.isCellUsable()){
			/* Save current location for later use: */
			lastX = currentX;
			lastY = currentY;
			
			/* Move to a random place: */
			int x = 10000000;
			tempRand=rand.nextInt(x);
			
			if(tempRand>=(x*3)/4){
				if(mazeLoc.north) currentY--;
				else if(mazeLoc.east) currentX++;
				else if(mazeLoc.south) currentY++;
				else if(mazeLoc.west) currentX--;
				
			}else if(tempRand>=(x*2)/4){
				if(mazeLoc.west) currentX--;
				else if(mazeLoc.north) currentY--;
				else if(mazeLoc.east) currentX++;
				else if(mazeLoc.south) currentY++; 
				
			}else if(tempRand>=x/4){					
				if(mazeLoc.south) currentY++;
				else if(mazeLoc.west) currentX--;
				else if(mazeLoc.north) currentY--;
				else if(mazeLoc.east) currentX++;
			}else if(tempRand>=0){					
				if(mazeLoc.east) currentX++;
				else if(mazeLoc.west) currentX--;
				else if(mazeLoc.south) currentY++; 
				else if(mazeLoc.north) currentY--;
			}
			
			/* Add render objects */
			path.middlePath.add(new Path(currentX*21, currentY*21));			
			path.middlePath.add(new Path(currentX*21-12*(currentX-lastX), currentY*21-12*(currentY-lastY)));
					
			/* Make cell occupied: */
			maze[currentX][currentY].setSelf(false);
			
			/* Set source cell: */
			maze[currentX][currentY].setSourceLoc(lastX, lastY);
			}
		/* Else if no viable paths, go back to source until viable cell can be occupied */
		else{
			currentX = mazeLoc.getSourceX();
			currentY = mazeLoc.getSourceY();
		}
		
		/* Block impossible paths: */
		blockUnviablePaths();
	}
	
	public void paintsAPic(Graphics g){
		g.setColor(Color.white);
		for(int i = 0; i < path.middlePath.size(); i++){
			g.fillRect(path.middlePath.get(i).getX(), path.middlePath.get(i).getY(), 12, 12);
		}
		g.setColor(Color.red);
		g.fillRect(currentX*21, currentY*21, 12, 12);
		g.setColor(Color.cyan);
		g.fillRect(startX*21, startY*21, 12, 12);
	}
}
