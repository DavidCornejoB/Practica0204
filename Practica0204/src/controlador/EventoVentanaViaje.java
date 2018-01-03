/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.persistence.RollbackException;
import javax.swing.JOptionPane;
import modelo.Viaje;
import vista.VentanaViaje;

/**
 *
 * @author David Cornejo
 */
public class EventoVentanaViaje implements ActionListener {

    private VentanaViaje vViaje;

    public EventoVentanaViaje(VentanaViaje vViaje) {
        this.vViaje = vViaje;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(this.vViaje.getbGuardar())) {
            try {
                try {
                    long id = Long.parseLong(this.vViaje.getTxtList().get(0).getText());
                    int compania = this.vViaje.getComboBox().getSelectedIndex();
                    String destino = this.vViaje.getTxtList().get(1).getText();

                    Viaje v = new Viaje(id, this.vViaje.getGd().getCompaniaList().get(compania), destino);
                    this.vViaje.getGd().insertarViaje(v);
                    this.vViaje.getGd().leerViaje();

                    this.vViaje.getGd().addViaje(new Viaje(id, this.vViaje.getGd().getCompaniaList().get(compania), destino));
                    this.vViaje.getModeloTabla().setDataVector(this.cargaViaje(this.vViaje.getGd().getViajeList().size(), 3), this.vViaje.getEncabezado());
                } catch (RollbackException err) {
                    JOptionPane.showInternalMessageDialog(vViaje, "Ya se encuentra el dato en nuestra base de datos", "Error", JOptionPane.ERROR_MESSAGE);

                }

            } catch (NumberFormatException errr) {
                JOptionPane.showInternalMessageDialog(vViaje, "Ingresar solo numeros en los parámetros pertinentes", "Error", JOptionPane.ERROR_MESSAGE);

            }
            this.vViaje.getTxtList().get(0).setText("");
            this.vViaje.getComboBox().setSelectedIndex(-1);

        }
    }

    private Object[][] cargaViaje(int size, int i) {
        Object[][] retorno = new Object[size][i];
        int a = 0;
        for (Viaje v : this.vViaje.getGd().getViajeList()) {
            retorno[a][0] = v.getId();
            retorno[a][1] = v.getCompania().getNombre();
            retorno[a][2] = v.getDestino();
            a++;
        }
        return retorno;

    }

}
