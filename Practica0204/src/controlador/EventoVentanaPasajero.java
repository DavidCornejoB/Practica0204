/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Pasajero;
import vista.VentanaPasajero;

/**
 *
 * @author David Cornejo
 */
public class EventoVentanaPasajero implements ActionListener {

    private VentanaPasajero vPasajero;
    private GestionDato gd;

    public EventoVentanaPasajero(VentanaPasajero vPasajero) {
        this.vPasajero = vPasajero;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(vPasajero.getbGuardar())) {
            
            long id = Long.parseLong(this.vPasajero.getTxtList().get(0).getText());
            String nombre = vPasajero.getTxtList().get(1).getText();
            String apellido = vPasajero.getTxtList().get(2).getText();
            String cedula = vPasajero.getTxtList().get(3).getText();

            Pasajero p = new Pasajero(id, nombre, apellido, cedula);
            this.vPasajero.getGd().insertarPasajero(p);
            this.vPasajero.getGd().leerPasajero();

            this.vPasajero.getGd().addPasajero(new Pasajero(id, nombre, apellido, cedula));
            this.vPasajero.getModeloTabla().setDataVector(this.cargaPasajero(this.vPasajero.getGd().getPasajeroList().size(), 4), this.vPasajero.getEncabezado());

            this.vPasajero.getTxtList().get(0).setText("");
            this.vPasajero.getTxtList().get(1).setText("");
            this.vPasajero.getTxtList().get(2).setText("");
            this.vPasajero.getTxtList().get(3).setText("");
        }
    }

    private Object[][] cargaPasajero(int f, int c) {

        Object[][] retorno = new Object[f][c];
        int i = 0;
        for (Pasajero p : this.vPasajero.getGd().getPasajeroList()) {

            retorno[i][0] = p.getId();
            retorno[i][1] = p.getNombre();
            retorno[i][2] = p.getApellido();
            retorno[i][3] = p.getCedula();
            this.gd.insertarPasajero(p);
            i++;

        }

        return retorno;

    }

}
