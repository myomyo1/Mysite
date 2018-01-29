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
			List<BoardVo> blist = dao.showListAll(); // 리스트 객체를 반환하는 showListAll() : select함수 사용하여 blist에 저장 
			String kwd=request.getParameter("kwd");
			List<BoardVo> slist = dao.search(kwd);
			if(kwd==null) {
				request.setAttribute("list", blist); // blist 값을 "list"이름으로 세팅 + 게시물은 list.jsp에서 forEach문으로 리스트 뿌려줄 예정.				
			}else {
				request.setAttribute("list", slist);
			}

			WebUtil.forward(request, response, "WEB-INF/views/board/list.jsp"); //list.jsp 파일로 이동하라고 명령.			
		}else if(actionName.equals("view")) {
			System.out.println("view 진입");
			int number = Integer.parseInt(request.getParameter("no")); //list.jsp에서 (링크에 이름 지정해서)넘겨준 "no"값 받아옴
			BoardDao dao = new BoardDao(); 
			dao.countHit(number); //countHit(no) 함수 : board테이블에 게시물번호 no(primary key)를 받아서 조회수 +1 시키는 update함수
			BoardVo vo =dao.viewBoard(number); // viewBoard()함수실행 : 받아온 no에 해당하는 제목, 내용, 유저넘버를 객체에 저장하여 select함수로 뽑아옴. + view.jsp에서 제목, 내용 보여줄때 사용할 예정.
			request.setAttribute("board", vo); // 위객체를 다음페이지에 보내기위해 board 이름값으로 세팅
			WebUtil.forward(request, response, "WEB-INF/views/board/view.jsp"); // view.jsp파일로 이동하라고 명령.
		}else if(actionName.equals("writeform")) {
			System.out.println("writeform 진입");
			WebUtil.forward(request, response, "WEB-INF/views/board/write.jsp"); //
			 
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
}
