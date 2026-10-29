
package kz.akbiyev.studentapi.controller;

import kz.akbiyev.studentapi.model.Student;
import kz.akbiyev.studentapi.repository.StudentRepository;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/info")
    public Map<String, String> info() {
        return Map.of(
                "student", "Baimurat Akbiyev",
                "studentId", "38150",
                "group", "IT2-2306",
                "project", "Student CRUD API"
        );
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@Valid @RequestBody Student student) {
        student.setId(null);
        return repository.save(student);
    }

    @GetMapping("/students")
    public List<Student> getAll() {
        return repository.findAll();
    }

    @GetMapping("/students/{id}")
    public Student getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student not found"
                ));
    }

    @PutMapping("/students/{id}")
    public Student update(
            @PathVariable Long id,
            @Valid @RequestBody Student newStudent) {

        Student student = getById(id);

        student.setName(newStudent.getName());
        student.setEmail(newStudent.getEmail());
        student.setGroupName(newStudent.getGroupName());

        return repository.save(student);
    }

    @DeleteMapping("/students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        Student student = getById(id);
        repository.delete(student);
    }


    @GetMapping("/students/count")
    public Map<String, Long> getStudentCount() {
        return Map.of("totalStudents", repository.count());
    }

}
