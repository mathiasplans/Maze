package maze.generator;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Runner {
	
	private int dimX,
		dimY,
		currentX=dimX/2, 
		currentY=dimY, 
		lastX=currentX, 
		lastY=currentY, 
		startX=currentX, 
		startY=currentY,
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
		currentX=20; 
		currentY=20;
		lastX=currentX; 
		lastY=currentY; 
		startX=currentX; 
		startY=currentY;
		
		maze = new Maze[dimX][dimY];
		this.path = path;
	}
	
	/**
	 * Generates objects into the object array and 
	 * initializes the first cell that is occupied
	 */
	public void arenaInit(){
		generateArena();
		initStartCell();
		blockUnviablePaths();
		path.middlePath.add(new Path(startX*7, startY*7));
	}
	
	/**
	 * Generates arena for maze
	 */
	private void generateArena(){
		for(int i=0;i<dimY-1;i++){
			for(int j=0;j<dimX-1;j++){
				maze[j][i]= new Maze(j, i);
			}
		}
	}
	
	/**
	 * initialize first cell
	 */
	private void initStartCell(){
		maze[currentX][currentY].setNorth(true);
		maze[currentX][currentY].setEast(true);
		maze[currentX][currentY].setSouth(true);
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
		if(currentX==dimX) maze[currentX][currentY].setEast(false);
		else if(!maze[currentX+1][currentY].self) maze[currentX][currentY].setEast(false);
		if(currentY==0) maze[currentX][currentY].setNorth(false);
		else if(!maze[currentX][currentY-1].self) maze[currentX][currentY].setNorth(false);
		if(currentY==dimY) maze[currentX][currentY].setSouth(false);
		else if(!maze[currentX][currentY+1].self) maze[currentX][currentY].setSouth(false);
	}
	
	/**
	 * @return	returns false if starting cell is fully occupied
	 */
	public boolean checkStartCell(){
		if(!(maze[startX][startY].isCellUsable())) return true;
		else return false;
	}
	
	/**
	 * moves the mazerunner
	 */
	public void takesAStep(){
		if(isGenerated==false){ 
			arenaInit();
			this.isGenerated = true;
		}
		
		Maze mazeLoc = maze[currentX][currentY];
		
		/* If there are viable cells next to current, move there */
		if(mazeLoc.isCellUsable()){
			/* Save current location for later use: */
			lastX = currentX;
			lastY = currentY;
			
			/* Move to a random place: */
			int x = 200;
			tempRand=rand.nextInt(x);
			
			if(tempRand>(x*3)/4){
				if(mazeLoc.north) currentY--;
				else if(mazeLoc.east) currentX++;
				else if(mazeLoc.south) currentY++;
				else if(mazeLoc.west) currentX--;
				
			}else if(tempRand>(x*2)/4){
				if(mazeLoc.west) currentX--;
				else if(mazeLoc.north) currentY--;
				else if(mazeLoc.east) currentX++;
				else if(mazeLoc.south) currentY++; 
				
			}else if(tempRand>(x*1)/4){					
				if(mazeLoc.east) currentX++; 
				else if(mazeLoc.west) currentX--;
				else if(mazeLoc.north) currentY--;
				else if(mazeLoc.south) currentY++;
			}else if(tempRand>=0){					
				if(mazeLoc.south) currentY++; 
				else if(mazeLoc.west) currentX--;
				else if(mazeLoc.north) currentY--;
				else if(mazeLoc.east) currentX++;
			}
			
			/* Add render objects */
			for(int i = 0; i < path.middlePath.size(); i++){
				if(path.checkIfRoad(currentX, currentY)) break;
				if(path.middlePath.size()==i){
					path.middlePath.add(new Path(currentX*7, currentY*7));
					if(currentX-lastX > 0) path.middlePath.add(new Path(currentX*7-4, currentY*7, Path.Direction_t.UP));
					else if(currentX-lastX < 0) path.middlePath.add(new Path(currentX*7+4, currentY*7, Path.Direction_t.UP));
					else if(currentY-lastY > 0) path.middlePath.add(new Path(currentX*7, currentY*7-4, Path.Direction_t.SIDE));
					else if(currentY-lastY < 0) path.middlePath.add(new Path(currentX*7, currentY*7+4, Path.Direction_t.SIDE));
				}
			}
			
			/* Make cell occupied: */
			maze[currentX][currentY].setSelf(false);
			
			/* Block impossible paths: */
			blockUnviablePaths();
			
			/* Set source cell: */
			maze[currentX][currentY].setSourceLoc(lastX, lastY);
//			System.out.println("B");
			System.out.println(maze[currentX][currentY].sourceX + " " + maze[currentX][currentY].sourceY + " (" + lastX + " " + lastY +")" + "    " + currentX + " " + currentY + "    " + maze[currentX][currentY].getFreeCount());
		}
		/* Else if no viable paths, go back to source until viable cell can be occupied */
		else{
			currentX = mazeLoc.sourceX;
			currentY = mazeLoc.sourceY;
			System.out.println(maze[currentX][currentY].sourceX + " " + maze[currentX][currentY].sourceY + "    " + currentX + " " + currentY + "    " + maze[currentX][currentY].getFreeCount() + " <");
		}
//	System.out.println(maze[currentX][currentY].sourceX + " " + maze[currentX][currentY].sourceY + "    " + currentX + " " + currentY);
	}
	
	public void paintsAPic(Graphics g){
		g.setColor(Color.white);
//		g.fillRect(20, 20, 6, 6);
//		System.out.println(currentX + " " + currentY);
		if(path.middlePath.size()==0) return;
		for(int i = 0; i < path.middlePath.size(); i++){
			if(path.middlePath.get(i).getDirection()==null) g.fillRect(path.middlePath.get(i).getX(), path.middlePath.get(i).getY(), 6, 6);
			if(path.middlePath.get(i).getDirection()==Path.Direction_t.UP) g.fillRect(path.middlePath.get(i).getX(), path.middlePath.get(i).getY(), 1, 6);
			if(path.middlePath.get(i).getDirection()==Path.Direction_t.SIDE) g.fillRect(path.middlePath.get(i).getX(), path.middlePath.get(i).getY(), 6, 1);
		}
	}
}
