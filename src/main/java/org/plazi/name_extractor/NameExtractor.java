package org.plazi.name_extractor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.plazi.name_extractor.domain.Species;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

/**
 * 
 * @author philipp.kuntschik@htwchur.ch
 *
 */
public class NameExtractor {

	// TODO: add synonyms to HashMap, but caution: homonyms may exist!
	private static final HashMap<String, Species> stringIndex = new HashMap<String, Species>();
	private static final HashMap<Integer, Species> integerIndex = new HashMap<Integer, Species>();

	private final static String SCIENTIFICNAME = "scientific name";
	private final static String SYNONYM = "synonym";

	private static Logger l = Logger.getLogger("NameExtractor");




	public static void parseAllSpecies(String fileName) {
		List<String> dataFromFile = readLinesFromFile(fileName);

		Species currentSpecies = null;
		for (String line : dataFromFile) {
			String[] part = line.split("\\|");

			int speciesTaxonomyId = Integer.parseInt(part[0].trim());
			String speciesName = part[1].trim();

			if (currentSpecies == null || currentSpecies.taxonomyId != speciesTaxonomyId) {

				if (currentSpecies != null)
					l.info("parseSpecies: " + currentSpecies.toString());

				currentSpecies = new Species();
				currentSpecies.taxonomyId = speciesTaxonomyId;
				stringIndex.put(speciesName, currentSpecies);
				integerIndex.put(speciesTaxonomyId, currentSpecies);
			}
			String nameClass = part[3].trim();
			if (nameClass.equalsIgnoreCase(SCIENTIFICNAME))
				currentSpecies.name = speciesName;

			else if (nameClass.equalsIgnoreCase(SYNONYM)) {
				currentSpecies.addSynonym(new Species(speciesName, speciesTaxonomyId));
			}
		}
	}




	public static void parseSpeciesRelation(String fileName) {
		List<String> dataFromFile = readLinesFromFile(fileName);

		for (String line : dataFromFile) {
			String[] part = line.split("\\|");

			Species species = integerIndex.get(part[0]);
			Species parentSpecies = integerIndex.get(part[0]);

			species.parent = parentSpecies;
			parentSpecies.addChild(species);

			l.info("parseRelation: " + parentSpecies.toString());
		}
	}




	private static List<String> readLinesFromFile(String fileName) {
		try {
			File file = new File(Thread.currentThread().getContextClassLoader().getResource("./" + fileName).getFile());
			return Files.readLines(file, Charsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}