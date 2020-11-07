package Praktikum_08_Code;

import java.awt.*;

public class LabyrinthServer implements CommandExecutor {

    private Graph<LabyrinthNode, Edge> labyrinth = new AdjListGraph<>(LabyrinthNode.class, Edge.class);
    private final double SCALE = 10;
    private ServerGraphics serverGraphics = new ServerGraphics();
    private int counter = 0;

    @Override
    public String execute(String command) throws Exception {
        parseCommand(command);
        labyrinth.getNodes().forEach(this::setEdges);

        //Labyrinth zeichnen
        serverGraphics.setColor(Color.LIGHT_GRAY);
        serverGraphics.fillRect(0.0, 0.0, 1, 1);

        //Weisse Wege zeichnen
        labyrinth.getNodes().forEach(x-> drawPath(x.from, x.to, x.mark));

        //Maus sucht schnellsten Ausgang
        String mouseRoute = findExit();

        return mouseRoute;
    }

    private void parseCommand(String command){

        String[] lines = command.split("\n");
        try {
            for (String line : lines) {
                String[] datas = line.split(" ");
                labyrinth.addNode(String.valueOf(counter));

                LabyrinthNode labyrinthNode = labyrinth.findNode(String.valueOf(counter));
                labyrinthNode.from = datas[0];
                labyrinthNode.to = datas[1];

                counter++;
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        counter -= 1;
    }

    private String findExit(){
        LabyrinthNode startNode = labyrinth.findNode(String.valueOf(0));
        LabyrinthNode goalNode = labyrinth.findNode(String.valueOf(counter));

        if(search(startNode.name, goalNode.name)){
            serverGraphics.setColor(Color.red);
            do{
                LabyrinthNode previous = (LabyrinthNode) goalNode.prev;
                drawPath(previous.from, previous.to, previous.mark);
                goalNode = previous;
            } while (goalNode.prev != null);
        }
        return serverGraphics.getTrace();
    }

    //Dist Gewicht herausfinden.
    private double getDist(LabyrinthNode node){
        double[] coordinates = getCoord(node.from, node.to);
        return (coordinates[1] == coordinates[3]) ? coordinates[2] - coordinates[0] : coordinates[3] - coordinates[1];
    }

    //Von den Folien
    private boolean search(String from, String to){
        LabyrinthNode<Edge> start = labyrinth.findNode(from);
        LabyrinthNode goal = labyrinth.findNode(to);
        start.mark = true;
        if(start == goal){
            return true;
        }

        for(Edge edge : start.getEdges()){
            LabyrinthNode successor = (LabyrinthNode) edge.dest;
            if(!successor.mark){
                successor.prev = start;
                if(search(successor.name, goal.name)){
                    return true;
                }
            }
        }
        start.mark = false;
        return false;
    }

    private double[] getCoord(String from, String to){
        double xh0 = from.charAt(0) - '0';
        double yh0 = from.charAt(2) - '0';
        double xh1 = to.charAt(0) - '0';
        double yh1 = to.charAt(2) - '0';
        double x0 = Math.min(xh0,xh1)/SCALE;
        double y0 = Math.min(yh0,yh1)/SCALE;
        double x1 = Math.max(xh0,xh1)/SCALE;
        double y1 = Math.max(yh0,yh1)/SCALE;
        return new double[]{x0, y0, x1, y1};
    }

    private void drawPath(String from, String to, boolean mouse) {
        double[] coordinates = getCoord(from ,to);
        double x0 = coordinates[0];
        double y0 = coordinates[1];
        double x1 = coordinates[2];
        double y1 = coordinates[3];
        double w = 1/SCALE;
        if (mouse){
            serverGraphics.setColor(Color.RED);
            serverGraphics.drawLine(x0+w/2,y0+w/2,x1+w/2,y1+w/2);
        }
        else {
            serverGraphics.setColor(Color.WHITE);
            if (y0 == y1)
                serverGraphics.fillRect(x0,y0,x1-x0+w,w);
            else
                serverGraphics.fillRect(x0,y0,w,y1-y0+w);
        }
    }

    private void setEdges(LabyrinthNode<Edge> node){
        labyrinth.getNodes().forEach(n -> {
            if(node.to.equals(n.from)){
                try {
                    labyrinth.addEdge(node.name, n.name, getDist(node));
                    labyrinth.addEdge(n.name, node.name, getDist(node));
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }
}
