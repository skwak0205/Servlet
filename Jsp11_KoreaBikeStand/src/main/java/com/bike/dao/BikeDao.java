package com.bike.dao;

import static com.bike.db.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.bike.dto.BikeDto;

public class BikeDao {
	public boolean insert(List<BikeDto> list) {
		Connection con = getConnection();

		String sql = " INSERT INTO KOREABIKE VALUES(?, ?, ?, ?, ?) ";

		PreparedStatement pstm = null;
		int res = 0;

		try {
			pstm = con.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				BikeDto dto = list.get(i);
				pstm.setString(1, dto.getName());
				pstm.setString(2, dto.getAddr());
				pstm.setDouble(3, dto.getLatitude());
				pstm.setDouble(4, dto.getLongitude());
				pstm.setInt(5, dto.getBike_count());

				pstm.addBatch(); // 메모리에 임시 저장
			}

			int[] result = pstm.executeBatch();
			for (int i = 0; i < result.length; i++) {
				if (result[i] == -2) {
					res++;
				}
			}

			if (res == list.size()) {
				con.commit();
			} else {
				con.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm, con);
		}

		return (res == list.size()) ? true : false;
	}

	public boolean delete() {
		Connection con = getConnection();

		String sql = " DELETE FROM KOREABIKE ";

		Statement stmt = null;
		int res = 0;
		boolean result = false;

		try {
			stmt = con.createStatement();

			res = stmt.executeUpdate(sql);
			if (res > 0) {
				result = true;
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, con);
		}

		return result;
	}

}
