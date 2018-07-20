package ClearOut.entities.statics;

import java.awt.Graphics;

import ClearOut.Handler;
import ClearOut.gfx.Animation;
import ClearOut.gfx.Assets;
import ClearOut.tiles.Tile;
import java.awt.image.BufferedImage;

public class Wall extends StaticEntity{

	Animation animWall;
	private boolean dead=false;
	private BufferedImage stillWall;
        
	public Wall(Handler handler, float x, float y,int top,int side) {
		//super(handler, x+4, y+4, Tile.TILEWIDTH-8, Tile.TILEHEIGHT-8);
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
                animWall=new Animation(100,Assets.wall,false);
                
                if(top==1)
                {
                    if(side==0) stillWall=Assets.walltl;
                    if(side==1) stillWall=Assets.walltm;
                    if(side==2) stillWall=Assets.walltr;
                }
                else
                {
                    if(side==0) stillWall=Assets.walll;
                    if(side==1) stillWall=Assets.wallm;
                    if(side==2) stillWall=Assets.wallr;
                }
	}

	@Override
	public void tick() {
		if(active && dead)animWall.tick();
	}

	@Override
	public void render(Graphics g) {
		if(!active) return;
		if(dead==true)
		{
			if(animWall.over) {active=false; return;}
			g.drawImage(animWall.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()), 
					(int) (y - handler.getGameCamera().getyOffset()), width, height, null);	
		}
		else
		{
			g.drawImage(stillWall, (int) (x - handler.getGameCamera().getxOffset()), 
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);		
		}
	}
	@Override
	public void hurt(int amt){
		health -= amt;
		if(health <= 0){
			die();
		}
	}
	
	@Override
	public void die() {
		//active=false;
		dead=true;
	}
	
}
