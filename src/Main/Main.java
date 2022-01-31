package Main;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JOptionPane;

import java.awt.*;

import Display.Display;
import Physics.PointCal;
import PointGen.RanPG;


public class Main implements Runnable{
	
	private Display display;
	private int width, height;
	public String title = "title";
	
	private Thread thread;
	private boolean running = false;
	Graphics gfx;
	static double t = 0;
	static double temp = 0;
	static double tempx = 0;
	static int x = 0;
	int xpos[] = new int[10];
	int ypos[] = new int[10];
	double mass[] = new double[10];
	double size[] = new double[10];
	int co = 0;
	public static boolean ison;

	public static void main(String[]args) {
		
	}
	
	//Input
	private Input.KeyManager keyManager;
		
		//Camera
		

		//Handler
		
		
		//
		public Main(String title, int width, int height) {
			
			this.width = width;
			this.height = height;
			this.title = title ;
			keyManager = new Input.KeyManager();

			
		}
		
		private void init() {
			
			display = new Display(title,width,height);
			display.getFrame();
			
		}
		
		private void tick() {
			t++;
			keyManager.tick();
			
		}
		private void render(double height) {
			
			BufferStrategy bs = display.getCanvas().getBufferStrategy();
			if(bs == null) {
				display.getCanvas().createBufferStrategy(2);
				return;
			}
			gfx = bs.getDrawGraphics();
			
			//Clear
			gfx.setColor(Color.WHITE);
			gfx.fillRect(0, 0, 1920, 1080);
			gfx.setColor(Color.BLACK);
			
			
			//Draw
			if (t%60 == 0) {
			//Create points---------------------
			if (RanPG.getCounter() != 4) {
			gfx.drawRect(RanPG.RandomPointx(), RanPG.RandomPointy(), 10, 10);
			xpos[RanPG.getCounter()] = RanPG.RandomPointx();
			ypos[RanPG.getCounter()] = RanPG.RandomPointy();
			RanPG.setCounter(RanPG.getCounter()+1);
			co++;
			
			}
			}
			//EndCreatePoints------------------
			
			//Redraw Points in Initial position-
			
			
			
			PointerInfo a = MouseInfo.getPointerInfo();
			Point b = a.getLocation();
			int x = (int) b.getX();
			int y = (int) b.getY();
			
			
			//xpos[0] = (int) b.getX();
			//ypos[0] = (int) b.getY();
			gfx.drawOval((int) b.getX() - 280, (int) b.getY() - 280 ,560, 560);
			
			
			
			
			
			//////
			//if (t%60 == 0) {
			for (int i = 0; i < co; i++) {
				
				for (int j = 0; j < co; j++) {
					
					if (i != j) {
				
				PointCal.PointCalculation(xpos[i], ypos[i], xpos[j], ypos[j], mass[i], mass[j]);
				/////////////////////////
				//Collision
				//=====================
				int tempx2 = xpos[i] - xpos[j];
				int tempy2 = ypos[i] - ypos[j];
				tempx2 = (int) Math.abs(tempx2);
				tempy2 = (int) Math.abs(tempy2);
				
				
				
				
				
				if (tempx2 < 60 && tempy2 < 60){
					
					if (PointCal.Quadrant == 0) {
						xpos[i] = (int) Math.random()*1920;
						ypos[i] = (int) Math.random()*1080;
						xpos[j] = (int) Math.random()*1920;
						ypos[j] = (int) Math.random()*1080;
					}
					
					
					if (size[i] <= size[j]) {
					mass[j] = mass[j] + 0.01;
					xpos[i] = (int) Math.random()*1920;
					ypos[i] = (int) Math.random()*1080;
					size[j] = size[j]+1;
					//JOptionPane.showMessageDialog(null, "yes");
					//JOptionPane.showMessageDialog(null, xpos[i] + " " + xpos[j] + " " + ypos[i] + " " + ypos[j]);
					
					} 
					if (size[i] >= size[j]) {
						mass[i] = mass[i] + 0.01;
						xpos[j] = (int) Math.random()*1920;
						ypos[j] = (int) Math.random()*1080;
						size[i] = size[j]+1;
						//JOptionPane.showMessageDialog(null, "yes");
						//JOptionPane.showMessageDialog(null, xpos[i] + " " + xpos[j] + " " + ypos[i] + " " + ypos[j]);
						
					}
				}
				
				///////////////////////
				
				xpos[i] = xpos[i]+((int)PointCal.getX()/1);
				ypos[i] = ypos[i]+((int)PointCal.getY()/1);
				
					}
					
				
				}

				
					
					for (int e = 0; e < co; e++) {
					
					
					//if (t%60 == 0) {
					//mass[f] = mass[f] + 0.01;
					//}
					if (xpos[i] >= 1720) {
						xpos[i] = 1000;
						
					}
					if (xpos[i] <= 200) {

						xpos[i] = 800;
					}
					if (ypos[i] >= 880) {
						ypos[i] = 600;
					}
					if (ypos[i] <= 200) {
							ypos[i] = 500;
						
					
					}
	

					
						
					//gfx.drawRect(xpos[f], ypos[f], 10, 10);
					//gfx.fillOval(xpos[f],ypos[f], (int) Math.round(mass[f]), (int) Math.round(mass[f]));
					gfx.fillOval(xpos[i],ypos[i], (int) size[i] + 5, (int) size[i] + 5);
					
					
					
					
				}
				
				
				/////////////
				
				
				//gfx.drawRect(xpos[i], ypos[i], 10, 10);
				//////////////
				
			}
			//}
			
			
			//----------------------------------
			
			
		
			
			//End Draw
			bs.show();
			gfx.dispose();
			
			
			
			
		}
		
	
		public void run() {
				
				init();
				
				int fps = 60;
				double timePerTick = 1000000000 / fps;
				double delta = 0;
				long now;
				long lastTime = System.nanoTime();
				long timer = 0;
				int ticks = 0;
				
				while(running) {
					
					now = System.nanoTime();
					delta = delta + ((now - lastTime) / timePerTick);
					timer = timer + (now - lastTime);
					lastTime = now;
					
					
					if (delta >= 1) {
					tick();
					render(temp);
					ticks++;
					delta--;
					}
					
					if (timer >= 1000000000) {
						System.out.println("Ticks and Frames: "+ ticks);
						ticks = 0;
						timer = 0;
						
						
					}
				}
				stop();
			}
		public synchronized void start() {
			if (running) 
				return;
			
			running = true;
			thread = new Thread(this);
			thread.start();
			
		}
		
		public synchronized void stop() {
			if (!running) 
				return;
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		public static double getT() {
			return t;
		}

	}


