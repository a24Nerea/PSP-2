/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.AgentModel;
import model.AgentSave;
import view.AgentJDialog;

/**
 *
 * @author dam2_alu10@inf.ald
 */
public class AgentController {

    public AgentJDialog view;
    private FrontController parentController;
    public AgentSave save;

    public AgentController(AgentJDialog view, AgentSave save) {
        this.view = view;
        this.save = save;
        this.view.addCancelarJButtonActionListener(this.getAddCancelarJButtonActionListener());
        this.view.addGuardarJButtonActionListener(this.getAddGuardarJButtonActionListener());
    }

    public void limpiarCampos() {
        view.setIdTextFiedl("");
        view.setNameJTextField("");
        view.setPriceInicial(Double.parseDouble("0.0"));
    }

    public ActionListener getAddGuardarJButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = view.getIdTextField();
                String nombre = view.getNameJTextField();
                double saldo = view.getPriceInicial();

                if (id.isEmpty() || nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Completa los campos", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if (saldo < 0) {
                    JOptionPane.showMessageDialog(view, "El saldo no puede ser negativo", "Saldo negativo", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (save.registroAgente(id, nombre, saldo)) {
                    view.agregarAgenteAList(id, nombre, saldo);
                    JOptionPane.showMessageDialog(view, "Agente resgistrado", "Agente registrado", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                } else {
                    if (save.existeID(id)) {
                        JOptionPane.showMessageDialog(view, "El id introducido [" + id + "] ya existe", "Id repetido", JOptionPane.ERROR_MESSAGE);
                    } else if (save.existeNombre(nombre)) {
                        JOptionPane.showMessageDialog(view, "El nombre introducio [" + nombre + "] ya está registrado", "Error nombre", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(view, "Error al registrar el agente", "Error agente registro", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        };
        return al;
    }

    public ActionListener getAddCancelarJButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        };
        return al;
    }

}
