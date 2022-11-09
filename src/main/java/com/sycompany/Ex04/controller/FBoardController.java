package com.sycompany.Ex04.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sycompany.Ex04.dao.mapper.IDao;
import com.sycompany.Ex04.dto.FreeBoardDto;
import com.sycompany.Ex04.dto.MemberDto;

@Controller
public class FBoardController {

	@Autowired
	private SqlSession sqlSession;
	
	
	// 멤버로그인 시작
	
	@RequestMapping("/joinMember")
	public String joinMember() {	
		return "joinMember";
	}
	
	@RequestMapping(value = "joinok", method = RequestMethod.POST)   // 숨겨진 값 가져오기
	public String joinok(HttpServletRequest request, Model model) {		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		//ID중복확인, 1=있음, 0=없음
		int checkIdFlag = dao.checkIdDao(request.getParameter("mid"));
		
		model.addAttribute("checkIdFlag", checkIdFlag);
		
		if(checkIdFlag == 0) {
		//DB에 값 넣기
		dao.joinMemberDao(request.getParameter("mid"), request.getParameter("mpw"), request.getParameter("mname"), request.getParameter("memail"));
		model.addAttribute("mname", request.getParameter("mname"));
		}
		return "joinok";
	}
	
	@RequestMapping("/checkId")
	public String checkId(HttpServletRequest request, Model model) {	
		IDao dao = sqlSession.getMapper(IDao.class);

		//ID중복확인, 1=있음, 0=없음
		int checkIdFlag = dao.checkIdDao(request.getParameter("checkId"));
		model.addAttribute("checkIdFlag", checkIdFlag);
		
		return "checkId";
	}

	@RequestMapping("/login")
	public String login() {	
		return "login";
	}
	
	@RequestMapping("/")
	public String home() {
		return "redirect:login";
	}
	
	@RequestMapping(value="loginok", method=RequestMethod.POST)
	public String loginok(HttpServletRequest request, Model model) {	
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		
		int checkIdFlag = dao.checkIdDao(mid);
		int checkPwFlag = dao.checkPwDao(mid,mpw);	
		
		model.addAttribute("checkIdFlag", checkIdFlag);
		model.addAttribute("checkPwFlag",checkPwFlag);
		model.addAttribute("mid", mid);
		
		if(checkPwFlag == 1) {  // 로그인 성공 시 세션에 아이디와 로그인 유효값을 설정
		
			HttpSession session	= request.getSession();
			// 세션값(화면에 남아 있는 값) 가져오기
			session.setAttribute("sessionId",mid);
			session.setAttribute("ValidMem","yes");
		
			MemberDto dto = dao.memberInfoDao(mid);
			String mname = dto.getMname();
			model.addAttribute("mname", mname);
			
		} else {		// 로그인에 문제가 생겻을 경우
			model.addAttribute("mname", "guest");		
		}
		
		return "loginok";
	}
	
	// 멤버 로그인 끝
	
	
	
	// board 내용 시작
	
	@RequestMapping("/writeForm")
	public String writeForm(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		// 세션값(화면에 남아 있는 값) 가져오기
		HttpSession session	= request.getSession();
		
		String sid = session.getAttribute("sessionId").toString();
	
		if(sid==null) {
			
			return "redirect:login";
		
		} else {
		
		MemberDto dto = dao.memberInfoDao(sid);	
	
		String mid = dto.getMid();
		String mname = dto.getMname();
		
		model.addAttribute("mid", mid);
		model.addAttribute("mname", mname);
	
			return "writeForm";
		}
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		HttpSession session	= request.getSession();
		
		String sid = session.getAttribute("sessionId").toString();
		
		MemberDto dto = dao.memberInfoDao(sid);	
		
		String mid = dto.getMid();
		String mname = dto.getMname();
		String ftitle = request.getParameter("ftitle");
		String fcontent = request.getParameter("fcontent");
		
		dao.writeDao(mid, mname, ftitle, fcontent);
		
		return "redirect:list";
	}
	
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {	
		
		
		HttpSession session	= request.getSession();
		
		session.invalidate();
		
		
		return "logout";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		ArrayList<FreeBoardDto> dtos = new ArrayList<FreeBoardDto>();
		
		dtos = dao.listDao();
		
		model.addAttribute("list",dtos);
		
		return "list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
