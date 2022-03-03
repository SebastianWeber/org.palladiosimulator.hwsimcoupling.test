package org.palladiosimulator.hwsimcoupling.util.impl.mocks;

import org.palladiosimulator.hwsimcoupling.commands.CopyCommand;

public class CopyCommandTest extends CommandTest implements CopyCommand {
	
	private String source_path;

	public CopyCommandTest(String source_path) {
		this.source_path = source_path;
	}
	
	@Override
	public String get_destination() {
		return source_path;
	}

	@Override
	protected String getFile() {
		return "copy";
	}

}
