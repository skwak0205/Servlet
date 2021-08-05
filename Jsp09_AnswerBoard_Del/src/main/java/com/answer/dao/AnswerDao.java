package com.answer.dao;

import java.util.List;

import com.answer.dto.AnswerDto;

public interface AnswerDao {
	String BOARD_LIST_SQL = " SELECT BOARDNO, GROUPNO, GROUPSEQ, TITLETAB, DELFLAG, TITLE, CONTENT, WRITER, REGDATE FROM ANSWERBOARD ORDER BY GROUPNO DESC, GROUPSEQ ";
	
	String BOARD_SELECT_SQL = " SELECT BOARDNO, GROUPNO, GROUPSEQ, TITLETAB, DELFLAG, TITLE, CONTENT, WRITER, REGDATE FROM ANSWERBOARD WHERE BOARDNO = ? ";
	
	String BOARD_INSERT_SQL = " INSERT INTO ANSWERBOARD VALUES(BOARDNOSEQ.NEXTVAL, GROUPNOSEQ.NEXTVAL, 1, 0, 'N', ?, ?, ?, SYSDATE) ";
	
	String BOARD_UPDATE_SQL = " UPDATE ANSWERBOARD SET TITLE = ?, CONTENT = ? WHERE BOARDNO = ? ";
	
	String BOARD_DELETE_SQL = " UPDATE ANSWERBOARD SET DELFLAG = 'Y' WHERE BOARDNO = ? ";

	String ANSWER_UPDATE_SQL = " UPDATE ANSWERBOARD SET GROUPSEQ = GROUPSEQ + 1 "
			+ " WHERE GROUPNO = ( SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO = ? ) "
			+ " AND GROUPSEQ > ( SELECT GROUPSEQ FROM ANSWERBOARD WHERE BOARDNO = ? ) ";

	String ANSWER_INSERT_SQL = " INSERT INTO ANSWERBOARD VALUES (BOARDNOSEQ.NEXTVAL, "
			+ " ( SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO = ? ), "
			+ " ( SELECT GROUPSEQ + 1 FROM ANSWERBOARD WHERE BOARDNO = ? ), "
			+ " ( SELECT TITLETAB + 1 FROM ANSWERBOARD WHERE BOARDNO = ? ), " 
			+ " 'N', ?, ?, ?, SYSDATE) ";

	public List<AnswerDto> selectList();

	public AnswerDto selectOne(int boardno);

	public boolean boardInsert(AnswerDto dto);

	public boolean boardUpdate(AnswerDto dto);

	public boolean boardDelete(int boardno);

	public boolean answerUpdate(int parentboardno);

	public boolean answerInsert(AnswerDto dto);
}
