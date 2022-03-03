package org.palladiosimulator.hwsimcoupling.util.impl.mocks;

import java.io.Serializable;
import java.util.Map;

import org.palladiosimulator.hwsimcoupling.commands.CopyCommand;
import org.palladiosimulator.hwsimcoupling.commands.ExtractionCommand;
import org.palladiosimulator.hwsimcoupling.commands.SimulationCommand;
import org.palladiosimulator.hwsimcoupling.consumers.ErrorConsumer;
import org.palladiosimulator.hwsimcoupling.consumers.OutputConsumer;
import org.palladiosimulator.hwsimcoupling.util.CommandHandler;

public class CommandHandlerTest extends CommandHandler{

	@Override
	public CopyCommand getCopyCommand(Map<String, Serializable> parameterMap, String source_path) {
		return new CopyCommandTest(source_path);
	}

	@Override
	public ExtractionCommand getExtractionCommand(Map<String, Serializable> parameterMap) {
		return new ExtractionCommandTest();
	}

	@Override
	public SimulationCommand getSimulationCommand(Map<String, Serializable> parameterMap) {
		return new SimulationCommandTest();
	}

	@Override
	public OutputConsumer getOutputConsumer() {
		return new OutputConsumerTest();
	}

	@Override
	public ErrorConsumer getErrorConsumer() {
		return new ErrorConsumerTest();
	}

}
