SELECT s.name as student_name, s.age, f.name as faculty_name
FROM student s
JOIN faculty f on s.faculty = f.id;

SELECT s.*
FROM student s
JOIN avatar a on s.id = a.student_id
