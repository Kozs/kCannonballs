package org.parabot.Kozs.kCannonballs;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class SmeltSteel implements Strategy {
	Constants c = new Constants();
	@Override
	public boolean activate() {
		return Inventory.getCount(c.COAL) >= 1
				&& Inventory.getCount(c.IRON) >= 1
				&& Npcs.getNearest(Anti.RANDOMS) != null;
	}
    public static int SleepTime(int amount) {
        return amount * 850;
    }
 
	@Override
	public void execute() {
		SceneObject furnace[] = SceneObjects.getNearest(c.FURNACE);
		if (Players.getMyPlayer().getAnimation() == -1
				&& Npcs.getNearest(Anti.RANDOMS) != null) {
			furnace[0].interact(0);
			Time.sleep(3000);
			if (Game.getOpenBackDialogId() == 2400) {
				//[index: 2, action1: 74, action2: 275, action3: 3997, id: 315]
				Menu.sendAction(315, 74, 275, 3997);
				Time.sleep(new SleepCondition()
                {
                    @Override
                    public boolean isValid() {
                        return Inventory.getCount(c.STEEL) >= 10;
                    }
 
                }, 12000);
			}
		}
	}
}
