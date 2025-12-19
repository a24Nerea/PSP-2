/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Clase que representa una operacion de Compra o venta en el broker
 *
 * @author dam2_alu10@inf.ald
 */
public class Operation {

    private AgentModel agent;
    private String operationType;
    private double precio;
    private double cantidad;
    private boolean activa;

    /**
     * Método toString
     *
     * @return
     */
    @Override
    public String toString() {
        return "Operation: " + "agent: " + agent + ", operationType: " + operationType + ", precio: " + precio + ", cantidad:" + cantidad + ", activa:" + activa;
    }

    /**
     * Contructo principal
     *
     * @param agent
     * @param operationType
     * @param precio
     * @param cantidad
     * @param activa
     */
    public Operation(AgentModel agent, String operationType, double precio, double cantidad, boolean activa) {
        this.agent = agent;
        this.operationType = operationType;
        this.precio = precio;
        this.cantidad = cantidad;
        this.activa = activa;
    }

    /**
     * Get que devuelve el agente
     *
     * @return
     */
    public AgentModel getAgent() {
        return agent;
    }

    /**
     * Set que establece el agente
     *
     * @param agent
     */
    public void setAgent(AgentModel agent) {
        this.agent = agent;
    }

    /**
     * Get que devuelve el tipo de operación
     *
     * @return
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * Set que establece el tipo de operación
     *
     * @param operationType
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    /**
     * Get que devuelve el precio
     *
     * @return
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Set que establece el precio
     *
     * @param precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Get que devuelve la cantidad a vender o comprar
     *
     * @return
     */
    public double getCantidad() {
        return cantidad;
    }

    /**
     * Set que establece la cantidad a vender o comprar
     *
     * @param cantidad
     */
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Método que devuelve si la operacion está activa (true) o no (false)
     *
     * @return
     */
    public boolean isActiva() {
        return activa;
    }

    /**
     * Método que estable la operación en activa (true) o no (false)
     *
     * @param activa
     */
    public void setActiva(boolean activa) {
        this.activa = activa;
    }

}
