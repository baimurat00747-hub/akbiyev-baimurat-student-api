
package kz.akbiyev.akbiyev_baimurat_student_api.controller;

import kz.akbiyev.studentapi.controller.StudentController;
import kz.akbiyev.studentapi.model.Student;
import kz.akbiyev.studentapi.repository.StudentRepository;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentControllerTest {

    @Test
    void shouldReturnStudentById() {
        StudentRepository repository = mock(StudentRepository.class);

        Student student = new Student();
        student.setId(1L);
        student.setName("Baimurat Akbiyev");
        student.setEmail("student@example.com");
        student.setGroupName("IT2-2306");

        when(repository.findById(1L))
                .thenReturn(Optional.of(student));

        StudentController controller =
                new StudentController(repository);

        Student result = controller.getById(1L);

        assertEquals("Baimurat Akbiyev", result.getName());
        assertEquals("IT2-2306", result.getGroupName());
    }

    @Test
    void shouldReturn404WhenStudentDoesNotExist() {
        StudentRepository repository = mock(StudentRepository.class);

        when(repository.findById(999L))
                .thenReturn(Optional.empty());

        StudentController controller =
                new StudentController(repository);

        ResponseStatusException exception =
                assertThrows(
                        ResponseStatusException.class,
                        () -> controller.getById(999L)
                );

        assertEquals(
                HttpStatus.NOT_FOUND,
                exception.getStatusCode()
        );
    }
}
