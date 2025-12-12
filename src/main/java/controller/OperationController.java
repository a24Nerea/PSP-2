/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import model.AgentModel;
import model.AgentSave;
import model.OperationSave;
import view.OperationJDialog;

/**
 *
 * @author nerea
 */
public class OperationController {

    private OperationJDialog view;
    private AgentSave agent;
    private OperationSave operation;

    public OperationController(OperationJDialog view, AgentSave agent) {
        this.view = view;
        this.agent = agent;
        this.operation = new OperationSave(agent);
        initComponents();
        this.view.addCancelarJButtonActionListener(this.getAddCancelarJButtonActionListener());
        this.view.addGuardarJButtonActionListener(this.getAddGuardarJButtonActionListener());
    }

    public void initComponents() {
        cargarAgentes();
        view.setTipoOperacionJComboBox("Compra");
        view.setTipoOperacionJComboBox("Venta");
    }

    public void cargarAgentes() {
        if (agent != null && agent.getAgentes() != null) {
            for (AgentModel agent : agent.getAgentes()) {
                String agentes = "(" + agent.getId() + ") -> " + agent.getNombre();
                view.setAgentJComboBox(agentes);
            }
        } else {
            view.setAgentJComboBox("No hay agentes disponibles");
        }
    }

    public ActionListener getAddGuardarJButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("guardar");
            }
        };
        return al;
    }

    public ActionListener getAddCancelarJButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                System.out.println("cancelar");
            }
        };
        return al;
    }
}
