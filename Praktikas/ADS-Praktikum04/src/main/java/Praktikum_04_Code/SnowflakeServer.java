package Praktikum_04_Code;

public class SnowflakeServer implements CommandExecutor {
    private Turtle turtle;
    @Override
    public String execute(String command) throws Exception {
        turtle = new Turtle(0.5,0);
        zeichneSchneeFlocke(Integer.valueOf(command), 0.5);
        return turtle.getTrace();
    }

    public void zeichneSchneeFlocke(int stufe, double dist) {
        turtle.turn(120);
        schneeflocke(stufe, dist);
        for (int i = 0; i < 2; i++){
            turtle.turn(-120);
            schneeflocke(stufe, dist);
        }
    }

    public void schneeflocke(int stufe, double dist) {
        if (stufe == 0) {
            turtle.move(dist);
        } else {
            stufe--;
            dist = dist/3;
            schneeflocke(stufe, dist);
            turtle.turn(60);
            schneeflocke(stufe, dist);
            turtle.turn(-120);
            schneeflocke(stufe, dist);
            turtle.turn(60);
            schneeflocke(stufe, dist);
        }
    }
}
