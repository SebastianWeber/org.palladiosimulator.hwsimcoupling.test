package org.palladiosimulator.hwsimcoupling.util.impl.mocks;

import org.palladiosimulator.hwsimcoupling.commands.ExtractionCommand;

public class ExtractionCommandMock extends CommandMock implements ExtractionCommand {

    @Override
    protected String getFile() {
        return "extraction";
    }
}
