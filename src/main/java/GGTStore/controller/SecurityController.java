package GGTStore.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import GGTStore.dao.AccountDAO;
import GGTStore.entity.Account;
import GGTStore.entity.ChangePassword;
import GGTStore.service.AccountService;
import GGTStore.service.SendMailService;
import GGTStore.service.UserService;
import GGTStore.service.impl.AccountServiceImpl;

@Controller
public class SecurityController {
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	UserService service;
	@Autowired
	HttpSession session;
	@Autowired
	AccountService accountService;
	@Autowired
	SendMailService sendMailService;
	@Autowired
	AccountServiceImpl accountServiceImpl;
	@Autowired
	AccountDAO adao;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping("/security/login/form")
	public String loginForm(Model model) {
		model.addAttribute("message", "<b><i class=\"fas fa-info-circle\"></i> Vui lòng đăng nhập để tiếp tục</b>");
		return "security/login";
	}

	@RequestMapping("/security/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("message", "<b><i class=\"fas fa-info-circle\"></i> Đăng nhập thành công</b>");
		return "redirect:/home/index";
	}

	@Autowired
	UserService userService;

	@RequestMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oauth2) {
		userService.loginFromOAuth2(oauth2);
		return "forward:/security/login/success";
	}

	@RequestMapping("/security/login/error")
	public String loginError(Model model) {
		model.addAttribute("message",
				"<b><i class=\"fas fa-info-circle\"></i> Tên đăng nhập hoặc mật khẩu không đúng !</b>");
		return "security/login";
	}

	@RequestMapping("/security/unauthorized")
	public String unauthorized(Model model) {
		model.addAttribute("message", "<b><i class=\"fas fa-info-circle\"></i> Không có quyền truy cập</b>");
		return "security/404";
	}

	@RequestMapping("/security/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("message", "<b><i class=\"fas fa-info-circle\"></i> Đăng xuất thành công</b>");
		return "security/login";
	}

	@GetMapping("/security/register/form")
	public String add(Model model) {
		model.addAttribute("account", new Account());
		return "security/register";
	}

	@GetMapping("security/edit/{username}")
	public ModelAndView edit(ModelMap model, @PathVariable("username") String username) {
		Optional<Account> opt = accountService.findByUsername(username);
		Account dto = new Account();
		if (opt.isPresent()) {
			Account entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true); // thiet lap o che do chinh sua thong tin
			dto.setPassword("");
			model.addAttribute("account", dto);
			return new ModelAndView("security/updateProfile", model);
		}
		model.addAttribute("message", "Register is not existed");
		return new ModelAndView("forward:/security", model);
	}

	@PostMapping("security/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @Valid @ModelAttribute("account") Account dto, BindingResult result,
			@RequestParam("password") String password, @RequestParam("email") String recipientEmail)
			throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("ngonnvpd04149@fpt.edu.vn", "GGT Store");
		helper.setTo(recipientEmail);
		String subject = "Here's the link to confilm otp";

		if (result.hasErrors()) {
			return "security/register";
		}
		if (!checkEmail(dto.getEmail())) {
			model.addAttribute("error", "Email này đã được sử dụng!");
			return "security/register";
		}
		session.removeAttribute("otp");
		int random_otp = (int) Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
		session.setAttribute("otp", random_otp);
		String content = "<div>\r\n" + " <h3>Mã OTP của bạn là: <span style=\"color:red; font-weight: bold;\">"
				+ random_otp + "</span></h3>\r\n" + "    </div>";
		sendMailService.queue(dto.getEmail(), "Đăng kí tài khoản", content);
		helper.setSubject(subject);
		helper.setText(content, true);
		mailSender.send(message);
		model.addAttribute("account", dto);
		model.addAttribute("message", "Mã OTP đã được gửi tới Email, hãy kiểm tra Email của bạn!");
		return "security/confirmOtpRegister";
	}

	@PostMapping("/security/confirmOtpRegister")
	public String confirmRegister(ModelMap model, @ModelAttribute("account") Account dto,
			@RequestParam("password") String password, @RequestParam("otp") String otp) {
		if (otp.equals(String.valueOf(session.getAttribute("otp")))) {
			Account entity = new Account();
			BeanUtils.copyProperties(dto, entity);
			session.removeAttribute("otp");
			accountService.save(entity);
			model.addAttribute("message", "Đăng kí thành công");
			return "security/register";
		}
		model.addAttribute("error", "Mã OTP không đúng, hãy thử lại!");
		return "security/confirmOtpRegister";
	}

	// check email
	public boolean checkEmail(String email) {
		List<Account> list = accountService.findAll();
		for (Account c : list) {
			if (c.getEmail().equalsIgnoreCase(email)) {
				return false;
			}
		}
		return true;
	}

	// @RequestMapping("/security/register/update")
	// public String registerUpdate(Model model) {
	// return "security/profile";
	// }

	// @PostMapping("/security/register/addOrUpdate")
	// public String register(Model model, @ModelAttribute("account") AccountDto
	// dto,
	// @RequestParam("password") String password, @RequestParam("repassword") String
	// repassword) {
	// if (!password.equals(repassword)) {
	// model.addAttribute("message", "<b><i class=\"fas fa-info-circle\"></i> Mật
	// khẩu không trùng khớp !</b>");
	// System.out.println("Mật khẩu không trùng khớp !");
	// return "security/register";
	// }

	// Account entity = new Account();
	// BeanUtils.copyProperties(dto, entity);
	// model.addAttribute("message", "<b><i class=\"fas fa-info-circle\"></i> Lưu
	// tài khoản thành công !</b>");
	// accountService.save(entity);
	// System.out.println("ok");

	// return "redirect:/security/login/form";
	// }

	// @PostMapping("/security/register/Update")
	// public String update(Model model, @ModelAttribute("account") AccountDto dto,
	// @RequestParam("password") String password, @RequestParam("repassword") String
	// repassword) {
	// if (!password.equals(repassword)) {
	// model.addAttribute("message", "<b><i class=\"fas fa-info-circle\"></i> Mật
	// khẩu không trùng khớp !</b>");
	// System.out.println("Mật khẩu không trùng khớp !");
	// return "security/register";
	// }

	// Account entity = new Account();
	// BeanUtils.copyProperties(dto, entity);
	// model.addAttribute("message", "<b><i class=\"fas fa-info-circle\"></i> Lưu
	// tài khoản thành công !</b>");
	// accountService.save(entity);
	// System.out.println("ok");
	// return "redirect:/security/login/form";
	// }

	@GetMapping()
	public String getAllAccount(Model model) {
		model.addAttribute("allAccount", accountService.count()).toString();
		return "/home/index";
	}

	@GetMapping("/security/forgotPassword")
	public ModelAndView forgotFrom() {
		return new ModelAndView("/security/forgotPassword");
	}

	@PostMapping("/security/forgotPassword")
	public ModelAndView forgot(ModelMap model, @Valid @ModelAttribute("account") Account dto, BindingResult result,
			@RequestParam("email") String recipientEmail) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("ngonnvpd04149@fpt.edu.vn", "GGT Store");
		helper.setTo(recipientEmail);
		String subject = "Quên mật khẩu?";
		List<Account> listA = adao.findAll();
		for (Account entity : listA) {
			if (recipientEmail.trim().equals(entity.getEmail())) {
				session.removeAttribute("otp");
				int random_otp = (int) Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
				session.setAttribute("otp", random_otp);
				String content = "<div>\r\n" + " <h3>Mã OTP của bạn là: <span style=\"color:red; font-weight: bold;\">"
						+ random_otp + "</span></h3>\r\n" + " </div>";
				sendMailService.queue(dto.getEmail(), "Quên mật khẩu?", content);
				helper.setSubject(subject);
				helper.setText(content, true);
				mailSender.send(message);
				model.addAttribute("account", dto);
				model.addAttribute("email", recipientEmail);
				model.addAttribute("message", "Mã OTP đã được gửi tới Email, hãy kiểm tra Email của bạn!");
				return new ModelAndView("/security/confirmOtp", model);
			}
		}
		model.addAttribute("error", "Email này không tồn tại trong hệ thống!");
		return new ModelAndView("/security/forgotPassword", model);
	}

	@PostMapping("/security/confirmOtp")
	public ModelAndView confirm(ModelMap model, @RequestParam("otp") String otp, @RequestParam("email") String email) {
		if (otp.equals(String.valueOf(session.getAttribute("otp")))) {
			model.addAttribute("email", email);
			model.addAttribute("newPassword", "");
			model.addAttribute("confirmPassword", "");
			model.addAttribute("changePassword", new ChangePassword());
			return new ModelAndView("/security/changePassword", model);
		}
		model.addAttribute("error", "Mã OTP không trùng, vui lòng kiểm tra lại!");
		return new ModelAndView("/security/confirmOtp", model);
	}

	@PostMapping("/security/changePassword")
	public ModelAndView changeForm(ModelMap model, Account dto,
			@Valid @ModelAttribute("changePassword") ChangePassword changePassword, BindingResult result,
			@RequestParam("email") String email, @RequestParam("newPassword") String newPassword,
			@RequestParam("confirmPassword") String confirmPassword) {
		if (result.hasErrors()) {
			model.addAttribute("newPassword", newPassword);
			model.addAttribute("newPassword", confirmPassword);
			model.addAttribute("email", email);
			return new ModelAndView("/security/changePassword", model);
		}

		if (!changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
			model.addAttribute("newPassword", newPassword);
			model.addAttribute("newPassword", confirmPassword);
			model.addAttribute("error", "error");
			model.addAttribute("email", email);
			return new ModelAndView("/security/changePassword", model);
		}
		Account entity = adao.findByEmail(email);
		// entity.setIsEdit(true);
		entity.setPassword(bCryptPasswordEncoder.encode(newPassword));
		session.removeAttribute("otp");
		adao.save(entity);
		model.addAttribute("message", "Đổi mật khẩu thành công!");
		model.addAttribute("email", "");
		session.removeAttribute("otp");
		return new ModelAndView("redirect:/security/login/form", model);
	}
}
