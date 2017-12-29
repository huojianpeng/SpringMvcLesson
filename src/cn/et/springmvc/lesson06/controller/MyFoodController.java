package cn.et.springmvc.lesson06.controller;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;



import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.et.springmvc.lesson06.dao.MyFoodDaoImpl;

/**
 * 后台验证的步骤 1.javabean添加验证注解 2.action中使用@Vaild表示javabean使用Errors或者BindingResult判断是否验证失败
 * 3.出现jar包冲突4.3.2
 * 
 * @author THINK
 * 
 */
@Controller
public class MyFoodController {

	/**
	 * 原始的输出json方式
	 * OutputStream os
	 * os.write（通过第三方json-lib转换的json字符串，getByte()）
	 * 
	 * 查询food
	 */
	@Autowired
	MyFoodDaoImpl mdi;

	@RequestMapping(value = "/queryFood", method = {RequestMethod.GET,RequestMethod.POST})
	public String queryFood(String foodname, OutputStream os) throws Exception {
		List<Map<String, Object>> queryAllFood=mdi.queryAllFood(foodname);
		JSONArray array=JSONArray.fromObject(queryAllFood);
		String jsonStr=array.toString();
		os.write(jsonStr.getBytes("UTF-8"));
		return null;
	}
	
	/**
	 * 直接返回字符数组+@ResponseBody
	 * 减少流的输出动作
	 * os.write(jsonStr.getBytes("UTF-8"));
	 * @param foodname
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/queryFoodReturn", method = {RequestMethod.GET})
	public byte[] queryFoodReturn(String foodname) throws Exception {
		List<Map<String, Object>> queryAllFood=mdi.queryAllFood(foodname);
		JSONArray array=JSONArray.fromObject(queryAllFood);
		String jsonStr=array.toString();
		return jsonStr.getBytes("UTF-8");
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/queryFoodList", method = {RequestMethod.GET})
	public List<Map<String, Object>> queryFoodList(String foodname) throws Exception {
		List<Map<String, Object>> queryAllFood=mdi.queryAllFood(foodname);
		return queryAllFood;
	}
	
	
	/**
	 * 删除food
	 * @param foodId
	 * @param os
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/food/{foodId}", method = {RequestMethod.DELETE})
	public String deleteFood(@PathVariable String foodId, OutputStream os) throws Exception {
		try {
			mdi.deleteFood(foodId);
			os.write("1".getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			os.write("0".getBytes("UTF-8"));
		}
		
		return null;
	}
	
	/**
	 * 修改food
	 * @param foodId
	 * @param os
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/food/{foodId}", method = {RequestMethod.PUT})
	public String updateFood(@PathVariable String foodId,String foodName,String price, OutputStream os) throws Exception {
		try {
			mdi.updateFood(foodId,foodName,price);
			os.write("1".getBytes("UTF-8"));
		} catch (Exception e) {
			os.write("0".getBytes("UTF-8"));
		}
		
		return null;
	}
	
	/**
	 * 新增菜品
	 * @param foodName
	 * @param price
	 * @param os
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/food", method = {RequestMethod.POST})
	public String saveFood(String foodName,String price, OutputStream os) throws Exception {
		try {
			mdi.saveFood(foodName,price);
			os.write("1".getBytes("UTF-8"));
		} catch (Exception e) {
			os.write("0".getBytes("UTF-8"));
		}
		
		return null;
	}

}
