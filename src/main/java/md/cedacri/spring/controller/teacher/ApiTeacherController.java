package md.cedacri.spring.controller.teacher;

import md.cedacri.spring.dao.teacher.TeacherImplement;

import md.cedacri.spring.model.Teacher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/teachers")
public class ApiTeacherController {
        private final TeacherImplement teacherImplement = new TeacherImplement();
        @GetMapping(
                produces = MediaType.APPLICATION_JSON_VALUE
        )
        public List<Teacher> selectAllTeachers()
        {
            return teacherImplement.selectAllTeachers();
        }

        @GetMapping(
                produces = MediaType.APPLICATION_JSON_VALUE,
                path = "{teacherID}"
        )
        public Teacher selectStudent(@PathVariable("teacherID") int id){
                return teacherImplement.selectTeacher(id);
        }
}
