package service;

import java.util.List;
import vo.MBoardVO;

public interface MBoardService {
	
	int rinsert(MBoardVO vo);
	List<MBoardVO> selectList();//selectList
	MBoardVO selectOne(MBoardVO vo);//selectOne

	int countUp(MBoardVO vo);

	int insert(MBoardVO vo);

	int update(MBoardVO vo);

	int delete(MBoardVO vo);

}//class