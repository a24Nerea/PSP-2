/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author dam2_alu10@inf.ald
 */
public class Operation {

    private AgentModel agent;
    private String operationType;
    private double precio;
    private double cantidad;
    private boolean activa;

    public Operation(AgentModel agent, String operationType, double precio, double cantidad, boolean activa) {
        this.agent = agent;
        this.operationType = operationType;
        this.precio = precio;
        this.cantidad = cantidad;
        this.activa = activa;
    }

    public AgentModel getAgent() {
        return agent;
    }

    public void setAgent(AgentModel agent) {
        this.agent = agent;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    

}
