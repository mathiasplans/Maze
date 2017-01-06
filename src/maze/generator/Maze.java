package maze.generator;

public class Maze {
	
	public Maze(int x, int y){
		setLoc(x, y);	
	}
	
	int locX, 
		locY, 
		sourceX, 
		sourceY;
	
	boolean north = true, 
			east = true, 
			south = true, 
			west = true, 
			self = true,
			isSafe = true;
	
	public void setX(int x){
		locX = x;
	}
	
	public void setY(int y){
		locY = y;
	}
	
	public void setLoc(int x, int y){
		setX(x);
		setY(y);
	}
	
	public void setSourceX(int x){
		sourceX = x;
	}
	
	public void setSourceY(int y){
		sourceY = y;
	}
	
	public void setSourceLoc(int x, int y){
		setSourceX(x);
		setSourceY(y);
		this.isSafe=true;
	}
	
	public void setNorth(boolean state){
		north = state;
	}
	
	public void setEast(boolean state){
		east = state;
	}
	
	public void setSouth(boolean state){
		south = state;
	}
	
	public void setWest(boolean state){
		west = state;
	}
	
	public void setSelf(boolean state){
		self = state;
	}
	
	public int getX(){
		return locX;
	}
	
	public int getY(){
		return locY;
	}
	
	public int getSourceX(){
		return sourceX;
	}
	
	public int getSourceY(){
		return sourceY;
	}
	
	public boolean isCellUsable(){
		if(getFreeCount() > 0) return true;
		else return false;
	}
	
	public int getFreeCount(){
		int count=0;
		
		if(north==true) count++;
		if(east==true) count++;
		if(south==true) count++;
		if(west==true) count++;
		
		return count;
	}
}
