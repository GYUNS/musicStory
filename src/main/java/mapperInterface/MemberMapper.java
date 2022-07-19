package mapperInterface;

import java.util.List;

import vo.MemberVO;
import vo.PageVO;

public interface MemberMapper {
	
	
	int totalRowsCount(PageVO<MemberVO> pvo); // PageList 1.
	List<MemberVO> pageList(PageVO<MemberVO> pvo);
	
	List<MemberVO> selectList();
	MemberVO selectOne(MemberVO vo);
	int insert(MemberVO vo);
	int update(MemberVO vo);
	int delete(MemberVO vo);
	
}//MemberMapper

