package tw.proj.utils;

import org.junit.Test;

public class SolrUtilsTest {

	@Test
	public void toStatement() {
		String s = SolrUtils.toStatement("人工智障");
		System.out.println(s);
	}
}