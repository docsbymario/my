package com.docsbymario.mockapi.repository;

import com.docsbymario.mockapi.entity.impl.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {
    private static final List<Student> MOCK_STUDENTS = List.of(
            new Student(1, "John Doe", 0, 2021, 8.0),
            new Student(2, "Jane Smith", 1, 2022, 7.5),
            new Student(3, "Alex Johnson", 2, 2020, 9.0),
            new Student(4, "Emily Davis", 3, 2023, 8.5),
            new Student(5, "Michael Brown", 4, 2019, 6.0),
            new Student(6, "Sarah Wilson", 5, 2021, 7.5),
            new Student(7, "David Martinez", 6, 2022, 6.5),
            new Student(8, "Olivia Garcia", 7, 2023, 8.0),
            new Student(9, "James Miller", 8, 2020, 5.5),
            new Student(10, "Sophia Anderson", 9, 2021, 9.0),
            new Student(11, "Daniel Thomas", 0, 2022, 7.0),
            new Student(12, "Matthew Taylor", 1, 2023, 6.5),
            new Student(13, "Megan Lee", 2, 2020, 8.5),
            new Student(14, "Ethan Hall", 3, 2021, 7.0),
            new Student(15, "Isabella Young", 4, 2022, 9.5),
            new Student(16, "William King", 5, 2023, 6.0),
            new Student(17, "Sophia Scott", 6, 2020, 8.0),
            new Student(18, "Lucas Adams", 7, 2021, 7.5),
            new Student(19, "Chloe Baker", 8, 2022, 8.0),
            new Student(20, "Henry Gonzalez", 9, 2023, 9.0),
            new Student(21, "Mia Nelson", 0, 2020, 7.5),
            new Student(22, "Sebastian Carter", 1, 2021, 9.0),
            new Student(23, "Amelia Mitchell", 2, 2022, 6.0),
            new Student(24, "Jack Perez", 3, 2023, 7.5),
            new Student(25, "Lily Roberts", 4, 2020, 8.0),
            new Student(26, "Zoe Clark", 5, 2021, 8.5),
            new Student(27, "Aiden Lewis", 6, 2022, 9.5),
            new Student(28, "Ella Walker", 7, 2023, 7.0),
            new Student(29, "Benjamin Hall", 8, 2020, 6.5),
            new Student(30, "Charlotte Allen", 9, 2021, 7.5)
    );

    public List<Student> getAll() {
        return MOCK_STUDENTS;
    }

    public Student getById(int id) {
        return MOCK_STUDENTS.stream()
                .filter(student -> student.id() == id)
                .findFirst()
                .get();
    }

    public List<Student> getByFaculty(int facultyId) {
        return MOCK_STUDENTS.stream()
                .filter(student -> student.facultyId() == facultyId)
                .collect(Collectors.toList());
    }

    public List<Student> getByAverageGrade(double averageGrade) {
        return MOCK_STUDENTS.stream()
                .filter(student -> student.averageGrade() == averageGrade)
                .collect(Collectors.toList());
    }
}
