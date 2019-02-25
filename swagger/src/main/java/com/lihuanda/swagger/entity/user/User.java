package com.lihuanda.swagger.entity.user;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;


/** 
 * Class Name: UserEntity  
 * Description: 
 * 实体对象
 * @date: 2018年1月23日
 * @version: 1.0
 *
 */  

@TableName("LY_XTGL_YHB")
public class User {
	
	@TableId("yhbh")
	private String userId;
	
	@TableField("yhzh")
	private String userName;

	@TableField("yhmc")
	private String fullName;

	@TableField("cjsj")
	private String email;

	@TableField("yhlx")
	private String mobile;

	@TableField("zt")
	private String status;

	public User() {
		super();
	}
	
	public User(String userId, String userName, String fullName, String email, String mobile, String status) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.fullName = fullName;
		this.email = email;
		this.mobile = mobile;
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
