package org.palladiosimulator.hwsimcoupling.util.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;
import org.palladiosimulator.hwsimcoupling.util.impl.CommandExecutor;
import org.palladiosimulator.hwsimcoupling.util.impl.mocks.ErrorConsumerTest;
import org.palladiosimulator.hwsimcoupling.util.impl.mocks.ExtractionCommandTest;
import org.palladiosimulator.hwsimcoupling.util.impl.mocks.OutputConsumerTest;

public class CommandExecutorTest {

	/**
	 * Test the execution of a test extraction command with the command executor. 
	 * All commands just read different files, so one test suffices.
	 */
	@Test
	void testCommandExecutorExtractionCommand() {
		try {
			OutputConsumerTest outputConsumerTest = new OutputConsumerTest();
			ErrorConsumerTest errorConsumerTest = new ErrorConsumerTest();
			double demandcpu = ThreadLocalRandom.current().nextInt(1, 100);
			double demandhdd = ThreadLocalRandom.current().nextInt(1, 100);
			TestHelper.setFileContent("Demand:" + TestHelper.getDemandString(demandcpu, demandhdd), "extraction");
			CommandExecutor.execute_command(new ExtractionCommandTest(), outputConsumerTest, errorConsumerTest);
			assertEquals(outputConsumerTest.get_demand(), TestHelper.getDemandString(demandcpu, demandhdd));
		} catch (IOException | InterruptedException e) {
			assertTrue(false, "CommandExecutor not working: " + e.getMessage());
		}
	}
	
}
