/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import com.TF.mantenimiento.ControlReservas;
import com.TF.persistencia.Reservas;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author juan.serranoUSAM
 */
@ManagedBean
@RequestScoped
public class reportes {

   private ControlReservas control = new ControlReservas();
  private  List<Reservas> listaRes = new ArrayList<Reservas>();

    /**
     * Creates a new instance of reportes
     */
    public reportes() {
    }
    
    public void reportePanda() throws JRException, IOException{
        try {
            Conexion conn = new Conexion();
        Integer suc = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sucursal");
        String sucName = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sucName");
        
        String Logo = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/imagen/pE.png");
        
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("sucursal", suc);
        parametros.put("sucName", sucName);
        parametros.put("logo", Logo);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reservasPanda.jasper"));
        JasperPrint jprint = JasperFillManager.fillReport(jasper.getPath(), parametros,conn.conectar());
        
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition","attachment; filename=reservasPanda.pdf");
        ServletOutputStream stream = response.getOutputStream();
        
        JasperExportManager.exportReportToPdfStream(jprint, stream);
        stream.flush();
        stream.close();
        
        FacesContext.getCurrentInstance().responseComplete();
        
        } catch (Exception e) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("er", e);
            FacesContext.getCurrentInstance().getExternalContext().redirect("vistaError.xhtml");         
        }
    }

    public ControlReservas getControl() {
        return control;
    }

    public void setControl(ControlReservas control) {
        this.control = control;
    }

    public List<Reservas> getListaRes() {
        return listaRes;
    }

    public void setListaRes(List<Reservas> listaRes) {
        this.listaRes = listaRes;
    }
    
}
