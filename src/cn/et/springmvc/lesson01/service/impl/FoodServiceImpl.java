package cn.et.springmvc.lesson01.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.springmvc.lesson01.dao.FoodDao;
import cn.et.springmvc.lesson01.service.FoodService;
import cn.et.springmvc.lesson01.utils.PageTools;
/**
 * 业务逻辑处理层
 * @author Administrator
 *
 */
@Service
public class FoodServiceImpl implements FoodService{
	@Autowired
	FoodDao dao;
	/**
	 * 封装了get方法
	 */
	public PageTools getTableListPager(String name,int curPage) throws Exception{
		if(name==null){
			name="";
		}
		int totalCount = dao.getTableListCountInteger(name);
		PageTools pt = new PageTools(curPage, totalCount, null);
		List<Map<String, Object>> tableListPager = dao.getTableListPager(name, pt.getStartIndex()-1, pt.getPageCount());
		
		pt.setData(tableListPager);
		return pt;
	
	}
	/**
	 * 添加信息
	 */
	public void saveFood(String foodName,String price,String imagePath) throws Exception{

		dao.saveFood(foodName, price, imagePath);
		
	}
	/**
	 * 删除信息
	 */
	public void deleteFood(String foodid) throws Exception{
		
		dao.deleteFood(foodid);
		
	}
	/**
	 * 修改信息
	 */
	public void updateFood(String foodid,String foodName,String price,String path) throws Exception{
	
		dao.updateFood(foodid, foodName, price, path);
		
	}
	/**
	 * 查看详细信息
	 */
	public Map<String, Object> findById(String foodid) {
		return dao.findById(foodid);
	}
}
