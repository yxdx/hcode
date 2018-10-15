package tw.proj.solr.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Component;
import tw.proj.entity.Blog;
import tw.proj.solr.BlogSolr;
import tw.proj.utils.SolrUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author ljc
 */
@Component("blogSolr")
public class BlogSolrImpl implements BlogSolr {
	private final static String BASE_SOLR_URL = "http://149.28.30.131:80/solr/new_core";


	/**
	 * 默认搜索方式
	 *
	 * @Date 2018/10/5
	 * @Param [statement, start]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@Override
	public Map<String, Object> searchDefault(String keywords, int start) throws IOException, SolrServerException {
		HttpSolrClient solrClient = new HttpSolrClient.Builder(BASE_SOLR_URL).build();

		//将关键词转换为查询语句
		String statement = SolrUtils.toStatement(keywords);

		SolrQuery query = new SolrQuery();
		query.setQuery(statement)
				.setStart(start)
				.setRows(20)
				.setHighlight(true)
				.setHighlightSimplePre("<span style='color:#ea4335'>")
				.setHighlightSimplePost("</span>")
				.addHighlightField("blog_tittle , blog_summary");
		QueryResponse response = solrClient.query(query);
		SolrDocumentList documents = response.getResults();
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		long numFound = documents.getNumFound();
		List<Blog> blogs = new ArrayList<>();
		addBlog(documents, highlighting, blogs,keywords);
		HashMap<String, Object> map = new HashMap<>(2);
		map.put("blogs", blogs);
		map.put("numFound", numFound);
		return map;
	}

	/**
	 * 最热排序搜索
	 *
	 * @Date 2018/10/5
	 * @Param [statement, start]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@Override
	public Map<String, Object> searchHottest(String keywords, int start) throws IOException, SolrServerException {
		HttpSolrClient solrClient = new HttpSolrClient.Builder(BASE_SOLR_URL).build();

		//将关键词转换为查询语句
		String statement = SolrUtils.toStatement(keywords);

		SolrQuery query = new SolrQuery();
		//setSort设置排序方式
		query.setQuery(statement)
				.setStart(start)
				.setRows(20)
				.setSort("blog_pageviews", SolrQuery.ORDER.desc)
				.setHighlight(true)
				.setHighlightSimplePre("<span style='color:#ea4335'>")
				.setHighlightSimplePost("</span>")
				.addHighlightField("blog_tittle , blog_summary");
		QueryResponse response = solrClient.query(query);
		SolrDocumentList documents = response.getResults();
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		long numFound = documents.getNumFound();
		List<Blog> blogs = new ArrayList<>();
		addBlog(documents, highlighting, blogs,keywords);
		HashMap<String, Object> map = new HashMap<>(2);
		map.put("blogs", blogs);
		map.put("numFound", numFound);
		return map;

	}


	/**
	 * 最新搜索排序
	 *
	 * @Date 2018/10/5
	 * @Param [statement, start]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@Override
	public Map<String, Object> searchNewest(String keywords, int start) throws IOException, SolrServerException {
		HttpSolrClient solrClient = new HttpSolrClient.Builder(BASE_SOLR_URL).build();

		//将关键词转换为查询语句
		String statement = SolrUtils.toStatement(keywords);

		SolrQuery query = new SolrQuery();
		//setSort设置排序方式
		query.setQuery(statement)
				.setStart(start)
				.setRows(20)
				.setSort("blog_postdate", SolrQuery.ORDER.desc)
				.setHighlight(true)
				.setHighlightSimplePre("<span style='color:#ea4335'>")
				.setHighlightSimplePost("</span>")
				.addHighlightField("blog_tittle , blog_summary");
		QueryResponse response = solrClient.query(query);
		SolrDocumentList documents = response.getResults();
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		long numFound = documents.getNumFound();
		List<Blog> blogs = new ArrayList<>();
		addBlog(documents, highlighting, blogs, keywords);
		HashMap<String, Object> map = new HashMap<>(2);
		map.put("blogs", blogs);
		map.put("numFound", numFound);
		return map;
	}


	private void addBlog(SolrDocumentList documents, Map<String, Map<String, List<String>>> highlighting, List<Blog> blogs, String keywords) throws IOException {
		for (SolrDocument document : documents) {
			String id = (String) document.get("id");
			Blog blog = new Blog();
			String url = (String) document.get("blog_url");
			String authorName = (String) document.get("blog_author_name");
			String authorUrl = (String) document.get("blog_author_url");
			Date postdate = (Date) document.get("blog_postdate");
			int pageviews = (int) document.get("blog_pageviews");
			String title = highlighting.get(id).get("blog_tittle").get(0);
//			String summary = highlighting.get(id).get("blog_summary").get(0);
			String summary = (String) document.get("blog_summary");


			int end = (summary.length() <= 350) ? summary.length() : 350;
			int start = (summary.length() <= 100) ? 0 : 100;
			String s = summary.substring(start, end);

			//设置summary高亮显示, summary中的代码会使页面乱码,放弃summary高亮
			/*IKAnalyzer ik = new IKAnalyzer();
			TokenStream tokenStream = ik.tokenStream("text", keywords);
			CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
			tokenStream.reset();
			while (tokenStream.incrementToken()) {
				String newSummary="<span style='color:#ea4335'>"+charTermAttribute+"</span>";
				if(s.contains(charTermAttribute)) {
					s=s.replace(charTermAttribute, newSummary);
				}
			}
			tokenStream.close();*/

			blog.setUrl(url)
					.setAuthorName(authorName)
					.setAuthorUrl(authorUrl)
					.setTitle(title)
					.setSummary(s)
					.setPageviews(pageviews)
					.setPostdate(postdate);
			blogs.add(blog);
		}
	}
}
