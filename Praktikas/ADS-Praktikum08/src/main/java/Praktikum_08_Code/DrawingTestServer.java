package Praktikum_08_Code;

import javax.print.DocFlavor;
import java.awt.*;

public class DrawingTestServer implements CommandExecutor {

    @Override
    public String execute(String command) throws Exception {
        ServerGraphics serverGraphics = new ServerGraphics();

        return drawRedRect(serverGraphics) + drawBlackTriangle(serverGraphics);
    }

    public String drawBlackTriangle(ServerGraphics serverGraphics) {
        serverGraphics.setColor(Color.BLACK);
        serverGraphics.drawLine(0.1, 0.1, 0.9, 0.1);
        serverGraphics.drawLine(0.9, 0.1, 0.5, 0.9);
        serverGraphics.drawLine(0.5, 0.9, 0.1, 0.1);
        return serverGraphics.getTrace();
    }

    public String drawRedRect(ServerGraphics serverGraphics) {
        serverGraphics.setColor(Color.red);
        serverGraphics.fillRect(0.1, 0.1, 0.8, 0.8);
        return serverGraphics.getTrace();
    }
}
