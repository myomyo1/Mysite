package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;


@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("user 진입");
		String actionName = request.getParameter("a");
		if(actionName.equals("joinform")) { //가입양식(joinform.jsp)만 보여줄 것
			System.out.println("joinform 진입");
			// / 쓰면  mysite까지 불러와지므로 그 밑에 주소
			// servelet을 "/user" 라고 저장했으므로 "http://localhost:8088/mysite/user?a=joinform" : joinform으로 이동
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinform.jsp");   
		}else if(actionName.equals("join")) { //joinform에서 입력한 값들을 여기에 받아야함.
			System.out.println("join 진입");
			String name =request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			
			//위에서 받은 값들 저장시켜서 한 주머니에 담을 수 있도록 VO 불러오기
			UserVo uservo= new UserVo();
			uservo.setName(name);
			uservo.setEmail(email);
			uservo.setPassword(password);
			uservo.setGender(gender);
			
			System.out.println(uservo.toString());

			UserDao userdao = new UserDao();
			userdao.insert(uservo);
			
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinsuccess.jsp");
		}else if(actionName.equals("loginform")){
			System.out.println("loginfrom 진입");
			//메인에서 로그인 누르면 로그인폼으로 넘어가게 포워드시킴. index.jsp에서 경로지정해줘야 페이지 넘어감.
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			//로그인폼.jsp에서 form수정해주고
		}else if(actionName.equals("login")) {
			System.out.println("login진입");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			//vo주머니에 담지않고 바로 두개 넘기기
			System.out.println(email + " / " + password); 
			
			UserDao userdao = new UserDao(); 
			UserVo uservo = userdao.getUser(email, password); //다오에서 이메일 패스워드 받아서  no, name반환하는 getUser함수이다. 그것을 uservo에 담은것
			if(uservo==null) {
				System.out.println("로그인실패");
				WebUtil.redirect(request, response, "/mysite/user?a=loginform&result=fail"); //여기서 result는 name값, fail은 value값. loginfrom.jsp에서 getparameter로 result받아줄 것.
			}else {
				System.out.println("로그인성공");
				HttpSession session = request.getSession(); //★★★★★★★★★★★SESSION 처음 생성한곳★★★★★★★★★★★★
				session.setAttribute("authUser", uservo); //no,name반환되서 저장된 객체인 uservo를 authUser라는 세션에 저장된것.
				WebUtil.redirect(request, response, "/mysite/main");
			}
		}else if(actionName.equals("logout")) {
			System.out.println("logout진입");
			HttpSession session =request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();
			WebUtil.redirect(request, response, "/mysite/main");
		}else if(actionName.equals("modifyform")) {
			System.out.println("회원정보 수정 진입");
			//세션에서 no getNo() 로 가져올 수 있음
			HttpSession session = request.getSession(true);
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			//no없으면(비로그인상태) 로그인폼으로 이동(리다이렉트)
			if(authUser==null){
				
			}else {//no있으면(로그인상태) Dao에서 회원정보 데이터 가져와서 유저객체에 담은 후 request에 저장하고 포워드 시켜줌.
				int no = authUser.getNo();
				UserDao userdao = new UserDao ();
				UserVo uservo = userdao.getUser(no);
				
				request.setAttribute("uservo", uservo);
								
				WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
			}
		}else if(actionName.equals("modify")) { //회원정보수정 화면에서 이름, 비밀번호, 성별 변경한 값으로 다시 셋팅해줘야함.
			
			HttpSession session = request.getSession(true);
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			
			if(authUser ==null) {
				
			}else { //로그인상태
				//vo(no, name, password, gender)
				int no = authUser.getNo();
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String gender = request.getParameter("gender");
				
				
				UserVo uservo = new UserVo(no,name,"",password,gender);
			
				//dao.update(vp)
				UserDao userdao = new UserDao();
				userdao.modify(uservo);
				//session name값 변경
				authUser.setName(name);
				
				//main리다이렉트
				WebUtil.redirect(request, response, "/mysite/main");
			}
		}
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
