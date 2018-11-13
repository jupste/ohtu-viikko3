package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
        url= "https://studies.cs.helsinki.fi/courses/courseinfo";
        String courseText= Request.Get(url).execute().returnContent().asString();
        
        url= "https://studies.cs.helsinki.fi/courses/rails2018/stats";
        String railsText= Request.Get(url).execute().returnContent().asString();
        url="https://studies.cs.helsinki.fi/courses/ohtu2018/stats";
        String ohtuText=Request.Get(url).execute().returnContent().asString();
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course[] courses= mapper.fromJson(courseText, Course[].class);
        JsonParser parser = new JsonParser();
        JsonObject parsittuRailsData = parser.parse(railsText).getAsJsonObject();
        JsonObject parsittuOhtuData = parser.parse(ohtuText).getAsJsonObject();
        ArrayList<CourseData> ohtuWeeks=new ArrayList<>();
        ArrayList<CourseData> railsWeeks= new ArrayList<>();
        //System.out.println(parsittuOhtuData.get("1").toString());
        for(int i=1; i<=3; i++){
            CourseData week= mapper.fromJson(parsittuOhtuData.get(Integer.toString(i)).toString(), CourseData.class);
            ohtuWeeks.add(week);
        }
        for(int i=1; i<=7; i++){
            CourseData week= mapper.fromJson(parsittuRailsData.get(Integer.toString(i)).toString(), CourseData.class);
            railsWeeks.add(week);
        }
        
        for(Submission s: subs){
            System.out.println(s);
        }
        System.out.println("Opiskelijanumero: " +studentNr);
        for (Course c : courses) {
            System.out.println("Kurssi: " + c.getFullName() + " "+c.getTerm()+" " + c.getYear());
            int tyomaara=0;
            int maxtyo=0;
            for(Submission sub: subs){
                if(sub.getCource().equals(c.getCourse())){
                    System.out.println("viikko: " +sub.getWeek());
                    System.out.print(sub.alternativeString());
                    System.out.println(" tehtyjä tehtäviä " + sub.getExercises().length+ "/" +c.getExercises()[sub.getWeek()]);
                }
            }
            if(c.getCourse().equals("ohtu2018")){
                int palautukset=0, tehtavat=0, aikaa=0;
                for(CourseData data:ohtuWeeks){
                    palautukset+=data.getStudents();
                    tehtavat+=data.getTotalWeekly();
                    aikaa+=data.getHour_total();
                }
                System.out.println("kurssilla yhteensä " + palautukset + " palautusta, palautettuja tehtäviä "+ tehtavat+ " aikaa käytetty yhteensä "+ aikaa + " tuntia");
            }
            if(c.getCourse().equals("rails2018")){
                int palautukset=0, tehtavat=0, aikaa=0;
                for(CourseData data:railsWeeks){
                    palautukset+=data.getStudents();
                    tehtavat+=data.getTotalWeekly();
                    aikaa+=data.getHour_total();
                }
                System.out.println("kurssilla yhteensä " + palautukset + " palautusta, palautettuja tehtäviä "+ tehtavat+ " aikaa käytetty yhteensä "+ aikaa + " tuntia");
            }
            System.out.println("");
            
        }
        
    }
}