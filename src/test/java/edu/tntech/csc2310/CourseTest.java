package edu.tntech.csc2310;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CourseTest {

    Course data;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getSubject() throws IOException {
        try {
            data = new Course("CSC", "1300", "202180");
            assertEquals("Subject", "CSC", data.getSubject());
            data = new Course("COMM", "1020", "202180");
            assertEquals("Subject", "COMM", data.getSubject());
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void courseNotExists() throws IOException {
        try {
            data = new Course("NURS", "4575", "202180");
            assertNull("Non existent",data.getSubject());
            assertNull("Non existent", data.getNumber());
            assertEquals("Non existent", -1, data.getCredits());
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
            assert(true);
        }

    }

    @Test
    public void getSubjectNumberTrim() throws IOException {
        try {
            data = new Course("CSC ", "1300", "202180");
            assertEquals("Subject", "CSC", data.getSubject());
            data = new Course(" COMM ", " 1020", " 202180");
            assertEquals("Subject", "COMM", data.getSubject());
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getCatalogisNum() throws IOException {
        try {
            data = new Course("COMM ", "1020", "a202180");
        } catch (NumberFormatException | CourseNotFoundException ex){
            assertNull(data);
        }
    }

    @Test
    public void getNumber() throws IOException {
        try {
            data = new Course("CSC", "1300", "202180");
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals("Subject", "1300", data.getNumber());
    }

    @Test
    public void getNumberTrim() throws IOException {
        try {
            data = new Course("COMM ", " 1020 ", "202180");
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals("Number", "1020", data.getNumber());
    }

    @Test
    public void getTitle() throws IOException {
        try {
            data = new Course("CSC", "1300", "202180");
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals("Title", "Intro/Prob Solving-Comp Prog", data.getTitle());
    }

    @Test
    public void getNullTitle() throws IOException {
        try {
            data = new Course("CSC", "1000", "202180");
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
            assert(true);
        }

    }

    @Test
    public void getPrerequisites() throws IOException {

        // CSC 1300 has three course-based pre-requisites
        try {
            data = new Course("CSC", "1300", "202180");
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
        }
        String[] eresults = {"CSC 1200","MATH 1845","MATH 1910"};
        String[] aresults = data.getPrerequisites();
        for (int i = 0; i < eresults.length; i++) {
            assertEquals("prereq list", eresults[i], aresults[i].trim());
        }
        // MATH 1910 has three course-based pre-requisites and two test-based pre-requisites
        try {
            data = new Course("MATH", "1910", "202180");
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
        }
        String[] e0results = {"MATH 1710","MATH 1720","MATH 1730", "A02 27 to 36", "S02 610 to 800", "S12B 630 to 800"};
        aresults = data.getPrerequisites();
        for (int i = 0; i < e0results.length; i++) {
            assertEquals("prereq list", e0results[i], aresults[i].trim());
        }
    }


    @Test
    public void getNoPrereqs() throws IOException {
        try {
            data = new Course("NURS", "4500", "202180");
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
        }
        String[] aresults = data.getPrerequisites();
        assertNull("Course with no prereq", aresults);
    }

    @Test
    public void getNoCoursePrereqs() throws IOException {
        try {
            data = new Course("NURS", "4575", "202180");
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
            assertTrue(true);
        }
    }

    @Test
    public void getCRH() throws IOException {

        try {
            data = new Course("CSC", "3300", "202180");
            data = new Course("CSC", "3500", "202180");
            assertTrue("Credit Hours", 1 == data.getCredits());
            data = new Course("CSC", "4100", "202180");
            assertTrue("Credit Hours", 3 == data.getCredits());
            assertTrue("Credit Hours", 3 == data.getCredits());
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getCRHNoCourse() throws IOException {
        try {
            data = new Course("NURS", "4575", "202180");
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
            assertTrue(true);
        }
    }

}