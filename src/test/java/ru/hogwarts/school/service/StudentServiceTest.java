package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.hogwarts.school.Constants.Constants.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentService out;

    @Test
    void createStudent() {
        Mockito.when(studentRepository.save(STUDENT1)).thenReturn(STUDENT1);
        Student test = out.createStudent(STUDENT1);
        assertEquals(STUDENT1, test);
    }

    @Test
    void getStudent() {
        Mockito.when(studentRepository.findById(2L)).thenReturn(Optional.of(STUDENT2));
        assertEquals(STUDENT2, out.getStudent(2));
    }

    @Test
    void updateStudent() {
        Mockito.when(studentRepository.findById(2L)).thenReturn(Optional.of(STUDENT2));
        assertEquals(STUDENT2, out.getStudent(2));
        assertEquals(UPDATESTUDENT, out.updateStudent(UPDATESTUDENT));
    }

    @Test
    void deleteStudent() {
        Mockito.when(studentRepository.findById(3L)).thenReturn(Optional.of(STUDENT3));
        assertEquals(STUDENT3, out.getStudent(3));
        assertEquals(STUDENT3, out.deleteStudent(3));
    }

    @Test
    void filterStudentsByAge() {
        Mockito.when(studentRepository.findStudentsByAge(20)).thenReturn(STUDENT_LIST);
        assertEquals(STUDENT_LIST, out.filterStudentsByAge(20));
    }

    @Test
    void allFaculties() {
        Mockito.when(studentRepository.findAll()).thenReturn(ALLSTUDENTS);
        assertEquals(ALLSTUDENTS, out.getAll());
    }

    @Test
    void findStudentsByAgeBetweenTest(){
        Mockito.when(studentRepository.findByAgeBetween(20,25)).thenReturn(STUDENT_LIST2);
        assertEquals(STUDENT_LIST2, out.findStudentsByAgeBetween(20, 25));
    }

    @Test
    void getStudentsCount() {
        Mockito.when(studentRepository.getStudentsCount()).thenReturn(4);
        assertEquals(4, out.getStudentsCount());
    }

    @Test
    void getAvgAgeStudents() {
        Mockito.when(studentRepository.getAvgAgeStudents()).thenReturn(20f);
        assertEquals(20f, out.getAvgAgeStudents());
    }
    @Test
    void getAverageAgeStudents() {
        Mockito.when(studentRepository.findAll()).thenReturn(STUDENT_LIST);
        assertEquals(20, out.getAverageAgeStudents());
    }

    @Test
    void getLastFive() {
        Mockito.when(studentRepository.getLastFive()).thenReturn(ALLSTUDENTS);
        assertEquals(ALLSTUDENTS, out.getLastFive());
    }
    @Test
    void getAllStudentsWithNameStartedATest(){
        Mockito.when(studentRepository.findAll()).thenReturn(ALLSTUDENTS);
        assertEquals(LIST_STUDENT_NAME, out.getAllStudentsWithNameStartedA());
    }
}