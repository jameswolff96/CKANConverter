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

import io.github.jwolff52.ckanconverter.CKANConverter;

public class Converter {

	public static void toText(HashMap<Type, ArrayList<String>> values, File out, int longestName) {
		ArrayList<String> output = new ArrayList<>();
		longestName+=8;
		for(int i = 0; i < values.get(Type.name).size(); i++) {
			String toPad = "Name: " + values.get(Type.name).get(i) + ",";
			String padded = new String(toPad + String.copyValueOf(new char[longestName-toPad.length()])).replace('\0', ' ');
			output.add(padded + "Version: " + ((values.get(Type.version).get(i).equalsIgnoreCase("")) ? "Unknown" : values.get(Type.version).get(i)));
		}
		if(out.exists()) {
			System.out.println("The output file already exists, would you like to overwrite it? Y/N");
			String answer = CKANConverter.scan.nextLine();
			if(answer.toLowerCase().startsWith("n")) {
				System.out.println("Exiting...");
				CKANConverter.quit();
			}
			TFileWriter.overWriteFile(out, output);
			return;
		}
		TFileWriter.writeFile(out, output);
	}

	public static void toCSV(HashMap<Type, ArrayList<String>> values, File out) {
		ArrayList<String> output = new ArrayList<>();
		output.add("Name, Version");
		for(int i = 0; i < values.get(Type.name).size(); i++) {
			output.add(values.get(Type.name).get(i) + ", " + ((values.get(Type.version).get(i).equalsIgnoreCase("")) ? "Unknown" : values.get(Type.version).get(i)));
		}
		if(out.exists()) {
			System.out.println("The output file already exists, would you like to overwrite it? Y/N");
			String answer = CKANConverter.scan.nextLine();
			if(answer.toLowerCase().startsWith("n")) {
				System.out.println("Exiting...");
				CKANConverter.quit();
			}
			TFileWriter.overWriteFile(out, output);
			return;
		}
		TFileWriter.writeFile(out, output);
	}

}
