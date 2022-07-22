package service;

import vo.MemberVO;

public interface MemberService {
	
	MemberVO selectOne(MemberVO vo);
	
	int insert(MemberVO vo);
}
