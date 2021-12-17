package GGTStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SendMailController {

	@Autowired
	JavaMailSender javaMailSender;

	@RequestMapping("/sendMail")
	public String form() {
		return "layout/_feedback";
	}

	@RequestMapping("/success")
	public String s() {
		return "layout/_mail-success";
	}

	@RequestMapping("/sendMail/success")
	public String success(@RequestParam("to") String to, @RequestParam("subject") String subject,
			@RequestParam("content") String content) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("ngoncnp01@gmail.com");
		msg.setSubject(subject);
		msg.setText(content);
		javaMailSender.send(msg);
		System.out.println("ok");

		return "layout/_mail-success";
	}

	@RequestMapping("/sendMail/blackFriday")
	public String backFriday() {
		SimpleMailMessage msg = new SimpleMailMessage();
		String link = "https://countdown.onlinealarmkur.com/vi/";
		String content = "<p>Xin chào,</p>" + "<p>Cảm ơn bạn đã quan tâm đêns chương trình của cửa hàng chúng tôi !</p>"
				+ "<p>Vui lòng bấm vào đường link bên dưới để biết thêm thông tin chi tiết thời gian lễ hội mua sắm sắp tới</p>"
				+ "<p><a href=\"" + link + "\">BLACK FRIDAY</a></p>";
		msg.setTo("nvngon2604@gmail.com", "ngoncnp01@gmail.com", "ngonnvpd04149@fpt.edu.vn");
		msg.setSubject("GGT-STORE Black Friday");
		msg.setText(
				"Bạn đã đăng kí lịch thông báo chương trình Black Friday thành công ! (Bấm để xem chi tiết: https://countdown.onlinealarmkur.com/vi/)");
		javaMailSender.send(msg);
		System.out.println("ok");

		return "layout/_blackFriday";
	}

}
