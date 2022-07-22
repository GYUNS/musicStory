package com.my.app;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import service.MemberService;
import vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired 
	MemberService service;
	
	@RequestMapping(value = "/join", method=RequestMethod.GET)
	public ModelAndView join(HttpServletRequest request, 
							ModelAndView mv, MemberVO vo) throws IOException {
				
		System.out.println("***** vo => "+vo);
		
		// 1. 요청분석

	
		// 2. Service
		int cnt = service.insert(vo);
		
		
		if ( cnt> 0 ) {
			// 입력성공 -> loginForm 으로 
			mv.addObject("message", "~~ 회원가입 완료 -> 로그인후 이용 하세요 ~~");
			mv.setViewName("main/main");
		}else {
			// 입력실패 -> 재시도 유도 joinus 
			mv.addObject("message", "~~ 회원가입 오류 -> 다시 하세요 ~~");
			mv.setViewName("main/joinus");
		}
		// 3. 결과 : view 처리
		return mv;
	} //join

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, ModelAndView mv, 
			MemberVO vo, RedirectAttributes rttr) {
		// 1. 요청분석
		String password = vo.getPassword();
		
		// 2. Service
		vo = service.selectOne(vo);
		if ( vo!=null ) {
			// id 성공 -> password 확인
			// ** password 암호화 이전
			if ( vo.getPassword().equals(password) ) {
			
			//** password 암호화 이후
			//if ( passwordEncoder.matches(password,vo.getPassword())) {
				
				// Login 성공 -> Login정보(id,name) 보관 -> home
				// => session 객체 생성후 보관
				HttpSession session = request.getSession(true);
				session.setAttribute("LoginID",vo.getId()); 
				session.setAttribute("LoginName",vo.getName());
				
				// BCryptPasswordEncoder 로 암호화되면 복호화가 불가능함.
	            // => password 수정 을 별도로 처리해야 함.
	            // => 그러나 기존의 update  Code 를 활용하여 updateForm.jsp 에서 수정을 위해
	            //    User가 입력한 raw_password 를 보관함. 
	            // => 이 session에 보관한 값은 detail 에서 "U" 요청시 사용함. 
				session.setAttribute("loginPW",password);
				
							
				// mv.addObject("message", "~~ Login 성공 ~~");
				// => redirect 의 경우 메시지 출력안됨
				//    해결 : RedirectAttributes 
				rttr.addFlashAttribute("message", "~~ Login 성공 ~~");
				mv.setViewName("redirect:home");
				// => 요청명 "home" 으로 sendRedirect
			}else {
				// password 오류 -> LoginForm
				mv.addObject("message", "~~ password 오류 !! 다시 하세요 ~~");
				mv.setViewName("member/loginForm");
			}
		}else {
			// id 오류 -> LoginForm
			mv.addObject("message", "~~ id 오류 !! 다시 하세요 ~~");
			mv.setViewName("member/loginForm");
		}
		// 3. 결과 : view 처리
		return mv;
		
	} //login
}
