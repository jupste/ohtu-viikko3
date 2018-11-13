package ohtu;

import java.util.Objects;

public class Submission {
    private int week;
    private int hours;
    private int[]  exercises;
    private String course;

    public Submission(int week, int hours, int[] exercises, String course) {
        this.week = week;
        this.hours = hours;
        this.exercises = exercises;
        this.course = course;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public String getCource() {
        return course;
    }

    public void setCource(String cource) {
        this.course = cource;
    }
    

    public int getWeek() {
        return week;
    }
    public String exercisesInString(){
        String ret="";
        for(int i: this.exercises){
            ret+=i;
            ret+=", ";
        }
        return ret;
    }
    @Override
    public String toString() {
        return course+" viikko: "+week + " aikaa kului " + hours +" tuntia "+ "tehdyt tehtävät: " + exercisesInString();
    }
    
    public String alternativeString(){
        return " aikaa kului " + hours +" tuntia "+ "tehdyt tehtävät: " + exercisesInString();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Submission other = (Submission) obj;
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        return true;
    }
    
}