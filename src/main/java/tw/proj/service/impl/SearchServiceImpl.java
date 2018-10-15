package tw.proj.service.impl;

import org.springframework.stereotype.Service;
import tw.proj.service.SearchService;
import tw.proj.solr.BlogSolr;
import tw.proj.solr.impl.BlogSolrImpl;

import java.util.Map;

/**
 * @author ljc
 * @date 2018/10/7
 */
@Service("searchService")
public class SearchServiceImpl implements SearchService {
	private BlogSolr blogSolr = new BlogSolrImpl();

	@Override
	public Map<String, Object> searchDefault(String keywords, int start) throws Exception {
		return blogSolr.searchDefault(keywords, start);
	}

	@Override
	public Map<String, Object> searchHottest(String keywords, int start) throws Exception {
		return blogSolr.searchHottest(keywords, start);
	}

	@Override
	public Map<String, Object> searchNewest(String keywords, int start) throws Exception {
		return blogSolr.searchNewest(keywords, start);
	}
}
