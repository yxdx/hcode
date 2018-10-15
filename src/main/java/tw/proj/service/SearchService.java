package tw.proj.service;

import java.util.Map;

/**
 * 搜索-业务层
 *
 * @author ljc
 * @date 2018/10/7
 */
public interface SearchService {
	Map<String, Object> searchDefault(String keywords, int start) throws Exception;
	Map<String, Object> searchHottest(String keywords, int start) throws Exception;
	Map<String, Object> searchNewest(String keywords, int start) throws Exception;

}
