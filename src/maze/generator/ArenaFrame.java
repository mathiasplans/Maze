package maze.generator;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class ArenaFrame extends Canvas{
	
	private static final long serialVersionUID = 1L;
	JFrame frame = new JFrame("MazeGen");
	
	public ArenaFrame(int dimX, int dimY, MazeGen maze){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setPreferredSize(new Dimension(dimX*7, dimY*7));
		frame.setMaximumSize(new Dimension(dimX*7, dimY*7));
		frame.setMinimumSize(new Dimension(dimX*7, dimY*7));
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		
		frame.add(maze);
		maze.start();
	}
}
