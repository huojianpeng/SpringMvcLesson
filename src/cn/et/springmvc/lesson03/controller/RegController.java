package cn.et.springmvc.lesson03.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.et.springmvc.lesson03.entity.UserInfo;

/**
 * 后台验证的步骤
 *    1.javabean添加验证注解
 *    2.action中使用@Vaild表示javabean使用Errors或者BindingResult判断是否验证失败
 *    3.出现jar包冲突4.3.2
 * @author THINK
 *
 */
@Controller
public class RegController {

	@RequestMapping(value="/reg",method=RequestMethod.POST)
	public String reg(@Valid UserInfo user,BindingResult error) throws Exception{
		if(!user.getPassword().equals(user.getRepassword())){
			error.addError(new FieldError("userInfo", "repassword", "两次密码不一致"));
		}
		/*if(user.getAge()==null || "".equals(user.getAge().trim())){
			error.addError(new FieldError("userInfo", "age", "年龄不能为空"));
		}else {
			Integer age;
			try {
				age = Integer.parseInt(user.getAge());
				if(age<1 || age>100){
					error.addError(new FieldError("userInfo", "age", "年龄必须在1-100之间"));
				}
			} catch (Exception e) {
				error.addError(new FieldError("userInfo", "age", "年龄必须是数字"));
			}
			
		}*/
		
		if(error.hasErrors()){
			return "/lesson03/reg.jsp";
		}
		return "/lesson03/suc.jsp";
	}
}
