import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DrawPanel extends JPanel {

	Image cSprite;
	GameModel gm;
	int width;
	int height;
	
	public DrawPanel(GameModel model, int width, int height) {
		super();
		//cSprite = character;
		gm = model;
		this.width = width;
		this.height = height;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		BufferedImage foo = null;
		try {
			foo = ImageIO.read(new File("img" + File.separator + "tile.jpg"));
		} catch (IOException e) {
			System.out.println("Could not load image!!");
			e.printStackTrace();
		}
		for (int i=0; i < (width / 32); i++) {
			for (int j=0; j < (height / 32); j++) {
	    			g.drawImage(foo, i*32, j*32, null);
			}
		}

	    super.paintComponents(g);
	    g.setColor(Color.WHITE);
	    gm.getPlayer().render(gm, g);
	}
	
}
