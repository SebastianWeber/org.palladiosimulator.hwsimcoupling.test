package org.palladiosimulator.hwsimcoupling.util.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;
import org.palladiosimulator.hwsimcoupling.util.impl.CommandExecutor;
import org.palladiosimulator.hwsimcoupling.util.impl.mocks.ErrorConsumerMock;
import org.palladiosimulator.hwsimcoupling.util.impl.mocks.ExtractionCommandMock;
import org.palladiosimulator.hwsimcoupling.util.impl.mocks.OutputConsumerMock;

public class CommandExecutorTest {

    /**
     * Test the execution of a test extraction command with the command executor. All commands just
     * read different files, so one test suffices.
     */
    @Test
    void testCommandExecutorExtractionCommand() {
        try {
            OutputConsumerMock outputConsumerTest = new OutputConsumerMock();
            ErrorConsumerMock errorConsumerTest = new ErrorConsumerMock();
            double demandcpu = ThreadLocalRandom.current()
                .nextInt(1, 100);
            double demandhdd = ThreadLocalRandom.current()
                .nextInt(1, 100);
            TestHelper.setFileContent("Demand:" + TestHelper.getDemandString(demandcpu, demandhdd), "extraction");
            CommandExecutor.executeCommand(new ExtractionCommandMock(), outputConsumerTest, errorConsumerTest);
            assertEquals(outputConsumerTest.getDemand(), TestHelper.getDemandString(demandcpu, demandhdd));
        } catch (IOException | InterruptedException e) {
            assertTrue(false, "CommandExecutor not working: " + e.getMessage());
        }
    }

}
