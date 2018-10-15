package tw.proj.solr;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.Map;

/**
 * @author ljc
 */
public interface BlogSolr {
	Map<String,Object> searchDefault(String keywords, int start) throws IOException, SolrServerException;
	Map<String,Object> searchHottest(String keywords, int start) throws IOException, SolrServerException;
	Map<String,Object> searchNewest(String keywords, int start) throws IOException, SolrServerException;
}
