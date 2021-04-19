package md.cedacri.spring.dao.course;

import md.cedacri.spring.exception.RecordNotFoundException;
import md.cedacri.spring.model.Course;


import java.util.List;

public interface CourseDAO {
    void insertCourse(Course course);

    Course selectCourse(int id);

    List<Course> selectAllCourses();

    void updateCourse(int id, Course course) throws RecordNotFoundException;

    void deleteCourse(int id) throws RecordNotFoundException;
}
