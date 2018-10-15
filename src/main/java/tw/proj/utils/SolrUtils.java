package tw.proj.utils;

/**
 * solr工具类
 *
 * @author ljc
 * @date 2018/10/7
 */
public class SolrUtils {
	public static String toStatement(String keywords) {
		//"blog_tittle:如何学习人工智能 && blog_summary:如何学习人工智能"
		String s = keywords.replaceAll("\\s", "");
		return "blog_tittle:" + s + " && blog_summary:" + s;

	}
}
