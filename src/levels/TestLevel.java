package levels;

import main.Globals;
import things.Gem;
import things.Thanos;
import things.Thing;

public class TestLevel extends Level {

	public TestLevel() {
		loopwalls = true;
		ptransfermomentum = true;
		this.Add(new sky2());
		for(int i = 0; i <= 5; i++) {
			this.Add(new Gem(i));
		}
		this.Add(new sky());
		this.thanos = new Thanos();
		this.Add(thanos);
		
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
