/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

/**
 *
 * @author jussiste
 */
public class Course {
    private String name;
    private int[] exercises;
    private String fullName;
    private String term;
    private int year;

    public Course(String name, int[] exercises, String fullName, String term, int year) {
        this.name = name;
        this.exercises = exercises;
        this.fullName = fullName;
        this.term = term;
        this.year = year;
    }

    public String getTerm() {
        return term;
    }

    public int getYear() {
        return year;
    }
            


    public String getFullName() {
        return fullName;
    }

    public String getCourse() {
        return name;
    }

    public void setCourse(String course) {
        this.name = course;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }
    
}
