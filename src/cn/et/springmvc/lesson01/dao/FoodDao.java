package cn.et.springmvc.lesson01.dao;

import java.util.List;
import java.util.Map;

public interface FoodDao {
	public Integer getTableListCountInteger(String name);
	/**
	 * 封装了get方法
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getTableListPager(String name,int startIndex,int length)throws Exception;

	/**
	 * 添加菜品
	 * @param deskName
	 * @throws Exception
	 */
	public abstract void saveFood(String foodName, String price,String imagePath) throws Exception;

	/**
	 * 刪除菜品
	 * @param deskId
	 * @throws Exception
	 */
	public abstract void deleteFood(String foodid) throws Exception;

	/**
	 * 修改菜品
	 * @param typeId
	 * @param typeName
	 * @throws Exception
	 */
	public abstract void updateFood(String foodid, String foodName,String price, String path) throws Exception;
	/**
	 * 查看详细菜品
	 * @param foodid
	 * @return
	 */
	public Map<String, Object> findById(String foodid);

}