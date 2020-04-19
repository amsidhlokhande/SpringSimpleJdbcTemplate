package com.amsidh.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;

import com.amsidh.dao.CircleDao;
import com.amsidh.model.Circle;

@Component
public class CircleDaoImpl implements CircleDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private SimpleJdbcTemplate simpleJdbcTemplate;
	
	@Override
	public Circle getCircle(int circleId) {
		return null;

	}
	@SuppressWarnings("deprecation")
	public int getCircleCount()
	{
		String query="SELECT COUNT(*) FROM CIRCLE";
	    return jdbcTemplate.queryForInt(query);
	}
	
	public Circle getCircleById(int circleId)
	{
		String query="SELECT * FROM CIRCLE WHERE ID=:circleId";
		SqlParameterSource sqlParameterSource=new MapSqlParameterSource().addValue("circleId", circleId);
		
		//return jdbcTemplate.queryForObject(query, new Object[]{circleId},new CircleRowMapper());
		return simpleJdbcTemplate.queryForObject(query, new CircleRowMapper(),sqlParameterSource);
	}
	@SuppressWarnings("unchecked")
	public List<Circle> getAllCircle()
	{
		String query="SELECT * FROM CIRCLE";
		return (List<Circle>) simpleJdbcTemplate.query(query,new CircleRowMapper());
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}


	private static final class CircleRowMapper implements RowMapper<Circle>{

		@Override
		public Circle mapRow(ResultSet rst, int rowNumber) throws SQLException {
			Circle circle=new Circle();
			circle.setId(rst.getInt("ID"));
			circle.setName(rst.getString("NAME"));
			return circle;
		}
		
	}
}
