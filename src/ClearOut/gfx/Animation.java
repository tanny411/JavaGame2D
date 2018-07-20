package ClearOut.gfx;

import java.awt.image.BufferedImage;

public class Animation {
	
	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;
	private boolean rep;
	public boolean over=false;
	private int len;
	public Animation (int speed,boolean rep,int len)
	{
		this.rep=rep;
		this.speed = speed;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
		this.len=len;
	}
	public Animation(int speed, BufferedImage[] frames,boolean rep){
		this.rep=rep;
		this.speed = speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
		len=frames.length;
	}
	
	public void tick(){
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed){
			index++;
			timer = 0;
			if(index >= len)
				if(rep) index = 0;
				else over=true;
		}
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public BufferedImage getCurrentFrame(){
		if(over) return null;
		return frames[index];
	}

}
