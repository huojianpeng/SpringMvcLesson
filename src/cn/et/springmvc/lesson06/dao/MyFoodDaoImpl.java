package cn.et.springmvc.lesson06.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MyFoodDaoImpl {

	@Autowired
	JdbcTemplate jdbc;
	public List<Map<String,Object>> queryAllFood(String foodname) {
		return jdbc.queryForList("select * from food where foodname like '%"+foodname+"%'");
	}
	
	
	public void deleteFood(String foodId) {
		jdbc.execute("delete from food where foodId = "+foodId);
	}
	
	
	public void saveFood(String foodName,String price) {
		jdbc.execute("insert into food (foodname,price) values ('"+foodName+"','"+price+"')");
		
	}


	public void updateFood(String foodId, String foodName, String price) {
		jdbc.execute("update food set foodname='"+foodName+"',price='"+price+"' where foodid="+foodId);
		
	}
	
}
