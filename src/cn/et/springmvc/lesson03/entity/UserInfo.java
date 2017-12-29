package cn.et.springmvc.lesson03.entity;



import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class UserInfo {

	/**
	 * NotNull 属性名!=null
	 * NotEmpty 属性名!=null && !属性名.equals("")
	 */
	@NotEmpty(message="用户名不能为空")
	@Length(min=5, max=20, message="用户名长度必须在5-20之间")  
	private String userName;
	@NotEmpty(message="密码不能为空")
	private String password;
	@NotEmpty(message="再次输入不能为空")
	//lixin@123.com
	private String repassword;
	@Pattern(message="邮箱格式错误",regexp=".+@.+\\..+")
	@NotEmpty(message="邮箱不能为空")
	private String email;
	@Max(value=100,message="年龄必须小于100")
	@Min(value=1,message="年龄必须大于1")
	@NotEmpty(message="年龄不能为空")
	private String age;
	@Size(min=11,max=11,message="手机号码必须是11位数")
	private String phone;
	@NotEmpty(message="日期不能为空")
	@Pattern(message="日期格式错误",regexp="\\d{4}-\\d{2}-\\d{2}")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String time;
	@Pattern(message="网址格式错误",regexp="[a-zA-z]+://[^s]*")
	private String url;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
