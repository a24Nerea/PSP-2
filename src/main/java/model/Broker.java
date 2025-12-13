/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nerea
 */
public class Broker {

    private double precioActual;
    private List<Operation> compra;
    private List<Operation> venta;
    private List<Double> todosLosPrecios;
    private Thread hiloMotor;
    private boolean motorActivo;

    public Broker(double precioActual) {
        this.precioActual = precioActual;
        this.compra = new ArrayList<>();
        this.venta = new ArrayList<>();
        this.todosLosPrecios = new ArrayList<>();
        todosLosPrecios.add(precioActual);
        this.motorActivo = false;
    }
    
    
    public void conseguirOperacionBroker(Operation operacion){
        if(operacion.getOperationType().equals("Compra")){
            compra.add(operacion);
        }else if(operacion.getOperationType().equals("Venta")){
            venta.add(operacion);
        }
    }
    
    public void iniciar(){
        if(motorActivo){
            return;
        }
        motorActivo = true;
        
    }
    
}
