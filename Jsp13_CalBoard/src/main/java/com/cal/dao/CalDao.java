package com.cal.dao;

import static com.cal.db.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cal.dto.CalDto;

public class CalDao {

	public List<CalDto> calendarList(String id, String yyyyMMdd) {
		Connection con = getConnection();

		String sql = " SELECT SEQ, ID, TITLE, CONTENT, MDATE, REGDATE "
				+ " FROM CALBOARD WHERE ID = ? AND SUBSTR(MDATE, 1, 8) = ? ";

		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CalDto> list = new ArrayList<CalDto>();

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMMdd);

			rs = pstm.executeQuery();
			while (rs.next()) {
				CalDto dto = new CalDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getDate(6));

				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
		}

		return list;
	}

	public int insertCalendar(CalDto dto) {
		Connection con = getConnection();

		String sql = " INSERT INTO CALBOARD VALUES(CALBOARDSEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE) ";

		PreparedStatement pstm = null;
		int res = 0;

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getId());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			pstm.setString(4, dto.getMdate());

			res = pstm.executeUpdate();
			if (res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm, con);
		}

		return res;
	}

	public List<CalDto> calendarViewList(String id, String yyyyMM) {
		Connection con = getConnection();

		String sql = " SELECT * FROM ( "
				+ "		SELECT (ROW_NUMBER() OVER(PARTITION BY SUBSTR(MDATE, 1, 8) ORDER BY MDATE)) RN, SEQ, ID, TITLE, CONTENT, MDATE, REGDATE "
				+ "		FROM CALBOARD WHERE ID = ? AND SUBSTR(MDATE, 1, 6) = ?) " + " WHERE RN BETWEEN 1 AND 3 ";

		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CalDto> list = new ArrayList<CalDto>();

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMM);

			rs = pstm.executeQuery();
			while (rs.next()) {
				CalDto dto = new CalDto(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getDate(7));

				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
		}

		return list;
	}

	public int calendarViewCount(String id, String yyyyMMdd) {
		Connection con = getConnection();

		String sql = " SELECT COUNT(*) FROM CALBOARD WHERE ID = ? AND SUBSTR(MDATE, 1, 8) = ? ";

		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMMdd);

			rs = pstm.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstm, con);
		}

		return count;
	}

	public int multiDelete(String[] seqs) {
		Connection con = getConnection();

		String sql = " DELETE FROM CALBOARD WHERE SEQ = ? ";

		PreparedStatement pstm = null;
		int res = 0;
		int[] cnt = null;

		try {
			pstm = con.prepareStatement(sql);

			for (int i = 0; i < seqs.length; i++) {
				pstm.setString(1, seqs[i]);

				pstm.addBatch();
			}

			cnt = pstm.executeBatch();

			for (int i = 0; i < cnt.length; i++) {
				if (cnt[i] == -2) {
					res++;
				}
			}

			if (seqs.length == res) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm, con);
		}

		return res;
	}
}
