package cn.et.springmvc.lesson01.hello;

import java.io.IOException;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * springmvc中一个路径和方法的映射叫做一个action(动作)
 	降耦合不能用servlet里的，用自带的
 * 浏览器的提交方式必须和@RequestMapping指定的资源动作必须一致的
 * 	抛出异常405(表示请求的方式不匹配)
 * user资源可以支持四个操作get,post,put,delete 而对于同一个资源只支持三个操作get,put,delete
 * @author Administrator
 *
 */
@Controller
public class RestController {
	/**
	 *user/2
	 *user/3
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	public String index(@PathVariable(value="id") String  userId,HttpServletResponse response) throws IOException{
		response.getWriter().println("<table></table>");
		return "/lesson01/user.jsp";
	}
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public String addUser(String name,ServletResponse response) throws IOException{
		response.getWriter().println(name+"-----");
		return null;
	}
	@RequestMapping(value="/user/{id}",method=RequestMethod.PUT)
	public String updateUser(@PathVariable(value="id") String  userId,String name,HttpServletResponse response) throws IOException{
		response.getWriter().println(userId+name+"-----");
		return null;
	}
	@RequestMapping(value="/user/{id}",method=RequestMethod.DELETE)
	public String deleteUser(@PathVariable(value="id") String  userId,HttpServletResponse response) throws IOException{
		response.getWriter().println(userId+"-----"+"delete");
		return null;
	}
}
