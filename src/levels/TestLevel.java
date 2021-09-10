package levels;

import java.awt.Point;

import main.Globals;
import things.Gem;
import things.Thanos;
import things.Thing;

public class TestLevel extends Level {

	public TestLevel() {
		Thing soccerfg = new Thing();
		soccerfg.Sprite = soccerfg.GetImageFile("soccergauntletfg.png");
		soccerfg.Location = new Point(20, (Globals.DIMENSION.height/2) - (soccerfg.Sprite.getHeight(null)/2));
		
		Thing soccerbg = new Thing();
		soccerbg.Sprite = soccerbg.GetImageFile("soccergauntletbg.png");
		soccerbg.Location = new Point(20, (Globals.DIMENSION.height/2) - (soccerbg.Sprite.getHeight(null)/2));
		
		loopwalls = true;
		ptransfermomentum = true;
		this.Add(new sky2());
		this.Add(soccerbg);
		for(int i = 0; i <= 5; i++) {
			this.Add(new Gem(i));
		}
		this.Add(new sky());
		this.thanos = new Thanos();
		this.Add(thanos);
		this.Add(soccerfg);
		
	}
	
	public class sky extends Thing {
		public sky() {
			Sprite = this.GetImageFile("nebula.png");
			Scale = 2;
		}
		@Override
		public void Update() {
			this.Location.x = (int)((float)thanos.Location.x / (Globals.DIMENSION.width - thanos.ScaledSize.width)
		* ((Sprite.getWidth(null) * Scale) - Globals.DIMENSION.width) * -1);
			this.Location.y = (int)((float)thanos.Location.y / (Globals.DIMENSION.height - thanos.ScaledSize.height)
		* ((Sprite.getHeight(null)* Scale) - Globals.DIMENSION.height) * -1);
			super.Update();
		}
	}
	public class sky2 extends Thing {
		public sky2() {
			Sprite = this.GetImageFile("nebularev.png");
			Scale = 3;
		}
		@Override
		public void Update() {
			this.Location.x = (int)((float)thanos.Location.x / (Globals.DIMENSION.width - thanos.ScaledSize.width)
		* ((Sprite.getWidth(null) * Scale) - Globals.DIMENSION.width) * -1);
			this.Location.y = (int)((float)thanos.Location.y / (Globals.DIMENSION.height - thanos.ScaledSize.height)
		* ((Sprite.getHeight(null)* Scale) - Globals.DIMENSION.height) * -1);
			super.Update();
		}
	}
}
