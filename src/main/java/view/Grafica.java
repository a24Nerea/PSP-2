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
 *
 * @author nerea
 */
public class Grafica extends JPanel{
    private TimeSeries puntos ;
    private TimeSeriesCollection dataSet;
    private JFreeChart graficaCompleta;
    private ChartPanel charPanel;
    private Broker broker;
    private Second time;
    
    public Grafica(Broker broker){
        this.broker = broker;
        this.time = new Second(new Date());
        pintarGrafica();
    }
    
    private void pintarGrafica(){
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
    public void actualizarGrafica(){
        time = (Second) time.next();
        double precioActual = broker.getPrecioActual();
        puntos.add(time, precioActual);
        charPanel.repaint();
    }
   
}
