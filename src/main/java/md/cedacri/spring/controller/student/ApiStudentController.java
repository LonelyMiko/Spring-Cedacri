package md.cedacri.spring.controller.student;

import md.cedacri.spring.dao.student.StudentImplement;
import md.cedacri.spring.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/students")
public class ApiStudentController {
    private final StudentImplement studentService;

    @Autowired
    public ApiStudentController(StudentImplement studentService){
        this.studentService =  studentService;
    }


    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Student> selectAllStudents(){
        return studentService.selectAllStudents();
    }


    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{studentId}"
    )
    public Student selectStudent(@PathVariable("studentId") UUID id){
        return studentService.selectStudent(id);
    }


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void insertStudent(@RequestBody Student student){
        studentService.insertStudent(student);
    }


    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            path = "{studentId}"
    )
    public void updateStudent(@PathVariable("studentId") UUID id, @RequestBody Student student){
        student.setId(id);
        studentService.updateStudent(id, student);
    }


    @DeleteMapping(
            path = "{studentId}"
    )
    public void deleteStudent(@PathVariable("studentId") UUID id){
        studentService.deleteStudent(id);
    }


}
