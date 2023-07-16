package ru.hogwarts.school.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyService.createFaculty(faculty));
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long id) {
        return ResponseEntity.ok(facultyService.getFaculty(id));
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyService.updateFaculty(faculty));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        return ResponseEntity.ok(facultyService.deleteFaculty(id));
    }

    @GetMapping("filter")
    public ResponseEntity<List<Faculty>> getFacultiesByColor(@RequestParam(required = false) String name,
                                                             @RequestParam(required = false) String color) {
        return ResponseEntity.ok(facultyService.FacultiesByNameIgnoreCaseOrColorIgnoreCase(name,color));
    }

    @GetMapping("all")
    public ResponseEntity<List<Faculty>> getAllFaculty(){
        return ResponseEntity.ok(facultyService.getAll());
    }

    @GetMapping("{id}/students")
    public ResponseEntity<List<Student>> getStudentsFromFaculty(@PathVariable long id){
        return ResponseEntity.ok(facultyService.getFaculty(id).getStudents());
    }
    @GetMapping("longest")
    public ResponseEntity<String> getLongestFacultyName(){
        return ResponseEntity.ok(facultyService.getLongestFacultyName());
    }

}
