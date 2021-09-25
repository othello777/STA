package things;
import java.awt.Point;

import main.Debug;
import main.Globals;

public class PhysicsThing extends MaterialThing {

	public PhysicsThing() {
		Velocity = new Point();
	}
	
	@Override
	public void Update() {
		//Velocity.y += 2;//apply gravity
		Point saveLocation = (Point)Location.clone();
		Location.x += Velocity.x;
		Location.y += Velocity.y;
		
		if(level.PathCollides(saveLocation, this) != null) {
			Thing forthing = level.PathCollides(saveLocation, this);
			((MaterialThing)forthing).Hit();
			((MaterialThing)this).Hit();
			Location = saveLocation;
			int i = 0;
			while (level.PathCollides(saveLocation, this) != null) {
				Debug.println("got stuck; yeeting");
				forthing.Location.translate(Velocity.x, Velocity.y);
				if(i > 10) {
					Location.translate(1, 1);
					if(i > 50)
						return;
				}
				i++;
			}
			if(level.ptransfermomentum) {
				Point saveVelocity = (Point)Velocity.clone();
				Velocity = ((PhysicsThing)forthing).Velocity;
				((PhysicsThing)forthing).Velocity = saveVelocity;
				//((PhysicsThing)forthing).Velocity.x += ((PhysicsThing)this).Velocity.x / 1.5;
				//((PhysicsThing)forthing).Velocity.y += ((PhysicsThing)this).Velocity.y / 1.5;
				
			}
			else {
				OnCollision();	
			}
		}
		if(level.loopwalls) {
			if(Location.x > Globals.DIMENSION.width - ScaledSize.width)
				Location.x = 0;
			if(Location.x < 0)
				Location.x = Globals.DIMENSION.width - ScaledSize.width;
			if(Location.y > Globals.DIMENSION.height - ScaledSize.height)
				Location.y = 0;
			if(Location.y < 0)
				Location.y = Globals.DIMENSION.height - ScaledSize.height;
		}
		
		super.Update();
	}
	
	public void OnCollision() {
		Velocity.x = 0;
		Velocity.y = 0;
	}
	
	public Point Velocity;
}
