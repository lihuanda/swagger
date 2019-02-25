package com.lihuanda.swagger.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Class Name: UserDTO  
 * Description: 
 * 	数据传输，用来接收参数
 * @date: 2018年1月23日
 * @version: 1.0
 *
 */
@Data
@ApiModel(value="医生对象模型")
public class UserDTO {
	@ApiModelProperty(value = "userId",required = false)
	private String userId;

	@ApiModelProperty(value = "userName",required = false)
	private String userName;

	@ApiModelProperty(value = "fullName",required = false)
	private String fullName;

	@ApiModelProperty(value = "email",required = false)
	private String email;

	@ApiModelProperty(value = "mobile",required = false)
	private String mobile;

	@ApiModelProperty(value = "status",required = false)
	private String status;

	@ApiModelProperty(value = "pageNum",required = false)
	private Integer pageNum;

	public UserDTO() {
		super();
	}

	public UserDTO(String userName, String fullName, String email) {
		super();
		this.userName = userName;
		this.fullName = fullName;
		this.email = email;
	}


}
