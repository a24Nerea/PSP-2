/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.AgentJDialog;
import view.MainFrame;

/**
 *
 * @author dam2_alu10@inf.ald
 */
public class FrontController {
    
    private MainFrame view;

    public FrontController(MainFrame view) {
        this.view = view;
        this.view.setQuitJMenu(this.setQuitJMenuActionListener());
        this.view.setGestionAgentesJMenu(this.setGestionAgentesJMenu());
    }
    
    public ActionListener setQuitJMenuActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                view.dispose();
            }
        };
        return al;
    }
    
    public ActionListener setGestionAgentesJMenu(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                AgentJDialog ajd = new AgentJDialog(view, true);
                AgentController ac = new AgentController(ajd);
                ajd.setVisible(true);
                
            
            }
        };
        return al;
    }
    
    
}
