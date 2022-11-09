package com.sycompany.Ex04.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sycompany.Ex04.dao.mapper.IDao;

@Controller
public class FBoardController {

	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/joinMember")
	public String joinMember() {
		
		return "joinMember";
	}
	
	@RequestMapping(value = "joinok", method = RequestMethod.POST)   // 숨겨진 값 가져오기
	public String joinok(HttpServletRequest request) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		dao.joinMemberDao(request.getParameter("mid"), request.getParameter("mpw"), request.getParameter("mname"), request.getParameter("memail"));
		
		return "joinok";
	}
	
}
