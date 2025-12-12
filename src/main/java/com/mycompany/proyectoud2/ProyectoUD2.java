/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyectoud2;

import controller.FrontController;
import view.MainFrame;

/**
 *
 * @author dam2_alu10@inf.ald
 */
public class ProyectoUD2 {
//hilosBroker
    //en el main tener la gráfica 
    public static void main(String[] args) {
        //tenemos que recuperar el precio y los valores anteriores
        //pintar el precio/tiempo -> Interfaz gráfica
        //crear agentes que tienen operaciones de entrada y salida (asigando una operacion entrada y salida)
        //hacen lectura de precio y compran o venden ---> dos tipos de hilos 
        //lógica de compraventa en el broker -- HILO

        //agentes con un capital que puedan lanzar las operaciones de compraventa
        //nuevos agentes 
        //crear operaciones
        //guardar usuarios y operaciones
        
        MainFrame mainFrame = new MainFrame();
        FrontController fc = new FrontController(mainFrame);
        mainFrame.setVisible(true);
    }
}
