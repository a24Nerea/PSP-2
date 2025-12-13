/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Action;
import javax.swing.JOptionPane;
import model.AgentModel;
import model.AgentSave;
import model.Broker;
import model.Operation;
import model.OperationSave;
import view.OperationJDialog;

/**
 *
 * @author nerea
 */
public class OperationController {

    private OperationJDialog view;
    private AgentSave agentSave;
    private OperationSave operation;
    private Broker broker;

    public OperationController(OperationJDialog view, AgentSave agentSave, Broker broker) {
        this.view = view;
        this.agentSave = agentSave;
        this.operation = new OperationSave(agentSave);
        this.broker = broker;
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
        if (agentSave != null && agentSave.getAgentes() != null) {
            for (AgentModel agent : agentSave.getAgentes()) {
                String agentes = "(" + agent.getId() + ") -> " + agent.getNombre() + " Saldo: " + agent.getSaldoEfectivo() + "€";
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
                int indiceAgente = view.getAgentsJCombobox().getSelectedIndex();
                String tipoOperacion = view.getTipoOperacionJComboBox();
                double precio = view.getPrecioLimiteJTextField();
                double cantidad = view.getCantidadJTextField();
                System.out.println("Tipo operacion: " + tipoOperacion);
                System.out.println("Indice agente: " + indiceAgente);

                if (indiceAgente < 0) {
                    JOptionPane.showMessageDialog(view, "Selecciona un agente", "Seleccionar agente", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (precio <= 0 || cantidad <= 0) {
                    JOptionPane.showMessageDialog(view, "El precio o la cantidad no puede ser menor que 0", "Precio o cantidad incorrectos", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                List<AgentModel> listaAgentes = agentSave.getAgentes();
                if (indiceAgente >= listaAgentes.size()) {
                    JOptionPane.showMessageDialog(view, "Error indice no valido", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                AgentModel agenteSeleccion = listaAgentes.get(indiceAgente);
                if (tipoOperacion.equals("Compra")) {
                    double total = precio * cantidad;
                    if (total > agenteSeleccion.getSaldoEfectivo()) {
                        JOptionPane.showMessageDialog(view, "Operacion incorrecta \n" + "Total: " + total + "€ \n" + "Su saldo: " + agenteSeleccion.getSaldoEfectivo() + "€", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (agenteSeleccion.getOperacionCompra() != null) {
                        int respuesta = JOptionPane.showConfirmDialog(view, "Este agente tiene orden de compra activada: \n" + "Precio: " + agenteSeleccion.getOperacionCompra().getPrecio() + "€ \n" + "Cantidad: " + agenteSeleccion.getOperacionCompra().getCantidad() + "¿Quiere reemplazar la operacion?", "Compra ya existente", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
                        if (respuesta != JOptionPane.YES_OPTION) {
                            broker.cancelarOperacionCompra(agenteSeleccion);
                        }else{
                            return;
                        }
                    }
                } else if (tipoOperacion.equals("Venta")) {
                    if (cantidad > agenteSeleccion.getAccionesVender()) {
                        JOptionPane.showMessageDialog(view, "Acciones insuficientes: \n" + "Acciones solicitadas: " + cantidad + "\n" + "Acciones disponibles: " + agenteSeleccion.getAccionesVender() + "\n", "Error acciones", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (agenteSeleccion.getOperacionVenta() != null) {
                        int respuesta = JOptionPane.showConfirmDialog(view, "Este agente tiene orden de venta activada: \n" + "Precio: " + agenteSeleccion.getOperacionVenta().getPrecio() + "€ \n" + "Cantidad: " + agenteSeleccion.getOperacionVenta().getCantidad() + "\n" + "¿Quiere reemplazar la operacion?", "Venta ya existente", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
                        if (respuesta != JOptionPane.YES_OPTION) {
                            broker.cancelarOperacionVenta(agenteSeleccion);
                        }else{
                            return;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Operacion no valida", "Error operacion", JOptionPane.ERROR_MESSAGE);
                }
                Operation operacion = new Operation(agenteSeleccion, tipoOperacion, precio, cantidad, true);
                broker.conseguirOperacionBroker(operacion);
                if (tipoOperacion.equals("Compra")) {
                    agenteSeleccion.setOperacionCompra(operacion);
                }else{
                    agenteSeleccion.setOperacionVenta(operacion);
                }
                boolean guardar = operation.guardarOperacion(operacion);
                if(guardar){
                    JOptionPane.showMessageDialog(view, "Orden registrada: \n" + "Agente: " + agenteSeleccion.getNombre() + "\n" + "Id: " + agenteSeleccion.getId() + "\n" + "Tipo: " + tipoOperacion + "\n" + "Cantidad: " + cantidad + "\n" + "Orden Registrada", "Operacion realizada", JOptionPane.INFORMATION_MESSAGE);
                    view.setCantidadJTextField(0);
                    view.setPrecioLimiteJTextField(0);
                }else{
                    JOptionPane.showMessageDialog(view, "Error al guardar en archivo", "Erro", JOptionPane.ERROR_MESSAGE);
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
                System.out.println("cancelar");
            }
        };
        return al;
    }
}
