package com.wipro.doconnect.controller;
/*
* @Author : Krishna,Anubhav
* Modified last date: 30-08-22
* Description :UserController
*/
import java.io.IOException;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wipro.doconnect.dto.AskQuestionDTO;
import com.wipro.doconnect.dto.PostAnswerDTO;
import com.wipro.doconnect.entity.Answer;
import com.wipro.doconnect.entity.ImageModel;
import com.wipro.doconnect.entity.ImageUploadResponse;
import com.wipro.doconnect.entity.ImageUtility;
import com.wipro.doconnect.entity.Question;
import com.wipro.doconnect.entity.User;
import com.wipro.doconnect.repository.IImageModelRepo;
import com.wipro.doconnect.service.IUserService;
import com.wipro.doconnect.vo.Message;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	private IImageModelRepo imageRepository;

	@Autowired
	private IUserService userService;

	@GetMapping("/login/{email}/{password}")
	public User userLogin(@PathVariable String email, @PathVariable String password) {
		return userService.userLogin(email, password);
	}

	@GetMapping("/logout/{userId}")
	public String userLogout(@PathVariable Long userId) {
		return userService.userLogout(userId);
	}

	@PostMapping("/register")
	public User userRegister(@Valid @RequestBody User user) {
		return userService.userRegister(user);
	}

	@PostMapping("/askQuestion")
	public Question askQuestion(@Valid @RequestBody AskQuestionDTO askQuestionDTO) {
		return userService.askQuestion(askQuestionDTO);
	}

	@PostMapping("/giveAnswer")
	public Answer giveAnswer(@Valid @RequestBody PostAnswerDTO postAnswerDTO) {
		return userService.giveAnswer(postAnswerDTO);
	}

	@GetMapping("/searchQuestion/{question}")
	public List<Question> searchQuestion(@PathVariable String question) {
		return userService.searchQuestion(question);
	}

	@GetMapping("/getAnswers/{questionId}")
	public List<Answer> getAnswers(@PathVariable Long questionId) {
		return userService.getAnswers(questionId);
	}

	@GetMapping("/getQuestions/{topic}")
	public List<Question> getQuestions(@PathVariable String topic) {
		return userService.getQuestions(topic);
	}

	@PostMapping("/upload")
	public ResponseEntity<ImageUploadResponse> uplaodImage(@RequestParam("picByte") MultipartFile file) throws IOException {

		
		 imageRepository.save(ImageModel.builder()
	                .name(file.getOriginalFilename())
	                .type(file.getContentType())
	                .picByte(ImageUtility.compressImage(file.getBytes())).build());
	        return ResponseEntity.status(HttpStatus.OK)
	                .body(new ImageUploadResponse("Image uploaded successfully: " +
	                        file.getOriginalFilename()));
//			return userService.uplaodImage(file);
	}

	@GetMapping(path = { "/get/{imageName}" })
	public ImageModel getImage(@PathVariable("imageName") String imageName) throws IOException {
		return userService.getImage(imageName);
	}

	@PostMapping("/sendMessage")
	public Message sendMessage(@Valid @RequestBody Message message) {
		return userService.sendMessage(message);
	}

}
