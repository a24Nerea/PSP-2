/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *
 * @author dam2_alu10@inf.ald
 */
public class AgentModel {

    private String id;
    private String nombre;
    private double saldo;
    private Operation operacionCompra;
    private Operation operacionVenta;

    public AgentModel(String id, String nombre, double saldo, Operation operacionCompra, Operation operacionVenta) {
        this.id = id;
        this.nombre = nombre;
        this.saldo = saldo;
        this.operacionCompra = operacionCompra;
        this.operacionVenta = operacionVenta;
    }
/*
    public boolean nuevaOperacionCompra(AgentModel this, String tipo, double limite, double cantidad) {
        switch (tipo) {
            case "compra":
                //crear nueva operación y asignarlo a la compra 
                if (operacionCompra == null) {
                    operacionCompra = new Operation(tipo, limite, cantidad);
                } else {
                    System.out.println("Ya existe una operacion compra para el agente " + getNombre());
                    return false;
                }
                break;
            case "venta":
                //crear nueva operacion y asignarlo a la venta
                break;
            default:
                return false;

        }
        return true;
    }
*/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Operation getOperacionCompra() {
        return operacionCompra;
    }

    public void setOperacionCompra(Operation operacionCompra) {
        this.operacionCompra = operacionCompra;
    }

    public Operation getOperacionVenta() {
        return operacionVenta;
    }

    public void setOperacionVenta(Operation operacionVenta) {
        this.operacionVenta = operacionVenta;
    }

}
