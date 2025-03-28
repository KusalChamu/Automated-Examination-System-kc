package com.auto.exam.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.auto.exam.Dto.ExamRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auto.exam.Dto.CoursesForLecture;
import com.auto.exam.Dto.ExamAdding;
import com.auto.exam.Model.Course;
import com.auto.exam.Model.Exam;
import com.auto.exam.Dto.ExamRequest;
import com.auto.exam.Dto.GenQuestion;
import com.auto.exam.Model.SendingExam;
import com.auto.exam.service.examService;
import com.auto.exam.service.questionService;
import com.auto.exam.Model.CourseOffering;
import com.auto.exam.repo.courseOfferingRepo;
import com.auto.exam.repo.questionRepo;

import jakarta.transaction.Transactional;

import com.auto.exam.service.courseService;



@RestController
@CrossOrigin
@RequestMapping("/lecturer")
public class lecturerController {

    private examService examService;
    private courseService courseService;
    private courseOfferingRepo courseOfferingRepo;
    private questionService questionService;

    
    @Autowired
    public lecturerController(examService examService, courseService courseService, courseOfferingRepo courseOfferingRepo, questionService questionService) {
        this.courseService = courseService;
        this.questionService = questionService;
        this.courseOfferingRepo = courseOfferingRepo;
        this.examService = examService;
    }
    

    @PostMapping("/getExamsByDate")
    public ResponseEntity<List<SendingExam>> getExamsOnDate_ACLecture(@RequestBody ExamRequest request) {
        List<SendingExam> ex = examService.getExamsUsingDateAndLecture(request);
        return new ResponseEntity<>(ex, HttpStatus.OK); 
    }
    // {
    //     "date": "2025-06-1 09:00:00"
    // }

    @GetMapping("/getCourses")
    @Transactional
    public ResponseEntity<List<CoursesForLecture>> getCourses() {
        List<CoursesForLecture> courses = courseService.getCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    // [
    // {
    //     "courseId": 1,
    //     "courseName": "Introduction to CS",
    //     "courseCode": "CS101",
    //     "credits": 3,
    //     "offeringId": 1
    // }
    // ]

    @PostMapping("/addExam")
    public ResponseEntity<Long> addExam(@RequestBody Map<String, Object> payload) {
        try {
            Long savedExamId = examService.addExam(payload);
            return new ResponseEntity<>(savedExamId, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    // {
    //     "Offering_ID": 1,
    //     "startDateTime": "2025-06-01 09:00:00",
    //     "duration": 60,
    //     "passingCriteria": 50,
    //     "type": 1,
    //     "totalMarks": 100
    // }


    @PostMapping("/addQuestions")
    public ResponseEntity<List<Long>> addQuestions(@RequestBody Map<String, Object> payload) {
        try {
            List<Long> savedExamId = questionService.addQuestions(payload);
            return new ResponseEntity<>(savedExamId, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    // {
    //     "examId": 32,
    //     "questions": [
    //       {
    //         "question": "What is the capital of France?",
    //         "marks": 5,
    //         "answer": "Paris"
    //       },
    //       {
    //         "question": "Solve: 5 + 3",
    //         "marks": 2,
    //         "answer": "8"
    //       },
    //       {
    //         "question": "What is the chemical symbol for water?",
    //         "marks": 3,
    //         "answer": "H2O"
    //       }
    //     ]
    //   }

    @PostMapping("/generateExamAi")
    public ResponseEntity<List<GenQuestion>> generateExamAi(@RequestBody Map<String, Object> payload) {
        try {
            List<GenQuestion> savedExamId = questionService.generateQuestions(payload);
            return new ResponseEntity<>(savedExamId, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}