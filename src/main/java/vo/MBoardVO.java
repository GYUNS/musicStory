package vo;

import lombok.Data;

@Data
public class MBoardVO extends MemberVO {
	private int seq;
	private String title;
	private String mb_id;
	private String cnt;
	private String hap;
}// class
