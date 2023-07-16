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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.hogwarts.school.Constants.Constants.*;

@WebMvcTest(controllers = FacultyController.class)
class FacultyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @SpyBean
    private FacultyService facultyService;
    @InjectMocks
    FacultyController facultyController;
    private  final ObjectMapper mapper = new ObjectMapper();
    @Test
    void createFaculty() throws Exception {
        when(facultyRepository.save(any(Faculty.class))).thenReturn(FACULTY1);
        JSONObject createStudentRq = new JSONObject();
        createStudentRq.put("name", FACULTY_NAME);
        createStudentRq.put("color", FACULTY_COLOR);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(createStudentRq.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(FACULTY_NAME))
                .andExpect(jsonPath("$.color").value(FACULTY_COLOR));
    }

    @Test
    void getFaculty() throws Exception {
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(FACULTY1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + FACULTY_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(FACULTY_NAME))
                .andExpect(jsonPath("$.color").value(FACULTY_COLOR));
    }

    @Test
    void updateFaculty() throws Exception {
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(FACULTY1));
        FACULTY1.setName(UPDATE_FACULTY_NAME);
        JSONObject updateStudentRq = new JSONObject();
        updateStudentRq.put("id", FACULTY1.getId());
        updateStudentRq.put("name", FACULTY1.getName());
        updateStudentRq.put("color", FACULTY1.getColor());

        when(facultyRepository.save(any(Faculty.class))).thenReturn(FACULTY1);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty/")
                        .content(updateStudentRq.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(UPDATE_FACULTY_NAME))
                .andExpect(jsonPath("$.color").value(FACULTY_COLOR));
    }

    @Test
    void deleteFaculty() throws Exception {
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(FACULTY1));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + FACULTY_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getFacultiesByColor() throws Exception {
        when(facultyRepository.findFacultiesByNameIgnoreCaseOrColorIgnoreCase(null, "White")).thenReturn(FACULTY_LIST);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/filter?color="+FACULTY_COLOR)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(FACULTY_LIST)));
    }
    @Test
    void getFacultiesByName() throws Exception {
        when(facultyRepository.findFacultiesByNameIgnoreCaseOrColorIgnoreCase(FACULTY_NAME, null)).thenReturn(FACULTY_LIST_ONE);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/filter?name="+FACULTY_NAME)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(FACULTY_LIST_ONE)));
    }

    @Test
    void getAllFaculty() throws Exception {
        when(facultyRepository.findAll()).thenReturn(ALLFACULTIES);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(ALLFACULTIES)));
    }

    @Test
    void getStudentsFromFaculty() throws Exception {
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(FACULTY1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + FACULTY_ID + "/students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void getLongestFacultyNameTest() throws Exception {
        when(facultyRepository.findAll()).thenReturn(ALLFACULTIES);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/longest")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}