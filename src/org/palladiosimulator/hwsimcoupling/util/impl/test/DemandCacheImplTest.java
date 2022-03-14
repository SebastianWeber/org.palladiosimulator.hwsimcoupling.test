package org.palladiosimulator.hwsimcoupling.util.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.hwsimcoupling.configuration.ProfileCache;
import org.palladiosimulator.hwsimcoupling.exceptions.DemandCalculationFailureException;
import org.palladiosimulator.hwsimcoupling.util.DemandCache.RESOURCE;
import org.palladiosimulator.hwsimcoupling.util.impl.DemandCacheImpl;
import org.palladiosimulator.hwsimcoupling.util.impl.mocks.CommandHandlerMock;

import de.uka.ipd.sdq.probfunction.math.IProbabilityFunctionFactory;
import de.uka.ipd.sdq.probfunction.math.impl.DefaultRandomGenerator;
import de.uka.ipd.sdq.probfunction.math.impl.ProbabilityFunctionFactoryImpl;
import de.uka.ipd.sdq.simucomframework.variables.cache.StoExCache;

class DemandCacheImplTest {

    static ProfileCache profileCache = mock(ProfileCache.class);

    static DemandCacheImpl demandCacheImpl = DemandCacheImpl.getInstance(profileCache);

    @BeforeEach
    void clearDemandCache() {
        demandCacheImpl.clearCache();
    }

    @BeforeEach
    void initStoExCache() {
        final IProbabilityFunctionFactory probFunctionFactory = ProbabilityFunctionFactoryImpl.getInstance();
        probFunctionFactory.setRandomGenerator(new DefaultRandomGenerator());
        StoExCache.initialiseStoExCache(probFunctionFactory);
    }

    @BeforeEach
    void clearFiles() {
        TestHelper.setFileContent("", "copy");
        TestHelper.setFileContent("", "simulation");
        TestHelper.setFileContent("", "extraction");
    }

    @Test
    void testCalculatesAndReturnsValue() {
        String profile = TestHelper.generateRandomString();
        double demandcpu = TestHelper.generateRandomDouble();
        double demandhdd = TestHelper.generateRandomDouble();
        TestHelper.setFileContent("Demand:" + TestHelper.getDemandString(demandcpu, demandhdd), "extraction");
        double processingrate = TestHelper.generateRandomDouble();
        Map<String, Serializable> parameterMap = TestHelper.getDefaultParameterMap(profile, processingrate);
        when(profileCache.mergeParameterMapWithProfile(any(), any())).thenReturn(parameterMap);
        assertEquals(demandcpu / processingrate,
                demandCacheImpl.get(parameterMap, RESOURCE.CPU, new CommandHandlerMock()));
        assertEquals(demandhdd / processingrate,
                demandCacheImpl.get(parameterMap, RESOURCE.HDD, new CommandHandlerMock()));
    }

    @Test
    void testDetectsErrorsInSimulation() {
        String profile = TestHelper.generateRandomString();
        TestHelper.setFileContent("Error", "extraction");
        double processingrate = TestHelper.generateRandomDouble();
        Map<String, Serializable> parameterMap = TestHelper.getDefaultParameterMap(profile, processingrate);
        when(profileCache.mergeParameterMapWithProfile(parameterMap, profile)).thenReturn(parameterMap);
        assertThrows(DemandCalculationFailureException.class,
                () -> demandCacheImpl.get(parameterMap, RESOURCE.CPU, new CommandHandlerMock()));
    }

    @Test
    void testDetectsErrorsInExtraction() {
        String profile = TestHelper.generateRandomString();
        TestHelper.setFileContent("Error", "extraction");
        double processingrate = TestHelper.generateRandomDouble();
        Map<String, Serializable> parameterMap = TestHelper.getDefaultParameterMap(profile, processingrate);
        when(profileCache.mergeParameterMapWithProfile(parameterMap, profile)).thenReturn(parameterMap);
        assertThrows(DemandCalculationFailureException.class,
                () -> demandCacheImpl.get(parameterMap, RESOURCE.CPU, new CommandHandlerMock()));
    }

    @Test
    void testDetectsErrorsInCopy() {
        String profile = "test4";
        TestHelper.setFileContent("Error", "copy");
        double processingrate = ThreadLocalRandom.current()
            .nextInt(1, 100);
        Map<String, Serializable> parameterMap = TestHelper.getDefaultParameterMap(profile, processingrate);
        parameterMap.put("test", "absolute:nonexistent");
        when(profileCache.mergeParameterMapWithProfile(parameterMap, profile)).thenReturn(parameterMap);
        assertThrows(DemandCalculationFailureException.class,
                () -> demandCacheImpl.get(parameterMap, RESOURCE.CPU, new CommandHandlerMock()));
    }

    /**
     * getDemands(), addDemand(String key, String value), removeDemand(String key), clearCache()
     */
    @Test
    void testUtilityMethods() {
        assertEquals(0, demandCacheImpl.getDemands()
            .size());
        String profile = TestHelper.generateRandomString();
        double demandcpu = TestHelper.generateRandomDouble();
        double demandhdd = TestHelper.generateRandomDouble();
        demandCacheImpl.addDemand(TestHelper.getDefaultKeyToProfile(profile),
                TestHelper.getDemandString(demandcpu, demandhdd));
        assertEquals(1, demandCacheImpl.getDemands()
            .size());
        double processingrate = TestHelper.generateRandomDouble();
        Map<String, Serializable> parameterMap = TestHelper.getDefaultParameterMap(profile, processingrate);
        when(profileCache.mergeParameterMapWithProfile(parameterMap, profile)).thenReturn(parameterMap);
        assertEquals(demandcpu / processingrate,
                demandCacheImpl.get(parameterMap, RESOURCE.CPU, new CommandHandlerMock()));
        demandCacheImpl.removeDemand(TestHelper.getDefaultKeyToProfile(profile));
        assertEquals(0, demandCacheImpl.getDemands()
            .size());
        demandCacheImpl.addDemand(TestHelper.getDefaultKeyToProfile(profile),
                TestHelper.getDemandString(demandcpu, demandhdd));
        assertEquals(1, demandCacheImpl.getDemands()
            .size());
        demandCacheImpl.clearCache();
        assertEquals(0, demandCacheImpl.getDemands()
            .size());
    }

}
