package cn.et.springmvc.lesson05.controller;

import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.et.springmvc.lesson05.dao.impl.MoneyDaoImpl;

/**
 * 后台验证的步骤 1.javabean添加验证注解 2.action中使用@Vaild表示javabean使用Errors或者BindingResult判断是否验证失败
 * 3.出现jar包冲突4.3.2
 * 
 * @author THINK
 * 
 */
@Controller
public class InterController {

	@RequestMapping(value = "/inter", method = RequestMethod.GET)
	public String reg(OutputStream os) throws Exception {
		os.write("hello".getBytes());
		return null;
	}

	@Autowired
	MoneyDaoImpl mdi;

	@RequestMapping(value = "/tm", method = RequestMethod.GET)
	public String reg(Integer money, OutputStream os) throws Exception {
		mdi.trasnateMoney(money);
		os.write(("lostedmoney is:" + mdi.selectMoney()).getBytes());
		return null;
	}

}
