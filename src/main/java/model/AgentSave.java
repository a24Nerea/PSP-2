/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Modelo donde se guardar los agentes en una lista y en un archivo .txt
 *
 * @author nerea
 */
public class AgentSave {

    private ArrayList<AgentModel> agents;
    private final String fileName = "agents.txt";

    /**
     * Constructor
     *
     * @throws IOException
     */
    public AgentSave() throws IOException {
        this.agents = new ArrayList<>();
        cargarAgentes();
    }

    /**
     * Get donde devuelve la lista de agentes
     *
     * @return
     */
    public ArrayList<AgentModel> getAgentes() {
        return agents;
    }

    /**
     * Metodo donde añadimos los agentes
     *
     * @param agent
     */
    public void addAgent(AgentModel agent) {
        if (!existeID(agent.getId()) && !existeNombre(agent.getNombre())) {
            this.agents.add(agent);
            guardarAgentesFile(agent);
        }
    }

    /**
     * Metodo donde añadimos los agentes en el archivo txt
     *
     * @param agent
     */
    private void guardarAgentesFile(AgentModel agent) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write(agent.getId() + ";" + agent.getNombre() + ";" + agent.getSaldoEfectivo() + ";" + agent.getAccionesVender());
            bw.newLine();
        } catch (IOException ex) {
            System.out.println("Error al guardar agentes: " + ex.getMessage());
        }
    }

    /**
     * Metodo donde cargamos los agentes
     *
     * @param agent
     */
    private void cargarAgentes() throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] data = linea.split(";");
                if (data.length >= 3) {
                    AgentModel agent = new AgentModel();
                    agent.setId(data[0]);
                    agent.setNombre(data[1]);
                    agent.setSaldoEfectivo(Double.parseDouble(data[2]));
                    if (data.length >= 4) {
                        agent.setAccionesVender(Double.parseDouble(data[3]));
                    } else {
                        agent.setAccionesVender(100.0);
                    }
                    agents.add(agent);
                }
            }

        } catch (IOException ex) {
            System.out.println("Error cargando agentes: " + ex.getMessage());
        }
    }

    /**
     * Método donde se comprueba por nombre si existe o no el agente
     *
     * @param nombre
     * @return
     */
    public boolean existeNombre(String nombre) {
        for (AgentModel agent : agents) {
            if (agent.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método donde se comprueba por ID si existe o no el agente
     *
     * @param id
     * @return
     */
    public boolean existeID(String id) {
        for (AgentModel agent : agents) {
            if (agent.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método donde se comprueba si hace login correctamente
     *
     * @param id
     * @param nombre
     * @return
     */
    public boolean login(String id, String nombre) {
        for (AgentModel agent : agents) {
            if (agent.getId().equalsIgnoreCase(id) && agent.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método donde se registra un nuevo agente
     *
     * @param id
     * @param nombre
     * @param saldo
     * @return
     */
    public boolean registroAgente(String id, String nombre, double saldo) {
        if (existeID(id)) {
            return false;
        }

        if (existeNombre(nombre)) {
            return false;
        }

        if (saldo < 0) {
            return false;
        }

        AgentModel nuevoAgente = new AgentModel();
        nuevoAgente.setId(id);
        nuevoAgente.setNombre(nombre);
        nuevoAgente.setSaldoEfectivo(saldo);

        addAgent(nuevoAgente);
        return true;
    }

}
