package org.palladiosimulator.hwsimcoupling.util.impl.mocks;

import org.palladiosimulator.hwsimcoupling.commands.CopyCommand;

public class CopyCommandMock extends CommandMock implements CopyCommand {

    private String sourcePath;

    public CopyCommandMock(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    @Override
    public String getDestination() {
        return sourcePath;
    }

    @Override
    protected String getFile() {
        return "copy";
    }

}
