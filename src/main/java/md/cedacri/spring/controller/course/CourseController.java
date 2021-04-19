package md.cedacri.spring.controller.course;

import md.cedacri.spring.dao.course.CourseImplementation;
import md.cedacri.spring.exception.RecordNotFoundException;
import md.cedacri.spring.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class CourseController {

    @Autowired
    CourseImplementation courseImplementation;

    @RequestMapping("/courses")
    public String viewCourses(Model model)
    {
        model.addAttribute("courses" ,new CourseImplementation().selectAllCourses());
        return "course";
    }

    @RequestMapping(path = "courses/create", method = RequestMethod.POST)
    public String create(Course course)
    {
        courseImplementation.insertCourse(course);
        return "redirect:/courses";
    }

    @RequestMapping(path = {"/courses/edit", "/courses/edit/{id}"})
    public String edit(Model model, @PathVariable("id") Optional<Integer> id)
    {
        if (id.isPresent())
        {
            Course course = courseImplementation.selectCourse(id.get());
            model.addAttribute("course", course);
        }
        else
        {
            model.addAttribute("course", new Course());
        }
        return "courseCrud";
    }

    @RequestMapping(path = "/courses/delete/{id}")
    public String delete(Model model, @PathVariable("id") int id) throws RecordNotFoundException
    {
        courseImplementation.deleteCourse(id);
        return "redirect:/courses";
    }
}
