package net.javaguides.sms.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.sms.dto.QuestionDto;

import net.javaguides.sms.entity.Question;
import net.javaguides.sms.repository.QuestionRepository;
import net.javaguides.sms.service.QuestionService;


@Controller
public class QuestionController {

	private QuestionService questionService;
	private QuestionRepository questionRepo;

	public QuestionController(QuestionService questionService) {
		super();
		this.questionService = questionService;
	}
	

	public String currentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		
		
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		  System.out.println(date);
		  return date;
		  }
	// handler method to handle list students and return mode and view
	@GetMapping("/questions")
	public String listStudents(Model model, @Param("keyword") String keyword) {
		
		List<Question> listQuestions = questionService.getByKeyword(keyword);
		model.addAttribute("listQuestions", listQuestions);
		model.addAttribute("keyword", keyword);
	
		//questionPageable(pageable);
	//	model.addAttribute("questions", questionService.getAllQuestionsDto());
		
//		return findPaginated(1,model,keyword);
		return "students";
	}
	
	
	
	@GetMapping("/questions/new")
	public String createStudentForm(Model model) {
		
		// create student object to hold student form data
		 	Question question = new Question();
		 	
		model.addAttribute("question", question);
		
		return "create_student";
		
	}
	
	@PostMapping("/questions")
	public String saveStudent(@ModelAttribute("question") QuestionDto dto,String date) {
		
		Question question = questionService.convertDtoToEntity(dto);
	date = currentDate();
	question.setDate(date);
		questionService.saveQuestion(question);
		System.out.println(question.toString());
		
		return "redirect:/questions";
	}
	
//	@GetMapping("/questions")
//	Page<Question> questionPageable(Pageable pageable){
//		return questionRepo.findAll(pageable);
//	}
	
	public Page<Question> q(@RequestParam int p, @RequestParam int s ){
		PageRequest pr = PageRequest.of(p, s);
		return questionRepo.findAll(pr);
	}
//	@GetMapping("/students/edit/{id}")
//	public String editStudentForm(@PathVariable Long id, Model model) {
//		model.addAttribute("student", studentService.getStudentById(id));
//		return "edit_student";
//	}

//	@GetMapping("/page/{pageNo}")
//	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model, @Param("keyword") String keyword) {
//		int pageSize = 5;
//		
//		
//		Page<Question> page = questionService.findPaginated(pageNo, pageSize,keyword);
//		List<Question> listQuestions = page.getContent();
//		model.addAttribute("currentPage", pageNo);
//		model.addAttribute("totalPages", page.getTotalPages());
//		model.addAttribute("totalItems", page.getTotalElements());
//		model.addAttribute("listQuestions", listQuestions);
//		model.addAttribute("keyword", keyword);
//		return "students";
//	}
//		
  @GetMapping("questions/bigger/{id}/{yes}")
  public String voteYes(@PathVariable(value = "id") Long id) {
	  System.out.println("reach1");
	  
	  Question existingQuestion = questionService.getQuestionById(id);
	  System.out.println("reach2");
	  System.out.println(existingQuestion.toString());
	  System.out.println("reach3");
		//existingStudent.setId(id);
		
		//existingStudent.setFirstName(student.getFirstName());
	
		existingQuestion.setYes(existingQuestion.getYes()+1);
		System.out.println("reach4");
		questionService.updateQuestion(existingQuestion);
         return "redirect:/questions";



  }
  @GetMapping("questions/some/{id}/{no}")
  public String voteNo(@PathVariable(value = "id") Long id) {
	  
	  Question existingQuestion = questionService.getQuestionById(id);
	  System.out.println("reach2");
	  System.out.println(existingQuestion.toString());
	  System.out.println("reach3");
		//existingStudent.setId(id);
		
		//existingStudent.setFirstName(student.getFirstName());
	
		existingQuestion.setNo(existingQuestion.getNo()+1);
		System.out.println("reach4");
		questionService.updateQuestion(existingQuestion);
         return "redirect:/questions";



  }
  

	// handler method to handle delete student request
	
	@GetMapping("/questions/delete/{id}")
	public String deleteStudent(@PathVariable Long id) {
	
		 questionService.deleteQuestionById(id);
		return "redirect:/questions";
	}
  
	@GetMapping("/questions/edit/{id}")
	public String editQuestionForm(@PathVariable Long id, Model model ) {
		
		
		model.addAttribute("question", questionService.getQuestionById(id));
		return "edit_question";
	}
	
	@PostMapping("/questions/{id}")
	public String updateQuestion(@PathVariable Long id, @ModelAttribute("question")Question question, Model model){
		
		Question existing = questionService.getQuestionById(id);
		existing.setList(question.getList());
		questionService.updateQuestion(question);
		return "redirect:/questions";
	}
//  @GetMapping("/questions/search")
//  public String search(Question question, Model model, String keyword) {
//	  
//	  if(keyword!=null) {
//		  List<Question> search = questionService.getByKeyword(keyword);
//		  model.addAttribute("search", search);
//		    
//	  }else {
//		  List<Question> search = questionService.getAllQuestions();
//		  model.addAttribute("search", search);
//		  
//	  }
//	  
//	  
//	  return "search";
//  }
//	
}
