package com.docsbymario.mockapi.controller;

import com.docsbymario.mockapi.service.FacultyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.docsbymario.mockapi.entity.impl.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "university")
@Tag(name="Faculty", description="Endpoints for managing the faculties")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @GetMapping("/faculties")
    public ResponseEntity getAllFaculties() {
        List<Faculty> facultyList = facultyService.getAllFaculties();
        return new ResponseEntity<>(facultyList, HttpStatus.OK);
    }

    @GetMapping("/faculties/id/{id}")
    public ResponseEntity getFacultiesById(@PathVariable("id") int id) {
        Faculty faculty = facultyService.getByFacultyId(id);
        return new ResponseEntity<>(faculty, HttpStatus.OK);
    }

    @GetMapping("/faculties/location/{faculty}")
    public ResponseEntity getFacultiesByLocation(@PathVariable("location") String location) {
        List<Faculty> facultyList = facultyService.getByLocation(location);
        return new ResponseEntity<>(facultyList, HttpStatus.OK);
    }

    @PostMapping("/faculties")
    public ResponseEntity postFaculty(@RequestBody Faculty student) {
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/faculties")
    public ResponseEntity putFaculty(@RequestBody Faculty student) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/faculties/id/{id}")
    public ResponseEntity deleteFaculty(@PathVariable("id") int id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
