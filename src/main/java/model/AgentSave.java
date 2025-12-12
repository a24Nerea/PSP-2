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
 *
 * @author nerea
 */
public class AgentSave {

    private ArrayList<AgentModel> agents;
    private final String fileName = "agents.txt";

    public AgentSave() throws IOException {
        this.agents = new ArrayList<>();
        cargarAgentes();
    }

    public ArrayList<AgentModel> getAgentes() {
        return agents;
    }

    public void addAgent(AgentModel agent) {
        if (!existeID(agent.getId()) && !existeNombre(agent.getNombre())) {
            this.agents.add(agent);
            guardarAgentesFile(agent);
        }
    }

    private void guardarAgentesFile(AgentModel agent) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write(agent.getId() + ";" + agent.getNombre() + ";" + agent.getSaldo());
            bw.newLine();
        } catch (IOException ex) {
            System.out.println("Error al guardar agentes: " + ex.getMessage());
        }
    }

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
                    agent.setSaldo(Double.parseDouble(data[2]));
                    agents.add(agent);
                }
            }

        } catch (IOException ex) {
            System.out.println("Error cargando agentes: " + ex.getMessage());
        }
    }

    public boolean existeNombre(String nombre) {
        for (AgentModel agent : agents) {
            if (agent.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeID(String id) {
        for (AgentModel agent : agents) {
            if (agent.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean login(String id, String nombre) {
        for (AgentModel agent : agents) {
            if (agent.getId().equalsIgnoreCase(id) && agent.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

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
        nuevoAgente.setSaldo(saldo);

        addAgent(nuevoAgente);
        return true;
    }

}
