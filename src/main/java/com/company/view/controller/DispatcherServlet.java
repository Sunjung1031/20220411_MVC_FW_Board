package com.company.view.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // 추가
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;
	
	public void init() throws ServletException {
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		viewResolver.setPrefix("./");
		viewResolver.setSuffix(".jsp");
	}
	
    public DispatcherServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response)
		throws IOException {
		// 1. 클라이언트의 요청 path 정보 추출
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));  // "/login.do"
		
		// 2. HandlerMapping을 통해서 path에 해당하는 Controller를 검색한다.
		Controller ctrl = handlerMapping.getController(path);
		
		// 3. 검색된 Controller를 실행한다.
		// 성공 시 => "getBoardList.do" 리턴, 실패 시 => "login" 리턴
		String viewName = ctrl.handleRequest(request, response);
		
		// 4. ViewResolver를 통해서 viewName에 해당하는 페이지로 포워딩해라!!
		String view = null;
		
		if (viewName.contains(".do")) {  // "getBoardList.do"
			view = viewName;  // "getBoardList.do"
		} else {  // "login"
			view = viewResolver.getView(viewName);  // "./login.jsp"
		}
		// 5. 검색된 페이지로 이동한다.
		response.sendRedirect(view); //
	}
}
