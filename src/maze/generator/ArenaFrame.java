package maze.generator;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * ArenaFrame class
 * Creates a window for displaying the maze
 * 
 * @author Mathias
 *
 */
public class ArenaFrame extends Canvas{
	
	private static final long serialVersionUID = 1L;
	JFrame frame = new JFrame("MazeGen");
	
	/**
	 * Constructor
	 * Creates and configures JFrame
	 * 
	 * @param dimX	x-dimensions
	 * @param dimY	y-dimensions
	 * @param maze	Runnable class object
	 */
	public ArenaFrame(int dimX, int dimY, MazeGen maze){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setPreferredSize(new Dimension(dimX*21+9, dimY*22));
		frame.setMaximumSize(new Dimension(dimX*21+9, dimY*22));
		frame.setMinimumSize(new Dimension(dimX*21+9, dimY*22));
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(true);
		
		frame.add(maze);
		maze.start();
	}
}
