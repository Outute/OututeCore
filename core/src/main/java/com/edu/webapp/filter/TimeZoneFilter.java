package com.edu.webapp.filter;

import java.io.IOException;
import java.util.TimeZone;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TimeZoneFilter implements Filter {

	public TimeZoneFilter() {
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		try {
			HttpSession session = ((HttpServletRequest) request).getSession();
			TimeZone timeZone = (TimeZone) session.getAttribute("TIME_ZONE");
			if (timeZone == null) {
				String tz = request.getParameter("time_zone");
				timeZone = TimeZone.getTimeZone("GMT"
						+ (tz.length() > 1 ? "" : "+") + tz);
				session.setAttribute("TIME_ZONE", timeZone);
			}
			System.out.println(timeZone + "=====" + timeZone.getRawOffset()
					/ (3600 * 1000));
		} catch (Exception e) {
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

}