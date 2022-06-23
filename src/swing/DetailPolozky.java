package swing;

import model.Polozka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DetailPolozky extends JDialog {

    private JTextField zadajMeno;
    private JTextField zadajPriezvisko;
    private JTextField zadajUlicu;
    private JTextField zadajMesto;
    private JComboBox zadajStat;
    private JTextField zadajCislo;

    String predv;
    private boolean zrusenie;
    public DetailPolozky(TelZoznam zoznam, Moznosti moznosti,  Polozka polozka){
        super(zoznam, true);
        switch(moznosti) {
            case PRIDAT:
                setTitle("Pridať položku");
                break;
            case UPRAVIT:
                setTitle("Upraviť položku");
                break;
            case ODSTRANIT:
                setTitle("Odstrániť položku");
                break;


        }
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
        zadajMeno = new JTextField(20);
        meno.add(zadajMeno);

        JPanel priezvisko = new JPanel();
        priezvisko.setLayout(new FlowLayout(FlowLayout.RIGHT));
        stlpec1.add(priezvisko);
        JLabel popisPriezvisko = new JLabel("Priezvisko:");
        priezvisko.add(popisPriezvisko);
        zadajPriezvisko = new JTextField(20);
        priezvisko.add(zadajPriezvisko);

        JPanel adresa = new JPanel();
        adresa.setLayout(new FlowLayout(FlowLayout.RIGHT));
        stlpec1.add(adresa);
        JLabel popisUlica = new JLabel("Ulica:");
        adresa.add(popisUlica);
        zadajUlicu = new JTextField(20);
        adresa.add(zadajUlicu);

        JPanel mesto = new JPanel();
        mesto.setLayout(new FlowLayout(FlowLayout.RIGHT));
        stlpec2.add(mesto);
        JLabel popisMesto = new JLabel("Mesto:");
        mesto.add(popisMesto);
        zadajMesto = new JTextField(10);
        mesto.add(zadajMesto);

        JPanel stat = new JPanel();
        stat.setLayout(new FlowLayout(FlowLayout.RIGHT));
        stlpec2.add(stat);
        JLabel popisStat = new JLabel("Stat:");
        stat.add(popisStat);
        zadajStat = new JComboBox(new String[] {"Slovensko", "Česko", "Maďarsko", "Poľsko", "Rakúsko"});
        stat.add(zadajStat);


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
            switch (zadajStat.getSelectedItem().toString()) {
                //zadajStat.getSelectedItem()
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

        }
        JLabel predvolba  =new JLabel(String.valueOf(predv));

        telC.add(popisCislo);telC.add(predvolba);
        zadajCislo = new JTextField(10);
        zadajCislo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char pismeno = e.getKeyChar();
                if(Character.isDigit(pismeno)) {
                    return;
                } else {
                    e.consume();
                }
            }
        });
        telC.add(zadajCislo);


        JPanel tlacidla = new JPanel();
        tlacidla.setLayout(new FlowLayout());
        okno.add(tlacidla);

        if(moznosti==Moznosti.UPRAVIT) {
            nacitajPolozku(polozka);
        }


        if(moznosti==Moznosti.PRIDAT) {
            JButton vytvor = new JButton("Vytvor!");
            vytvor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (pridajPolozku(polozka)){
                        zrusenie = false;
                        DetailPolozky.this.setVisible(false);
                    }
                }
            });
            tlacidla.add(vytvor);
        }

        if(moznosti==Moznosti.UPRAVIT) {
            JButton uprav = new JButton("Zmen!");
            uprav.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (pridajPolozku(polozka)) {
                        zrusenie = false;
                        DetailPolozky.this.setVisible(false);
                    }
                }
            });
            tlacidla.add(uprav);
        }
        if(moznosti==Moznosti.ODSTRANIT) {
            JButton zmaz = new JButton("Vymaz!");
            zmaz.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (pridajPolozku(polozka)) {
                        zrusenie = false;
                        DetailPolozky.this.setVisible(false);
                    }
                }
            });
            tlacidla.add(zmaz);
        }

        JButton zrus = new JButton("Zrušiť!");
        zrus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    zrusenie =true;
                    DetailPolozky.this.setVisible(false);
                }
        });
        tlacidla.add(zrus);

        pack();


    }
    public boolean Zrusit() {
        return zrusenie;
    }
    private boolean pridajPolozku(Polozka pol){
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
    }
}
