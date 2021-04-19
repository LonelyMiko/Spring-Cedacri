package md.cedacri.spring.dao.teacher;

import md.cedacri.spring.exception.RecordNotFoundException;
import md.cedacri.spring.model.Teacher;

import java.util.List;

public interface TeacherDAO {
    void insertTeacher(Teacher teacher);

    Teacher selectTeacher(int id);

    List<Teacher> selectAllTeachers();

    void updateTeacher(int id, Teacher teacher) throws RecordNotFoundException;

    void deleteTeacher(int id) throws RecordNotFoundException;
}
