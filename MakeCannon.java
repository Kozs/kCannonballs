package org.parabot.Kozs.kCannonballs;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class MakeCannon implements Strategy {
	Constants c = new Constants();
	@Override
	public boolean activate() {
		return Inventory.getCount(c.STEEL) == 26
				&& Npcs.getNearest(Anti.RANDOMS) != null;
	}

	@Override
	public void execute() {
		SceneObject furnace[] = SceneObjects.getNearest(c.FURNACE);
		if (Players.getMyPlayer().getAnimation() == -1
				&& Npcs.getNearest(Anti.RANDOMS) != null) {
			Menu.sendAction(447, 2354 - 1, Inventory.getItems(c.STEEL)[0].getSlot(), 3214);
			Time.sleep(200);
			//action2: 53, action3: 55, id: 62]
			Menu.sendAction(62, furnace[0].getHash(), 53, 55);
			Time.sleep(1000);
		}
			if (Game.getOpenBackDialogId() == 2492) {
				//[index: 1, action1: 41975808, action2: 173, action3: 2497, id: 315]
				Menu.sendAction(315, 41975808, 173, 2497);
				System.out.println("Interface achieved!");
				Time.sleep(1000);
	
		}
	}
}
