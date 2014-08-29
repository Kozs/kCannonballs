package org.parabot.Kozs.kCannonballs;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;

public class BankingOre implements Strategy {
	
	Constants c = new Constants();
	@Override
	public boolean activate() {
		return Inventory.isFull();
	}

	@Override
	public void execute() {
		if (!Banks.isOpen()) {
		Banks.open();
		Time.sleep(1000);
		}
		if (Banks.isOpen()) {
			Banks.depositAllExcept(c.PICKAXES);
		}
	}
}
