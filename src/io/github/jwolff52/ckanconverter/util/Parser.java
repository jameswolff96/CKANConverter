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
