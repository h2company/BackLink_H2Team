package com.backlink.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.backlink.entities.Mailer;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
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

//		MimeMessage mail = javaMailSender.createMimeMessage();
//		MimeMessageHelper helper;

		try {
			SendGrid sg = new SendGrid(
					System.getenv("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"));

			Email from = new Email("thiendaikyl@gmail.com");
			String subject = "Sending with SendGrid is Fun";
			Email to = new Email("qa1796@gmail.com");
			Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
			Mail mail = new Mail(from, subject, to, content);

			Request request = new Request();
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
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
