package util_DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import vo.MemberVO;

@Repository
public class MemberDAO {
	private  Connection cn = DBConnection.getConnection(); 
	private  Statement st;
	private  PreparedStatement pst;
	private  ResultSet rs;
	private  String sql;
	
	// insert
	public int insert (MemberVO vo) { 
		sql="insert into member values(?,?,?,?,?)";
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, vo.getId());
			pst.setString(2, vo.getPassword());
			pst.setString(3, vo.getName());
			pst.setString(4, vo.getBirth());
			pst.setString(5, vo.getGender());
			return pst.executeUpdate();
		}catch (Exception e) {
			System.out.println(" insert Exception =>"+ e);
			return 0;
		}//try
	}//insert
	
	
	
}
