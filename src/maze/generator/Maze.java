package maze.generator;

/**
 * Maze class
 * Is a cell in the maze
 * 
 * @author Mathias
 *
 */
public class Maze {
	
	private int sourceX, 
				sourceY;
	
	boolean north = true, 
			east = true, 
			south = true, 
			west = true, 
			self = true,
			isSafe = true;
	
	/**
	 * Set method for x and y coordinate
	 * @param x	x coordinate
	 * @param y	y coordinate
	 */
	public void setSourceLoc(int x, int y){
		sourceX = x;
		sourceY = y;
	}
	
	/**
	 * Set method for northern cell status
	 * @param state	false if impassable, true if passable
	 */
	public void setNorth(boolean state){
		north = state;
	}
	
	/**
	 * Set method for eastern cell status
	 * @param state	false if impassable, true if passable
	 */
	public void setEast(boolean state){
		east = state;
	}
	
	/**
	 * Set method for southern cell status
	 * @param state	false if impassable, true if passable
	 */
	public void setSouth(boolean state){
		south = state;
	}
	
	/**
	 * Set method for western cell status
	 * @param state	false if impassable, true if passable
	 */
	public void setWest(boolean state){
		west = state;
	}
	
	/**
	 * Set method for local cell status
	 * @param state	false if impassable, true if passable
	 */
	public void setSelf(boolean state){
		self = state;
	}
	
	/**
	 * Get method for local cell source x coordinate
	 * @return	local cell's source x coordinate
	 */
	public int getSourceX(){
		return sourceX;
	}
	
	/**
	 * Get method for local cell source y coordinate
	 * @return	local cell's source y coordinate
	 */
	public int getSourceY(){
		return sourceY;
	}
	
	/**
	 * Checks if there are any ways to move forward
	 * @return	true if there are adjacent unclaimed cells, else false
	 */
	public boolean isCellUsable(){
		if(getFreeCount() > 0) return true;
		else return false;
	}
	
	/**
	 * Returns a number of unclaimed adjacent cells
	 * @return	number of unclaimed adjacent cells
	 */
	private int getFreeCount(){
		int count=0;
		
		if(north==true) count++;
		if(east==true) count++;
		if(south==true) count++;
		if(west==true) count++;
		
		return count;
	}
}
