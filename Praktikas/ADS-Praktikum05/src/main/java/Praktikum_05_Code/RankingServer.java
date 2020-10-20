package Praktikum_05_Code;

import java.util.ArrayList;
import java.util.List;

public class RankingServer implements CommandExecutor {
    private final static String CSV_NOTATION = ";";
    private final static String LINE_BREAKS = "\n";
    @Override
    public String execute(String command) {
        Tree<Competitor_ToDo> obj = objectParser(command);
        Visitor<Competitor_ToDo> v = new RankedVisitor<>();
        obj.traversal().inorder(v);
        return v.toString();
    }


    private Tree<Competitor_ToDo> objectParser(String csvContent) {
        Tree<Competitor_ToDo> comps = new SortedBinaryTree<>();
        String[] lines = csvContent.split(LINE_BREAKS);
        int counter = 0;
        String[] tempArray = new String[6];

        for (String line : lines) {
            String[] datas = line.split(CSV_NOTATION);
            for (String data : datas) {
                tempArray[counter] = data;
                counter++;
            }
            comps.add(new Competitor_ToDo(Integer.parseInt(tempArray[0]), tempArray[1], Integer.parseInt(tempArray[2]), tempArray[3], tempArray[4]));
            counter = 0;
        }
        return comps;
    }
}

class RankedVisitor<T extends Comparable<T>> implements Visitor<T> {
    StringBuilder output;

    RankedVisitor() {
        output = new StringBuilder();
    }

    public void visit(T s) {
        output.append(s+"\n");
    }

    public String toString() {
        return output.toString();
    }
}