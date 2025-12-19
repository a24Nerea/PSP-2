/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que guarda las operaciones de cada agente
 *
 * @author nerea
 */
public class OperationSave {

    private ArrayList<Operation> operaciones;
    private AgentSave agente;
    private final String fileName = "operaciones.txt";

    /**
     * Constructor principal
     *
     * @param agent
     */
    public OperationSave(AgentSave agent) {
        this.agente = agent;
        this.operaciones = new ArrayList<>();
        cargarOperaciones();
    }

    /**
     * Método donde se cargan las operaciones
     *
     * @param agent
     */
    private void cargarOperaciones() {
        File file = new File(fileName);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 5) {
                    AgentModel agente = encontrarAgente(datos[0]);
                    if (agente != null) {
                        Operation operation = new Operation(agente, datos[1], Double.parseDouble(datos[2]), Double.parseDouble(datos[3]), Boolean.parseBoolean(datos[4]));
                        operaciones.add(operation);

                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(OperationSave.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método donde se guardan las operaciones en el fichero
     *
     * @param operacion
     * @return
     */
    public boolean guardarOperacion(Operation operacion) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            String linea = operacion.getAgent().getId() + ";" + operacion.getOperationType() + ";" + operacion.getPrecio() + ";" + operacion.getCantidad() + ";" + operacion.isActiva();
            bw.write(linea);
            bw.newLine();
            operaciones.add(operacion);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(OperationSave.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Get que devuelve la lista de operaciones
     *
     * @return
     */
    public ArrayList<Operation> getOperaciones() {
        return operaciones;
    }

    /**
     * Método para encontrar el agente según el id
     *
     * @param id
     * @return
     */
    public AgentModel encontrarAgente(String id) {
        for (AgentModel agente : agente.getAgentes()) {
            if (agente.getId().equals(id)) {
                return agente;
            }
        }
        return null;
    }

}
