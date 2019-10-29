package com.backlink.service;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.backlink.entities.Mailer;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
@Service
public class MailService {
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private TemplateEngine templateEngine;

	private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(20);

	public void sendEmailRecover(Mailer mailer, String pass) {

		Context context = new Context();
		context.setVariable("content", "Mật khẩu của bạn là: " + pass);
		String body = templateEngine.process("recover", context);

		MimeMessage mail = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;

		SendGrid sg = new SendGrid(System.getenv("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"));
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(
					"{\"personalizations\":[{\"to\":[{\"email\":\""+mailer.getSendTo()[0]+"\"}],\"subject\":\""+mailer.getSubject()+"\"}],\"from\":{\"email\":\"no-reply@h2team.vn\"},\"content\":[{\"type\":\"text/html\",\"value\": \""+body+"\"}]}");
			
				sg.api(request);
//			helper = new MimeMessageHelper(mail, true);
//			helper.setTo(mailer.getSendTo()[0]);
//			helper.setSubject(mailer.getSubject());
//			helper.setText(body, true);
//			quickService.submit(new Runnable() {
//				@Override
//				public void run() {
//					javaMailSender.send(mail);
//				}
//			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
