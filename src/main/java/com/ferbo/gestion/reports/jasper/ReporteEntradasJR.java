package com.ferbo.gestion.reports.jasper;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ferbo.gestion.tools.GestionException;

public class ReporteEntradasJR extends AbstractJR {
	private static Logger log = LogManager.getLogger(ReporteEntradasJR.class);
	
	public static final String reportNameJASPER = "/jasper/almacen/Entradas.jrxml";
	
	public ReporteEntradasJR(Connection conn) {
		super(conn);
	}
	
	public ReporteEntradasJR(Connection conn, String logoAbsolutePath) {
		super(conn, logoAbsolutePath);
	}
	
	public byte[] getPDF(Date fechaInicio, Date fechaFin, Integer idCliente,  Integer idPlanta, Integer idCamara)
	throws GestionException {
		byte[] bytes = null;
		
        InputStream         jrxml = null;
        JasperBL jasperBO = new JasperBL();
        
        try {
        	log.debug("Ruta logo: " + this.logoPath);
        	jrxml = fsTools.getResourceStream(reportNameJASPER);
            this.jrParams.put("FechaIni",  fechaInicio);
            this.jrParams.put("FechaFin", fechaFin);
            this.jrParams.put("idCliente", idCliente);
            this.jrParams.put("Camara", idCamara);
            this.jrParams.put("Planta", idPlanta);
            this.jrParams.put("imagen", this.logoPath);
            
            bytes = jasperBO.createPDF(jrParams, jrxml);
            
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
        	log.debug("Ruta logo: " + this.logoPath);
        	jrxml = this.fsTools.getResourceStream(reportNameJASPER);
            this.jrParams.put("FechaIni",  fechaInicio);
            this.jrParams.put("FechaFin", fechaFin);
            this.jrParams.put("idCliente", idCliente);
            this.jrParams.put("Camara", idCamara);
            this.jrParams.put("Planta", idPlanta);
            this.jrParams.put("imagen", logoPath);
            
            bytes = jasperBO.createXLSX(this.jrParams, jrxml);
            
        } catch(Exception ex) {
            throw new GestionException("Problema en el procesamiento del reporte de inventario (PDF)...", ex);
        }
		
		return bytes;
	}
}
