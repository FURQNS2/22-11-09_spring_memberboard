package com.sycompany.Ex04.dao.mapper;

import java.util.ArrayList;

import com.sycompany.Ex04.dto.FreeBoardDto;
import com.sycompany.Ex04.dto.MemberDto;

public interface IDao {
	
	//멤버 관련 메서드 시작
	
	//멤버가입 메서드
	public void joinMemberDao(String mid, String mpw, String mname, String memail);
	
	//회원가입여부확인
	public int checkIdDao(String mid);
	public int checkPwDao(String mid, String mpw);
	
	//가입된 회원정보 가져오기
	public MemberDto memberInfoDao(String mid);
	
	
	
	//board 관련 메서드	
	
	//게시판 글 쓰기
	public void writeDao(String mid, String mname, String ftitle, String fcontent);
	
	// 목록(모든 글 불러오기)
	public ArrayList<FreeBoardDto> listDao(); 
	
}
