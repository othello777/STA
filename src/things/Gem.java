package things;

import levels.Level;
import main.Globals;

public class Gem extends PhysicsThing {

	public Gem(int c) {
		type = c;
		switch (c) {
		case 0:
			Sprite = GetImageFile("stone_yellow.png");
			break;
		case 1:
			Sprite = GetImageFile("stone_orange.png");
			break;
		case 2:
			Sprite = GetImageFile("stone_red.png");
			break;
		case 3:
			Sprite = GetImageFile("stone_blue.png");
			break;
		case 4:
			Sprite = GetImageFile("stone_purple.png");
			break;
		case 5:
			Sprite = GetImageFile("stone_green.png");
			break;
		}
		Location.x = Globals.RAND.nextInt(Globals.DIMENSION.width);
		Location.y = Globals.RAND.nextInt(Globals.DIMENSION.height);
		
		Scale = 0.1f;
		CalculateSize();
	}

	@Override
	public void Add(Level lev) {
		super.Add(lev);
		while (level.Collides(this)) {
			Location.x = Globals.RAND.nextInt(Globals.DIMENSION.width);
			Location.y = Globals.RAND.nextInt(Globals.DIMENSION.height);
		}
	}
	
	@Override
	public void Hit() {
		Globals.AUDIO.play("rock" + (Globals.RAND.nextInt(3) + 1) + ".wav");
		super.Hit();
	}
	public int type;
}
