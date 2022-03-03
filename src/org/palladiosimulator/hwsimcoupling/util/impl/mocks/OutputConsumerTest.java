package org.palladiosimulator.hwsimcoupling.util.impl.mocks;

import org.palladiosimulator.hwsimcoupling.consumers.OutputConsumer;

public class OutputConsumerTest implements OutputConsumer {

	private final String keyword = "Demand:";
	
	private String demand;
	
	@Override
	public void accept(String t) {
		if (t.contains(keyword)) {
			demand = t.replace(keyword, "");
		}
	}
	
	public String get_demand() {
		return demand;
	}

}
