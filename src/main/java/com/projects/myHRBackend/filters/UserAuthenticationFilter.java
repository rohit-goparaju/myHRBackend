package com.projects.myHRBackend.filters;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class UserAuthenticationFilter extends HttpFilter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String method = request.getMethod();
		
		if(method.equalsIgnoreCase("OPTIONS")) {
			chain.doFilter(request, response);
			return;
		}
		
		HttpSession session = request.getSession(false);
		String path = request.getRequestURI();
		String loginPath = request.getContextPath()+"/Login";
		String rootPath = request.getContextPath()+"/";
		String logoutPath = request.getContextPath() + "/Logout";
//		String createsuperadminPath = request.getContextPath() + "/createsuperadmin";
		if(path.equals(loginPath) || path.equals(rootPath) || path.equals(logoutPath)) {
			chain.doFilter(request, response);
			return;
		}
		else if(session != null && session.getAttribute("user")!=null){
			chain.doFilter(request, response);
			return;
		}else {
			response.sendRedirect(rootPath);
			return;
		}
	}
	
}
