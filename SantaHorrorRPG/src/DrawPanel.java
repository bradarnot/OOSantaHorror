import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {

	Image cSprite;
	
	public DrawPanel(Image character) {
		super();
		cSprite = character;
	}
	
	@Override
	protected void paintComponent(Graphics g) {

	    super.paintComponents(g);
	    g.setColor(Color.WHITE);
	    g.drawImage(cSprite, 0, 0,32,32,0,0,32,32, null);
	}
	
}
