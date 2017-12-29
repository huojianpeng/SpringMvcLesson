package cn.et.springmvc.lesson05.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MoneyDaoImpl {

	@Autowired
	JdbcTemplate jdbc;

	public void trasnateMoney(Integer money) {
		String sql = "update mymoney set lostedmoney=lostedmoney-" + money
				+ " where id=1";
		jdbc.execute(sql);
	}

	public int selectMoney() {
		String sql = "select lostedmoney from mymoney where id=1";
		Integer lostedMoney = jdbc.queryForObject(sql, Integer.class);
		return lostedMoney;
	}
}
