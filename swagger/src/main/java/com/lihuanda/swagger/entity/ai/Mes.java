package com.lihuanda.swagger.entity.ai;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;

@Data
@TableName("LY_DMXY")
public class Mes
{

	@TableId("aid")
	private String id;

	@TableField("wznr")
	private String content;

	@TableField("wjmc")
	private String voiceFileName;

	@TableField("scsj")
	private String requestTime;

	@TableField("sbjg")
	private String isRecognize;

	@TableField("clzt")
	private String status;

	public Mes()
	{
		super();
	}

	public Mes(String id, String content, String voiceFileName, String requestTime, String isRecognize, String status)
	{
		super();
		this.id = id;
		this.content = content;
		this.voiceFileName = voiceFileName;
		this.requestTime = requestTime;
		this.isRecognize = isRecognize;
		this.status = status;
	}

}
