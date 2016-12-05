package com.novellius.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.novellius.pojo.Admin;
import com.novellius.pojo.AdminRowMapper;

@Component("adminDao")
public class AdminDaoImpl implements AdminDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public boolean save(Admin admin) {
		/*
		 * MapSqlParameterSource paramMap = new MapSqlParameterSource();
		 * paramMap.addValue("nombre", admin.getNombre());
		 * paramMap.addValue("cargo", admin.getCargo());
		 * paramMap.addValue("fechaCreacion", admin.getFechaCreacion());
		 */

		// Si los nombres de los campos son iguales a los de la base de datos...
		BeanPropertySqlParameterSource paramMap = new BeanPropertySqlParameterSource(admin);

		int result = jdbcTemplate.update(
				"INSERT INTO admin (nombre, cargo, fechaCreacion) VALUES(:nombre, :cargo, :fechaCreacion)", paramMap);
		return result == 1;
	}

	@Override
	public List<Admin> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT * FROM admin", new RowMapper<Admin>() {
			@Override
			public Admin mapRow(ResultSet rs, int arg1) throws SQLException {
				Admin admin = new Admin();

				admin.setIdAd(rs.getInt("idAd"));
				admin.setNombre(rs.getString("nombre"));
				admin.setCargo(rs.getString("cargo"));
				admin.setFechaCreacion(rs.getTimestamp("fechaCreacion"));

				return admin;
			}
		});
	}

	@Override
	public Admin findById(int id) {
		// return (Admin)jdbcTemplate.query("SELECT * FROM admin WHERE
		// idAd=:idAd",
		// new MapSqlParameterSource("idAd", id),
		// new AdminRowMapper());
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM admin WHERE idAd=:idAd",
					new MapSqlParameterSource("idAd", id), new AdminRowMapper());
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public List<Admin> findByName(String nombre) {
		return jdbcTemplate.query("SELECT * FROM admin WHERE nombre LIKE :nombre",
				new MapSqlParameterSource("nombre", "%" + nombre + "%"), new AdminRowMapper());
	}

	@Override
	public boolean update(Admin admin) {
		BeanPropertySqlParameterSource paramMap = new BeanPropertySqlParameterSource(admin);
		int result = jdbcTemplate.update(
				"UPDATE admin SET nombre=:nombre, cargo=:cargo, fechaCreacion=:fechaCreacion WHERE idAd=:idAd",
				paramMap);
		return result == 1;
	}

	@Override
	public boolean delete(int id) {
		int result = jdbcTemplate.update("DELETE FROM admin WHERE idAd=:idAd", new MapSqlParameterSource("idAd", id));
		return result == 1;
	}
	
	// Todo este método es transaccional
	@Transactional
	@Override
	public int[] saveAll(List<Admin> admins) {
		SqlParameterSource[] batchArgs = SqlParameterSourceUtils.createBatch(admins.toArray());
		return jdbcTemplate.batchUpdate("INSERT INTO admin (idAd, nombre, cargo, fechaCreacion) VALUES(:idAd, :nombre, :cargo, :fechaCreacion)", 
				batchArgs);
		
	}

}
