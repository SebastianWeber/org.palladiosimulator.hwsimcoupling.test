package org.palladiosimulator.hwsimcoupling.util.impl.mocks;

import org.palladiosimulator.hwsimcoupling.consumers.ErrorConsumer;
import org.palladiosimulator.hwsimcoupling.exceptions.DemandCalculationFailureException;

public class ErrorConsumerTest implements ErrorConsumer {

	@Override
	public void accept(String t) throws DemandCalculationFailureException{
		if (t.contains("Error")) {
			throw new DemandCalculationFailureException(t);
		}
	}

}
