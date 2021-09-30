package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/home")
	public String viewHomePage() {
		return "index";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
	}

	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepository.save(user);
		//sendEmail();
		return "register_success";
	}

	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userRepository.findAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}

	public void sendEmail() {

		String from = "vishnumaddy16@gmail.com";
		String to = "revanthprabu28@gmail.com";
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject("This is a plain text email");
		message.setText("Registration succesfull !!!!!!!!!!!!!");
		javaMailSender.send(message);

	}

}
