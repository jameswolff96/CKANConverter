package io.github.jwolff52.ckanconverter;

import java.io.File;
import java.util.Scanner;

import io.github.jwolff52.ckanconverter.util.Parser;

public class CKANConverter {
	
	private static String importLocation;
	private static String exportLocation;
	
	public static Scanner scan;

	/**
	 * 
	 * @param args - file import location, file export location, optional behavior parameters
	 *  -c Convert to CSV
	 *  -t Convert to Text (default)
	 */
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		if(args.length < 2 || args.length > 4) {
			System.out.println("You must pass at least an import and export location (be sure to surround each with \"\")");
			quit();
		}
		importLocation = args[0];
		exportLocation = args[1];
		boolean isText=true;
		if(args[2].equalsIgnoreCase("-c")) {
			isText = false;
		}
		Parser.parseFile(new File(importLocation), new File(exportLocation), isText);
		scan.close();
	}
	
	public static void quit() {
		scan.close();
		System.exit(0);
	}
}
