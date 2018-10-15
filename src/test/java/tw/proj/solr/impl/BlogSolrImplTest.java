package tw.proj.solr.impl;

import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BlogSolrImplTest {

	@Test
	public void searchDefault() {
		try {
			Map<String, Object> map = new BlogSolrImpl().searchDefault("blog_tittle:如何学习人工智能 && blog_summary:如何学习人工智能", 0);
			List blogs = (List) map.get("blogs");
			Object numFound = map.get("numFound");
			System.out.println("共搜索到" + numFound + "条记录");
			for (Object blog : blogs) {
				System.out.println(blog);
			}


		} catch (IOException | SolrServerException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void searchByPageviews() {
		try {
			Map<String, Object> map = new BlogSolrImpl().searchHottest("blog_tittle:人工智能 && blog_summary:人工智能", 0);
			List blogs = (List) map.get("blogs");
			Object numFound = map.get("numFound");
			System.out.println("共搜索到" + numFound + "条记录");
			for (Object blog : blogs) {
				System.out.println(blog);
			}


		} catch (IOException | SolrServerException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void searchByPostdate() {
		try {
			Map<String, Object> map = new BlogSolrImpl().searchNewest("blog_tittle:人工智能 && blog_summary:人工智能", 0);
			List blogs = (List) map.get("blogs");
			Object numFound = map.get("numFound");
			System.out.println("共搜索到" + numFound + "条记录");
			for (Object blog : blogs) {
				System.out.println(blog);
			}


		} catch (IOException | SolrServerException e) {
			e.printStackTrace();
		}
	}
}