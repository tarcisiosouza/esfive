package de.l3s.elasticquery;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import de.l3s.souza.esfive.ElasticMain;
import de.l3s.souza.esfive.LivingKnowledgeSnapshot;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
	
	private static String topicID;
	private static StringBuilder sb;
	public AppTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	@SuppressWarnings("unchecked")
	public void testApp() throws IOException {
	//	walk("/home/souza/NTCIR-eval/ntcir11_Temporalia_taskdata/TaskData/TIR");
}
	
	public static void walk(String path) throws IOException
	{

		File root = new File(path);
		File[] list = root.listFiles();

		for (File f : list) {
			if (f.isDirectory()) {
				walk(f.getAbsolutePath());
		
			} else {
				
				if (!f.getAbsolutePath().contains(".rel"))
					continue;
				
				File parent = f.getParentFile();
				
				topicID = parent.getName().toString();
				sb = new StringBuilder ();
				BufferedWriter bw = new BufferedWriter(new FileWriter(topicID+".txt", true));;
				FileReader fr = new FileReader (f);
				BufferedReader br = new BufferedReader (fr);
				
				String line;
				while ((line = br.readLine()) != null)
				{
					StringTokenizer token = new StringTokenizer (line);
					while (token.hasMoreTokens())
					{
						String rel;
						String id;
						
						id = token.nextToken();
						rel = token.nextToken();
						double relevance;
						
						if (rel.contentEquals("L0"))
							continue;
						
						new ElasticMain(id, 1, "id","souza_livingknowledge_2");
					//	ElasticMain.run();
						Map<LivingKnowledgeSnapshot, Double> documents = new HashMap<LivingKnowledgeSnapshot, Double>();
						documents = (Map<LivingKnowledgeSnapshot, Double>) ElasticMain.getResults();
						
						for (Entry<LivingKnowledgeSnapshot,Double> s : documents.entrySet())
						{
							//System.out.println((s.getKey().getDocId()));
							sb.append(s.getKey().getText());
						}
					}
				}
				
				bw.write(sb.toString());
				bw.close();
				
				System.out.println(topicID + "Done");
			}
		}
	}
}

