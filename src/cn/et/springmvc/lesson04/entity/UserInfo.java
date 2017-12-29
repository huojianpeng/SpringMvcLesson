package cn.et.springmvc.lesson04.entity;



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
	@NotEmpty(message="{userError}")
	@Length(min=5, max=20, message="{userLength}")  
	private String userName;
	@NotEmpty(message="{passwordError}")
	private String password;
	@NotEmpty(message="{repasswordError}")
	//lixin@123.com
	private String repassword;
	@Pattern(message="{emailFError}",regexp=".+@.+\\..+")
	@NotEmpty(message="{emailError}")
	private String email;
	@Max(value=100,message="{ageSmall}")
	@Min(value=1,message="{ageBig}")
	@NotEmpty(message="{ageError}")
	private String age;
	@Size(min=11,max=11,message="{phoneError}")
	private String phone;
	@NotEmpty(message="{timeError}")
	@Pattern(message="{timeFormt}",regexp="\\d{4}-\\d{2}-\\d{2}")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String time;
	@Pattern(message="{urlFormt}",regexp="[a-zA-z]+://[^s]*")
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
