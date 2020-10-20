package Praktikum_04_Code;

import java.util.ArrayList;
import java.util.List;

public class HanoiServer implements CommandExecutor {
    private List<String> hanoiList; 
    @Override
    public String execute(String command) throws Exception {
        hanoiList = new ArrayList<>();
        moveDisk(Integer.valueOf(command), 'A', 'B', 'C');
        StringBuilder list = new StringBuilder();
        for (String hanoi: hanoiList) {
            list.append(hanoi);
        } 
        return list.toString();
    }
    

    public void moveDisk(int n, char from, char to, char help) {
        if (n > 0) {
            moveDisk(n-1, from, help, to);

            hanoiList.add("move " + from + " to " + to + "\n");

            moveDisk(n-1, help, to, from); 
        }
    }
}
