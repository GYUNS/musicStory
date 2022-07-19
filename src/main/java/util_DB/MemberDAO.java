package util_DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import vo.MemberVO;

//** DAO(Data Access Object)
//=> SQL 구문 처리
//=> CRUD 구현 
//   Create(insert), Read(select), Update, Detete

@Repository // 외부 I/O 작업 DB
public class MemberDAO {
	private  Connection cn = DBConnection.getConnection(); 
	private  Statement st;
	private  PreparedStatement pst;
	private  ResultSet rs;
	private  String sql;
	
	public int insert(MemberVO vo) {
		sql="insert into member values(?,?,?,?,?)";
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, vo.getId());
			pst.setString(2, vo.getPassword());
			pst.setString(3, vo.getName());
			pst.setString(4, vo.getBirth());
			pst.setString(5, vo.getGender());
			return pst.executeUpdate();  
		} catch (Exception e) {
			System.out.println("** insert Exception => "+e);
			return 0;
		} //try
	} //insert
	
	// 4. update
	// => name, point, weight 수정
	public int update(MemberVO vo) {
		sql="update member set password=?, name=?, birth=?, gender=? where id=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, vo.getId());
			pst.setString(2, vo.getPassword());
			pst.setString(3, vo.getName());
			pst.setString(4, vo.getBirth());
			pst.setString(5, vo.getGender());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** update Exception => "+e);
			return 0;
		} //try
	} //update
	
	// 5. delete
	// => 삭제대상 : id 로 선택
	public int delete(MemberVO vo) {
		sql="delete from member where id=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, vo.getId());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** delete Exception => "+e);
			return 0;
		} //try
	} //delete			
	
} //class
