package com.docsbymario.mockapi.service;

import com.docsbymario.mockapi.entity.impl.Faculty;
import com.docsbymario.mockapi.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;
    public List<Faculty> getAllFaculties() {
        return facultyRepository.getAll();
    }

    public Faculty getByFacultyId(int facultyId) {
        return facultyRepository.getById(facultyId);
    }

    public List<Faculty> getByLocation(String location) {
        return facultyRepository.getByLocation(location);
    }
}
