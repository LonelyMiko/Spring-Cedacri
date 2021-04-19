package md.cedacri.spring.controller.student;

import md.cedacri.spring.dao.student.StudentImplement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {
    @RequestMapping("/students")
    public String viewStudents(Model model)
    {
        model.addAttribute("students", new StudentImplement().selectAllStudents());
        return "student";
    }

}
