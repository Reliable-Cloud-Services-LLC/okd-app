package net.javaguides.sms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.javaguides.sms.dto.QuestionDto;

import net.javaguides.sms.entity.Question;

import net.javaguides.sms.repository.QuestionRepository;

import net.javaguides.sms.service.QuestionService;


@Service
public class QuestionServiceImpl implements QuestionService{

	private QuestionRepository questionRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public QuestionServiceImpl(QuestionRepository questionRepository) {
		super();
		this.questionRepository = questionRepository;
	}

	@Override
	public List<Question> getAllQuestions() {
		
		return questionRepository.findAll();
	}

	@Override
	public Question saveQuestion(Question question) {
		return questionRepository.save(question);
	}

	@Override
	public Question getQuestionById(Long id) {
		if(!getAllQuestions().isEmpty()) {
		Question question = questionRepository.findById(id).get();
		
		return question;
		}
		return null;
	}

	@Override
	public Question updateQuestion(Question question) {
		return questionRepository.save(question);
	}

//	@Override
//	public void deleteStudentById(Long id) {
//		studentRepository.deleteById(id);	
//	}

	@Override
	public QuestionDto convertEntityToDto(Question question) {
		
		QuestionDto dto = new QuestionDto();
		dto = modelMapper.map(question, QuestionDto.class);
		
		return dto;
	}
	@Override

	public List<QuestionDto> getAllQuestionsDto() {
		
		return questionRepository.findAll()
				.stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}

//	@Override
//	public StudentDto saveStudentDto(StudentDto student) {
//		// TODO Auto-generated method stub
//		//StudentDto dto =convertEntityToDto(student);
//		return studentRepository.save(student);
//	}

//	@Override
//	public StudentDto updateStudentDto(Student student) {
//		StudentDto dto = convertEntityToDto(student);
//	
//		return studentRepository.save(dto);
//		
//	}
	@Override
	public Question convertDtoToEntity(QuestionDto dto) {
		
		Question question = new Question();
		question = modelMapper.map(dto, Question.class);
		
		return question;
	}

	@Override
	public List<Question> getByKeyword(String keyword) {
		// TODO Auto-generated method stub
		if(keyword!=null) {
			return questionRepository.findByKeyWord(keyword);
		}
//	 questionRepository.findByKeyWord(keyword);
		return questionRepository.findAll();
	}

	@Override
	public void deleteQuestionById(Long id) {
		questionRepository.deleteById(id);		
	}

	@Override
	public Page<Question> findPaginated(int pageNo, int pageSize)
	{
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		
		
		//Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
	
	
		return questionRepository.findAll(pageable);
	}


}
