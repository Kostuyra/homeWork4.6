package ru.hogwarts.school.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.Exception.NotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student getStudent(long id) {
        logger.info("Was invoked method for get student");
        Student student = studentRepository.findById(id).get();
        if (student == null) {
            logger.error("There is not student with id = " + id);
            throw new NotFoundException();
        }
        return student;
    }

    public Student updateStudent(Student student) {
        logger.info("Was invoked method for update student");
        if (studentRepository.findById(student.getId()) == null) {
            logger.error("There is not student  = " + student);
            throw new NotFoundException();
        }
        studentRepository.save(student);
        return student;
    }

    public Student deleteStudent(long id) {
        logger.info("Was invoked method for delete student");
        Student student = studentRepository.findById(id).get();
        if (student == null) {
            logger.error("There is not student with id = " + id);
            throw new NotFoundException();
        }
        studentRepository.deleteById(id);
        return student;
    }

    public List<Student> getAll() {
        logger.info("Was invoked method for find all students");
        return studentRepository.findAll().stream().collect(Collectors.toList());
    }

    public List<Student> filterStudentsByAge(int age) {
        logger.info("Was invoked method for filter students by age");
        return studentRepository.
                findStudentsByAge(age).
                stream().
                collect(Collectors.toList());
    }

    public List<Student> findStudentsByAgeBetween(int minAge, int maxAge) {
        logger.info("Was invoked method for find students by age");
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }


    public Integer getStudentsCount() {
        logger.info("Was invoked method for get students count");
        return studentRepository.getStudentsCount();
    }


    public Float getAvgAgeStudents() {
        logger.info("Was invoked method for get average age students");
        return studentRepository.getAvgAgeStudents();
    }
    public Double getAverageAgeStudents(){
        logger.info("Was invoked method for get average age students");
        return studentRepository.findAll().stream().mapToDouble(s ->  s.getAge()).average().orElseThrow();
    }


    public List<Student> getLastFive() {
        logger.info("Was invoked method for get last five students");
        return studentRepository.getLastFive();
    }

    public List<String> getAllStudentsWithNameStartedA() {
        logger.info("Was invoked method for get all students with name started A");
      return   studentRepository
                .findAll()
                .stream()
                .filter(s -> s.getName().startsWith("A"))
                .map(s -> s.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());


    }



}
