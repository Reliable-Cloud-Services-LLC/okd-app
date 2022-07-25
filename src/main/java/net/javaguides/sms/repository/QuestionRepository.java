package net.javaguides.sms.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.javaguides.sms.entity.Question;


public interface QuestionRepository extends JpaRepository<Question, Long>{

	
//	public Student findById(int id);
//
//	public StudentDto save(StudentDto dto);
	@Query(value = "select * from first f where f.list like %:keyword%", nativeQuery = true)
	List<Question> findByKeyWord(@Param("keyword") String keyword);
	
}
