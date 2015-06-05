package org.plazi.name_extractor.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author philipp.kuntschik@htwchur.ch
 *
 */
public class Species implements Serializable {

	private static final long serialVersionUID = 1L;

	public String name;
	public int taxonomyId;
	public Species parent;

	private HashMap<Integer, Species> children;
	private List<Species> synonyms;




	public Species(String name, int taxonomyId) {
		super();
		this.name = name;
		this.taxonomyId = taxonomyId;
		this.children = new HashMap<Integer, Species>();
		this.synonyms = new ArrayList<Species>();
	}




	public Species() {
		this.children = new HashMap<Integer, Species>();
		this.synonyms = new ArrayList<Species>();
	}




	public void addChild(Species child) {
		this.children.put(child.taxonomyId, child);
	}




	public void addSynonym(Species synonym) {
		this.synonyms.add(synonym);
	}




	@Override
	public String toString() {
		String result = "";
		result += taxonomyId + ",";
		result += name + ",";

		result += "{";
		for (Species child : children.values()) {
			result += child.taxonomyId + ",";
		}
		result += result + "},{";
		for (Species synonym : synonyms) {
			result += synonym.taxonomyId + ",";
		}
		result += "}";
		return result;
	}
}
