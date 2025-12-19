/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.awt.*;
import java.util.Date;
import model.Broker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 * Clase para pintar la gráfica en la ventana principal
 *
 * @author nerea
 */
public class Grafica extends JPanel {

    private TimeSeries puntos;
    private TimeSeriesCollection dataSet;
    private JFreeChart graficaCompleta;
    private ChartPanel charPanel;
    private Broker broker;
    private Thread hiloParaGraficaActulizar;
    private boolean ejecutado = true;
    private Second time;

    /**
     * Constructor
     *
     * @param broker
     */
    public Grafica(Broker broker) {
        this.broker = broker;
        this.time = new Second(new Date());
        pintarGrafica();
        inicarHiloParaGrafica();
    }

    /**
     * Método donde se pinta la gráfica
     */
    private void pintarGrafica() {
        setLayout(new BorderLayout());
        puntos = new TimeSeries("Precio");
        puntos.add(time, broker.getPrecioActual());

        dataSet = new TimeSeriesCollection();
        dataSet.addSeries(puntos);

        graficaCompleta = ChartFactory.createTimeSeriesChart("Evolucion precio", "Tiempo", "Precio", dataSet, true, true, false);
        graficaCompleta.setBackgroundPaint(Color.WHITE);

        charPanel = new ChartPanel(graficaCompleta);
        charPanel.setPreferredSize(new Dimension(600, 400));
        charPanel.setMouseWheelEnabled(true);
        add(charPanel, BorderLayout.CENTER);

    }

    /**
     * Método donde se inicia el hilo para actualizar la gráfica
     */
    public void inicarHiloParaGrafica() {
        hiloParaGraficaActulizar = new Thread(new Runnable() {
            @Override
            public void run() {
                while (ejecutado) {
                    try {

                        Thread.sleep(1000);
                        double precioActualBroker = broker.getPrecioActual();
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                actualizarGrafica();
                            }
                        });
                    } catch (InterruptedException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
            }
        });
        hiloParaGraficaActulizar.start();
    }

    /**
     * Método para parar el hilo de la gráfica
     */
    public void pararHiloDeGrafica() {
        ejecutado = false;
        if (hiloParaGraficaActulizar != null) {
            hiloParaGraficaActulizar.interrupt();
        }
    }

    /**
     * Método donde se actualiza la gráfica con nuevos datos
     */
    public void actualizarGrafica() {
        time = (Second) time.next();
        double precioActual = broker.getPrecioActual();
        System.out.println("-------------------------------------------");
        System.out.println("[GRÁFICA] actualizarGrafica() llamado");
        System.out.println("[GRÁFICA] Tiempo: " + time);
        System.out.println("[GRÁFICA] Precio obtenido: " + precioActual);
        System.out.println("-------------------------------------------");
        puntos.add(time, precioActual);
        if (puntos.getItemCount() > 100) {
            puntos.delete(0, 0);
        }
        charPanel.repaint();
    }

}
