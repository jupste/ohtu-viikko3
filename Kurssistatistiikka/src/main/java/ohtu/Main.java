package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

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
        System.out.println("json-muotoinen data:");
        System.out.println( courseText);
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course[] courses= mapper.fromJson(courseText, Course[].class);
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
        }
        
    }
}