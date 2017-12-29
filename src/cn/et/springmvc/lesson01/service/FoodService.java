package cn.et.springmvc.lesson01.service;

import java.util.Map;

import cn.et.springmvc.lesson01.utils.PageTools;

public interface FoodService {
	/**
	 * 封装了get方法
	 * @return
	 * @throws Exception
	 */
	public PageTools getTableListPager(String name,int curPage) 
			throws Exception;

	/**
	 * 添加菜品
	 * @param deskName
	 * @throws Exception
	 */
	public abstract void saveFood(String foodName, String price,String imagePath) throws Exception;

	/**
	 * 刪除菜品
	 * @param deskId
	 * @return 
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
	 * 查看详细信息
	 * @param foodid
	 * @return
	 */
	public Map<String, Object> findById(String foodid);
}