import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {

	Image cSprite;
	GameModel gm;
	
	public DrawPanel(Image character, GameModel model) {
		super();
		cSprite = character;
		gm = model;
	}
	
	@Override
	protected void paintComponent(Graphics g) {

	    super.paintComponents(g);
	    g.setColor(Color.WHITE);
	    //g.drawImage(gm.getPlayer().getAppearance(), 0, 0,32,32,0,0,32,32, null);
	    gm.getPlayer().render(gm, g);
	}
	
}
