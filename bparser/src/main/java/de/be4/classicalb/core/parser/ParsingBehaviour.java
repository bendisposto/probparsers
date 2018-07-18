package de.be4.classicalb.core.parser;

import java.io.File;
import java.io.PrintStream;

public class ParsingBehaviour {
	private boolean prologOutput = false;
	private boolean useIndention = false;
	private boolean addLineNumbers = false;
	private boolean displayGraphically = false;
	private boolean verbose = false; //verbose mode includes debug prints
	private boolean printTime = false;
	private PrintStream out = System.out;
	private boolean printAST = false;
	private boolean fastPrologOutput = false;
	private File outputFile;
	private boolean machineNameMustMatchFileName = false;

	public boolean isPrologOutput() {
		return prologOutput;
	}

	public void setPrologOutput(boolean prologOutput) {
		this.prologOutput = prologOutput;
	}

	public boolean isUseIndention() {
		return useIndention;
	}

	public void setUseIndention(boolean useIndention) {
		this.useIndention = useIndention;
	}

	public boolean isAddLineNumbers() {
		return addLineNumbers;
	}

	public void setAddLineNumbers(boolean addLineNumbers) {
		this.addLineNumbers = addLineNumbers;
	}

	public boolean isDisplayGraphically() {
		return displayGraphically;
	}

	public void setDisplayGraphically(boolean displayGraphically) {
		this.displayGraphically = displayGraphically;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public boolean isPrintTime() {
		return printTime;
	}

	public void setPrintTime(boolean printTime) {
		this.printTime = printTime;
	}

	public PrintStream getOut() {
		return out;
	}

	public void setOut(PrintStream out) {
		this.out = out;
	}

	public boolean isPrintAST() {
		return printAST;
	}

	public void setPrintAST(boolean printAST) {
		this.printAST = printAST;
	}

	public boolean isFastPrologOutput() {
		return fastPrologOutput;
	}

	public void setFastPrologOutput(boolean fastPrologOutput) {
		this.fastPrologOutput = fastPrologOutput;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

	public boolean isMachineNameMustMatchFileName() {
		return machineNameMustMatchFileName;
	}

	public void setMachineNameMustMatchFileName(boolean machineNameMustMatchFileName) {
		this.machineNameMustMatchFileName = machineNameMustMatchFileName;
	}

}
