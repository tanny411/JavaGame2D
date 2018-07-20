package ClearOut.entities.statics;

import java.awt.Graphics;

import ClearOut.Handler;
import ClearOut.gfx.Assets;
import ClearOut.items.Item;
import ClearOut.tiles.Tile;

public class Rock extends StaticEntity {

	public Rock(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		
		bounds.x = 7;
		bounds.y = (int) (height / 2f) - 15;
		bounds.width = width - 20;
		bounds.height = (int) (height - height / 2f) ;
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void die(){
		handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int) x, (int) y));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.rock, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		//g.fillRect((int) (x+bounds.x - handler.getGameCamera().getxOffset()), (int) (y+bounds.y - handler.getGameCamera().getyOffset()),bounds.width, bounds.height);
	}

}
