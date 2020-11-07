/**
 * @author K. Rege
 * @version 1.0 -- Experimentierkasten
 */
package Praktikum_08_Code;

public class ExBox {

    public static void main(String[] args) throws Exception {
        ExBoxFrame f = new ExBoxFrame();
        f.setLocationRelativeTo(null);  
        f.setVisible(true);
    }

    private static void p(int i)
    {
        if (i<10){
            p(i+1);
            System.out.println(i);
        }
    }
}