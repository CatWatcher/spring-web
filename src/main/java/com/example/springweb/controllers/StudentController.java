package com.example.springweb.controllers;

import com.example.springweb.entity.Event;
import com.example.springweb.entity.Student;
import com.example.springweb.entity.University;
import com.example.springweb.repositories.EventRepository;
import com.example.springweb.repositories.StudentRepository;
import com.example.springweb.repositories.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {
    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping(value = "/student/add", method = RequestMethod.GET)
    public String showForm(Model model) {
        // для получения
        model.addAttribute("events", eventRepository.findAll());
        model.addAttribute("universities", universityRepository.findAll());
        model.addAttribute("student", new Student());

        return "add_student";
    }

    @RequestMapping(value = "/student/add", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute Student student,
                             // тут айди универа отдельно
                             @RequestParam(name = "universityId", required = false) int universityId,
                             // айди события
                             @RequestParam(name = "eventId", required = false) int eventId,
                             // для передачи
                             Model model) {

        University university = universityRepository.findById(universityId).get();
        Event event = eventRepository.findById(eventId).get();

        String studentCode = student.getStudentCode();
        // проверяем есть ли там значение
        boolean isPresent = studentRepository.findByCode(studentCode).isPresent();

        if (isPresent) {
            // обновляем информацию о студенте

            // получаем студента из базы
            Student presentStudent = studentRepository.findByCode(studentCode).get();
            updateStudentInfo(presentStudent, event, university);
            // сохраняем изменения
            studentRepository.save(presentStudent);
        } else {
            // добавляем студента нового
            updateStudentInfo(student, event, university);
            studentRepository.save(student);
        }

        model.addAttribute("events", eventRepository.findAll());
        model.addAttribute("universities", universityRepository.findAll());
        model.addAttribute("student", new Student());

        return "add_student";
    }

    private void updateStudentInfo (Student student, Event event, University university) {
        student.getEvents().add(event);
        student.setUniversity(university);

        university.getStudents().add(student);
        event.getStudents().add(student);
    }
}
