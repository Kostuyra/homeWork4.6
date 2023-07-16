package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentsByAge(int age);

    List<Student> findByAgeBetween(int minAge, int maxAge);

    @Query(value = "select count(*) from Student", nativeQuery = true)
    Integer getStudentsCount();

    @Query(value = "select avg(age) from Student ", nativeQuery = true)
    Float getAvgAgeStudents();

    @Query(value = "select * from Student order by id desc limit 5", nativeQuery = true)
    List<Student> getLastFive();
}
