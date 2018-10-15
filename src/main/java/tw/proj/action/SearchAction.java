package tw.proj.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import tw.proj.service.SearchService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 搜索-web层
 *
 * @author ljc
 * @date 2018/10/7
 */
@Controller("searchAction")
@Scope("prototype")
public class SearchAction extends ActionSupport {
	@Resource(name = "searchService")
	private SearchService searchService;

	public String search() throws Exception {
		//获取Request对象
		HttpServletRequest request = ServletActionContext.getRequest();
		//获取关键词
		String keywords = request.getParameter("keywords");
		//定义页码
		String page = "page";
		//获取页码
		if (request.getParameter(page) == null) {
			page = "1";
			request.setAttribute("page", page);
		} else {
			page = request.getParameter(page);
		}
		int pageint = Integer.parseInt(page);
		//将页码转换为查询起始
		int start = (pageint - 1) * 20;
		//获取查询方式searchMethod,并查询
		String searchMethod = request.getParameter("searchMethod");
		Map<String, Object> map;
		if ("hottest".equals(searchMethod)) {
			map = searchService.searchHottest(keywords, start);
		} else if ("newest".equals(searchMethod)) {
			map = searchService.searchNewest(keywords, start);
		} else {
			map = searchService.searchDefault(keywords, start);
			request.setAttribute("searchMethod", "default");
		}
		//获取blog的list集合
		Object blogs = map.get("blogs");
		//获取查询总数
		Object numFound = map.get("numFound");
		//将结果存入值栈
		request.getSession().setAttribute("blogs", blogs);
		request.getSession().setAttribute("numFound", numFound);
		//执行结束,返回
		return SUCCESS;
	}
}
