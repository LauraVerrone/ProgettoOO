package gui;

import controller.Controller;
import model.enumerazioni.StatoSalute;
import model.enumerazioni.Taglia;
import model.enumerazioni.Temperamento;

import javax.swing.*;
import java.awt.*;

public class AggiungiAnimalePanel extends JPanel {
    private JPanel aggiungiAnimaliPanel;
    private JLabel title;
    private JComboBox<String> tipoAnimaleComboBox;
    private JTextField idAnimaleField;
    private JTextField nomeField;
    private JTextField sessoField;
    private JComboBox<Temperamento> temperamentoComboBox;
    private JTextField idLibrettoField;
    private JTextField razzaField;
    private JTextField vaccinazioniField;
    private JComboBox<StatoSalute> statoSaluteComboBox;
    private JTextField etaStimataField;
    private CardLayout datiSpecificiLayout;
    private JComboBox<Taglia> tagliaComboBox;
    private JCheckBox indoorCheckBox;

    private JButton salvaButton;
    private JButton indietroButton;
    private JPanel datiSpecificiPanel;
    private JLabel labelAnimale;
    private JLabel labelIdAnimale;
    private JLabel labelNome;
    private JLabel labelSesso;
    private JLabel labelTemperamento;
    private JLabel labelLibretto;
    private JLabel labelRazza;
    private JLabel labelVaccinazioni;
    private JLabel labelSano;
    private JLabel labelEta;


    public AggiungiAnimalePanel(Controller controller) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(title);
        JPanel formPanel = new JPanel(new GridLayout(11, 2, 8, 8));

        creaPannelloDatiSpecifici();

        formPanel.add(labelAnimale);
        formPanel.add(tipoAnimaleComboBox);
        formPanel.add(labelIdAnimale);
        formPanel.add(idAnimaleField);
        formPanel.add(labelNome);
        formPanel.add(nomeField);
        formPanel.add(labelSesso);
        formPanel.add(sessoField);
        formPanel.add(labelTemperamento);
        formPanel.add(temperamentoComboBox);
        formPanel.add(labelLibretto);
        formPanel.add(idLibrettoField);
        formPanel.add(labelRazza);
        formPanel.add(razzaField);
        formPanel.add(labelVaccinazioni);
        formPanel.add(vaccinazioniField);
        formPanel.add(labelSano);
        formPanel.add(statoSaluteComboBox);
        formPanel.add(labelEta);
        formPanel.add(etaStimataField);
        formPanel.add(new JLabel("Dati specifici"));
        formPanel.add(datiSpecificiPanel);


        add(formPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        salvaButton.addActionListener(e -> salvaAnimale(controller));
        indietroButton.addActionListener(e -> controller.goToHome());

        buttonsPanel.add(salvaButton);
        buttonsPanel.add(indietroButton);
        add(buttonsPanel, BorderLayout.SOUTH);

        tipoAnimaleComboBox.addActionListener(e -> aggiornaDatoSpecifico());
        aggiornaDatoSpecifico();



    }

    private void creaPannelloDatiSpecifici() {
        tagliaComboBox = new JComboBox<>(Taglia.values());
        indoorCheckBox = new JCheckBox("Gatto indoor / da appartamento");

        datiSpecificiLayout = new CardLayout();
        datiSpecificiPanel = new JPanel(datiSpecificiLayout);

        JPanel canePanel = new JPanel(new GridLayout(1, 2, 8, 8));
        canePanel.add(new JLabel("Taglia cane"));
        canePanel.add(tagliaComboBox);

        JPanel gattoPanel = new JPanel(new GridLayout(1, 1, 8, 8));
        gattoPanel.add(indoorCheckBox);

        datiSpecificiPanel.add(canePanel, "Cane");
        datiSpecificiPanel.add(gattoPanel, "Gatto");

        datiSpecificiPanel.setPreferredSize(new Dimension(300, 40));
    }

    private void aggiornaDatoSpecifico() {
            String tipoAnimale = (String) tipoAnimaleComboBox.getSelectedItem();
            datiSpecificiLayout.show(datiSpecificiPanel, tipoAnimale);
    }

    private void salvaAnimale(Controller controller) {
        try {
            String tipoAnimale = (String) tipoAnimaleComboBox.getSelectedItem();
            String tagliaTesto = null;
            boolean indoor = false;

            if ("Cane".equals(tipoAnimale)) {
                tagliaTesto = String.valueOf(tagliaComboBox.getSelectedItem());
            } else if ("Gatto".equals(tipoAnimale)) {
                indoor = indoorCheckBox.isSelected();
            }

            String messaggio = controller.registraAnimale(
                    tipoAnimale,
                    idAnimaleField.getText(),
                    nomeField.getText(),
                    sessoField.getText(),
                    String.valueOf(temperamentoComboBox.getSelectedItem()),
                    idLibrettoField.getText(),
                    razzaField.getText(),
                    vaccinazioniField.getText(),
                    String.valueOf(statoSaluteComboBox.getSelectedItem()),
                    etaStimataField.getText(),
                    tagliaTesto,
                    indoor
                );
                JOptionPane.showMessageDialog(this, messaggio);
                svuotaCampi();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
        private void svuotaCampi() {
            idAnimaleField.setText("");
            nomeField.setText("");
            sessoField.setText("");
            idLibrettoField.setText("");
            razzaField.setText("");
            vaccinazioniField.setText("");
            etaStimataField.setText("");
            indoorCheckBox.setSelected(false);
        }


    }

