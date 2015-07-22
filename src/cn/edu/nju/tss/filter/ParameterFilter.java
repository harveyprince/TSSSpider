package cn.edu.nju.tss.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class ParamterFilter
 */
@WebFilter("/")
public class ParameterFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public ParameterFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
			HttpServletRequest req=(HttpServletRequest)request;
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			FilterSQL filterSQL=new FilterSQL(req,req.getParameterMap());
			if (req.getRequestURI().contains(";")) {
				httpResponse.sendRedirect(req.getRequestURI().replace(";", "?"));
				return;
			}
			chain.doFilter(filterSQL, response);
		// pass the request along the filter chain
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public class FilterSQL extends HttpServletRequestWrapper {
		private Map params;

		public FilterSQL(HttpServletRequest request, Map newParams) {
			super(request);
			this.params = newParams;
		}

		public Map getParameterMap() {
			Map paramst = new HashMap();
			Enumeration req = getParameterNames();
			while (req.hasMoreElements()) {
			     String obj = (String) req.nextElement();
			     //System.out.println("obj.toString()==="+obj.toString());
			     paramst.put(obj, getParameter(obj));
			 }
			return paramst;
		}

		public Enumeration getParameterNames() {
			Vector l = new Vector(params.keySet());
			return l.elements();
		}

		public String[] getParameterValues(String name) {
			Object v = params.get(name);
			if (v == null) {
				return null;
			} else if (v instanceof String[]) {
				String[] a = (String[]) v;
				String[] b = new String[] {};
				for (int i = 0; i < a.length; i++) {
					String c = filter(a[i].toString());
					b[i] = c;
				}
				return b;
			} else if (v instanceof String) {
				String[] a = (String[]) v;
				String b = filter(a[0]);
				return new String[] { (String) b };
			} else {
				return new String[] { filter(v.toString()) };
			}
		}

		public String getParameter(String name) {
			Object v = params.get(name);
			if (v == null) {
				return null;
			} else if (v instanceof String[]) {
				String[] strArr = (String[]) v;
				if (strArr.length > 0) {
					return filter(strArr[0]);
				} else {
					return null;
				}
			} else if (v instanceof String) {
				return filter((String) v);
			} else {
				return filter(v.toString());
			}
		}

		public String filter(String avaluelue) {
			if (avaluelue == null) {
				return null;
			}
			StringBuffer parm = new StringBuffer();
			for (int i = 0; i < avaluelue.length(); ++i) {
				char j = avaluelue.charAt(i);
				if (j != '<' && j != '>' && j != '"' && j != '\'' && j != '%'
						&& j != ';' && j != ')' && j != '(' && j != '+'&& j != ' ') {
					parm.append(j);
				}
			}
			return parm.toString().trim();
		}
	}

}