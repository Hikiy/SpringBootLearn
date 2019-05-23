package com.hiki.springbootlearn.mail;

import com.hiki.springbootlearn.util.MailUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMail {
    @Autowired
    private MailUtil mailUtil;
    @Autowired
    private TemplateEngine templateEngine;

    @Test
    @Ignore
    public void testSendMail(){
        String to = "695954591@qq.com";
        String subject = "来自Hiki的邮件哦！";
        String content = "一路走来累了吧，别放弃";
        mailUtil.sendMail(to,subject,content);
    }

    @Test
    @Ignore
    public void sendHTMLMail() {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>来自Hiki的HTML邮件哦！</h3>\n" +
                "    <a href=\"https://github.com/Hikiy\">Hiki的Github</a>\n"+
                "    <p>一路走来累了吧，别放弃</p>"+
                "</body>\n" +
                "</html>";
        mailUtil.sendHtmlMail("695954591@qq.com","来自Hiki的HTML邮件哦！",content);
    }

    @Test
    @Ignore
    public void sendAttachmentsMail() {
        String filePath="e:\\mail\\test.txt";
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>来自Hiki的附件邮件哦！</h3>\n" +
                "    <a href=\"https://github.com/Hikiy\">Hiki的Github</a>\n"+
                "    <p>送你个文件</p>"+
                "</body>\n" +
                "</html>";
        mailUtil.sendAttachmentsMail("695954591@qq.com", "来自Hiki的HTML邮件哦！", content, filePath);
    }

    @Test
    //@Ignore
    public void sendInlineResourceMail() {
        String rscId = "flag";
        String content="<html><body>送你张图：<img src=\'cid:" + rscId + "\' >" +
                "<p>一路走来累了吧，别放弃</p>" +
                "</body></html>";
        String imgPath = "e:\\mail\\34066075.jpg";

        mailUtil.sendInlineResourceMail("695954591@qq.com", "来自Hiki的附件邮件哦！", content, imgPath, rscId);
    }

    @Test
    @Ignore
    public void sendTemplateMail() {
    //目前会报错。。暂时没找到什么原因
        Context context = new Context();
        context.setVariable("id", "SpringBootLearn");
        String emailContent = templateEngine.process("emailT", context);
        mailUtil.sendHtmlMail("695954591@qq.com","来自Hiki的HTML邮件哦！",emailContent);
    }
}
