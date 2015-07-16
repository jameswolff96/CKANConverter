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
