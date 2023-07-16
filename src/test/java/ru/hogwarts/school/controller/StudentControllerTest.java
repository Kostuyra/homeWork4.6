package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.hogwarts.school.Constants.Constants.*;

@WebMvcTest(controllers = StudentController.class)
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentRepository studentRepository;
    @SpyBean
    private StudentService studentService;
    @InjectMocks
    StudentController studentController;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void createStudent() throws Exception {
        when(studentRepository.save(any(Student.class))).thenReturn(STUDENT1);
        JSONObject createStudentRq = new JSONObject();
        createStudentRq.put("name", STUDENT_NAME);
        createStudentRq.put("age", STUDENT_AGE);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(createStudentRq.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(STUDENT_NAME))
                .andExpect(jsonPath("$.age").value(STUDENT_AGE));
    }

    @Test
    void getStudent() throws Exception {
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(STUDENT1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + STUDENT_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(STUDENT_NAME))
                .andExpect(jsonPath("$.age").value(STUDENT_AGE));
    }

    @Test
    void updateStudent() throws Exception {
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(STUDENT1));
        STUDENT1.setName(UPDATE_STUDENT_NAME);
        JSONObject updateStudentRq = new JSONObject();
        updateStudentRq.put("id", STUDENT1.getId());
        updateStudentRq.put("name", STUDENT1.getName());
        updateStudentRq.put("age", STUDENT1.getAge());

        when(studentRepository.save(any(Student.class))).thenReturn(STUDENT1);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student/")
                        .content(updateStudentRq.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(UPDATE_STUDENT_NAME))
                .andExpect(jsonPath("$.age").value(STUDENT_AGE));
    }

    @Test
    void deleteStudent() throws Exception {
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(STUDENT1));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/" + STUDENT_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void getAllStudents() throws Exception {
        when(studentRepository.findAll()).thenReturn(ALLSTUDENTS);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(ALLSTUDENTS)));

    }

    @Test
    void testGetStudentsBetweenAge() throws Exception {
        when(studentRepository.findByAgeBetween(20, 25)).thenReturn(STUDENT_LIST2);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/age?minAge=20&maxAge=25")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(STUDENT_LIST2)));
    }

    @Test
    void testGetStudentsEquallyAge() throws Exception {
        when(studentRepository.findStudentsByAge(20)).thenReturn(STUDENT_LIST);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/age = 20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(STUDENT_LIST)));
    }

    @Test
    void getFaculty() throws Exception {
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(STUDENT1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + STUDENT_ID + "/faculty")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void getCount() throws Exception {
        when(studentRepository.getStudentsCount()).thenReturn(4);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/count")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAvgAgeStudents() throws Exception {
        when(studentRepository.getAvgAgeStudents()).thenReturn(20f);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/avg")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getFiveStudents() throws Exception {
        when(studentRepository.getLastFive()).thenReturn(STUDENT_LIST);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/last")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(STUDENT_LIST)));
    }

    @Test
    void getNameStartWithA() throws Exception {
        when(studentRepository.findAll()).thenReturn(ALLSTUDENTS);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/startNameA"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(LIST_STUDENT_NAME)));
    }

    @Test
    void getAverageAgeStudents() throws Exception {
        when(studentRepository.findAll()).thenReturn(STUDENT_LIST);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/avgAge")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}