package org.palladiosimulator.hwsimcoupling.util.impl.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.palladiosimulator.hwsimcoupling.configuration.PersistenceManager;

public class PersistenceManagerTest {
	


	@Test
	void testSaveAndLoadProfiles() {
		// If there is no profile a default profile will be returned
		assertEquals(1, PersistenceManager.loadProfiles().size());
		Map<String, Map<String, String>> profiles = new HashMap<String, Map<String, String>>();
		profiles.put(TestHelper.generateRandomString(), TestHelper.generateRandomMap());
		profiles.put(TestHelper.generateRandomString(), TestHelper.generateRandomMap());
		PersistenceManager.saveProfiles(profiles);
		Map<String, Map<String, String>> profilesLoaded = PersistenceManager.loadProfiles();
		assertTrue(profiles.equals(profilesLoaded));	
	}
	
	@Test
	void testSaveAndLoadDemands() {
		// If there is no profile a default profile will be returned
		assertEquals(0, PersistenceManager.loadDemands().size());
		Map<String, String> demands = TestHelper.generateRandomMap();
		PersistenceManager.saveDemands(demands);
		Map<String, String> demandsLoaded = PersistenceManager.loadDemands();
		assertTrue(demands.equals(demandsLoaded));	
	}
	
}
