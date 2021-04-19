package md.cedacri.spring.controller.teacher;

import md.cedacri.spring.dao.teacher.TeacherImplement;
import md.cedacri.spring.exception.RecordNotFoundException;
import md.cedacri.spring.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class TeacherController {

    @Autowired
    TeacherImplement teacherImplement;

    @RequestMapping("/instructors")
    public String viewTeachers(Model model)
    {
        model.addAttribute("instructors", teacherImplement.selectAllTeachers());
        return "instructor";
    }

    @RequestMapping(path = "instructors/create", method = RequestMethod.POST)
    public String create(Teacher teacher)
    {
        teacherImplement.insertTeacher(teacher);
        return "redirect:/instructors";
    }

    @RequestMapping(path = {"/instructors/edit", "/instructors/edit/{id}"})
    public String edit(Model model, @PathVariable("id")Optional<Integer> id)
    {
        if (id.isPresent())
        {
            Teacher teacher = teacherImplement.selectTeacher(id.get());
            model.addAttribute("teacher", teacher);
        }
        else
            {
                model.addAttribute("teacher", new Teacher());
            }
        return "teacherCrud";
    }

    @RequestMapping(path = "/instructors/delete/{id}")
    public String delete(Model model, @PathVariable("id") int id) throws RecordNotFoundException
    {
            teacherImplement.deleteTeacher(id);
            return "redirect:/instructors";
    }


}
