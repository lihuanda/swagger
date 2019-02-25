package com.lihuanda.swagger.vo.ai;

/**
 * Class Name: AiMessageVO Description: 用作返回数据
 * 
 * @date: 2018年1月23日
 * @version: 1.0
 *
 */
public class MesVO
{

	private String id;
	private String content;
	private String voiceFileName;
	private String requestTime;
	private String isRecognize;
	private String status;

	public MesVO()
	{
		super();
	}

	public MesVO(String id, String content, String voiceFileName, String requestTime, String isRecognize, String status)
	{
		super();
		this.id = id;
		this.content = content;
		this.voiceFileName = voiceFileName;
		this.requestTime = requestTime;
		this.isRecognize = isRecognize;
		this.status = status;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getVoiceFileName()
	{
		return voiceFileName;
	}

	public void setVoiceFileName(String voiceFileName)
	{
		this.voiceFileName = voiceFileName;
	}

	public String getRequestTime()
	{
		return requestTime;
	}

	public void setRequestTime(String requestTime)
	{
		this.requestTime = requestTime;
	}

	public String getIsRecognize()
	{
		return isRecognize;
	}

	public void setIsRecognize(String isRecognize)
	{
		this.isRecognize = isRecognize;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

}
