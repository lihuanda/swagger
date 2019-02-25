package com.lihuanda.swagger.controller.rabbitmq;


import com.lihuanda.swagger.response.BaseResponse;
import com.lihuanda.swagger.response.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Administrator on 2018/9/1.
 */
@RestController
@Api(value="MailRabbitDemo", tags="MailRabbitDemo")
public class MailController {
    private static final Logger log= LoggerFactory.getLogger(MailController.class);

    private static final String Prefix="mail";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment env;

    @ApiOperation(value="Rabbit异步发送邮件", notes="字符串", httpMethod = "GET")
    @RequestMapping(value = Prefix+"/send",method = RequestMethod.GET)
    public BaseResponse sendMail(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            rabbitTemplate.setExchange(env.getProperty("mail.exchange.name"));
            rabbitTemplate.setRoutingKey(env.getProperty("mail.routing.key.name"));
            rabbitTemplate.convertAndSend(MessageBuilder.withBody("mail发送".getBytes("UTF-8")).build());

        }catch (Exception e){
            e.printStackTrace();
        }

        log.info("邮件发送完毕----");
        return response;
    }
}























