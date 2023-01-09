package com.xwb.springcloud.controller;

import com.xwb.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Administrator
 */
@RestController
public class SendMessageController {
    @Resource
    private IMessageProvider messageProvider;

    /**
     * 发送消息
     *
     * @return 返回发送的消息返回值
     */

    @GetMapping("/sendMessage")
    public String sendMessage() {
        return messageProvider.send();
    }
}
