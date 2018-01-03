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
import modelo.Terminal;
import vista.VentanaTerminal;

/**
 *
 * @author David Cornejo
 */
public class EventoVentanaTerminal implements ActionListener {

    private VentanaTerminal vTerminal;

    public EventoVentanaTerminal(VentanaTerminal vTerminal) {
        this.vTerminal = vTerminal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(this.vTerminal.getbGuardar())) {
            try {
                try {
                    long id = Long.parseLong(this.vTerminal.getTxtList().get(0).getText());
                    String nombre = this.vTerminal.getTxtList().get(1).getText();
                    String direccion = this.vTerminal.getTxtList().get(2).getText();

                    Terminal t = new Terminal(id, nombre, direccion);
                    this.vTerminal.getGd().insertarTerminal(t);
                    this.vTerminal.getGd().leerTerminal();

                    this.vTerminal.getGd().addTerminal(new Terminal(id, nombre, direccion));
                    this.vTerminal.getModeloTabla().setDataVector(this.cargaTerminal(this.vTerminal.getGd().getTerminalList().size(), 3), this.vTerminal.getEncabezado());

                } catch (RollbackException err) {

                    JOptionPane.showInternalMessageDialog(vTerminal, "Ya se encuentra el dato en nuestra base de datos", "Error", JOptionPane.ERROR_MESSAGE);

                }

            } catch (NumberFormatException errr) {
                JOptionPane.showInternalMessageDialog(vTerminal, "Ingresar solo numeros en Id", "Error", JOptionPane.ERROR_MESSAGE);

            }

            this.vTerminal.getTxtList().get(0).setText("");
            this.vTerminal.getTxtList().get(1).setText("");
            this.vTerminal.getTxtList().get(2).setText("");
        }

    }

    private Object[][] cargaTerminal(int f, int c) {

        Object[][] retorno = new Object[f][c];
        int i = 0;
        for (Terminal t : this.vTerminal.getGd().getTerminalList()) {
            retorno[i][0] = t.getId();
            retorno[i][1] = t.getNombre();
            retorno[i][2] = t.getDireccion();
            i++;
        }
        return retorno;
    }

}
