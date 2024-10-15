package Application.Service;

import Application.Model.Classroom;
import Application.Model.Student;
import Application.Repository.ClassroomRepository;
import Application.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    StudentRepository studentRepository;
    ClassroomRepository classroomRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, ClassroomRepository classroomRepository) {
        this.studentRepository = studentRepository;
        this.classroomRepository = classroomRepository;
    }

    /**
     * Persist a new student entity.
     * @param student a transient student entity.
     * @return the persisted student entity.
     */
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    /**
     * @return all student entities.
     */
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    /**
     * Assign the classroom to the student by manipulating the classroom field of the student.
     * Don't forget to save the changes to the student entity via the studentRepository.
     * 
     * @param studentId the id of a persisted student
     * @param classroom a persisted, existing classroom passed into this method
     */
    public void assignClassroomToStudent(long studentId, Classroom classroom) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setClassroom(classroom);  
            studentRepository.save(student);  
        }
    }

    /**
     * Return the assigned classroom of the student by retrieving the 'classroom' field of student.
     * 
     * @param studentId Id of a persisted, existing student entity
     * @return the Classroom of the student
     */
    public Classroom getClassroomOfStudent(long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        return optionalStudent.map(Student::getClassroom).orElse(null);  // Return the classroom or null if not found
    }

    /**
     * Unassign the classroom by setting the student's classroom field to null.
     * Don't forget to save the changes to the student entity via the studentRepository.
     *     /**
     * Unassign the classroom by setting the student's classroom field to null.
     * Don't forget to save the changes to the student entity via the studentRepository.
     * 
     * @param studentId Id of a persisted, existing student entity
     */
    public void unassignClassroomOfStudent(long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setClassroom(null);  
            studentRepository.save(student);  
        }
    }
}
