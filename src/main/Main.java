package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
/*import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;*/
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
//import java.util.ArrayList;
//import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

//import cars.Car;
import levels.*;
/*import menus.Menu;
import menus.testmenu;*/
import things.Sprite;

public class Main {
	public static JFrame win = new JFrame();
	public static JPanel pan;
	public static void main(String[] args) {
		JPanel content = new GameWindow();
		//win.setSize(new Dimension(960, 600));
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.add(content);
		win.setTitle("Scratch-Thanos's Adventures");
		Debug.println("MinSize=640x400");
		win.setSize(1440, 900);
		win.setPreferredSize(new Dimension(1440, 926));
		win.setMinimumSize(new Dimension(640, 400));//win.getSize());
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pan = content;
		Sprite icon = new Sprite();
		icon.Sprite = icon.GetImageFile("thanos.png");
		//BufferedImage buff = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		//buff.getGraphics().drawImage(icon.Sprite, 0, 0, 100, 100, null);
		win.setIconImage(icon.Sprite);//(Image)buff);
		
		win.pack();
		win.setVisible(true);
	}
	
	public static class GameWindow extends JPanel implements KeyListener {
		private static final long serialVersionUID = 1L;
		private double scaleY = 0;
		private Graphics dbg; 
		private Image dbImage = null;
		private Thread updateTimer;
		private Timer fpsTimer;
		public boolean clrFrame = true;
		public static boolean Fullscreened = false;
		public static int FPS = 60;
		
		public GameWindow() {
			setBackground(Color.green);
			Debug.println("Request Focus");
			setFocusable(true);
			requestFocusInWindow();
			addKeyListener(this);
			//addMouseListener(this);
			Debug.println("Loading config");
			Settings.Load("config.properties");
			addMouseListener(new MouseAdapter() { 
			  @SuppressWarnings("deprecation")
			  public void mousePressed(MouseEvent me) { 
			    System.out.println(me);
			    Globals.currentLevel.KeyUpdateAll(new KeyEvent(pan, 0, 0, 0, KeyEvent.VK_E), true);
			  } 
			});
			Globals.currentLevel = new TestLevel();
			
			fpsTimer = new Timer(1000 / 60, new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	repaint();
	            }
	        }); fpsTimer.start(); 
	        
	        class UpdateRun implements Runnable {
	        	public void run()
	        	/* Repeatedly: update, render, sleep so loop takes close
	        	to period ms */
	        	{
		        	long beforeTime, timeDiff, sleepTime;
		        	beforeTime = System.currentTimeMillis();
		        	int fpsbreak = 0;
		        	while(true) {
			        	Globals.currentLevel.UpdateAll();
			            Sysinfo.UpdateUPS();
			        	timeDiff = System.currentTimeMillis() - beforeTime;
			        	sleepTime = 1000/60 - timeDiff; // time left in this loop
			        	
			        	if(fpsbreak <= 0) {
			            	fpsbreak = FPS;
			        	}
			        	else {
							fpsbreak -= 1;
						}
			        	
			        	if (sleepTime <= 0){ // update/render took longer than period	
			        		sleepTime = 1; // sleep a bit anyway
			        		Debug.println("Update render took too long!");
			        	}
			        	try {
			        		Thread.sleep(sleepTime); // in ms
			        	}
			        	catch(InterruptedException ex){}
			        	beforeTime = System.currentTimeMillis();
		        	}
	        	} // end of run()
	        }
	        updateTimer = new Thread(new UpdateRun()); 
	        updateTimer.setPriority(Thread.MAX_PRIORITY);
	        updateTimer.start();
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			Globals.currentLevel.KeyUpdateAll(e, true);
			int keyCode = e.getKeyCode();
			switch (keyCode) {
				case KeyEvent.VK_F4:
				case KeyEvent.VK_F11:
					if(!Fullscreened) {
						GraphicsConfiguration config = win.getGraphicsConfiguration();
						config.getDevice().setFullScreenWindow(win);
						//updateTimer.setDelay(1000 / 200);
					Fullscreened = true;
					}
					else {
						GraphicsConfiguration config = win.getGraphicsConfiguration();
						config.getDevice().setFullScreenWindow(null);
						win.setVisible(true);
						//updateTimer.setDelay(1000 / 90);
						Fullscreened = false;
					}
					break;
				
				case KeyEvent.VK_R:
					Globals.currentLevel = new TestLevel();
					break;
					
				case KeyEvent.VK_MINUS:
					if(Globals.GlobalScale > 0.04){
						if(Globals.GlobalScale <= 1) {
							Globals.GlobalScale /= 2;
						}
						else {
							if(Globals.GlobalScale > 1)
								Globals.GlobalScale -= 1;
						}
					}
					
					
					Debug.println("Scale=" + Globals.GlobalScale);
					break;
					
				case KeyEvent.VK_EQUALS:
						
					if(Globals.GlobalScale <= 1) {
						Globals.GlobalScale *= 2;
					}
					else {
						Globals.GlobalScale += 1;
					}
					Debug.println("Scale=" + Globals.GlobalScale);
					break;
					
				case KeyEvent.VK_M:
					FPS += 10;
					fpsTimer.setDelay(1000 / FPS);
					Debug.println("FPS=" + FPS);
					break;
					
				case KeyEvent.VK_L:
					if(FPS > 10)
						FPS -= 10;
					fpsTimer.setDelay(1000 / FPS);
					Debug.println("FPS=" + FPS);
					break;
					
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
					break;
					
				case KeyEvent.VK_B:
					Globals.Debug = !Globals.Debug;
					break;
					
				case KeyEvent.VK_CONTROL:
					/*if(Menu.use != null)
						Menu.Show(null);
					else {
						Menu.Show(new testmenu());
					}*/
					break;
					
				case KeyEvent.VK_SEMICOLON:
					if(Globals.Debug)
						clrFrame = !clrFrame;
					break;	
			}
		}
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {
			Globals.currentLevel.KeyUpdateAll(e, false);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			if (dbImage == null){
			      dbImage = createImage(Globals.DIMENSION.width, Globals.DIMENSION.height); 
			      if (dbImage == null) {
			        System.out.println("dbImage is null");
			        return;
			      }
			      else
			        dbg = dbImage.getGraphics();
				}
			scaleY = ((double)this.getHeight()) / (double)Globals.DIMENSION.height;
			super.paintComponent(g);
			dbg.setColor(Color.blue);
			if(clrFrame)
				dbg.fillRect(0, 0, dbImage.getWidth(null), dbImage.getHeight(null));
			Globals.currentLevel.DrawAll(dbg);
			
			if(Globals.Debug) {
				dbg.setColor(Color.white);
				dbg.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
				dbg.drawString("FPS: " + Sysinfo.fps, 0, 15);
				dbg.drawString("UPS: " + Sysinfo.ups + "/60", 0, 33);
				dbg.drawString("SET: " + FPS, 100, 15);
			}
			
			Graphics2D g2d = (Graphics2D) g;
			AffineTransform at = AffineTransform.getScaleInstance(scaleY, scaleY);
			g2d.drawRenderedImage((BufferedImage)dbImage, at);
			
			
			Sysinfo.UpdateFPS();
		}	
		
		public static class Sysinfo {
			public static int delay = 0;
			public static int delayu = 0;
			public static double fps = 0;
			public static double ups = 0;
			static long lastFpsTime = System.currentTimeMillis();
			static long lastUpsTime = System.currentTimeMillis();
			public static void UpdateFPS() {
				if(delay < 1) {
					try {
						fps = 1000 / (System.currentTimeMillis() - lastFpsTime);
					} catch (ArithmeticException e) {Debug.println("FPS/0");}
					delay = 30;
				} delay -= 1;
				lastFpsTime = System.currentTimeMillis();
			}
			public static void UpdateUPS() {
				if(delayu < 1) {
					try {
						ups = 1000 / (System.currentTimeMillis() - lastUpsTime);
					} catch (ArithmeticException e) {Debug.println("UPS/0");}
					delayu = 30;
				} delayu -= 1;
				lastUpsTime = System.currentTimeMillis();
			}
		}

	}	
}
