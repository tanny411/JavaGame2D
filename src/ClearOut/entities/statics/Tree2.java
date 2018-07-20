package ClearOut.entities.statics;

import java.awt.Graphics;

import ClearOut.Handler;
import ClearOut.gfx.Assets;
import ClearOut.items.Item;
import ClearOut.tiles.Tile;

public class Tree2 extends StaticEntity {

	public Tree2(Handler handler, float x, float y) {
		super(handler, x, y, Assets.tree2.getWidth(), Assets.tree2.getHeight());
		//super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT*2);
		bounds.x = 23;
		bounds.y = (int) (height / 1.5f)-10;
		bounds.width = 17;
		bounds.height = (int) (height - height / 1.5f)+10;
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void die(){
		handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) (x+bounds.x), (int) (y+bounds.y) ));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree2, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		//g.fillRect((int) (x+bounds.x - handler.getGameCamera().getxOffset()), (int) (y+bounds.y - handler.getGameCamera().getyOffset()),bounds.width, bounds.height);
	}

}
