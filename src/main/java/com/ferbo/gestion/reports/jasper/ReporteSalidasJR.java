package com.ferbo.gestion.reports.jasper;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ferbo.gestion.tools.GestionException;

public class ReporteSalidasJR extends AbstractJR {
	
	private static Logger log = LogManager.getLogger(ReporteSalidasJR.class);
	public static final String reportNameJASPER = "/jasper/almacen/Salidas.jrxml";
	
	public ReporteSalidasJR(Connection conn) {
		super(conn);
	}
	
	public ReporteSalidasJR(Connection conn, String logoAbsolutePath) {
		super(conn, logoAbsolutePath);
	}
	
	public byte[] getPDF(Date fechaInicio, Date fechaFin, Integer idCliente,  Integer idPlanta, Integer idCamara)
	throws GestionException {
		byte[] bytes = null;
		
        InputStream         jrxml = null;
        JasperBL jasperBO = new JasperBL();
        
        try {
        	log.info("Ruta logo: " + this.logoPath);
        	jrxml = fsTools.getResourceStream(reportNameJASPER);
            this.jrParams.put("idCliente", idCliente);
            this.jrParams.put("fechaInicio",  fechaInicio);
            this.jrParams.put("fechaFin", fechaFin);
            this.jrParams.put("planta", idPlanta);
            this.jrParams.put("camara", idCamara);
            this.jrParams.put("imagen", this.logoPath);
            
            bytes = jasperBO.createPDF(this.jrParams, jrxml);
            
        } catch(Exception ex) {
            throw new GestionException("Problema en el procesamiento del reporte de inventario (PDF)...", ex);
        }
		
		return bytes;
	}
	
	public byte[] getXLSX(Date fechaInicio, Date fechaFin, Integer idCliente,  Integer idPlanta, Integer idCamara)
	throws GestionException {
		byte[] bytes = null;
		
        InputStream         jrxml = null;
        JasperBL jasperBO = new JasperBL();
        
        try {
        	log.info("Ruta logo: " + this.logoPath);
        	jrxml = fsTools.getResourceStream(reportNameJASPER);
            this.jrParams.put("idCliente", idCliente);
            this.jrParams.put("fechaInicio", fechaInicio);
            this.jrParams.put("fechaFin", fechaFin);
            this.jrParams.put("planta", idPlanta);
            this.jrParams.put("camara", idCamara);
            this.jrParams.put("imagen", this.logoPath);
            
            bytes = jasperBO.createXLSX(this.jrParams, jrxml);
            
        } catch(Exception ex) {
            throw new GestionException("Problema en el procesamiento del reporte de inventario (PDF)...", ex);
        }
		
		return bytes;
	}

}
