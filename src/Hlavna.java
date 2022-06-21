import swing.TelZoznam;

public class Hlavna{
    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TelZoznam gui = new TelZoznam();
                gui.setVisible(true);
            }
        });

    }
}
