/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nerea
 */
public class Broker {

    private double precioActual;
    private List<Operation> compras;
    private List<Operation> ventas;
    private List<Double> todosLosPrecios;

    private Thread hiloMotor;
    private boolean motorActivo;

    public Broker(double precioActual) {
        this.precioActual = precioActual;
        this.compras = new ArrayList<>();
        this.ventas = new ArrayList<>();
        this.todosLosPrecios = new ArrayList<>();
        todosLosPrecios.add(precioActual);
        this.motorActivo = false;
        System.out.println("[BROKER] Creado, precio inicial: " + precioActual + " €");
    }

    public synchronized void conseguirOperacionBroker(Operation operacion) {
        if (operacion.getOperationType().equals("Compra")) {
            compras.add(operacion);
            System.out.println("[BROKER] Compra anhadida / Agente: " + operacion.getAgent().getNombre() + " Cantidad: " + operacion.getCantidad() + " Precio: " + operacion.getPrecio() + "€");
        } else if (operacion.getOperationType().equals("Venta")) {
            ventas.add(operacion);
            System.out.println("[BROKER] Venta anhadida / Agente: " + operacion.getAgent().getNombre() + " Cantidad: " + operacion.getCantidad() + " Precio: " + operacion.getPrecio() + "€");
        }
        System.out.println("[BROKER] Estado operacion: " + compras.size() + " compras, " + ventas.size() + " ventas pendientes");
    }

    public void transaccion(Operation compra, Operation venta, double cantidad, double precio) {

        AgentModel comprador = compra.getAgent();
        AgentModel vendedor = venta.getAgent();
        double total = cantidad * precio;

        System.out.println("\n=== TRANSACCION INICIADA ===");
        System.out.println("[TRANSACCION] Comprador: " + comprador.getNombre());
        System.out.println("[TRANSACCION] Vendedor: " + vendedor.getNombre());
        System.out.println("[TRANSACCION] Cantidad: " + cantidad + " acciones");
        System.out.println("[TRANSACCION] Precio: " + precio + " €");
        System.out.println("[TRANSACCION] Total: " + total + " €");

        System.out.println("[ANTES] Comprador - Saldo: " + comprador.getSaldoEfectivo() + " €, Acciones: " + comprador.getAccionesVender());
        System.out.println("[ANTES] Vendedor - Saldo: " + vendedor.getSaldoEfectivo()+ " €, Acciones: " + vendedor.getAccionesVender());

        vendedor.setSaldoEfectivo(vendedor.getSaldoEfectivo() + total); //precio al vendedor
        comprador.setAccionesVender(comprador.getAccionesVender() + cantidad); //acciones al comprado

        System.out.println("[DESPUES] Comprador - Saldo: " + comprador.getSaldoEfectivo() + " €, Acciones: " + comprador.getAccionesVender());
        System.out.println("[DESPUES] Vendedor - Saldo: " + vendedor.getSaldoEfectivo()+ " €, Acciones: " + vendedor.getAccionesVender());
        System.out.println("=== TRANSACCION COMPLETADA ===\n");

    }

    public void actualizarOdern(Operation compra, Operation venta, double cantidad) {
        System.out.println("[BROKER] Actualiza orden  despues de una transacción...");
        double cantidadCompra = compra.getCantidad() - cantidad;
        if (cantidadCompra <= 0) {
            compras.remove(compra);
            compra.getAgent().setOperacionCompra(null);
            System.out.println("[BROKER] Actualiza orden de compra COMPLETADA y eliminada");
        } else {
            compra.setCantidad(cantidadCompra);
            System.out.println("[BROKER] Actualiza orden de compra reducida a " + cantidadCompra + " acciones");
        }
        double cantidadVenta = venta.getCantidad() - cantidad;
        if (cantidadVenta <= 0) {
            ventas.remove(venta);
            venta.getAgent().setOperacionVenta(null);
            System.out.println("[BROKER] Actualiza orden de venta COMPLETADA y eliminada");
        } else {
            venta.setCantidad(cantidadVenta);
            System.out.println("[BROKER] Actualiza orden de venta reducida a " + cantidadVenta + " acciones");
        }
        System.out.println("[BROKER] Actualiza estado final: " + compras.size() + " compras, " + ventas.size() + " ventas pendientes");
    }

    public void procesandoOrden() {
        System.out.println("\n[BROKER] Procesando ordenes...");
        if (compras.isEmpty() || ventas.isEmpty()) {
            System.out.println("[BROKER] No hay suficientes ordenes");
            return;
        }
        System.out.println("[BROKER] Buscando coincidencias");

        for (Operation compra : compras) {
            for (Operation venta : ventas) {
                System.out.println("[BROKER] Mirando operaciones");
                if (compra.getPrecio() >= venta.getPrecio()) {
                    double cantidad = Math.min(compra.getCantidad(), venta.getCantidad());
                    double precioTransacion = venta.getPrecio();

                    System.out.println("[BROKER] ¡COINCIDENCIA ENCONTRADA!");
                    System.out.println("[BROKER] Cantidad a negociar: " + cantidad);

                    transaccion(compra, venta, cantidad, precioTransacion);
                    precioActual = precioTransacion;
                    todosLosPrecios.add(precioActual);
                    System.out.println("[BROKER] Nuevo precio actual: " + precioActual + " €");
                    actualizarOdern(compra, venta, cantidad);
                    return;
                }
            }
        }
        System.out.println("[BROKER] No se encontraron coincidencias de precios");
    }

    public void iniciar() {
        if (motorActivo) {
            System.out.println("[BROKER] Motor ya esta activo");
            return;
        }
        motorActivo = true;
        hiloMotor = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("[BROKER] Hilo del motor iniciado");
                while (motorActivo) {
                    try {
                        Thread.sleep(3000);
                        System.out.println("\n--- CICLO DE PROCESAMIENTO ---");
                        procesandoOrden();
                    } catch (InterruptedException ex) {
                        System.out.println("[BROKER] Hilo interrumpido");
                        break;
                    }
                }
                System.out.println("[BROKER] Hilo del motor finalizado");
            }
        });
        hiloMotor.start();
        System.out.println("[BROKER] Motor iniciado (procesa cada 3 segundos)");
    }
    
    public synchronized void cancelarOperacionCompra(AgentModel agente){
        Operation eliminarOperacion = null;
        for(Operation compra: compras){
            if(compra.getAgent().equals(agente)){
                eliminarOperacion = compra;
                break;
            }
        }
        if(eliminarOperacion != null){
            compras.remove(eliminarOperacion);
            double saldo = eliminarOperacion.getPrecio() * eliminarOperacion.getCantidad();
            agente.setSaldoEfectivo(agente.getSaldoEfectivo() + saldo);
            agente.setOperacionCompra(null);
        }
    }
    
    public synchronized void cancelarOperacionVenta(AgentModel agentes){
        Operation eliminarOperacion = null;
        for(Operation venta: ventas){
            if(venta.getAgent().equals(agentes)){
                eliminarOperacion =venta;
                break;
            }
        }
        if(eliminarOperacion!=null){
            ventas.remove(eliminarOperacion);
            agentes.setAccionesVender(agentes.getAccionesVender() + eliminarOperacion.getCantidad());
            agentes.setOperacionVenta(null);
        }
    }

    public double getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(double precioActual) {
        this.precioActual = precioActual;
    }

    public List<Operation> getCompras() {
        return compras;
    }

    public void setCompras(List<Operation> compras) {
        this.compras = compras;
    }

    public List<Operation> getVentas() {
        return ventas;
    }

    public void setVentas(List<Operation> ventas) {
        this.ventas = ventas;
    }

    public List<Double> getTodosLosPrecios() {
        return todosLosPrecios;
    }

    public void setTodosLosPrecios(List<Double> todosLosPrecios) {
        this.todosLosPrecios = todosLosPrecios;
    }

    public Thread getHiloMotor() {
        return hiloMotor;
    }

    public void setHiloMotor(Thread hiloMotor) {
        this.hiloMotor = hiloMotor;
    }

    public boolean isMotorActivo() {
        return motorActivo;
    }

    public void setMotorActivo(boolean motorActivo) {
        this.motorActivo = motorActivo;
    }

}
