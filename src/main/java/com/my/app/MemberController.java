package com.my.app;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.MemberService;
import vo.MemberVO;
import vo.PageVO;

// ** Bean 생성하는 @
// Java : @Component
// Spring 세분화 됨
// => @Controller,  @Service,  @Repository

@Controller
public class MemberController {
	@Autowired 
	// 자동주입 (injection)
	// => 조건: 주입받으려는 구현 클래스가 반드시 생성되어있어야함. 
	MemberService service;
	//MemberService service = new MemberServiceImpl();
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    // PasswordEncoder interface 구현 클래스
    // => Pbkdf2PasswordEncoder, BCryptPasswordEncoder, 
    //    SCryptPasswordEncoder, StandardPasswordEncoder, 
    //    NoOpPasswordEncoder
    // => 대표적인 BCryptPasswordEncoder root-context.xml 또는 
    //    servlet-context.xml 에 bean 설정 후 @Autowired 가능
	   
	
	
	//** Page_List 1
	@RequestMapping(value = "/mpagelist", method=RequestMethod.GET)
	public ModelAndView mpagelist(ModelAndView mv,PageVO<MemberVO> pvo) {
		// 1. Paging 준비
		int currPage=1;
		if (pvo.getCurrPage() > 1) currPage=pvo.getCurrPage();
		else pvo.setCurrPage(currPage);
		
		int sno = (currPage-1)*pvo.getRowsPerPage();
		pvo.setSno(sno);
		
		int eno = sno + pvo.getRowsPerPage()-1;
		pvo.setEno(eno);
		
		
		int totalPageNo = pvo.getTotalRowCount() / pvo.getRowsPerPage();
		if (pvo.getTotalRowCount() % pvo.getRowsPerPage() !=0 )
			totalPageNo +=1;
		
		mv.addObject("currPage",currPage);
		mv.addObject("totalPageNo",totalPageNo);
		mv.addObject("banana",pvo.getList());
		
		int sPageNo= ((currPage -1) / pvo.getPageNocount())*pvo.getPageNocount() + 1;
		int ePageNo= sPageNo + pvo.getPageNocount() - 1;
		// 계산으로 얻어지s ePageNo가 실제 LastPage 인 totalPageNo 보다 크면 수정 필요
		if (ePageNo> totalPageNo) ePageNo=totalPageNo;
		
		
		mv.addObject("sPageNo", sPageNo);
		mv.addObject("ePageNo", ePageNo);
		mv.addObject("pageNocount", pvo.getPageNocount());
		
		
		mv.setViewName("member/mPageList");
		
		// 2. 결과 : view 처리
		return mv;
	} //mpagelist
	
// *** jsonView Login Test
	@RequestMapping(value = "/jslogin")
	public ModelAndView jslogin(HttpServletRequest request, HttpServletResponse response, ModelAndView mv, MemberVO vo, RedirectAttributes rttr) {
		// 1. 요청분석
		// => 입력한 password 보관
		String password = vo.getPassword();
		// => response 한글처리
		response.setContentType("text/html; charset=UTF-8");
		
		// 2. Service
		vo = service.selectOne(vo);
		if ( vo!=null ) {
			// id 성공 -> password 확인
			if ( vo.getPassword().equals(password) ) {
				// Login 성공 -> Login정보(id,name) 보관 -> reload
				// => session 객체 생성후 보관
				HttpSession session = request.getSession(true);
				session.setAttribute("LoginID",vo.getId()); 
				session.setAttribute("LoginName",vo.getName());
				mv.addObject("code","200");
				// => 요청명 "home" 으로 sendRedirect
			}else {
				// password 오류 -> LoginForm
				mv.addObject("code","201");
				mv.addObject("message","~~ password 오류 !!! 다시  ");
			}
		}else {
			// id 오류 -> LoginForm
			mv.addObject("code", "201");
			mv.addObject("message","~~ id 오류 !!! 다시  ");
		}
		// 3. 결과 : view 처리
		mv.setViewName("jsonView");
		return mv;
		
	} //jslogin
	
// *** Image (File) Download
	@RequestMapping(value = "/dnload")
	public ModelAndView dnload(HttpServletRequest request, ModelAndView mv, 
			@RequestParam("dnfile") String dnfile ) {
		   // => 동일기능 : String dnfile = request.getParameter("dnfile");
		
		// 1. File Download
		// => real path 확인 -> 해당화일선택 -> response 처리 (response의 body 에 담아줌) 
		
		String realPath = request.getRealPath("/"); // deprecated Method
		String fileName = dnfile.substring(dnfile.lastIndexOf("/")+1); // dnfile => resources\\uploadImage\\aaa.gif 
		
		// => 개발중인지, 배포했는지 에 따라 결정
		// => 해당화일 File 찾기
		if ( realPath.contains(".eclipse.") )  // eslipse 개발환경 (배포전)
			realPath = "D:/MTest/MyWork/Spring01/src/main/webapp/resources/uploadImage/" 
						+ fileName;
		else  // 톰캣서버에 배포 후 : 서버내에서의 위치
			realPath += "resources\\uploadImage\\" + fileName ;
		
		// => 해당화일 File 객체화 
		File file = new File(realPath);
		mv.addObject("downloadFile", file);
		
		// 2. 결과 view처리 : Java File 객체 -> File 정보를 response 에 전달
		mv.setViewName("downloadView");
		// ** 일반적인 경우 ~/views/downloadView.jsp 를 찾음, 그러나 이 경우에는 아님
		// => servlet-context.xml 에 설정하는 view 클래스 (DownloadView.java) 의
		//    id 와 동일 해야함.
		
		return mv;
	} //dnload
	
	
	//** Ajax Member Delete 
	@RequestMapping(value = "/axmdelete")
	public ModelAndView axmdelete(HttpServletRequest request, ModelAndView mv, MemberVO vo) {
		// 1. 요청분석 & Service
		HttpSession session = request.getSession(false); 
		if ( session!=null && ((String)session.getAttribute("LoginID")).equals("admin")) {
			// 삭제가능
			if ( service.delete(vo) > 0 ) {
				// 삭제성공   
				mv.addObject("code", "200");
			}else {
				// 삭제실패 -> 서버오류  
				mv.addObject("code", "201");
			}
		}else {
			// 삭제 불가능 -> 로그인 정보 없음  
			mv.addObject("code", "202");
		}
		// 2. 결과 view처리 : Java 객체 -> JSON 
		mv.setViewName("jsonView");
		return mv;
	} //axmdelete
	
	//** Ajax_MemberList
	@RequestMapping(value = "/axmlist", method=RequestMethod.GET)
	public ModelAndView axmlist(ModelAndView mv) {
		mv.addObject("banana", service.selectList());        
		mv.setViewName("axTest/axMemberList");
		return mv;
	} //axmlist
	
	//** ID 중복 확인
	@RequestMapping(value = "/idDupCheck", method=RequestMethod.GET)
	public ModelAndView idDupCheck(ModelAndView mv, MemberVO vo) {
		// 입력한 newID 보관
		mv.addObject("newId", vo.getId());
		vo = service.selectOne(vo);
		if ( vo!=null ) { 
			// id 존재 -> 사용불가능
			mv.addObject("idUse", "F");
		}else {
			// id 존재하지 않음 -> 사용가능
			mv.addObject("idUse", "T");
		}
		mv.setViewName("member/idDupCheck");
		return mv;
	} //idDupCheck
	
	//** 1. Login
	@RequestMapping(value = "/loginf", method=RequestMethod.GET)
	public ModelAndView loginf(ModelAndView mv) {
		mv.setViewName("member/loginForm");
		return mv;
 	} //loginf
	
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, ModelAndView mv, MemberVO vo, RedirectAttributes rttr) {
		// 1. 요청분석
		String password = vo.getPassword();
		
		// 2. Service
		vo = service.selectOne(vo);
		if ( vo!=null ) {
			// id 성공 -> password 확인
			// ** password 암호화 이전
			//if ( vo.getPassword().equals(password) ) {
			
			//** password 암호화 이후
			if ( passwordEncoder.matches(password,vo.getPassword())) {
				
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
	
	@RequestMapping(value = "/logout") 
	public ModelAndView logout(HttpServletRequest request, ModelAndView mv, RedirectAttributes rttr) {
		// 1. Logout => session 무효화
		request.getSession().invalidate(); 
	
		// 2. 결과(View) 처리
		//mv.addObject("message", "~~ Logout 성공 ~~");
		rttr.addFlashAttribute("message", "~~ Logout 성공 ~~");
		mv.setViewName("redirect:home");
		return mv;
	} //logout
	
	//** 2. Join
	@RequestMapping(value = "/joinf", method=RequestMethod.GET)
	public ModelAndView joinf(ModelAndView mv) {
		mv.setViewName("member/joinForm");
		return mv;
	} //joinf
	
	//@RequestMapping(value = "/join", method=RequestMethod.GET)
	// => 405: Request method 'POST' not supported
	@RequestMapping(value = "/join", method=RequestMethod.POST)
	public ModelAndView join(HttpServletRequest request, 
							ModelAndView mv, MemberVO vo) throws IOException {
		
		int cnt=0;
		if ( cnt> 0 ) {
			// 입력성공 -> loginForm 으로 
			mv.addObject("message", "~~ 회원가입 완료 -> 로그인후 이용 하세요 ~~");
			mv.setViewName("member/loginForm");
		}else {
			// 입력실패 -> 재시도 유도 joinForm 으로
			mv.addObject("message", "~~ 회원가입 오류 -> 다시 하세요 ~~");
			mv.setViewName("member/joinForm");
		}
		// 3. 결과 : view 처리
		return mv;
	} //join
	
	//** 3. MemberList
	@RequestMapping(value = "/mlist")
	public ModelAndView mlist(ModelAndView mv) {
		// 1. 요청분석 & Service
		mv.addObject("banana", service.selectList());
		mv.setViewName("member/memberList");
		
		// 2. 결과 : view 처리
		return mv;
	} //mlist
	
	//** 4. MemberDetail
	@RequestMapping(value = "/mdetail")
	public ModelAndView mdetail(HttpServletRequest request, ModelAndView mv, MemberVO vo) {
		// 1. 요청분석 & Service
		
		HttpSession session = request.getSession(false);  
		if ( session!=null && session.getAttribute("LoginID")!=null ) {
			 
			vo.setId((String)session.getAttribute("LoginID"));
			vo = service.selectOne(vo);
			if ( vo!=null ) { 
				
				// => Detail or Update 확인 
				if ( request.getParameter("jcode")!=null && request.getParameter("jcode").equals("U") )  {
					
					// ** PasswordEncoder 사용 때문에 
		            //    session에 보관해 놓은 raw_password 를 수정할수 있도록 vo에 set 해줌.
				      vo.setPassword((String)session.getAttribute("loginPW"));	
					  mv.setViewName("member/updateForm");
				}else mv.setViewName("member/memberDetail");
				// => vo를 View가 출력 가능하도록 담고 View 지정
				request.setAttribute("apple", vo);
			}else {
				// => user 정보 읽어오는데 실패 -> 재로그인 유도 ( loginForm.jsp ) 
				mv.addObject("message", "~~ vo null: 개인정보 읽어오기 실패 !! ~~");
				mv.setViewName("member/loginForm");
			} // vo
		}else {
			// 로그인정보가 없음을 알려준다 -> 재로그인 유도 ( loginForm.jsp )
			mv.addObject("message", "~~ session null: 로그인 정보가 없습니다 !! ~~");
			mv.setViewName("member/loginForm");
		} // session_if-else
		
		// 2. 결과 : view 처리
		return mv;
	} //mdetail
	
	
	//** 8. Member Delete 
	@RequestMapping(value = "/mdelete")
	public ModelAndView mdelete(HttpServletRequest request, ModelAndView mv, MemberVO vo, RedirectAttributes rttr) {
		// 1. 요청분석 & Service
		HttpSession session = request.getSession(false); 
		if ( session!=null && session.getAttribute("LoginID")!=null ) {
			// 삭제가능
			vo.setId((String)session.getAttribute("LoginID"));
			if ( service.delete(vo) > 0 ) {
				// 삭제성공 -> session 무효화 -> home.jsp
				rttr.addFlashAttribute("message", " ~~ 회원탈퇴 성공, 1개월 후 재가입 가능 ~~");
				session.invalidate();
			}else {
				// 삭제실패 -> 서버오류 -> home.jsp
				rttr.addFlashAttribute("message", " ~~ 회원 탈퇴 처리중 서버 문제발생 , 잠시후 다시 하세요 ~~");
			}
		}else {
			// 삭제 불가능 -> 로그인 정보 없음 -> home.jsp
			rttr.addFlashAttribute("message", " ~~ 회원 탈퇴를 처리할수 없습니다 : 로그인 정보 없음 ~~");
		}
		
		// 2. 결과 : view 처리
		mv.setViewName("redirect:home");
		return mv;
	} //mdelete
	
} //class
