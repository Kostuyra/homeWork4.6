package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }

    @GetMapping("/age = {age}")
    public ResponseEntity<List<Student>> getStudentsEquallyAge(@PathVariable int age) {
        return ResponseEntity.ok(studentService.filterStudentsByAge(age));
    }

    @GetMapping("all")
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("age")
    public ResponseEntity<List<Student>> getStudentsEquallyAge(@RequestParam int minAge, @RequestParam int maxAge) {
        return ResponseEntity.ok(studentService.findStudentsByAgeBetween(minAge, maxAge));
    }

    @GetMapping("{id}/faculty")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long id){
        return ResponseEntity.ok(studentService.getStudent(id).getFaculty());
    }
    @GetMapping("count")
    public ResponseEntity<Integer> getCount(){
        return ResponseEntity.ok(studentService.getStudentsCount());
    }
    @GetMapping("avg")
    public ResponseEntity<Float> getAvgAgeStudents(){
        return ResponseEntity.ok(studentService.getAvgAgeStudents());
    }
    @GetMapping("last")
    public ResponseEntity<List<Student>> getFiveStudents(){
        return ResponseEntity.ok(studentService.getLastFive());
    }

    @GetMapping("startNameA")
    public ResponseEntity<List<String>> getNameStartWithA() {
        return ResponseEntity.ok(studentService.getAllStudentsWithNameStartedA());
    }

    @GetMapping("avgAge")
    public ResponseEntity<Double> getAverageAgeStudents() {
        return ResponseEntity.ok(studentService.getAverageAgeStudents());
    }

}
