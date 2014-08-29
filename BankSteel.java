package org.parabot.Kozs.kCannonballs;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;

public class BankSteel implements Strategy {

	Constants c = new Constants();
	@Override
	public boolean activate() {
		return Inventory.getCount(c.STEEL) >= 10 
				|| Inventory.isEmpty();
	}

	@Override
	public void execute() {
		if (!Banks.isOpen()) {
		Banks.open();
		Time.sleep(1000);
		}
		if (Banks.isOpen()) {
			Banks.depositAllExcept(c.PICKAXES);
			Banks.withdraw(c.COAL, 10, 1000);
			Time.sleep(500);
			Banks.withdraw(c.IRON, 10, 1000);
			Banks.close();
		}
	}
}