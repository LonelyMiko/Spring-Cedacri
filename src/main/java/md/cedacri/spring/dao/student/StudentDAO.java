package md.cedacri.spring.dao.student;

import md.cedacri.spring.model.Student;

import java.util.List;
import java.util.UUID;

public interface StudentDAO {
    void insertStudent(Student student);

    Student selectStudent(UUID id);

    List<Student> selectAllStudents();

    void updateStudent(UUID id, Student student);

    void deleteStudent(UUID id);
}
