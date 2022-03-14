package org.palladiosimulator.hwsimcoupling.util.impl.mocks;

import org.palladiosimulator.hwsimcoupling.commands.SimulationCommand;

public class SimulationCommandMock extends CommandMock implements SimulationCommand {

    @Override
    protected String getFile() {
        return "simulation";
    }
}
