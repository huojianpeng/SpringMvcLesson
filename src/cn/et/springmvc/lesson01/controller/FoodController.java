package cn.et.springmvc.lesson01.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import cn.et.springmvc.lesson01.service.FoodService;
import cn.et.springmvc.lesson01.utils.PageTools;
/**
 * 控制层
 * @author Administrator
 *
 */
@Controller
public class FoodController {
	@Autowired
	FoodService service;
	
	/**
	 * 查询菜品
	 * @param dname
	 * @param curPage
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/showFood",method=RequestMethod.GET)
	public String queryFood(String dname,Integer curPage,Model model) throws Exception{
		if(curPage == null){
			curPage=1;
		}
		PageTools tableListPager = service.getTableListPager(dname,curPage);
		//request.setAttribute("","") 和model.addAttribute("",“”) 一样的形式;
	
		model.addAttribute("foodList",tableListPager);
		return "/detail/foodList.jsp";
	}
	/**
	 * 查看详细信息
	 * @param foodid
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/food/{foodid}",method=RequestMethod.GET)
	public String findById(@PathVariable String foodid,Model model) throws Exception{
		model.addAttribute("myFood", service.findById(foodid));
		return "/detail/detailFood.ftl";
	}
	
	/**
	 * 添加菜品
	 * @param foodName
	 * @param price
	 * @param imageUrl
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/food",method=RequestMethod.POST)
	public String  addFood(String foodName,String price,MultipartFile imageUrl,Model model) throws Exception{
		
		//获取文件名
		String fileName=imageUrl.getOriginalFilename();
		//获取绝对路径
		String absPath="D:\\Myeclipse案例\\.metadata\\.me_tcat\\webapps\\SpringMvcLesson\\images";
		imageUrl.transferTo(new File(absPath+"\\"+fileName));
		service.saveFood(foodName, price, fileName);
		
		return queryFood(foodName, 1, model);
		
	}
	
	/**
	 * 删除茶品
	 * @param fid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/food",method=RequestMethod.GET)
	public String deleteFoodID(String foodId) throws Exception{
		service.deleteFood(foodId);

		return "/showFood";
	}
	
	/**
	 * 修改菜品
	 * @param foodId
	 * @param foodName
	 * @param price
	 * @param imageUrl
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/food/{foodId}",method=RequestMethod.POST)
	public String updateFood(@PathVariable String foodId,String foodName,String price,MultipartFile imageUrl,Model model) throws Exception{
		//获取文件名
		String fileName=imageUrl.getOriginalFilename();
		//获取绝对路径
		String absPath="D:\\Myeclipse案例\\.metadata\\.me_tcat\\webapps\\SpringMvcLesson\\images";
		imageUrl.transferTo(new File(absPath+"\\"+fileName));
		service.updateFood(foodId, foodName, price,fileName);
		return queryFood(null,1,model);
	}
	
	/**
	 * 下载图片
	 * @param imagePath
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/download",method=RequestMethod.GET)
	public ResponseEntity<byte[]>  downloadFile(String imagePath) throws IOException{
		
		String absPath="D:\\Myeclipse案例\\.metadata\\.me_tcat\\webapps\\SpringMvcLesson\\images"+imagePath;
		
		String fileName=imagePath;
		//需要下载的目标文件
		File file=new File(absPath);
		//设置响应头
		HttpHeaders hh=new HttpHeaders();
		//设置下载的文件的名称
		hh.setContentDispositionFormData("attachment", URLEncoder.encode(fileName,"UTF-8"));
		//读取目标文件为二进制数组
		byte[] fileByte=FileCopyUtils.copyToByteArray(file);
		//构建ResponseEntity对象
		ResponseEntity<byte[]> re = new ResponseEntity<byte[]>(fileByte,hh,HttpStatus.CREATED);
		return re;
	}
}
