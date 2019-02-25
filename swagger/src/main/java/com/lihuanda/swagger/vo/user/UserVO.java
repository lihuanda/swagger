package com.lihuanda.swagger.vo.user;

/** 
 * Class Name: UserVo  
 * Description: 
 * 用作返回数据
 * @date: 2018年1月23日
 * @version: 1.0
 *
 */

import lombok.Data;

@Data
public class UserVO {

	private String userId;
	
	private String userName;
	
	private String fullName;
	
	private String email;
	
	private String telephone;
	
	private String status;

	public UserVO() {
		super();
	}

	public UserVO(String userId, String userName, String fullName, String email, String telephone, String status) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.fullName = fullName;
		this.email = email;
		this.telephone = telephone;
		this.status = status;
	}

}
  