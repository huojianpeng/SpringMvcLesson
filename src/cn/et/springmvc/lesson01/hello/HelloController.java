package cn.et.springmvc.lesson01.hello;


import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 
 * springmvc中一个路径和方法的映射叫做一个action(动作)
 * 降耦合不能用servlet里的，用自带的
 * @author Administrator
 *
 */

@Controller
public class HelloController {
	
	@RequestMapping("/test")
	public String index(ServletRequest request,ServletResponse response) throws IOException{
		response.getWriter().println("hello springmvc="+request.getParameter("id"));
		return null;
	}
	@RequestMapping("/param")
	public String param(User user,ServletRequest request,ServletResponse response) throws IOException{
		response.getWriter().println(user.getId()+"==="+user.getName());
		return null;
	}
	@RequestMapping("/m")
	public String mvc(ServletRequest request) throws IOException{
		request.setAttribute("name", "df");
		return "/index.jsp";
	}
}
