package com.sycompany.Ex04.controller;

import java.net.http.HttpRequest;
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
	public String list(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		ArrayList<FreeBoardDto> dtos = new ArrayList<FreeBoardDto>();
		
		dtos = dao.listDao();
		
		model.addAttribute("boardCount", dtos.size());	
		model.addAttribute("list",dtos);
		
		
		//화면에 저장 중인 로그인 값 가져오기
		HttpSession session = request.getSession();
		String sid = session.getAttribute("sessionId").toString();
		
		//idflag==1이면 로그인중, 0이면 비로그인
		int idflag = 0;
			if(sid != null) {
				idflag = 1;   // 로그인 중
			}else {
				idflag = 0;    //비로그인
			}
		model.addAttribute("idflag",idflag); // 로그인 중인지 아닌지 판단된 값 jsp에 보내주기
		
		model.addAttribute("sid", sid); // 로그인하고 있는 아이디값 jsp로 보내주기

		return "list";
	}
	
	
	@RequestMapping("/contentView")
	public String contentView(HttpServletRequest request, Model model) {
		
		String fnum = request.getParameter("fnum");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		dao.uphitDao(fnum);  //조회수
		
		FreeBoardDto fbdto = dao.contentViewDao(fnum); // 조회수를 입력받은 후 값을 송출
 		
		
		//현재세션에 로그인 되어있는 아이디 가져오기
		HttpSession session = request.getSession();
		String sid = session.getAttribute("sessionId").toString();
		
		String fid = fbdto.getFid(); // 보고 있는 글의 아이디
		
		//idflag==1이면 수정, 삭제 권한 설정
		int idflag = 0;
		
			if((sid != null) && (sid.equals(fid))) {
				idflag = 1;
			}else {
				idflag = 0;
			}
		model.addAttribute("idflag",idflag); 
		
		model.addAttribute("fbdto",  fbdto);
		
		return "contentView";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		String fnum = request.getParameter("fnum");
		
		dao.deleteDao(fnum);

		return "redirect:list";
	}
	
	@RequestMapping("/modifyView")
	public String modifyView(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		String fnum = request.getParameter("fnum");
		
		FreeBoardDto fbdto = dao.contentViewDao(fnum);
		
		model.addAttribute("fbdto",  fbdto);
		
		return "modifyView";
	}
	
	 
	@RequestMapping("/modify")
	public String modify(HttpServletRequest request) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		String fnum = request.getParameter("fnum");
		String ftitle = request.getParameter("ftitle");
		String fcontent = request.getParameter("fcontent");	
		
		dao.modifyDao(fnum, ftitle, fcontent);
		
		return "redirect:list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
