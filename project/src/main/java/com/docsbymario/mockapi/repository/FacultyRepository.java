package com.docsbymario.mockapi.repository;

import com.docsbymario.mockapi.entity.impl.Faculty;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FacultyRepository {
    private static final List<Faculty> MOCK_FACULTIES = List.of(
            new Faculty(1, "Faculty of Computer Science", "New York"),
            new Faculty(2, "Faculty of Mathematics", "Los Angeles"),
            new Faculty(3, "Faculty of Physics", "Chicago"),
            new Faculty(4, "Faculty of Biology", "San Francisco"),
            new Faculty(5, "Faculty of Chemistry", "Boston"),
            new Faculty(6, "Faculty of Engineering", "Austin"),
            new Faculty(7, "Faculty of Law", "Miami"),
            new Faculty(8, "Faculty of Economics", "Seattle"),
            new Faculty(9, "Faculty of Philosophy", "Denver"),
            new Faculty(10, "Faculty of Literature", "Portland")
    );

    public List<Faculty> getAll() {
        return MOCK_FACULTIES;
    }

    public Faculty getById(int id) {
        return MOCK_FACULTIES.stream()
                .filter(faculty -> faculty.id() == id)
                .findFirst()
                .get();
    }

    public List<Faculty> getByLocation(String location) {
        return MOCK_FACULTIES.stream()
                .filter(faculty -> faculty.location() == location)
                .collect(Collectors.toList());
    }
}
