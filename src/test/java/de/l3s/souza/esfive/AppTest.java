package de.l3s.souza.esfive;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	
	private static Logger log = LoggerFactory.getLogger(App.class);
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws IOException 
     */
    public void testApp() throws IOException
    {
    	log.info("hello info");
        log.debug("debug");
    	ElasticMain es = new ElasticMain("", 1, "id","souza_livingknowledge_2");
		es.setField("id");
		es.setKeywords("lk-20110611040101_1222");
		es.setLimit(5000);
		es.run();
		Map<LivingKnowledgeSnapshot, Double> documents = new HashMap<LivingKnowledgeSnapshot, Double>();
		documents = (Map<LivingKnowledgeSnapshot, Double>) es.getResults();
		for (Entry<LivingKnowledgeSnapshot, Double> s:documents.entrySet())
		{
			System.out.println(s.getKey().getUrl() + " " + s.getKey().getText() + " " + s.getKey().getDocId());
			System.out.println ("-------------------------------------------");
			System.out.println ("-------------------------------------------");
			System.out.println ("-------------------------------------------");
		}
    }
}
