package mapperInterface;

import java.util.List;
import vo.MBoardVO;
public interface MBoardMapper {
	
	
	
	int rinsert(MBoardVO vo); // Reply_Insert
	int stepUpdate(MBoardVO vo);
	
	List<MBoardVO> selectList();
	MBoardVO selectOne(MBoardVO vo);
	int countUp(MBoardVO vo);
	int insert(MBoardVO vo);
	int update(MBoardVO vo);
	int delete(MBoardVO vo);

}//BoardMapper
