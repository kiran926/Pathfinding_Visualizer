import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyIn implements KeyListener{
	
	PathDemo pd;
	
	public KeyIn(PathDemo pd) {
		this.pd=pd;
		
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {
		int code=e.getKeyCode();
		
		if(code==KeyEvent.VK_ENTER) {
			pd.AutoSearch();		
			}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	

}
