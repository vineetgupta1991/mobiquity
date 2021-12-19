package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


/**
 * Unit test for Packer
 */
public class PackerTest {
	private List<String> inputLines;
	private List<String> outputLines;
	
	@Before
    public void setup() {
		inputLines = readLinesFromResource("input-sample.txt");
		outputLines = readLinesFromResource("output-sample.txt");
    }
	
	@After
	public void teardown() {
		inputLines = null;
		outputLines = null;
	}

	@Test
    public void testResources() {
		assertNotNull(inputLines);
		assertNotNull(outputLines);
		assertEquals(inputLines.size(), outputLines.size());
	}
	
    @Test
    public void testPacker() {
    	
    	for ( int i = 0; i < inputLines.size(); i++ ) {
    		try {
    			
				assertEquals( outputLines.get(i), Packer.process(inputLines.get(i)) );
				
			} catch (APIException e) {
				fail(e.getMessage());
			}
    	}
    }
    
    private List<String> readLinesFromResource(String filename) {
    	ClassLoader  loader = PackerTest.class.getClassLoader();
    	List<String> lines;
		BufferedReader br = new BufferedReader(new InputStreamReader(
				Objects.requireNonNull(loader.getResourceAsStream(filename)), StandardCharsets.UTF_8
		));
		lines = br.lines().parallel().map(Object::toString).collect(Collectors.toList());
		return lines;
    }
}
