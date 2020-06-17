import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.json.*;
import java.util.List;

public class Score {
    private List<ScoreName> li = new ArrayList<>();


    private static Score s = null;

    public static synchronized Score getImplementation() {
        if (s == null) {
            s = new Score();
            s.readScore();
        }

        return s;
    }

    private Score() {

    }

    public void addScore(String name, int points) {
        var s = new ScoreName();

        s.name = name;
        s.score = points;

        li.add(s);

        EventQueue.invokeLater(this::saveScore);
    }

    private void readScore() {
        try {
            li.clear();

            var o = new FileInputStream("scores.txt");

            JsonReader reader = Json.createReader(o);

            var personObject = reader.readArray();

            reader.close();

            for (JsonValue jsonValue : personObject) {
                var s = new ScoreName();

                s.name = jsonValue.asJsonObject().getString("Name");
                s.score = jsonValue.asJsonObject().getInt("Score");

                li.add(s);
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void saveScore() {
        try {
            var o = new FileOutputStream("scores.txt");

            JsonWriter writer = Json.createWriter(o);

            JsonBuilderFactory jsonBuilderFactory = Json.createBuilderFactory(null);
            var jsonObjectBuilder = jsonBuilderFactory.createArrayBuilder();

            for (var v : li) {
                var enc = Json.createObjectBuilder();

                enc.add("Name", v.name);
                enc.add("Score", v.score);

                jsonObjectBuilder.add(enc);
            }

            var jsonObject = jsonObjectBuilder.build();

            writer.writeArray(jsonObject);

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public class ScoreName {
        public String name;
        public int score;
    }
}
