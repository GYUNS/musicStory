package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapperInterface.MBoardMapper;
import vo.MBoardVO;

@Service
public class MBoardServiceImpl implements MBoardService {
	
	@Autowired
	MBoardMapper mapper;

	@Override // Reply_Insert
	public int rinsert(MBoardVO vo) {
		System.out.println("** stepUpdate Count =>"+mapper.stepUpdate(vo));
		return mapper.rinsert(vo);
	}

	@Override
	public List<MBoardVO> selectList(){
		return mapper.selectList();
	}//selectList
	
	@Override
	public MBoardVO selectOne(MBoardVO vo) {
		return mapper.selectOne(vo);
	}//selectOne
	
	@Override
	public int countUp(MBoardVO vo) {
		return mapper.countUp(vo);
	}
	
	@Override
	public int insert(MBoardVO vo) {
		return mapper.insert(vo);
	}
	
	@Override
	public int update(MBoardVO vo) {
		return mapper.update(vo);
	}
	
	@Override
	public int delete(MBoardVO vo) {
		return mapper.delete(vo);
	}
	
}//class
