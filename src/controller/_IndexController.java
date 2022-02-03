package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/")
public class _IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
//--------------------------------------------------------------------------------------------------------
		int cookNo = 0;
		HttpSession session = request.getSession();
		
		if (session.getAttribute("cookNo") != null) {
			cookNo = (Integer) session.getAttribute("cookNo");
		}	
		
//--------------------------------------------------------------------------------------------------------				
		String path = request.getContextPath();
		String page = path + "/main_servlet/login.do";
		//String page =  "/main_servlet/login.do";
		
		if(cookNo > 0) { //로그인 상태
			page = path + "/main_servlet/main.do";
			//page = "/main_servlet/main.do";
		}		
		response.sendRedirect(page);
	}

}
