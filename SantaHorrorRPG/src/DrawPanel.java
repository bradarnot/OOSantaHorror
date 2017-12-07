import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {

	Image bgImage;
	
	public DrawPanel(Image background) {
		super();
		bgImage = background;
	}
	
	@Override
	protected void paintComponent(Graphics g) {

	    super.paintComponents(g);
	    g.setColor(Color.WHITE);
	    g.drawImage(bgImage, 0, 0, null);
	}
	
}
