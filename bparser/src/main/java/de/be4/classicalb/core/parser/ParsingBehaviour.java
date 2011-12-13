/**
 * 
 */
package de.be4.classicalb.core.parser;

import java.io.File;
import java.io.PrintStream;

/**
 * @author plagge
 * 
 */
public class ParsingBehaviour {
	public boolean prologOutput;
	public boolean useIndention;
	public boolean addLineNumbers;
	public boolean displayGraphically;
	public boolean verbose;
	public boolean printTime;
	public PrintStream out = System.out;
	public boolean printAST;
	public boolean fastPrologOutput;
	public File outputFile;
}
