package com.lihuanda.swagger.dto.ai;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;


/**
 * Class Name: MesDTO Description: 数据传输，用来接收参数
 * 
 * @date: 2018年1月23日
 * @version: 1.0
 *
 */
@Entity
@Data
public class MesDTO
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String id;
	private String content;
	private String voiceFileName;
	private String requestTime;
	private String isRecognize;
	private String status;
	private Integer pageNum;

	public MesDTO()
	{
		super();
	}

	public MesDTO(String id, String content, String voiceFileName, String requestTime, String isRecognize, String status, Integer pageNum)
	{
		super();
		this.id = id;
		this.content = content;
		this.voiceFileName = voiceFileName;
		this.requestTime = requestTime;
		this.isRecognize = isRecognize;
		this.status = status;
		this.pageNum = pageNum;
	}

}
