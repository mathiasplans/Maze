package maze.generator;
import java.util.LinkedList;


public class Path {
	int x=-1, y=-1;
	
	
	public Path(){
		middlePath = new LinkedList<Path>();
	}
	
	public Path(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Path(int x, int y, Direction_t direction){
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public enum Direction_t{
		SIDE,
		UP
	}

	private Direction_t direction = null;
	LinkedList<Path> middlePath;
	
	public boolean checkIfRoad(int checkX, int checkY){
		int indexCount = this.middlePath.size();
		if(this.middlePath.size()==0) return false;
		for(int i=0;i<indexCount;i++){
			if(this.middlePath.get(i).x == checkX && this.middlePath.get(i).y == checkY) return true;
		}
		return false;
	}
	
	public Direction_t getDirection(){
		return direction;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
}
