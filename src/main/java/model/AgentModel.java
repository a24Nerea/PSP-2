/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Modelo donde almacenamos los datos de los Agentes
 *
 * @author dam2_alu10@inf.ald
 */
public class AgentModel {

    private String id;
    private String nombre;
    private double saldoEfectivo;
    private double accionesVender;
    private Operation operacionCompra;
    private Operation operacionVenta;

    /**
     * Método toString
     *
     * @return
     */
    @Override
    public String toString() {
        return "AgentModel-> " + "id: " + id + ", nombre: " + nombre + ", saldoEfectivo: " + saldoEfectivo + ", accionesVender: " + accionesVender + ", operacionCompra: " + operacionCompra + ", operacionVenta: " + operacionVenta;
    }

    /**
     * Constructo del modelo
     *
     * @param id
     * @param nombre
     * @param saldoIncio
     */
    public AgentModel(String id, String nombre, double saldoIncio) {
        this.id = id;
        this.nombre = nombre;
        this.saldoEfectivo = saldoIncio;
        this.accionesVender = 100.0;
        this.operacionCompra = null;
        this.operacionVenta = null;
    }

    /**
     * Constructo que crear un Agente por defecto
     */
    public AgentModel() {
        this.id = "";
        this.nombre = "";
        this.saldoEfectivo = 100.0;
        this.accionesVender = 100.0;
        this.operacionCompra = null;
        this.operacionVenta = null;
    }

    /**
     * Método que comprueba si puede vender
     *
     * @param cantidadAVender
     * @return
     */
    public boolean puedeVender(double cantidadAVender) {
        return accionesVender >= cantidadAVender;
    }

    /**
     * Método que comprueba si puede comprar
     *
     * @param precio
     * @param cantidadAComprar
     * @return
     */
    public boolean puedeComprar(double precio, double cantidadAComprar) {
        double total = precio * cantidadAComprar;
        return saldoEfectivo >= total;
    }

    /**
     * Get donde devuelve el ID del agente
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Set que establece el ID del agente
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get donde devuelve el NOMBRE del agente
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set que establece el NOMBRE del agente
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Get donde devuelve la operación de compra del agente
     *
     * @return
     */
    public Operation getOperacionCompra() {
        return operacionCompra;
    }

    /**
     * Set que establece la operación de compra del agente
     *
     * @param operacionCompra
     */
    public void setOperacionCompra(Operation operacionCompra) {
        this.operacionCompra = operacionCompra;
    }

    /**
     * Get donde devuelve la operación de venta del agente
     *
     * @return
     */
    public Operation getOperacionVenta() {
        return operacionVenta;
    }

    /**
     * Set que establece la operación de venta del agente
     *
     * @param operacionVenta
     */
    public void setOperacionVenta(Operation operacionVenta) {
        this.operacionVenta = operacionVenta;
    }

    /**
     * Get donde devuelve la cantidad de dinero del agente
     *
     * @return
     */
    public double getSaldoEfectivo() {
        return saldoEfectivo;
    }

    /**
     * Set que establece la cantidad de saldo del agente
     *
     * @param saldoEfectivo
     */
    public void setSaldoEfectivo(double saldoEfectivo) {
        this.saldoEfectivo = saldoEfectivo;
    }

    /**
     * Get donde devuelve las acciones a vender del agente
     *
     * @return
     */
    public double getAccionesVender() {
        return accionesVender;
    }

    /**
     * Set que establece la cantidad de acciones del agente
     *
     * @param accionesVender
     */
    public void setAccionesVender(double accionesVender) {
        this.accionesVender = accionesVender;
    }
}
