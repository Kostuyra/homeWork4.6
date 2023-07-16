package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.Exception.NotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(long id) {
        logger.info("Was invoked method for get faculty");
        Faculty faculty = facultyRepository.findById(id).get();
        if (faculty == null){
            logger.error("There is not faculty with id = " + id);
            throw new NotFoundException();
        }
        return faculty;
    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Was invoked method for save faculty");
        if (facultyRepository.findById(faculty.getId()) == null) {
            logger.error("Faculty " + faculty + " is not found");
            throw new NotFoundException();
        }
        facultyRepository.save(faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long id) {
        logger.info("Was invoked method for delete faculty");
        Faculty faculty = facultyRepository.findById(id).get();
        if (faculty == null) {
            logger.error("There is not faculty with id = " + id);
            throw new NotFoundException();
        }
        facultyRepository.deleteById(id);
        return faculty;
    }

    public List<Faculty> getAll() {
        logger.info("Was invoked method for get all faculties");
        return facultyRepository.findAll().stream().collect(Collectors.toList());
    }
    public List<Faculty> FacultiesByNameIgnoreCaseOrColorIgnoreCase(String name, String color) {
        logger.info("Was invoked method for get find faculties");
        logger.debug("Name = " + name + ", color = " + color);
        return facultyRepository.findFacultiesByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    public String getLongestFacultyName(){
        return facultyRepository.findAll()
                .stream()
                .map(f -> f.getName())
                .max(Comparator.comparingInt(String::length)).orElse("")
                .toString();
    }
}
