package cn.et.springmvc.lesson01.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.et.springmvc.lesson01.dao.FoodDao;
/**
 * 数据层
 * @author Administrator
 *
 */
@Repository
public class FoodDaoImpl implements FoodDao {
	
	@Autowired
	JdbcTemplate template;
	/**
	 * 总条数
	 */
	public Integer getTableListCountInteger(String name){
		String sql="select count(*) as cr from food where foodname like '%"+name+"%'";
		
		List<Map<String ,Object>> reList =template.queryForList(sql);
		return Integer.parseInt(reList.get(0).get("cr").toString());
	}
	/**
	 * 封装了get方法
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getTableListPager(String name,int startIndex,int length){
		

		String sql="select * from  food  where foodname like '%"+name+"%' limit "+startIndex+","+length;		
		List<Map<String, Object>> result = template.queryForList(sql);
		
		return result;
	
	}
	/**
	 * 添加菜品
	 */
	public void saveFood(String foodName,String price,String imagePath) throws Exception{
	
		String sql="insert into Food values((select IFNULL(max(foodid),0)+1 from food f),'"+foodName+"','"+price+"','"+imagePath+"')";
		template.execute(sql);
		
	}
	/**
	 * 删除菜品
	 */
	public void deleteFood(String foodid) throws Exception{
		String sql="delete from food where foodid="+foodid;
		template.execute(sql);
		
	}
	/**
	 * 修改菜品
	 */
	public void updateFood(String foodid,String foodName,String price,String path) throws Exception{
	
		String sql="update food set foodname='"+foodName+"',price='"+price+"',imagePath='"+path+"' where foodId="+foodid;
		template.execute(sql);
		
	}
	/**
	 * 查看详细菜品
	 */
	public Map<String, Object> findById(String foodid) {
		
		String sql="select * from  food  where foodid="+foodid;	
		List<Map<String, Object>> result = template.queryForList(sql);
		return result.get(0);
	}
}
