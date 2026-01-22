package com.ferbo.gestion.reports.jasper;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
        Map<String, Object> jrParams = null;
        
        JasperBL jasperBO = new JasperBL();
        
        try {
        	log.debug("Ruta logo: " + this.logoPath);
        	jrxml = fsTools.getResourceStream(reportNameJASPER);
        	
            jrParams = new HashMap<String, Object>();
            jrParams.put("REPORT_CONNECTION", conn);
            jrParams.put("FechaIni",  fechaInicio);
            jrParams.put("FechaFin", fechaFin);
            jrParams.put("idCliente", idCliente);
            jrParams.put("Camara", idCamara);
            jrParams.put("Planta", idPlanta);
            jrParams.put("imagen", logoPath);
            jrParams.put("REPORT_LOCALE", new Locale("es", "MX"));
            
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
        Map<String, Object> jrParams = null;
        
        JasperBL jasperBO = new JasperBL();
        
        try {
        	log.debug("Ruta logo: " + this.logoPath);
        	jrxml = fsTools.getResourceStream(reportNameJASPER);
        	
            jrParams = new HashMap<String, Object>();
            jrParams.put("REPORT_CONNECTION", conn);
            jrParams.put("FechaIni",  fechaInicio);
            jrParams.put("FechaFin", fechaFin);
            jrParams.put("idCliente", idCliente);
            jrParams.put("Camara", idCamara);
            jrParams.put("Planta", idPlanta);
            jrParams.put("imagen", logoPath);
            jrParams.put("REPORT_LOCALE", new Locale("es", "MX"));
            
            bytes = jasperBO.createXLSX(jrParams, jrxml);
            
        } catch(Exception ex) {
            throw new GestionException("Problema en el procesamiento del reporte de inventario (PDF)...", ex);
        }
		
		return bytes;
	}
}
