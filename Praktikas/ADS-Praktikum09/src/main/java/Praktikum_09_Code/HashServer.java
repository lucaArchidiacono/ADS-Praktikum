package Praktikum_09_Code;

import java.util.HashMap;
import java.util.Map;

public class HashServer implements CommandExecutor {

    private Map<Integer, Competitor> competitors = new HashMap<>();
    private Map<Integer, Competitor> myHashTableCompetitors;
    private StringBuilder stringBuilder = new StringBuilder();
    private static final String errorMessage = "Please consider this syntax -> GET <Competitors-name>;<Competitors-birthyear>";

    @Override
    public String execute(String command) throws Exception {
        return executeWithMyHashtable(command);
    }

    private String executeWithHashMap(String command) {
        if (command.toUpperCase().startsWith("GET")) {
            //do the magic and collect/get the athlete
            String newCommand = command.replace("GET", "").trim().toUpperCase();
            if (newCommand.contains(";")) {
                String[] values = newCommand.split(";");
                Competitor competitor = new Competitor(values[0], Integer.parseInt(values[1]));
                clearStringBuilderWith(competitors.get(competitor.hashCode()));
            } else {
                clearStringBuilderWith(errorMessage);
            }
        } else {
            String[] lines = command.split("\n");
            try {
                for (String line : lines) {
                    String[] values = line.toUpperCase().split(";");
                    Competitor competitor = new Competitor(Integer.parseInt(values[0]), values[1], Integer.parseInt(values[2]), values[3], values[4]);
                    competitors.put(competitor.hashCode(), competitor);
                }
                clearStringBuilderWith(competitors.size()+" loaded");
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        return stringBuilder.append("\n").toString();
    }

    private String executeWithMyHashtable(String command) {
        if (command.toUpperCase().startsWith("GET")) {
            //do the magic and collect/get the athlete
            String newCommand = command.replace("GET", "").trim().toUpperCase();
            if (newCommand.contains(";")) {
                String[] values = newCommand.split(";");
                Competitor competitor = new Competitor(values[0], Integer.parseInt(values[1]));
                clearStringBuilderWith(myHashTableCompetitors.get(competitor.hashCode()));
            } else {
                clearStringBuilderWith(errorMessage);
            }
        } else {
            String[] lines = command.split("\n");
            myHashTableCompetitors = new MyHashtable<>(lines.length);
            try {
                for (String line : lines) {
                    String[] values = line.toUpperCase().split(";");
                    Competitor competitor = new Competitor(Integer.parseInt(values[0]), values[1], Integer.parseInt(values[2]), values[3], values[4]);
                    myHashTableCompetitors.put(competitor.hashCode(), competitor);
                }
                clearStringBuilderWith(myHashTableCompetitors.size()+" loaded");
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        return stringBuilder.append("\n").toString();
    }

    private void clearStringBuilderWith(Object obj) {
        stringBuilder.replace(0, stringBuilder.length(), (obj == null ? errorMessage : obj.toString()));
    }
    private void clearStringBuilderWith(String message) {
        stringBuilder.replace(0, stringBuilder.length(), message);
    }
}

