/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import database.dbConnweb;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author EKaunda
 */
public class getDuplicates extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           
          
            
            
            String facilitymfl="15288";
            
            
            if(request.getParameter("mfl")!=null){
            
            facilitymfl=request.getParameter("mfl");
            
            }
            
            
            dbConnweb conn = new dbConnweb();
            
            
            String getdata=" select  `ID` as id,  `Facility Name` as facility, `Counsellor` as counsellor, `Register No.` as register_no, `Patient Serial no` as serialno, `Date Tested` as date_tested, "
                    + "`Age` as age, `Gender` as gender,`modality`,`Test Result` as testresult,`Linked` as linked,`Cccno` as cccno,`Linked site` as linked_site,"
                    + "`Reason Not Linked` as reason_not_linked,`Reason for death` as reason_for_death,`Reason for declining` as reason_for_declining ,`timestamp`, ( lastsynced + INTERVAL 3 HOUR) as lastsynced, `mflcode`,ifnull(datestartedart,'') as datestartedart "
                    + " ,datestartedart ,started_on_art,reason_not_started_art,started_tx_site,other_facility_started_art,reason_for_declining_art,other_reason_for_declining_art,reason_for_death_tx,other_reason_for_death_tx FROM aphiaplus_moi.hts_duplicates "
                    + " where  Replace(`Facility Name`,\"'\",\"\") in ('"+facilitymfl+"')  ";
          //SUBSTR(`daily_raw`.`id`, 1, 5) in ('"+facilitymfl+"')
            
          
            System.out.println(""+getdata);
            conn.rs= conn.st.executeQuery(getdata);
            
            JSONArray  jarr= new JSONArray();
            
            
            while(conn.rs.next())
            {
            
            
            JSONObject jobj= new JSONObject();

jobj.put("id",conn.rs.getString("id"));
jobj.put("facility",conn.rs.getString("facility"));
jobj.put("counsellor",conn.rs.getString("counsellor"));
jobj.put("register_no",conn.rs.getString("register_no"));
jobj.put("serialno",conn.rs.getString("serialno"));
jobj.put("date_tested",conn.rs.getString("date_tested"));
jobj.put("age",conn.rs.getString("age"));
jobj.put("gender",conn.rs.getString("gender"));
jobj.put("modality",conn.rs.getString("modality"));
jobj.put("testresult",conn.rs.getString("testresult"));
jobj.put("linked",conn.rs.getString("linked"));
jobj.put("cccno",conn.rs.getString("cccno"));
jobj.put("linked_site",conn.rs.getString("linked_site"));
//jobj.put("other_facility_linked",conn.rs.getString("other_facility_linked"));
jobj.put("reason_not_linked",conn.rs.getString("reason_not_linked"));
jobj.put("reason_for_death",conn.rs.getString("reason_for_death"));
//jobj.put("other_reason_for_death",conn.rs.getString("other_reason_for_death"));
jobj.put("reason_for_declining",conn.rs.getString("reason_for_declining"));
//jobj.put("other_reason_for_declining",conn.rs.getString("other_reason_for_declining"));

jobj.put("timestamp",conn.rs.getString("timestamp"));
jobj.put("lastsynced",conn.rs.getString("lastsynced"));
jobj.put("datestartedart",conn.rs.getString("datestartedart"));

jobj.put("linkagedate",conn.rs.getString("datestartedart"));
jobj.put("started_on_art",conn.rs.getString("started_on_art"));
jobj.put("reason_not_started_art",conn.rs.getString("reason_not_started_art"));
//jobj.put("other_reason_not_started_on_treatment",conn.rs.getString("other_reason_not_started_on_treatment"));
jobj.put("started_tx_site",conn.rs.getString("started_tx_site"));
jobj.put("other_facility_started_art",conn.rs.getString("other_facility_started_art"));
jobj.put("reason_for_declining_art",conn.rs.getString("reason_for_declining_art"));
jobj.put("other_reason_for_declining_art",conn.rs.getString("other_reason_for_declining_art"));
jobj.put("reason_for_death_tx",conn.rs.getString("reason_for_death_tx"));
jobj.put("other_reason_for_death_tx",conn.rs.getString("other_reason_for_death_tx"));



            jarr.put(jobj);
                
                
            
            }
            
            
            out.println(jarr);
            
     
         if(conn.rs!=null){  conn.rs.close();  }
         if(conn.st!=null){ conn.st.close(); }
         if(conn.conne!=null){conn.conne.close(); }
         
        } catch (SQLException ex) {
            Logger.getLogger(getrawdata.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
