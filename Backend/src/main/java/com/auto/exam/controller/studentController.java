package com.auto.exam.controller;

import com.auto.exam.Model.*;
import com.auto.exam.repo.userRepo;
import com.auto.exam.service.studentDetailsService;
import com.auto.exam.service.examService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class studentController {
    private final studentDetailsService studentDetailsService;
    private final examService examService;

    @Autowired
    public studentController(studentDetailsService studentDetailsService, examService examService) {
        this.studentDetailsService = studentDetailsService;
        this.examService = examService;
    }

    @Autowired
    private userRepo userRepo;


    @GetMapping("/getall")
    public ResponseEntity<List<Student>> getStudents(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName()+"---------------------------");
        List<Student> st = studentDetailsService.get_student();
        return new ResponseEntity<>(st, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student newStudent = studentDetailsService.save_student(student);
        System.out.println(newStudent.toString());
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    @GetMapping("/profile")
    public  User getUserProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByUsername(username);
    }

    @PostMapping("/getexams")
    public ResponseEntity<List<Exam>> getExamsOnDate(@RequestBody ExamRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();        
        User user = userRepo.findByUsername(userPrincipal.getUsername());   
        Student student = studentDetailsService.getStudentByUser(user);
        Date date;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = formatter.parse(request.getDate());
        } catch (ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Exam> ex = examService.getExamsUsingDate(date, student);
        return new ResponseEntity<>(ex, HttpStatus.OK); 
    }

    @PostMapping("/exam/{ExamID}")
    public List<ProvideQuestion> examQuestions(@PathVariable long ExamID){
        return examService.getQuestions(ExamID);
    }


    @PostMapping("/exam/{ExamID}/submit")
    public List<MarkQuestions> markQuestions(@RequestBody List<MarkQuestions> markQuestions){
        return examService.markQuestions(markQuestions);
    }


}
