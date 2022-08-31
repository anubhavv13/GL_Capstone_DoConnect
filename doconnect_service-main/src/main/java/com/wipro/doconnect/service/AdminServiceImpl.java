package com.wipro.doconnect.service;

/*
* @Author :
* Modified last date: 30-08-22
* Description :
*/

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.doconnect.dto.ResponseDTO;
import com.wipro.doconnect.entity.Admin;
import com.wipro.doconnect.entity.Answer;
import com.wipro.doconnect.entity.Question;
import com.wipro.doconnect.entity.User;
import com.wipro.doconnect.exception.AlreadyThere;
import com.wipro.doconnect.exception.NotFound;
import com.wipro.doconnect.repository.IAdminRepo;
import com.wipro.doconnect.repository.IAnswerRepo;
import com.wipro.doconnect.repository.IQuestionRepo;
import com.wipro.doconnect.repository.IUserRepo;
import com.wipro.doconnect.util.EmailSenderService;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private IAdminRepo adminRepo;

	@Autowired
	private IQuestionRepo questionRepo;

	@Autowired
	private IAnswerRepo answerRepo;

	@Autowired
	private IUserRepo userRepo;

	@Autowired
	private EmailSenderService emailSenderService;

	/*
	 * @Author : Adarsh
	 * Modified last date: 30-08-22
	 * Description:Login credentials of admin are compared with credentials of admin in database to login the user successfully 
	 * Params: 
	 * Return TYpe:
	 * Exception:
	 */
	@Override
	public Admin adminLogin(String email, String password) {

		Admin admin = adminRepo.findByEmail(email);
		if (Objects.isNull(admin))
			throw new NotFound();

		if (admin.getPassword().equals(password)) {
			admin.setIsActive(true);
			adminRepo.save(admin);
		} else
			throw new NotFound();
		return admin;
	}
	/*
	 * @Author : Adarsh
	 * Modified last date: 30-08-22
	 * Description: admin is logged out suucessfully
	 * Params: 
	 * Return TYpe:
	 * Exception:
	 */

	@Override
	public String adminLogout(Long adminId) {

		Admin admin = adminRepo.findById(adminId).orElseThrow(() -> new NotFound("Admin not found"));
		admin.setIsActive(false);
		adminRepo.save(admin);
		return "Logged Out";
	}

	/*
	 * @Author : Sourabh
	 * Modified last date: 30-08-22
	 * Description: register the new admin
	 * Params: 
	 * Return TYpe:
	 * Exception:
	 */
	@Override
	public Admin adminRegister(Admin admin) {

		Admin admin1 = adminRepo.findByEmail(admin.getEmail());
		if (Objects.isNull(admin1))
			return adminRepo.save(admin);

		throw new AlreadyThere();
	}
	/*
	 * @Author : Krishna
	 * Modified last date: 30-08-22
	 * Description: get all the unapproved questions
	 * Params: 
	 * Return TYpe:
	 * Exception:
	 */
	@Override
	public List<Question> getUnApprovedQuestions() {
		return questionRepo.findByIsApproved();
	}

	/*
	 * @Author : Krishna
	 * Modified last date: 30-08-22
	 * Description: get all the unapproved answers
	 * Params: 
	 * Return TYpe:
	 * Exception:
	 */
	@Override
	public List<Answer> getUnApprovedAnswers() {
		return answerRepo.findByIsApproved();
	}

	/*
	 * @Author : Anubhav
	 * Modified last date: 30-08-22
	 * Description:  a mail should go to the list of admins that the question is approved
	 * Params: 
	 * Return TYpe:
	 * Exception:
	 */
	@Override
	public Question approveQuestion(Long questionId) {

		Question question = questionRepo.findById(questionId).orElseThrow(() -> new NotFound("Question not found"));

		question.setIsApproved(true);
		question = questionRepo.save(question);

		List<Admin> admins = adminRepo.findAll();
		for (Admin admin : admins) {
			sendMail(admin.getEmail(), "Question Added");
		}
		//
		return question;
	}
	/*
	 * @Author : Anubhav
	 * Modified last date: 30-08-22
	 * Description:  a mail should go to the admin that a answer is published
	 * Params: 
	 * Return TYpe:
	 * Exception:
	 */
	@Override
	public Answer approveAnswer(Long answerId) {
		Answer answer = answerRepo.findById(answerId).orElseThrow(() -> new NotFound("Answer not found"));

		answer.setIsApproved(true);
		answer = answerRepo.save(answer);

		List<Admin> admins = adminRepo.findAll();
		for (Admin admin : admins) {
			sendMail(admin.getEmail(), "Answer Added");
		}

		
		return answer;
	}

	/*
	 * @Author : 
	 * Modified last date: 30-08-22
	 * Description: delete the inappropriate questions
	 * Params: Krishna
	 * Return TYpe:
	 * Exception:
	 */
	@Override
	public ResponseDTO deleteQuestion(Long questionId) {

		ResponseDTO responseDTO = new ResponseDTO();
		Question question = questionRepo.findById(questionId).orElseThrow(() -> new NotFound("Question not found"));

		questionRepo.delete(question);
		responseDTO.setMsg("Question removed");
		return responseDTO;
	}

	/*
	 * @Author : Paras
	 * Modified last date: 30-08-22
	 * Description:  delete the inappropriate answers
	 * Params: 
	 * Return TYpe:
	 * Exception:
	 */
	@Override
	public ResponseDTO deleteAnswer(Long answerId) {
		ResponseDTO responseDTO = new ResponseDTO();

		Answer answer = answerRepo.findById(answerId).orElseThrow(() -> new NotFound("Answer not found"));

		answerRepo.delete(answer);
		responseDTO.setMsg("Answer Removed");
		return responseDTO;
	}

	/*
	 * @Author : Anubhav
	 * Modified last date: 30-08-22
	 * Description: 
	 * Params: 
	 * Return TYpe:
	 * Exception:
	 */
	public Boolean sendMail(String emailId, String type) {
		try {
			emailSenderService.sendEmail(emailId, type, type);
			return true;
		} catch (Exception e) {
			System.out.println("error in sending mail " + e);
			return false;
		}
	}

	/*
	 * @Author : Sourabh
	 * Modified last date: 30-08-22
	 * Description: get the user
	 * Params: 
	 * Return TYpe:
	 * Exception:
	 */
	@Override
	public User getUser(String email) {
		return userRepo.findByEmail(email);
	}

	/*
	 * @Author : Sourabh
	 * Modified last date: 30-08-22
	 * Description: get all the users
	 * Params: 
	 * Return TYpe:
	 * Exception:
	 */

	@Override
	public List<User> getAllUser() {
		return userRepo.findAll();
	}

}
