package ClearOut.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

	private BufferedImage image;
	private ClickListener clicker;
	
	public UIImageButton(int x, int y, int width, int height, BufferedImage images, ClickListener clicker) {
		super(x, y, width, height);
		this.image = images;
		this.clicker = clicker;
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		if(hovering)
			g.drawImage(image,  x,  y, width+(int)(.1*width), height+(int)(.1*height), null);
		else
			g.drawImage(image,  x,  y, width, height, null);
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}

}
