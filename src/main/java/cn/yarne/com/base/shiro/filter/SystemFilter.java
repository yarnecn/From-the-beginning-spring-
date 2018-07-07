package cn.yarne.com.base.shiro.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author yarne
 * @version 2016年6月21日 上午9:50:58
 */

public class SystemFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest sreq, ServletResponse sresp, FilterChain chain)
			throws IOException, ServletException {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();

		chain.doFilter(sreq, sresp);
	}

	/**
	 * 判断是否为Ajax请求
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return 是true, 否false
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		return (request.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
	}

	public void init(FilterConfig config) throws ServletException {
	}
}
