package com.backlink.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.backlink.entities.Mailer;

@Service
public class MailService {
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
    private TemplateEngine templateEngine;
	
	public void sendEmailRecover(Mailer mailer, String pass) {
		
		Context context = new Context();		
		context.setVariable("content", "Mật khẩu của bạn là: "+ pass);		
		String body = templateEngine.process("recover", context);
		
		MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mail, true);
	        helper.setTo(mailer.getSendTo()[0]);
	        helper.setSubject(mailer.getSubject());
	        helper.setText(body, true);
	        javaMailSender.send(mail);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
