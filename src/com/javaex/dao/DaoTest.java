package com.javaex.dao;

import com.javaex.vo.UserVo;

public class DaoTest {
public static void main(String[] args) {
	
//	//Dao에 자료가 잘 들어가는지 확인하기위해
//	//Vo 객체에 정보 넣고, Dao객체에 insert
//	//vo객체를 List로 받아서 안의 값 쭉 뽑아보기
//	
//	UserVo vo = new UserVo("이름2","이메일2","비밀번호2","성별2");
//	UserDao dao = new UserDao();
//		
//	dao.insert(vo);
	
	UserDao dao = new UserDao();
	UserVo vo = dao.getUser("이메일2", "비밀번호2");
	System.out.println(vo.toString());

}
}
