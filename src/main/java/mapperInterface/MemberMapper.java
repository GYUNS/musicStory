package mapperInterface;

import vo.MemberVO;

public interface MemberMapper {
	MemberVO selectOne(MemberVO vo);
	int insert(MemberVO vo);
	
}
