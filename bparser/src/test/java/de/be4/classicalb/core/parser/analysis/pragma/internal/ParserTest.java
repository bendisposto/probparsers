package de.be4.classicalb.core.parser.analysis.pragma.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class ParserTest {

	@Test
	public void test() throws Exception {
		String s = "ltl-assertion \"inc is always enabled\" 'G [inc]'";
		
		// Snippet by Jan Goyvaerts from 
		// http://stackoverflow.com/questions/366202/regex-for-splitting-a-string-using-space-when-not-surrounded-by-single-or-double
		List<String> matchList = new ArrayList<String>();
		Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
		Matcher regexMatcher = regex.matcher(s);
		while (regexMatcher.find()) {
		    if (regexMatcher.group(1) != null) {
		        // Add double-quoted string without the quotes
		        matchList.add(regexMatcher.group(1));
		    } else if (regexMatcher.group(2) != null) {
		        // Add single-quoted string without the quotes
		        matchList.add(regexMatcher.group(2));
		    } else {
		        // Add unquoted word
		        matchList.add(regexMatcher.group());
		    }
		} 
		
	Assert.assertEquals("ltl-assertion", matchList.get(0));
	Assert.assertEquals("inc is always enabled", matchList.get(1));
	Assert.assertEquals("G [inc]", matchList.get(2));
		
	}

}
