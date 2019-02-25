package com.lihuanda.swagger.controller.ai;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lihuanda.swagger.util.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.plugins.Page;
import com.lihuanda.swagger.config.R;
import com.lihuanda.swagger.dto.ai.MesDTO;
import com.lihuanda.swagger.service.ai.MesService;
import com.lihuanda.swagger.util.FileUtil;
import com.lihuanda.swagger.util.SystemOS;
import com.lihuanda.swagger.vo.ai.MesVO;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Class Name: UserController Description: 控制器
 * 
 * @date: 2018年1月23日
 * @version: 1.0
 *
 */
@RestController
@RequestMapping("/aiMessage")
@Api(value = "ai_message_manage", tags = "ai_message_manage")
public class MesController
{

	private static final Logger logger = LoggerFactory.getLogger(MesController.class);

	@Autowired
	private MesService mesService;

	@Value("${file.PathWindows}")
	private String fileWindows;

	@Value("${file.PathLinux}")
	private String fileLinux;

	@ApiOperation(value = "查询ai消息", notes = "查询ai消息列表,返回全部记录的详细数据", httpMethod = "GET")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "id", dataType = "string", required = false, value = "记录唯一ID，系统自动生成"),
			@ApiImplicitParam(paramType = "query", name = "content", dataType = "string", required = false, value = "语音的文字内容"),
			@ApiImplicitParam(paramType = "query", name = "voiceFileName", dataType = "string", required = false, value = "对应的语音文件名"),
			@ApiImplicitParam(paramType = "query", name = "requestTime", dataType = "string", required = false, value = "记录生成时间，时间由后台系统自动生成"),
			@ApiImplicitParam(paramType = "query", name = "isRecognize", dataType = "string", required = false, value = "识别结果，0：未识别，1：已识别"),
			@ApiImplicitParam(paramType = "query", name = "status", dataType = "string", required = false, value = "语音被后台人员手动处理的状态，0：未处理，1：已处理，2：已收集。通过修改状态的接口进行修改。") })
	@GetMapping("/list")
	@ResponseBody

	public JsonResult<Page<MesVO>> query(@RequestBody(required = false) MesDTO param)
	{
		try
		{
			// MesDTO param=new MesDTO();
			Page page = new Page(1, 10);
			Page<MesVO> mes = mesService.query(page, param);
			if (mes != null)
			{
				return JsonResult.success(mes);
			} else
			{
				return JsonResult.failure(R.queryFailure);
			}
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			return JsonResult.failure(R.queryFailure);
		}
	}

	@ApiOperation(value = "识别语音文件,添加ai消息记录", notes = "通过识别语音的文字内容，插入ai消息记录", httpMethod = "GET")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "content", dataType = "string", required = false, value = "语音的文字内容"),
			@ApiImplicitParam(paramType = "query", name = "voiceFileName", dataType = "string", required = false, value = "对应的语音文件名"),
			@ApiImplicitParam(paramType = "query", name = "isRecognize", dataType = "string", required = false, value = "识别结果，0：未识别，1：已识别") })

	public JsonResult insert(HttpServletRequest request)
	{
		try
		{
			String content = request.getParameter("content");
			String voiceFileName = request.getParameter("voiceFileName");
			String isRecognize = request.getParameter("isRecognize");
			MesDTO param = new MesDTO();
			param.setContent(content);
			param.setVoiceFileName(voiceFileName);
			param.setRequestTime(isRecognize);

			if (mesService.insert(param))
			{
				return JsonResult.success(param);
			} else
			{
				return JsonResult.failure(R.insertFailure);
			}
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			return JsonResult.failure(R.insertFailure);
		}
	}

	@ApiOperation(value = "修改ai消息状态", notes = "通过id编号，修改状态值", httpMethod = "GET")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "id", dataType = "string", required = false, value = "记录唯一ID，系统自动生成"),
			@ApiImplicitParam(paramType = "query", name = "status", dataType = "string", required = false, value = "语音被后台人员手动处理的状态，0：未处理，1：已处理，2：已收集。通过修改状态的接口进行修改。") })
	@GetMapping("/changeStatus")
	public JsonResult update(HttpServletRequest request)
	{
		try
		{
			String id = request.getParameter("id");
			String status = request.getParameter("status");
			MesDTO param = new MesDTO();
			param.setId(id);
			param.setStatus(status);
			if (mesService.update(param))
			{
				return JsonResult.success(param);
			} else
			{
				return JsonResult.failure(R.insertFailure);
			}
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			return JsonResult.failure(R.insertFailure);
		}
	}

	/**
	 * 文件上传
	 * 
	 * @param file
	 * @return
	 */
	@ApiOperation(value = "上传语音消息", notes = "上传语音文件到服务器路径然后将文件记录保存到数据库", httpMethod = "POST")
	@PostMapping("/upload")
	@ResponseBody
	public JsonResult upload(@RequestParam("file") MultipartFile file)
	{
		try
		{
			// String contentType = file.getContentType();
			// 上传的文件名
			String uploadFilePath = file.getOriginalFilename();
			// 截取上传文件的后缀
			String uploadFileSuffix = uploadFilePath.substring(uploadFilePath.indexOf('.'), uploadFilePath.length());
			// 给上传文件名加密
			String fileName = UUID.randomUUID().toString() + uploadFileSuffix;
			// fname = fileName;
			// 保存在内嵌tomcat根路径/upload/
			// String filePath =
			// request.getSession().getServletContext().getRealPath("upload/");
			String filePath = null;

			boolean isWindows = SystemOS.isWindowsOS(); // 判断是否是windows系统
			if (isWindows)
			{
				filePath = fileWindows + "/upload/";
			} else
			{
				filePath = fileLinux + "/upload/";
			}

			System.out.println("filePath=" + filePath);

			try
			{
				FileUtil.fileupload(file.getBytes(), filePath, fileName);
			} catch (Exception e)
			{
				// TODO: handle exception
			}
			// 识别语音内容
			MesDTO param = new MesDTO();
			param.setContent("123456hhh");
			param.setIsRecognize("0");
			param.setVoiceFileName(fileName);
			param.setStatus("0");
			param.setRequestTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			// param.setId("1");
			if (mesService.insert(param))
			{
				return JsonResult.success(param);
			} else
			{
				return JsonResult.failure(R.insertFailure);
			}

		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			return JsonResult.failure(R.insertFailure);
		}
	}

	/**
	 * 文件下载
	 * 
	 * @return
	 */
	@GetMapping("/download/{fname:.+}")
	@ResponseBody
	public String downFile(HttpServletRequest request, HttpServletResponse response, @PathVariable String fname)
	{
		String isDownload = "error";
		if (fname != null)
		{
			// 获取当前路径下的文件
			// String realPath =
			// request.getServletContext().getRealPath("/upload/") + fname;
			String realPath = null;
			boolean isWindows = SystemOS.isWindowsOS(); // 判断是否是windows系统
			if (isWindows)
			{
				realPath = fileWindows + "/upload/" + fname;
			} else
			{
				realPath = fileLinux + "/upload/" + fname;
			}

			System.out.println("realPath=" + realPath);
			File file = new File(realPath);
			if (file.exists())
			{
				// 设置强制下载
				response.setContentType("application/x-msdownload");
				// 设置文件名
				response.addHeader("Content-Disposition", "attachment;fileName=" + fname);
				byte[] bytes = new byte[1024];
				FileInputStream fis = null;
				BufferedInputStream bis = null;

				try
				{
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					OutputStream os = response.getOutputStream();
					int b = bis.read(bytes);
					while (b != -1)
					{
						os.write(bytes, 0, b);
						b = bis.read(bytes);
					}
					os.close();
					isDownload = "success";
				} catch (Exception e)
				{
					e.printStackTrace();
				} finally
				{
					if (bis != null)
					{
						try
						{
							bis.close();
						} catch (IOException e)
						{
							e.printStackTrace();
						}
					}
					if (fis != null)
					{
						try
						{
							fis.close();
						} catch (IOException e)
						{
							e.printStackTrace();
						}
					}
				}
			}
		}
		return isDownload;
	}

	/**
	 * 一个测试入口
	 * 
	 * @return
	 */
	@GetMapping("/hello")
	@ResponseBody
	public JsonResult<String> helloOne()
	{
		return JsonResult.success("hello world");
	}

}
