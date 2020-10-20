package Praktikum_03_Code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RankingServer implements CommandExecutor {
    private final static String CSV_NOTATION = ";";
    private final static String LINE_BREAKS = "\n";
    @Override
    public String execute(String command) {
        List<Competitor_ToDo> rangliste = objectParser(command);
        List<Competitor_ToDo> namensListe = objectParser(command);

        Collections.sort(rangliste);
        int ranking = 1;

        //Hier wird ein String kreiert mit der Ranking-Rangliste
        StringBuilder ranglisteAlsString = new StringBuilder();
        for (Competitor_ToDo competitor: rangliste) {
            competitor.setRank(ranking);
            ranglisteAlsString.append(competitor.toString());
            ranglisteAlsString.append("\n");
            ranking++;
        }

        rangliste.sort((o1, o2) -> {
            if (o1.equals(o2)) {
                return Integer.compare(o1.getJg(), o2.getJg());
            } else {
                return o1.getName().compareTo(o2.getName());
            }
        });


        //Hier wird ein String kreiert mit der Namens-Rangliste
        StringBuilder namensListeAlsString = new StringBuilder();
        for (Competitor_ToDo competitor: rangliste) {
            namensListeAlsString.append(competitor.toString());
            namensListeAlsString.append("\n");
        }

        //Hier kann man entweder die Rangliste als String retournieren oder die Namens-Rangliste
        return namensListeAlsString.toString();
    }
    

    private List<Competitor_ToDo> objectParser(String csvContent) {
        List<Competitor_ToDo> competitors = new ArrayList<>();
        String[] lines = csvContent.split(LINE_BREAKS);
        int counter = 0;
        String[] tempArray = new String[6];

        for (String line : lines) {
            String[] datas = line.split(CSV_NOTATION);
            for (String data : datas) {
                tempArray[counter] = data;
                counter++;
            }
            competitors.add(new Competitor_ToDo(Integer.parseInt(tempArray[0]), tempArray[1], Integer.parseInt(tempArray[2]), tempArray[3], tempArray[4]));
            counter = 0;
        }
        return competitors;
    }
}
