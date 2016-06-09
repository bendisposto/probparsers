package de.be4.classicalb.core.parser.util;

import java.io.PrintStream;

public class DebugPrinter {

	
	private static PrintStream out = System.out;

	private DebugPrinter() {
	}

	public static void println(String message) {
		out.print("*** Debug: ");
		out.println(message);
	}

	public static void print(String message) {
		out.print(message);
	}
}
