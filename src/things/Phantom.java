package things;

public class Phantom extends Thing {

	public Phantom(Thing parent) {
		this.Sprite = parent.Sprite;
		this.Location = parent.Location.getLocation();
		this.Scale = parent.Scale;
		
	}
	
	@Override
	public void Update() {
		if(typefade == 0) {
			if(timer >= ticktime)
				level.Remove(this);
		}
		else if(typefade == 1) {
			alpha = (((float)timer - ticktime) * -1) / ticktime;
			if(timer >= ticktime)
				level.Remove(this);
		}
		else if(typefade == 2) {
			alpha = (((float)timer - ticktime) * -1) / ticktime;
			if(timer >= ticktime)
				level.Remove(this);
		}
		
		if(typefade > 0)
			timer++;
		super.Update();
	}
	
	private int timer = 0;
	public int ticktime = 0;
	public int typefade = 0; //0=remove; 1=fadeout; 2=spacestone;
}
