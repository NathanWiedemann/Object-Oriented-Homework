package edu.tntech.csc2310;


public class CoursePRCourse {

    private final long id;

    private final Course attribute;

    public CoursePRCourse(long id, Course course) {
        this.id = id;
        this.attribute = course;
    }

    public Course getAttribute(){
        return this.attribute;
    }

    public long getId() {
        return id;
    }

}
