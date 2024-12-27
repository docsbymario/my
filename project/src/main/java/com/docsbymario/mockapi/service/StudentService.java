package com.docsbymario.mockapi.service;

import com.docsbymario.mockapi.entity.impl.Student;
import com.docsbymario.mockapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public List<Student> getAllStudents() {
        return studentRepository.getAll();
    }

    public Student getByStudentId(int id) {
        return studentRepository.getById(id);
    }

    public List<Student> getByFacultyId(int facultyId) {
        return studentRepository.getByFaculty(facultyId);
    }

    public List<Student> getByAverageGrade(double averageGrade) {
        return studentRepository.getByAverageGrade(averageGrade);
    }
}
