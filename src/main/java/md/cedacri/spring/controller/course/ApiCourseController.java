package md.cedacri.spring.controller.course;

import md.cedacri.spring.dao.course.CourseImplementation;
import md.cedacri.spring.model.Course;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
public class ApiCourseController {
    private final CourseImplementation courseImplementation = new CourseImplementation();

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Course> selectAllCourses() {
        return courseImplementation.selectAllCourses();
    }
}
