package org.palladiosimulator.hwsimcoupling.util.impl.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang.RandomStringUtils;
import org.palladiosimulator.hwsimcoupling.configuration.Parameter;

public class TestHelper {

	public static String doubleToString(double d) {
		return NumberFormat.getInstance(Locale.GERMAN).format(d);
	}
	
	public static String getDemandString(double cpu, double hdd) {
		return "CPU:" + cpu + "&HDD:" + hdd;
	}
	
	public static void setFileContent(String content, String file) {
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter("data/" + file + ".txt"));
			br.write(content);
			br.flush();
			br.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static Map<String, Serializable> getDefaultParameterMap(String profile, double processingrate) {
		Map<String, Serializable> parameterMap = new HashMap<String, Serializable>();
		parameterMap.put(Parameter.PROFILE.getKeyword(), profile);
		parameterMap.put(Parameter.PROCESSINGRATE.getKeyword(), doubleToString(processingrate));
		return parameterMap;
	}
	
	public static Map<String, String> getDefaultDemandMap(String profile, double demandcpu, double demandhdd) {
		Map<String, String> demands = new HashMap<String, String>();
		demands.put("profile:" + profile + "|", getDemandString(demandcpu, demandhdd));
		return demands;
	}
	
	public static String getDefaultKeyToProfile(String profile) {
		return "profile:" + profile + "|";
	}
	
	public static String generateRandomString() {
	    return RandomStringUtils.random(5, true, false);
	}
	
	public static double generateRandomDouble() {
		return ThreadLocalRandom.current().nextInt(1, 100);
	}
	
	public static Map<String, String> generateRandomMap() {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < 10; i++) {
			map.put(TestHelper.generateRandomString(), TestHelper.generateRandomString());
		}
		return map;
	}
	
}
