package org.palladiosimulator.hwsimcoupling.util.impl.mocks;

import org.palladiosimulator.hwsimcoupling.commands.SimulationCommand;

public class SimulationCommandTest extends CommandTest implements SimulationCommand {

	@Override
	protected String getFile() {
		return "simulation";
	}
}
