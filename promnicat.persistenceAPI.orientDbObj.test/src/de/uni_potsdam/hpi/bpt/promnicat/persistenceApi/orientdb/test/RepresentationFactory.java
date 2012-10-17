/**
 * PromniCAT - Collection and Analysis of Business Process Models
 * Copyright (C) 2012 Cindy Fähnrich, Tobias Hoppe, Andrina Mascher
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.uni_potsdam.hpi.bpt.promnicat.persistenceApi.orientdb.test;

import de.uni_potsdam.hpi.bpt.promnicat.persistenceApi.impl.Model;
import de.uni_potsdam.hpi.bpt.promnicat.persistenceApi.impl.Representation;
import de.uni_potsdam.hpi.bpt.promnicat.persistenceApi.impl.Revision;
import de.uni_potsdam.hpi.bpt.promnicat.util.Constants;
/**
 * @author Andrina Mascher
 *
 */
public class RepresentationFactory {
	
	public static Representation createLightweightRepresentation() {
		Model model = new Model("a title", Constants.ORIGIN_BPMAI);
		Revision revision = new Revision(0);
		revision.addMetadataAtKey("key1", "value1");
		revision.addMetadataAtKey("key1", "value1a");
		revision.addMetadataAtKey("key2", "value2");
		model.connectRevision(revision);
		model.connectLatestRevision(revision);
		
		Representation representation = new Representation(Constants.FORMAT_BPMAI_JSON, 
				Constants.NOTATION_EPC);
		revision.connectRepresentation(representation);
		
		return representation;
	}
	
	public static Representation createRepresentationWithMultipleLinks() {
		Model model = new Model("model with multiple links", Constants.ORIGIN_BPMAI, "oneImportedId");
		Revision revision = new Revision(0);
		revision.addMetadataAtKey("k1", "v1");
		revision.addMetadataAtKey("k1", "v1a");
		revision.addMetadataAtKey("k2", "v2");
		revision.addMetadataAtKey("kX", "vY");
		model.connectRevision(revision);
		
		Revision revision2 = new Revision(1);
		model.connectRevision(revision2);
		model.connectLatestRevision(revision);
		
		Representation representation = new Representation(Constants.FORMAT_BPMAI_JSON, 
				Constants.NOTATION_BPMN2_0);
		Representation representation2 = new Representation(Constants.FORMAT_SVG, 
				Constants.NOTATION_BPMN2_0);
		Representation representation3 = new Representation(Constants.FORMAT_BPMAI_JSON, 
				Constants.NOTATION_BPMN2_0);
		revision.connectRepresentation(representation);
		revision.connectRepresentation(representation2);
		revision.connectRepresentation(representation3);
		
		return representation;
	}
	
	public static Representation createUnconnectedRepresentation() {
		return new Representation(Constants.FORMAT_SVG, Constants.NOTATION_BPMN2_0);
	}
}
