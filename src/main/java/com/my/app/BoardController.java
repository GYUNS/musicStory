package com.my.app;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.MBoardService;
import vo.MBoardVO;

@Controller
public class BoardController {
	
	@Autowired
	MBoardService service;
	
	// 답글달기
	// Reply_InsertfForm
	@RequestMapping(value = "/rinsertf", method = RequestMethod.GET)
	public ModelAndView rinsert(ModelAndView mv, MBoardVO vo) {
      // => vo 에는 전달된 부모글의 root, step, indent 가 담겨있음 
      // => 매핑메서드의 인자로 정의된 vo 는 request.setAttribute 와 동일 scope
      //    단, 클래스명의 첫글자를 소문자로 ...  ${boardVO.root}
		mv.setViewName("board/rinsertForm");
		return mv;
	}//rinsert
	
	//** 1. Board list 
		@RequestMapping(value = "/blist", method = RequestMethod.GET)
		public ModelAndView blist(ModelAndView mv) {
			// 1. 요청분석  2. Service
		  
			mv.addObject("banana",service.selectList());
		
			// 3. 결과 : view 처리
			mv.setViewName("board/boardList");
			return mv;
		} // blist
		
		//** Board Detail
		@RequestMapping(value = "/bdetail", method = RequestMethod.GET)
		public ModelAndView bdetail(HttpServletRequest request, HttpServletResponse response,RedirectAttributes rttr, ModelAndView mv, MBoardVO vo) {
			// 1. 요청분석 // 2. Service
			
			// 1.1 쿠키 생성
			Cookie viewCookie = null;
			Cookie[] cookies = request.getCookies();
			
			// 1.2) 현재글의 조회수 증가 반영 확인
			// => 없을때만 증가
			if ( cookies != null ) {
				for( Cookie c:cookies ) {
					if (c.getName().equals("|"+vo.getSeq()+"|")) {
						viewCookie = c ;
						break ;
					}
				}
			}
			
			if ( viewCookie == null ) {
				if ( service.countUp(vo) > 0 ) {
					Cookie newCookie = new Cookie("|"+vo.getSeq()+"|","view");
					newCookie.setMaxAge(24*60*60);
					response.addCookie(newCookie);	
				}else {
					System.out.println("** 조회수 증가가 정상적으로 처리되지 않음 **");
					mv.addObject("message","조회수 증가가 정상적으로 처리되지 않음" );
				}
			}
			vo =service.selectOne(vo);
			// => Mybatis 적용시에는 중간객체를 거쳐 전달 되기때문에 vo에 결과를 담아야함
			
			if ( vo != null) {
				mv.addObject("apple",vo);
				mv.setViewName("board/boardDetail");
			}else {
				rttr.addFlashAttribute("message","글 번호에 해당하는 글이 없습니다");
				mv.setViewName("redirect:blist");
			}

			// 3. 결과 : view 처리
			
			return mv;
		} // bdetail
		
		//**  Board insert
		@RequestMapping(value = "/binsertf", method = RequestMethod.GET)
		public ModelAndView binsert(ModelAndView mv) {
			mv.setViewName("board/insertForm");
			return mv;
		}
		
		@RequestMapping(value = "/binsert", method = RequestMethod.GET)
		public ModelAndView binsert(ModelAndView mv, MBoardVO vo, RedirectAttributes rttr) {
			
			// 1. 요청분석
			if ( service.insert(vo)>0 ) {
				//성공
				rttr.addFlashAttribute("message", "~~새글 등록 성공");
				mv.setViewName("redirect:blist");
			}else {
				//실패
				rttr.addFlashAttribute("message", "~~새글 등록 실패! 다시 써주세요");
				mv.setViewName("board/insertForm");
			}

			// 2. 결과 : view 처리
			return mv;
		} // binsert
		
	
		//**  Board update
		@RequestMapping(value = "/bupdatef", method = RequestMethod.GET)
		public ModelAndView bupdatef(ModelAndView mv, MBoardVO vo, RedirectAttributes rttr) {
		// 1. 요청분석 ,Service
			vo =service.selectOne(vo);
			// => Mybatis 적용시에는 중간객체를 거쳐 전달 되기때문에 vo에 결과를 담아야함
			
			if ( vo != null ) {
				// 출력 -> updateForm.jsp
				mv.addObject("apple", vo);
				mv.setViewName("board/updateForm");
			} else  {
				// 메시지 보관, boardList.jsp 출력 (서블릿으로)
				rttr.addFlashAttribute("message", "~~ 글번호의 자료를 읽어오는데 실패 했습니다 ~~");
				mv.setViewName("redirect:blist");
			}
			
			// 2. 결과 : view 처리
			return mv;
		} // update
		
		@RequestMapping(value = "/bupdate", method = RequestMethod.GET)
		public ModelAndView bupdate(ModelAndView mv, MBoardVO vo, RedirectAttributes rttr) {
			// 1. 요청분석 & Service
			if ( service.update(vo) > 0) {
				rttr.addFlashAttribute("message","글 수정 성공");
				mv.setViewName("redirect:blist");
			}else {
				rttr.addFlashAttribute("message","글 수정 실패 다시하시죠");
				mv.setViewName("redirect:bupdatef?seq=?"+vo.getSeq());
			}
			return mv;
		}
		
		//**  Board delete
		@RequestMapping(value = "/bdelete", method = RequestMethod.GET)
		public ModelAndView bdelete(ModelAndView mv , MBoardVO vo, RedirectAttributes rttr) {
			// 1. 요청분석
			if (service.delete(vo) > 0) {
				// 삭제성공 -> 메시지 보관, boardList.jsp 출력 (서블릿으로)
				rttr.addFlashAttribute("message", "글삭제를 완료했습니다");
				mv.setViewName("redirect:blist");
			}else {
				// 삭제실패 boardDetail.jsp -> (서블릿으로)
				rttr.addFlashAttribute("message", "글삭제를 실패했습니다");
				mv.setViewName("redirect:bdetail?seq="+vo.getSeq());
			}
			
			// 3. 결과 : view 처리

			return mv;
		} // delete
}//class
