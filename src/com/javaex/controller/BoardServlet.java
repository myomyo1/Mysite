package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;


@WebServlet("/board")
public class BoardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		String actionName = request.getParameter("a");
							
		if(actionName.equals("list")) {
			System.out.println("list 진입");
			BoardDao dao = new BoardDao();
			List<BoardVo> blist = dao.showListAll(); 
			request.setAttribute("list", blist);
			WebUtil.forward(request, response, "WEB-INF/views/board/list.jsp");
		}else if(actionName.equals("view")) {
			System.out.println("view 진입");
			BoardDao dao = new BoardDao(); //BoardDao에 만든 viewBoard함수 꺼내오기위해
			int no = Integer.parseInt(request.getParameter("no")); //list에서 넘겨준 "no"값 받기위해 getparameter로
			dao.countHit(no);
			BoardVo vo =dao.viewBoard(no);//위 번호 받아서 함수실행하면 제목, 내용이 담겨진 객체를 받아옴
			request.setAttribute("board", vo); // 위객체를 다음페이지에 보내기위해 세팅
			WebUtil.forward(request, response, "WEB-INF/views/board/view.jsp");
		}else if(actionName.equals("writeform")) {
			System.out.println("writeform 진입");
			WebUtil.forward(request, response, "WEB-INF/views/board/write.jsp");
			 
		}else if(actionName.equals("write")) {
			System.out.println("write진입");
			HttpSession session = request.getSession();
			UserVo authuser = (UserVo)session.getAttribute("authUser");
			int no = authuser.getNo();
			System.out.println("로그인 유저 번호 : " + no);
			
			String title =request.getParameter("title");
			String content =request.getParameter("content");
			BoardVo bvo = new BoardVo();
			bvo.setUserNo(no);
			bvo.setTitle(title);
			bvo.setContent(content);
			BoardDao bdao = new BoardDao();
			bdao.write(bvo);
			WebUtil.redirect(request, response, "/mysite/board?a=list");
		}else if(actionName.equals("delete")) {
			int no = Integer.parseInt(request.getParameter("no"));
			BoardDao dao = new BoardDao();
			dao.delete(no);
			WebUtil.redirect(request, response, "/mysite/board?a=list");
		}else if(actionName.equals("modifyform")) {
			System.out.println("modifyform 진입");
			int no =Integer.parseInt(request.getParameter("no"));
			BoardDao dao = new BoardDao();
			BoardVo vo = dao.viewBoard(no);
			request.setAttribute("vo", vo);
			WebUtil.forward(request, response, "/WEB-INF/views/board/modify.jsp");
		}else if(actionName.equals("modify")) {
			System.out.println("modify진입");
			int userno = Integer.parseInt(request.getParameter("no"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			BoardVo vo = new BoardVo();
			vo.setUserNo(userno);
			vo.setTitle(title);
			vo.setContent(content);
			
			BoardDao dao = new BoardDao();
			dao.modify(vo);				
			
			WebUtil.redirect(request, response, "/mysite/board?a=list");
		}
	}

//		}else if(actionName.equals("deleteform")) {
//			System.out.println("deleteform 진입");
//			int no = Integer.parseInt(request.getParameter("no"));
//			request.setAttribute("no", no);
////			RequestDispatcher rd = request.getRequestDispatcher("guestDeleteform.jsp"); //"~" : 포워드할 페이지 지정해준 것. 
////			rd.forward(request, response); //url로 확인해보면 포워드 시킨 상태로 guestList.jsp화면이 보여짐.
//			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");
//			//이제 http://localhost:8088/guest2/guestList.jsp X. 
//			//사용자요청 : http://localhost:8088/guest2/WEB-INF/guestDeleteform.jsp X.
//		}else if(actionName.equals("delete")) {
//			System.out.println("delete 진입");
//			int no = Integer.parseInt(request.getParameter("no"));
//			String pw = request.getParameter("password");
//			GuestbookDao dao = new GuestbookDao();
//			dao.delete(no, pw);
////			response.sendRedirect("gs?a=list");
//			WebUtil.redirect(request, response, "/mysite/guestbook?a=list");
//		}
//		
//	}
//
//
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
}
}
