package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.AdminDAO;
import model.dto.AdminDTO;

@WebServlet("/main_servlet/*")
public class _MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String path = request.getContextPath();
		String url = request.getRequestURL().toString();

//--------------------------------------------------------------------------------------------------------
		int cookNo = 0;
		String authority = "";
		HttpSession session = request.getSession();
		
		if (session.getAttribute("cookNo") != null) {
			cookNo = (Integer) session.getAttribute("cookNo");
			authority = (String) session.getAttribute("cookAuthority");			
		}
		
		if(cookNo == 0) {
			if(url.contains("login.do") == true) {			
				
			} else if(url.contains("loginProc.do") == true) {
				
			} else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인 후 이용하세요');");
				out.println("location.href='" + path + "';");
				out.println("</script>");
				out.flush();
				out.close();
				return;
			}
		}
		
//--------------------------------------------------------------------------------------------------------		
		String page = "";
		if(url.contains("login.do") == true) {
			page = "/WEB-INF/_main/login.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("loginProc.do") == true) {
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");			
			
			AdminDTO dto =  new AdminDTO();
			dto.setId(id);
			dto.setPasswd(passwd);
			
			AdminDAO dao = new AdminDAO();
			dto = dao.getLogin(dto);
					
			String msg = "";
			page = "";
			
			if(dto.getNo() > 0) {
				session.setAttribute("cookNo", dto.getNo());
				session.setAttribute("cookId", dto.getId());
				session.setAttribute("cookName", dto.getName());
				session.setAttribute("cookDep_name", dto.getDep_name());
				session.setAttribute("cookLevel_staff", dto.getLevel_staff());
				session.setAttribute("cookAuthority", dto.getAuthority());
				session.setAttribute("cookPassChg_period", dto.getPassChg_period());
				session.setMaxInactiveInterval(5 * 60); //5분
				msg = "로그인에 성공했습니다.";
				page = path + "/main_servlet/main.do";
				
				System.out.println(msg);
				response.sendRedirect(page);
			} else {
				msg = "아이디 또는 비밀번호가 다릅니다.";
				page = path + "/main_servlet/login.do";
				
				System.out.println(msg);
				response.sendRedirect(page);
			}
			
		} else if(url.contains("logout.do") == true) {
			//System.out.println("logout");
			session.invalidate();
			
			String move = path + "/main_servlet/login.do";
			response.sendRedirect(move);
			
		} else if(url.contains("main.do") == true) {
			//String inc_page = "../product/list.jsp";
			//request.setAttribute("inc_page", inc_page);
			
			page = "/WEB-INF/_main/main.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
		
	}
}
