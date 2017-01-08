package maze.generator;
import java.util.LinkedList;

/**
 * Path class
 * Is a render object or a linked list of the render objects
 * 
 * @author Mathias
 *
 */
public class Path {
	private int x, y;
	LinkedList<Path> middlePath;
	
	/**
	 * Constructor
	 * initializes linked list
	 */
	public Path(){
		middlePath = new LinkedList<Path>();
	}
	
	/**
	 * Constructor
	 * initializes x and y coordinates
	 */
	public Path(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/*
	 * Get method for x coordinate
	 */
	public int getX(){
		return this.x;
	}
	
	/*
	 * Get method for y coordinate
	 */
	public int getY(){
		return this.y;
	}
}
