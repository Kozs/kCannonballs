package org.parabot.Kozs.kCannonballs;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.accessors.Npc;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class MineIron implements Strategy {

	public int IRONORE[] = {2092, 2093};
	@Override
	public boolean activate() {
		return !Inventory.isFull() && Npcs.getNearest(Anti.RANDOMS) != null;
	}

	@Override
	public void execute() {
		SceneObject[] ore = SceneObjects.getNearest(IRONORE);
		if (Players.getMyPlayer().getAnimation() == -1) {
		if (IRONORE != null) {
		try {
		ore[0].interact(0);
		Time.sleep(1000);
		 } catch(Exception e) {
		 }
		
		}
		}

	}
}
