package org.farmlei.smail.testweb.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.reger.dubbo.annotation.Inject;
import org.farmlei.smail.provider.IMailSendService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过http方式测试邮件发送接口
 */
@Controller
@RequestMapping("test")
public class TestController {
    @Inject
    private IMailSendService mailSendService;

    /**
     * 邮件发送测试
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping("/send")
    public Map send() {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            mailSendService.send(null, "yuer5531@163.com", "测试邮件主题", "测试邮件内容");
            resultMap.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "failed");
            resultMap.put("error", e.getMessage());
        }
        return resultMap;
    }

    /**
     * 邮件发送测试
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping("/batchSend")
    public Map batchSend() {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            for (int i = 0; i < 100; i++) {
                mailSendService.send(null, "yuer5531@163.com", "测试邮件主题" + i,
                        "测试邮件内容");
            }
            resultMap.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "failed");
            resultMap.put("error", e.getMessage());
        }

        return resultMap;
    }
}
