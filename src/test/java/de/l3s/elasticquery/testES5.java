package de.l3s.elasticquery;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import de.l3s.souza.esfive.ElasticMain;
import de.l3s.souza.esfive.LivingKnowledgeSnapshot;
import junit.framework.TestCase;

public class testES5 extends TestCase {
	@SuppressWarnings("unchecked")
	public void testApp() throws IOException   {
		ElasticMain es = new ElasticMain("", 1, "text","souza_livingknowledge_2");
		es.setField("text");
		es.setKeywords("stress");
		es.setLimit(10);
		es.run();
		Map<LivingKnowledgeSnapshot, Double> documents = new HashMap<LivingKnowledgeSnapshot, Double>();
		documents = (Map<LivingKnowledgeSnapshot, Double>) es.getResults();
		for (Entry<LivingKnowledgeSnapshot, Double> s:documents.entrySet())
		{
			System.out.println(s.getKey().getDocId());
		}
}
}
