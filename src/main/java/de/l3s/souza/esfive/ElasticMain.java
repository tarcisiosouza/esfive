package de.l3s.souza.esfive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class ElasticMain {

	public static boolean ASC = true;
    public static boolean DESC = false;
    private static String keywords;
    private static String field;
    private static int limit;
    private static long count;
    private static Map<Url, Double> result;
    private static String propFileName;
    private static UrlElasticQuery query;
    private static Map<LivingKnowledgeSnapshot, Double> resultLK;
    private static String indexName;
    
    public ElasticMain (String q, int lim, String f, String index) throws IOException
    {
    	
    	ElasticMain.indexName = index;
    	result = new HashMap<Url, Double>();
    	keywords = q;
    	limit = lim;
    	field = f;
    	count = 0;
    	query = new UrlElasticQuery (indexName);
    	
    }
  
    public void closeConnection ()
    {
    	query.closeConnection();
    }
    
	public static String getKeywords() {
		return keywords;
	}
	public void setKeywords(String key) {
		keywords = key;
	}
	
	public static String getField() {
		return field;
	}
	public void setField(String field) {
		ElasticMain.field = field;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int lim) {
		limit = lim;
	}
	
	public static Map<Url, Double> getResult() {
		return result;
	}
	
	public static Map<LivingKnowledgeSnapshot, Double> getResultLK() {
		return resultLK;
	}

	public static Map<?, Double> getResults ()
	{
		switch (indexName)
		{
		
		case "souza_livingknowledge":
		{
			return  resultLK;
		}
		
		case "souza_livingknowledge_2":
		{
			return  resultLK;
		}
		
		case "souza_german_cdx_urls":
		{
			return result;
			
		}
		
		
		}
		
		return null;
	}
	public HashMap <String, Integer> getDomains (){
		return query.getDomains();
	}
	
	public ArrayList<String> getArticleText () {
		return query.getArticleText();
	}
	public ArrayList<String> getTotalDocuments (){
		return query.getArrayTotalDoc();
	}
	public void run () throws IOException
	{
		
		switch (indexName)
		{
		
		case "souza_livingknowledge_2":
		{
			resultLK = new HashMap<LivingKnowledgeSnapshot, Double>();
			resultLK = query.getRankedDocumentsLivingKnowledge(keywords, limit, field);
			break;
		}
		
		case "souza_livingknowledge":
		{
			resultLK = new HashMap<LivingKnowledgeSnapshot, Double>();
			resultLK = query.getRankedDocumentsLivingKnowledge(keywords, limit, field);
			break;
		}
		
	/*	case "souza_german_cdx_urls":
		{
			result = query.getRankedDocuments(keywords, limit, field);
			break;
		}
		*/
		}
		
			
	}
	

	public HashMap<String, Article> getAllDocuments () throws IOException
	{
		return query.getDocuments(limit);
	}
	
	public static void main (String args[])
	{
		
	}
	}
