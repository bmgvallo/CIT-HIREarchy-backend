// src/main/java/com/cithirearchy/cithirearchy/util/DepartmentCourseMapper.java
package com.cithirearchy.cithirearchy.util;

import java.util.*;

public class DepartmentCourseMapper {
    
    private static final Map<String, List<String>> DEPARTMENT_COURSES = new HashMap<>();
    
    static {
        // CEA Courses
        DEPARTMENT_COURSES.put("CEA", Arrays.asList(
            "BS Architecture",
            "BS Chemical Engineering",
            "BS Civil Engineering",
            "BS Computer Engineering",
            "BS Electrical Engineering",
            "BS Electronics Engineering",
            "BS Industrial Engineering",
            "BS Mechanical Engineering",
            "BS Mining Engineering"
        ));
        
        // CCS Courses
        DEPARTMENT_COURSES.put("CCS", Arrays.asList(
            "BS Information Technology",
            "BS Computer Science"
        ));
        
        // CASE Courses
        DEPARTMENT_COURSES.put("CASE", Arrays.asList(
            "AB Communication",
            "AB English with Applied Linguistics",
            "Bachelor of Elementary Education",
            "Bachelor of Secondary Education",
            "Bachelor of Multimedia Arts",
            "BS Biology",
            "BS Math with Applied Industrial Mathematics",
            "BS Psychology"
        ));
        
        // CMBA Courses
        DEPARTMENT_COURSES.put("CMBA", Arrays.asList(
            "BS Accountancy",
            "BS Accounting Information Systems",
            "BS Management Accounting",
            "BS Business Administration",
            "BS Hospitality Management",
            "BS Tourism Management",
            "BS Office Administration",
            "Bachelor in Public Administration"
        ));
        
        // CNAHS Courses
        DEPARTMENT_COURSES.put("CNAHS", Arrays.asList(
            "BS Nursing",
            "BS Pharmacy",
            "BS Medical Technology"
        ));
        
        // CCJ Courses
        DEPARTMENT_COURSES.put("CCJ", Arrays.asList(
            "BS Criminology"
        ));
    }
    
    public static String getDepartmentForCourse(String course) {
        for (Map.Entry<String, List<String>> entry : DEPARTMENT_COURSES.entrySet()) {
            if (entry.getValue().contains(course)) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    public static List<String> getCoursesForDepartment(String department) {
        return DEPARTMENT_COURSES.getOrDefault(department.toUpperCase(), new ArrayList<>());
    }
    
    public static boolean isCourseInDepartment(String course, String department) {
        List<String> courses = DEPARTMENT_COURSES.get(department.toUpperCase());
        return courses != null && courses.contains(course);
    }
}