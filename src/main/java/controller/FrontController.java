/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.AgentSave;
import model.Broker;
import view.AgentJDialog;
import view.MainFrame;
import view.OperationJDialog;

/**
 *
 * @author dam2_alu10@inf.ald
 */
public class FrontController {

    private MainFrame view;
    private Broker broker;
    private AgentSave save;

    public FrontController(MainFrame view) {
        this.view = view;
        try {
            this.save = new AgentSave();
            this.view.setQuitJMenu(this.setQuitJMenuActionListener());
            this.view.setGestionAgentesJMenu(this.setGestionAgentesJMenu());
            this.view.setGestionCompraVentaJMenuItem(this.setGestionCompraVentaJMenuItem());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error iniciando el sistema" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ActionListener setQuitJMenuActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                view.dispose();
            }
        };
        return al;
    }
    
    public ActionListener setGestionCompraVentaJMenuItem(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OperationJDialog ojd = new OperationJDialog(view);
                OperationController opc = new OperationController(ojd, save, broker);
                ojd.setVisible(true);
            }
        };
        return al;
    }

    public ActionListener setGestionAgentesJMenu() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    AgentJDialog ajd = new AgentJDialog(view, true, save);
                    AgentController ac = new AgentController(ajd, save);
                    ajd.setVisible(true);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(view, "Error al abrir dialogo agentes" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        };
        return al;
    }

}
