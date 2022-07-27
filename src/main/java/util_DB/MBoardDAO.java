package util_DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import vo.MBoardVO;

@Repository
public class MBoardDAO {
	private Connection cn= DBConnection.getConnection();
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private String sql;
	
	//** selectList
	public List<MBoardVO> selectList(){
		sql="select * from board order by seq desc";
		List<MBoardVO> list = new ArrayList<MBoardVO>();
		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			// 출력 record 가 있는지 확인 
			if ( rs.next() ) {
				// 출력 자료 있음
				// ResultSet 의 Data -> list 에 담기
				do {
					MBoardVO vo = new MBoardVO();
					vo.setSeq(rs.getInt(1));
					vo.setTitle(rs.getString(2));
					vo.setMb_id(rs.getString(3));
					vo.setCnt(rs.getString(4));
					vo.setHap(rs.getString(5));
					list.add( vo );
				}while(rs.next());
				
			}else {
				// 출력자료없음
				System.out.println("** 출력할 자료가 1개도 없습니다 ");
				list = null;
			}
			
		} catch (Exception e) {
			System.out.println("** seledtList Exception => "+ e.toString());
			list = null;
		}
		return list;
		
	}//selectList
	
	public MBoardVO selectOne(MBoardVO vo) {
		sql = "select * from board where seq=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1,vo.getSeq());
			rs = pst.executeQuery();
			if ( rs.next()) {
				vo.setSeq(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setMb_id(rs.getString(3));
				vo.setCnt(rs.getString(4));
				vo.setHap(rs.getString(5));

			}else {
				System.out.println("** 글번호에 해당하는 글이 없습니다 ** ");
				vo=null;
			}
		} catch (Exception e) {
			System.out.println("** seledtOne Exception => "+ e.toString());
			vo=null;
		}
		return vo;
	}//selectOne
	// 조회수 증가 메서드 작성
	public int countUp(MBoardVO vo) {
		// 조회수 증가 Sql 처리
		sql = "update board set cnt=cnt+1 where seq=?";
		try {
			pst =cn.prepareStatement(sql);
			pst.setInt(1, vo.getSeq());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** countUp Exception => "+ e.toString());
			return 0;
		}
	}//count up
	
	public int insert(MBoardVO vo) {
		sql = "insert into board(title,id)values(?,?)";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, vo.getTitle());
			pst.setString(2, vo.getId());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** Board insert Exception => "+ e.toString());
			return 0;
		}
		
	}//insert
	
	public int update(MBoardVO vo) {
		sql = "update board set title=? where seq=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, vo.getTitle());
			pst.setInt(2, vo.getSeq());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** Board update Exception => "+ e.toString());
			return 0;
		}
		
	}//update
	
	public int delete(MBoardVO vo) {
		sql = "delete from board where seq=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, vo.getSeq());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** Board delete Exception => "+ e.toString());
			return 0;
		}
		
	}//delete
	
	
}//class
