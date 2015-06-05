package org.plazi.name_extractor;

import org.junit.Test;

public class NameExtractorTest {

	private final static String NAMES = "names.dmp";
	private final static String NODES = "nodes.dmp";




	@Test
	public void testNameExtractor() {
		parseAllSpecies();
		parseSpeciesRelation();
	}




	public void parseAllSpecies() {
		NameExtractor.parseAllSpecies(NAMES);
	}




	public void parseSpeciesRelation() {
		NameExtractor.parseSpeciesRelation(NODES);
	}

}
