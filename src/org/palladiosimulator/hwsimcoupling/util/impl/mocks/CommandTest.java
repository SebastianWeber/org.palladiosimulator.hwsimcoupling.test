package org.palladiosimulator.hwsimcoupling.util.impl.mocks;

import java.util.List;

import org.palladiosimulator.hwsimcoupling.commands.Command;

public abstract class CommandTest implements Command {
	
	@Override
	public List<String> get_command() {
		List<String> command = get_shell();
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		if (isWindows) {
			command.add("type data\\" + getFile() + ".txt" + " && " + "type data\\" + getFile() + ".txt 1>&2");
		} else {
			command.add("cat data/" + getFile() + ".txt" + " && " + "type data\\" + getFile() + ".txt 1>&2");
		}
		return command;
	}
	
	protected abstract String getFile();

}
