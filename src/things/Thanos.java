package things;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;

import main.Debug;
import main.Globals;
import main.Main;

public class Thanos extends PhysicsThing {

	public Thanos() {
		Location = new Point(Globals.DIMENSION.width/2,Globals.DIMENSION.height/2);
		Sprite = GetImageFile("thanos.png");
		facingLeft = GetImageFile("thanos.png");
		facingRight = GetImageFile("thanosrev.png");
		isKeyPressed = new IKP();
		Scale = 0.2f;
		speed = 6;
		SetDefault(1);
		CalculateSize();
	}
	

	@Override
	public void Update() {
		if(turnswick)
		{
			//Point prevVal = (Point)Location.clone();
			if(isKeyPressed.Right && Velocity.x < speed) {
				Velocity.x += 1;
			}
			if(isKeyPressed.Left && Velocity.x > speed * -1) {
				Velocity.x -= 1;
			}
			if(isKeyPressed.Up && Velocity.y > speed * -1) {
				Velocity.y -= 1;
			}
			if(isKeyPressed.Down && Velocity.y < speed) {
				Velocity.y += 1;
			}
			/*if(level.Collides(this))
				Location = prevVal;*/
			
			if(Velocity.x > 0)
				Sprite = facingLeft;
			else if (Velocity.x < 0) {
				Sprite = facingRight;
			}
			
			turnswick = false;
		}
		else {
			turnswick = true;
		}
		super.Update();
	}
	
	public void KeyUpdate(KeyEvent e, boolean state) {
		int keyCode = e.getKeyCode();
		if(state) {
			Debug.println("on");
			if(keyCode == KeyEvent.VK_E) {
				CreatePhantom(1, 20);
				
				this.Location.x = (int)(Main.pan.getMousePosition().x / ((float)Main.pan.getSize().width / Globals.DIMENSION.width))
						- ScaledSize.width / 2;
				this.Location.y = (int)(Main.pan.getMousePosition().y / ((float)Main.pan.getSize().height / Globals.DIMENSION.height))
						- ScaledSize.height / 2;
			}
			
			if(keyCode == Up)
				isKeyPressed.Up = true;
			else if (keyCode == Down)
				isKeyPressed.Down = true;
			else if (keyCode == Left)
				isKeyPressed.Left = true;
			else if (keyCode == Right)
				isKeyPressed.Right = true;
		}
		else {
			Debug.println("off");
			if(keyCode == Up)
				isKeyPressed.Up = false;
			else if (keyCode == Down)
				isKeyPressed.Down = false;
			else if (keyCode == Left)
				isKeyPressed.Left = false;
			else if (keyCode == Right)
				isKeyPressed.Right = false;
		}
	}
	
	public void SetDefault(int PlayerNo) {
		switch (PlayerNo) {
		case 1:
			Up = KeyEvent.VK_UP;
			Down = KeyEvent.VK_DOWN;
			Left = KeyEvent.VK_LEFT;
			Right = KeyEvent.VK_RIGHT;
			break;
		case 2:
			Up = KeyEvent.VK_W;
			Down = KeyEvent.VK_S;
			Left = KeyEvent.VK_A;
			Right = KeyEvent.VK_D;
			break;
		case 3:
			Up = KeyEvent.VK_P;
			Down = KeyEvent.VK_SEMICOLON;
			Left = KeyEvent.VK_L;
			Right = KeyEvent.VK_QUOTE;
			break;
		case 4:
			Up = KeyEvent.VK_Y;
			Down = KeyEvent.VK_H;
			Left = KeyEvent.VK_G;
			Right = KeyEvent.VK_J;
			break;
		case 5:
			Up = KeyEvent.VK_HOME;
			Down = KeyEvent.VK_END;
			Left = KeyEvent.VK_DELETE;
			Right = KeyEvent.VK_PAGE_DOWN;
			break;
		case 6:
			Up = KeyEvent.VK_NUMPAD5;
			Down = KeyEvent.VK_NUMPAD2;
			Left = KeyEvent.VK_NUMPAD1;
			Right = KeyEvent.VK_NUMPAD3;
			break;
		case 7:
			Up = KeyEvent.VK_SLASH;
			Down = KeyEvent.VK_NUMPAD8;
			Left = KeyEvent.VK_NUMPAD7;
			Right = KeyEvent.VK_NUMPAD9;
			break;
		case 8:
			Up = KeyEvent.VK_K;
			Down = KeyEvent.VK_COMMA;
			Left = KeyEvent.VK_M;
			Right = KeyEvent.VK_PERIOD;
			break;
		case 9:
			Up = KeyEvent.VK_F;
			Down = KeyEvent.VK_C;
			Left = KeyEvent.VK_X;
			Right = KeyEvent.VK_V;
			break;

		default:
			break;
		}
	}
	
	public IKP isKeyPressed;
	public int Up;
	public int Down;
	public int Left;
	public int Right;
	public int speed;
	private boolean turnswick = true;
	public Image facingLeft;
	public Image facingRight;
	
	public static class IKP {
		public boolean Up = false;
		public boolean Down = false;
		public boolean Left = false;
		public boolean Right = false;
		public boolean Space = false;
	}
}