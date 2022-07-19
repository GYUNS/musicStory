package vo;

import java.util.List;

import lombok.Data;

//** DTO : Data Transfer Object
//** member , board 모두 사용 가능하도록 Generic 설정

@Data
public class PageVO<T> {
	private List<T> list; //출력목록
	private int totalRowCount;   // 전체Row 갯수 ( 전체
	private int rowsPerPage = 5; // 1page당 출력할 row 갯수
	private int pageNocount = 3; // 1page당 표시할 pageNo 갯수
	private int currPage; // 요청(출력할) page PageNO
	private int sno; // start RowNo
	private int eno; // end RowNo (Oracle에서만 필요함)
}// class
