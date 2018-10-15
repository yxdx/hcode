package tw.proj.action;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

/**
 * 当用户发送空请求时进行拦截
 *
 * @author ljc
 * @date 2018/10/9
 */
@Component("searchInterceptor")
public class SearchInterceptor extends MethodFilterInterceptor {
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		String keywords = ServletActionContext.getRequest().getParameter("keywords");
		if ("".equals(keywords)) {
			//空串,拦截
			return ActionSupport.ERROR;
		} else {
			//非空串,放行
			return invocation.invoke();
		}
	}
}
