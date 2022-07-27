package com.my.app;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import service.MemberService;
import vo.MemberVO;


@Controller
public class MemberController {
	@Autowired 
	MemberService service;
	
	@RequestMapping(value = "/join", method=RequestMethod.POST)
	public ModelAndView join(HttpServletRequest request, 
							ModelAndView mv, MemberVO vo) throws IOException {
	
		int cnt = service.insert(vo);
		
		if ( cnt> 0 ) {
			mv.addObject("message", "~~ 회원가입 완료 -> 로그인후 이용 하세요 ~~");
			mv.setViewName("main/main");
		}else {
			mv.addObject("message", "~~ 회원가입 오류 -> 다시 하세요 ~~");
			mv.setViewName("main/joinus");
		}
		return mv;
	} //join
	
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, ModelAndView mv, MemberVO vo, RedirectAttributes rttr) {
		// 1. 요청분석
		String password = vo.getPassword();
		
		// 2. Service
		vo = service.selectOne(vo);
		if ( vo!=null ) {
			if ( vo.getPassword().equals(password) ) {
		
				
			HttpSession session = request.getSession(true);
			session.setAttribute("LoginID",vo.getId()); 
			session.setAttribute("LoginName",vo.getName());
			session.setAttribute("loginPW",password);
			
			mv.setViewName("redirect:main");
		}else {
			mv.addObject("message", "~~ password 오류 !! 다시 하세요 ~~");
			mv.setViewName("main/main");
		}
		}else {
			mv.addObject("message", "~~ id 오류 !! 다시 하세요 ~~");
			mv.setViewName("main/main");
		}
	// 3. 결과 : view 처리
	return mv;
	
} //login
	
} //class
