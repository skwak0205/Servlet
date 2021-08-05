package com.mvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mvc.dto.MVCDto;

public class MVCDaoImpl extends SqlMapConfig implements MVCDao {

	private String namespace = "mvc.mapper.";

	@Override
	public List<MVCDto> selectList() {
		List<MVCDto> list = new ArrayList<MVCDto>();

		try (SqlSession session = getSqlSessionFactory().openSession(false)) {
			list = session.selectList(namespace + "selectList");
		}

		return list;
	}

	@Override
	public MVCDto selectOne(int seq) {
		MVCDto dto = null;

		try (SqlSession session = getSqlSessionFactory().openSession(false)) {
			dto = session.selectOne(namespace + "selectOne", seq);
		}

		return dto;
	}

	@Override
	public int insert(MVCDto dto) {
		int res = 0;

		try (SqlSession session = getSqlSessionFactory().openSession(false)) {
			res = session.insert(namespace + "insert", dto);

			if (res > 0) {
				session.commit();
			}
		}

		return res;
	}

	@Override
	public int update(MVCDto dto) {
		int res = 0;

		try (SqlSession session = getSqlSessionFactory().openSession(true)) {
			res = session.update(namespace + "update", dto);
		}

		return res;
	}

	@Override
	public int delete(int seq) {
		int res = 0;

		try (SqlSession session = getSqlSessionFactory().openSession(true)) {
			res = session.delete(namespace + "delete", seq);
		}

		return res;
	}

}
