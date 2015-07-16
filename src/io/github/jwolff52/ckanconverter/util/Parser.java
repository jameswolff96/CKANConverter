/*
 * Converts .ckan files to .txt or .csv
    Copyright (C) 2015  James Wolff

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.jwolff52.ckanconverter.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Parser {

	public static void parseFile(File in, File out, boolean isText) {
		HashMap<Type, ArrayList<String>> values = new HashMap<>();

		JsonArray depends = new JsonParser().parse(TFileReader.readFileAsString(in)).getAsJsonObject()
				.getAsJsonArray("depends");

		ArrayList<String> names = new ArrayList<>();
		ArrayList<String> versions = new ArrayList<>();
		
		int longestName = 0;

		for (JsonElement o : depends) {
			try {
				String name = o.getAsJsonObject().get("name").getAsString(); 
				names.add(name);
				if(name.length() > longestName) {
					longestName = name.length();
				}
			} catch (NullPointerException e) {
				names.add("");
			}
			
			try {
				versions.add(o.getAsJsonObject().get("version").getAsString());
			} catch (NullPointerException e) {
				versions.add("");
			}
		}

		values.put(Type.name, names);
		values.put(Type.version, versions);

		if (isText) {
			Converter.toText(values, out, longestName);
			return;
		}

		Converter.toCSV(values, out);
	}

}
