
import java.awt.event.*;
import java.awt.Window;

/**gameWindowListener.java
 * This class allows the window to gather input from the user especially when closing the program using the red cross.
 * @author Jessica Hu (5975122)
 */

public class gameWindowListener extends WindowAdapter {
	public void windowClosing (WindowEvent e)
	{
		Window window =e.getWindow();
		window.dispose();
		System.exit(0);
	}
	
	public void windowClosed(WindowEvent e){}
	public void windowActivated (WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowDeiconified (WindowEvent e){}
	public void windowOpened(WindowEvent e){}	
}
