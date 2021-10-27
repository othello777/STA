package things;

import java.awt.image.BufferedImage;

public class EventBox extends Thing {
	
	public EventBox() {
		this.Sprite = new BufferedImage(1,1,BufferedImage.TYPE_BYTE_GRAY);
		this.alpha = 0f;
	}
	
	
}
