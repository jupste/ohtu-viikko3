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
public class CourseData {
    private int students;
    private double hour_total;
    private int exercise_total;	
    private Integer [] exercises;
    @Override
    public String toString() {
        return "CourseData{" + "students=" + students + ", hour_total=" + hour_total + ", exercise_total=" + exercise_total + '}';
    }
    public int getTotalWeekly(){
        int ret=0;
        for(Integer i: exercises){
            if(i==null){
                continue;
            }
            ret+=i;
        }
        return ret;
    }
    public int getStudents() {
        return students;
    }

    public void setStudents(int students) {
        this.students = students;
    }

    public double getHour_total() {
        return hour_total;
    }

    public void setHour_total(int hour_total) {
        this.hour_total = hour_total;
    }

    public int getExercise_total() {
        return exercise_total;
    }

    public void setExercise_total(int exercise_total) {
        this.exercise_total = exercise_total;
    }

    public CourseData(int students, double hour_total, int exercise_total, Integer[] exercises) {
        this.students = students;
        this.hour_total = hour_total;
        this.exercise_total = exercise_total;
        this.exercises = exercises;
    }


}
