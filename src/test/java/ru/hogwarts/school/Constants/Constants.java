package ru.hogwarts.school.Constants;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;


public class Constants {
    public static final Faculty FACULTY1 = new Faculty(1L, "FacultyName1", "White");
    public static final Student STUDENT1 = new Student(1L, "AName1", 20, FACULTY1);
    public static final Student STUDENT2 = new Student(2L, "Name2", 19);
    public static final Student STUDENT3 = new Student(3L, "AName3", 20);
    public static final Student STUDENT4 = new Student(4L, "Name4", 21);

    public static final Student UPDATESTUDENT = new Student(3L, "NewName", 22);
    public static final List<Student> ALLSTUDENTS = List.of(STUDENT1, STUDENT2,STUDENT3, STUDENT4);
    public static final List<Student> STUDENT_LIST = List.of(STUDENT1, STUDENT3);
    public static final List<Student> STUDENT_LIST2 = List.of(STUDENT1, STUDENT3, STUDENT4);
    public static final List<String> LIST_STUDENT_NAME = List.of("ANAME1", "ANAME3");




    public static final Faculty FACULTY2 = new Faculty(2L, "FacultyName2", "Red");
    public static final Faculty FACULTY3 = new Faculty(3L, "FacultyName3", "White");
    public static final Faculty FACULTY4 = new Faculty(4L, "FacultyName44", "Blue");
    public static final Faculty UPDATEFACULTY = new Faculty(1L, "NewName", "Blue");
    public static final List<Faculty> FACULTY_LIST = List.of(FACULTY1, FACULTY3);
    public static final List<Faculty> ALLFACULTIES = List.of(FACULTY1, FACULTY2,FACULTY3, FACULTY4);
    public static final List<Faculty> FACULTY_LIST_ONE = List.of(FACULTY1);


    public static final Long STUDENT_ID = 1L;
    public static final String STUDENT_NAME = "Name1";
    public static final Integer STUDENT_AGE = 20;
    public static final String UPDATE_STUDENT_NAME = "Name11";
    public static final Long FACULTY_ID = 1L;
    public static final String FACULTY_NAME = "FacultyName1";
    public static final String FACULTY_COLOR = "White";
    public static final String UPDATE_FACULTY_NAME = "FacultyName11";

}
