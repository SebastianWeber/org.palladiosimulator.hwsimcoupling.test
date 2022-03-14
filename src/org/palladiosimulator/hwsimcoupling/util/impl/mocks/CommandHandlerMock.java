package org.palladiosimulator.hwsimcoupling.util.impl.mocks;

import java.io.Serializable;
import java.util.Map;

import org.palladiosimulator.hwsimcoupling.commands.CopyCommand;
import org.palladiosimulator.hwsimcoupling.commands.ExtractionCommand;
import org.palladiosimulator.hwsimcoupling.commands.SimulationCommand;
import org.palladiosimulator.hwsimcoupling.consumers.ErrorConsumer;
import org.palladiosimulator.hwsimcoupling.consumers.OutputConsumer;
import org.palladiosimulator.hwsimcoupling.util.CommandHandler;

public class CommandHandlerMock extends CommandHandler {

    @Override
    public CopyCommand getCopyCommand(Map<String, Serializable> parameterMap, String sourcePath) {
        return new CopyCommandMock(sourcePath);
    }

    @Override
    public ExtractionCommand getExtractionCommand(Map<String, Serializable> parameterMap) {
        return new ExtractionCommandMock();
    }

    @Override
    public SimulationCommand getSimulationCommand(Map<String, Serializable> parameterMap) {
        return new SimulationCommandMock();
    }

    @Override
    public OutputConsumer getOutputConsumer() {
        return new OutputConsumerMock();
    }

    @Override
    public ErrorConsumer getErrorConsumer() {
        return new ErrorConsumerMock();
    }

}
