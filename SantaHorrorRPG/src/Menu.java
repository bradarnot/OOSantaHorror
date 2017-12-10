import java.awt.*;

import javax.swing.*;

public class Menu extends JPanel {

	Image bgImage;
	
	public Menu(Image background) {
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
