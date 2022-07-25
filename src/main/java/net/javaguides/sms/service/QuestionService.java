package net.javaguides.sms.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.sms.dto.QuestionDto;
import net.javaguides.sms.entity.Question;

public interface QuestionService {
	List<Question> getAllQuestions();
	
	Question saveQuestion(Question question);
	
	Question getQuestionById(Long id);
	
	Question updateQuestion(Question question);
	
//	void deleteById(Long id);
	
	QuestionDto convertEntityToDto(Question question);
	List<QuestionDto> getAllQuestionsDto();
	// saveStudentDto(QuestionDto question);
//	StudentDto updateStudentDto(Student student);
//	StudentDto getStudentByIdDto(Long id);
	Question convertDtoToEntity(QuestionDto dto);
	List<Question>getByKeyword(String keyword);
	void deleteQuestionById(Long id);
	Page<Question> findPaginated(int pageNo, int pageSize);
}
