package swing;

import model.Polozka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Podrobnosti extends JDialog {

    private JLabel zadajMeno;
    private JLabel zadajPriezvisko;
    private JLabel zadajUlicu;
    private JLabel zadajMesto;
    private JComboBox zadajStat;
    private JLabel zadajCislo;
    private JLabel predv;
    //String predv;
    private boolean zrusenie;
    public Podrobnosti(TelZoznam zoznam, Moznosti moznosti, Polozka polozka) {
        super(zoznam, true);
        switch (moznosti) {
            case PRIDAT:
                setTitle("Pridať položku");
                break;
            case UPRAVIT:
                setTitle("Upraviť položku");
                break;
            case ODSTRANIT:
                setTitle("Odstrániť položku");
                break;
            /*case DETAIL:
                setTitle("Podrobnosti o:" + zadajMeno);
                break;*/
        }
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        Container okno = getContentPane();
        okno.setLayout(new BoxLayout(okno, BoxLayout.PAGE_AXIS));
        JPanel udaje = new JPanel();
        udaje.setLayout(new BoxLayout(udaje, BoxLayout.LINE_AXIS));
        okno.add(udaje);

        JPanel stlpec1 = new JPanel();
        stlpec1.setLayout(new BoxLayout(stlpec1, BoxLayout.PAGE_AXIS));
        udaje.add(stlpec1);

        JPanel stlpec2 = new JPanel();
        stlpec2.setLayout(new BoxLayout(stlpec2, BoxLayout.PAGE_AXIS));
        udaje.add(stlpec2);

        JPanel meno = new JPanel();
        meno.setLayout(new FlowLayout(FlowLayout.RIGHT));
        stlpec1.add(meno);
        JLabel popisMeno = new JLabel("Meno:");
        meno.add(popisMeno);
        zadajMeno = new JLabel();
        meno.add(zadajMeno);

        JPanel priezvisko = new JPanel();
        priezvisko.setLayout(new FlowLayout(FlowLayout.RIGHT));
        stlpec1.add(priezvisko);
        JLabel popisPriezvisko = new JLabel("Priezvisko:");
        priezvisko.add(popisPriezvisko);
        zadajPriezvisko = new JLabel();
        priezvisko.add(zadajPriezvisko);

        JPanel adresa = new JPanel();
        adresa.setLayout(new FlowLayout(FlowLayout.RIGHT));
        stlpec1.add(adresa);
        JLabel popisUlica = new JLabel("Ulica:");
        adresa.add(popisUlica);
        zadajUlicu = new JLabel();
        adresa.add(zadajUlicu);

        JPanel mesto = new JPanel();
        mesto.setLayout(new FlowLayout(FlowLayout.RIGHT));
        stlpec2.add(mesto);
        JLabel popisMesto = new JLabel("Mesto:");
        mesto.add(popisMesto);
        zadajMesto = new JLabel();
        mesto.add(zadajMesto);

        JPanel stat = new JPanel();
        stat.setLayout(new FlowLayout(FlowLayout.RIGHT));
        stlpec2.add(stat);
        JLabel popisStat = new JLabel("Stat:");
        stat.add(popisStat);
        zadajStat = new JComboBox(new String[]{"Slovensko", "Česko", "Maďarsko", "Poľsko", "Rakúsko"});
        stat.add(zadajStat);

        //setTitle("podrobnosti o: ");

        //zamer - vybrat stat a zmeni sa predvolba automaticky
        zadajStat.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //if(zadajStat.contentsChanged()) zmena();
            }
        });


        JPanel telC = new JPanel();
        telC.setLayout(new FlowLayout(FlowLayout.RIGHT));
        stlpec2.add(telC);
        JLabel popisCislo = new JLabel("Telefónne č.:");
        /*switch (zadajStat.getSelectedItem().toString()) {

            case "Slovensko":
                predv = "+421";
                break;
            case "Cesko":
                predv = "+420";
                break;
            case "Madarsko":
                predv = "+36";
                break;
            case "Rakusko":
                predv = "+43";
                break;
            case "Polsko":
                predv = "+48";
                break;

        }*/
        JLabel predvolba = new JLabel(String.valueOf(predv));

        telC.add(popisCislo);
        telC.add(predvolba);
        zadajCislo = new JLabel();
        zadajCislo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char pismeno = e.getKeyChar();
                if (Character.isDigit(pismeno)) {
                    return;
                } else {
                    e.consume();
                }
            }
        });
        telC.add(zadajCislo);

        if(moznosti==Moznosti.DETAIL) {
            nacitajPolozku(polozka);
        }
        if(moznosti==Moznosti.DETAIL) {
            JButton podr = new JButton("Zmen!");
            podr.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (pridajPolozku(polozka)) {
                        zrusenie = false;
                        Podrobnosti.this.setVisible(false);
                    }
                }
            });



            pack();
        }




    }
    public boolean Zrusit() {
        return zrusenie;
    }private boolean pridajPolozku(Polozka pol){
        if(zadajMeno.getText().length()==0) {
            JOptionPane.showMessageDialog(this, "Nezadal si meno!");
            zadajMeno.requestFocus();
            return false;
        }
        if(zadajPriezvisko.getText().length()==0) {
            JOptionPane.showMessageDialog(this, "Nezadal si priezvisko!");
            zadajPriezvisko.requestFocus();
            return false;
        }
        if(zadajUlicu.getText().length()==0) {
            JOptionPane.showMessageDialog(this, "Nezadal si adresu!");
            zadajUlicu.requestFocus();
            return false;
        }
        if(zadajMesto.getText().length()==0) {
            JOptionPane.showMessageDialog(this, "Nezadal si mesto!");
            zadajMesto.requestFocus();
            return false;
        }
        if(zadajCislo.getText().length()==0) {
            JOptionPane.showMessageDialog(this, "Nezadal si cislo!");
            zadajCislo.requestFocus();
            return false;
        }
        pol.setMeno(zadajMeno.getText());
        pol.setPriezvisko(zadajPriezvisko.getText());
        pol.setAdresa(zadajUlicu.getText());
        pol.setMesto(zadajMesto.getText());
        pol.setStat(zadajStat.getSelectedItem());
        pol.setTelC(zadajCislo.getText());
        return true;
    }
    private void nacitajPolozku(Polozka pol){
        zadajMeno.setText(pol.getMeno());
        zadajPriezvisko.setText(pol.getPriezvisko());
        zadajUlicu.setText(pol.getAdresa());
        zadajMesto.setText(pol.getMesto());
        zadajStat.setSelectedItem(pol.getStat());
        zadajCislo.setText(pol.getTelC());
        switch (zadajStat.getSelectedItem().toString()) {

            case "Slovensko":
                predv.setText("+421");
                break;
            case "Cesko":
                predv.setText("+42");
                break;
            case "Madarsko":
                predv.setText("+36");
                break;
            case "Rakusko":
                predv.setText("+43");
                break;
            case "Polsko":
                predv.setText("+448");
                break;}

        setTitle("podrobnosti o: " + pol.getMeno()
                + " " + pol.getPriezvisko());
    }
}