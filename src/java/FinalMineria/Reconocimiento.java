/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalMineria;

import Herramientas.Grabador;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

/**
 *
 * @author Enmanuel
 */
@WebServlet(name = "Reconocimiento", urlPatterns = {"/Reconocimiento"})
public class Reconocimiento extends HttpServlet {

    Grabador grabar;
    long tiempo = 60000;
    String linea;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        
        String accion = request.getParameter("accion");
        BufferedReader br = null;
        String ruta = request.getServletContext().getRealPath("/Recursos");
        br = new BufferedReader(new FileReader(ruta+"/nombres.txt"));
        linea = br.readLine();
        br.close();  
        if("Detener".equals(accion))
        {
            grabar.finish();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GrabarAudio.class.getName()).log(Level.SEVERE, null, ex);
            }
            String comando = "cmd /c "+request.getServletContext().getRealPath("/Recursos/OpenSmile")+"\\SMILExtract_Release.exe -C "+request.getServletContext().getRealPath("/Recursos/config")+"\\IS12_speaker_trait.conf -I "+request.getServletContext().getRealPath("/Recursos/audios")+"\\prueba.wav -O "+request.getServletContext().getRealPath("/Recursos/arff")+"\\prueba.arff -classes {"+linea+"} -classlabel ?";
            Process proceso = Runtime.getRuntime().exec(comando);
            proceso.waitFor();            
            Instances prueba, conocimiento;
            try (                
                BufferedReader archivoBase = new BufferedReader(new FileReader(request.getServletContext().getRealPath("/Recursos/arff")+"\\baseDatos.arff"))
                ) {
                conocimiento = new Instances(archivoBase);
                }
            try(
                BufferedReader archivoPrueba = new BufferedReader(new FileReader(request.getServletContext().getRealPath("/Recursos/arff")+"\\prueba.arff"))) {
                prueba = new Instances(archivoPrueba);
            }
            
        conocimiento.deleteStringAttributes();    
        conocimiento.setClassIndex(981);
        prueba.deleteStringAttributes();
        prueba.setClassIndex(981);
        Classifier clasificadorModelo = (Classifier)new NaiveBayes();
        clasificadorModelo.buildClassifier(conocimiento);
        double valorP = clasificadorModelo.classifyInstance(prueba.instance(prueba.numInstances()-1));
        String prediccion=prueba.classAttribute().value((int)valorP);
        System.out.println(prediccion);
        request.setAttribute("prediccion", prediccion);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./Hablante.jsp");
        dispatcher.forward(request, response);
        }
        else if("Grabar".equals(accion))
        {
            ruta = request.getServletContext().getRealPath("/Recursos/audios");
            grabar = new Grabador(ruta+"\\"+"prueba");
            Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(tiempo);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                grabar.finish();
            }
        });
 
        stopper.start();
 
        // start recording
        grabar.start();
        response.sendRedirect("./grabar.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Reconocimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Reconocimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
