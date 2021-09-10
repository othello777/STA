package levels;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.Globals;
import things.*;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;

public abstract class Level {

	public void Add(Thing thing) {
		thing.Add(this);
		things.add(thing);
		if(Globals.Debug)
			System.out.println("Added " + thing);
	}
	
	public void Remove(Thing thing) {
		things.remove(thing);
	}
	
	/*public void SetGround(Ground inGround) {
		inGround.Add(this);
		things.add(0, inGround);
		ground = inGround;
		if(Game.ArtillaryWar.Debug)
			System.out.println("Grounded");
	}*/
	
	public void DrawAll(Graphics g) {
		for (int i = 0; i < things.size(); i++) {
			Thing thing = things.get(i);
			thing.draw(g);
		}
	}
	
	public void UpdateAll() {
		for (int i = 0; i < things.size(); i++) {
			things.get(i).Update();
		}
	}
	
	public void KeyUpdateAll(KeyEvent e, boolean state) {			
		for (int i = 0; i < things.size(); i++) {
			Thing thing = things.get(i);
			if(thing instanceof Thanos) {
				((Thanos) thing).KeyUpdate(e, state);
			}
		}
	}
	
	public boolean Collides(Thing thing) {
		Rectangle senderThing = HitBoxCalc(thing);
		thing.Hitbox = senderThing;
		for (Thing forthing : things) {
			if(HitBoxCalc(forthing).intersects(senderThing) && 
					thing != forthing && forthing instanceof MaterialThing)
			{
				/*((MaterialThing)forthing).Hit();
				((MaterialThing)thing).Hit();
				if(ptransfermomentum) {
					((PhysicsThing)forthing).Velocity.x += ((PhysicsThing)thing).Velocity.x;
					((PhysicsThing)forthing).Velocity.y += ((PhysicsThing)thing).Velocity.y;
				}*/
				return true;
			}
		}
		return false;
	}
	
	public Thing PathCollides(Point saveLocation, Thing sender) {
		Line2D path = new Line2D.Float(saveLocation, sender.Location);
		Rectangle senderThing = HitBoxCalc(sender);
		sender.Hitbox = senderThing;
		for (Thing forthing : things) {
			if(HitBoxCalc(forthing).intersectsLine(path) && 
					sender != forthing && forthing instanceof MaterialThing 
					|| HitBoxCalc(forthing).intersects(senderThing) && 
					sender != forthing && forthing instanceof MaterialThing)
			{
				
				return forthing;
			}
		}
		return null;
	}
	
	private Rectangle HitBoxCalc(Thing thing) {
		Rectangle hitbox;
		if (thing.Scale > 1) {
			hitbox = new Rectangle(thing.Location, new Dimension(
					thing.Sprite.getWidth(null) * (int)thing.Scale,
					thing.Sprite.getHeight(null) * (int)thing.Scale));
		}
		else if (thing.ScaledSize != null || thing.ScaledSize != new Dimension(0 , 0)) {
			hitbox = new Rectangle(thing.Location, new Dimension(
					thing.ScaledSize.width, thing.ScaledSize.height));
		} 
		else {
			hitbox = new Rectangle(thing.Location, new Dimension(
					thing.Sprite.getWidth(null),
					thing.Sprite.getHeight(null)));
		}	
		return hitbox;
	}
	
	/*public void InvokeFire() {
		for (Thing thing : things) {
			if(thing instanceof Tank) {
				((Tank) thing).Fire();
			}
		}
	}*/
	
	private List<Thing> things = new ArrayList<Thing>();
	public boolean loopwalls = false;
	public Thanos thanos;
	public boolean ptransfermomentum = false;
	//public Ground ground;
}
