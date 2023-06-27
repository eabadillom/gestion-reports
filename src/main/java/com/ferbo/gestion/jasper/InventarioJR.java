package com.ferbo.gestion.jasper;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ferbo.gestion.tools.GestionException;

public class InventarioJR {
	
	private Connection conn = null;
	
	public InventarioJR(Connection conn) {
		this.conn = conn;
	}
	
	private static Logger log = LogManager.getLogger(InventarioJR.class);
	
	public byte[] getPDFReporteInventario(Integer idCliente, Integer idPlanta) throws GestionException {
		byte[] bytes = null;
		
		Date                fecha = new Date();
        String              reportNameJASPER = null;
        //File                reportFile = null;
        InputStream         reportFile = null;
        
        Map<String, Object> jrParams = null;
        
        JasperBL jasperBO = new JasperBL();
        
        try {
            
            String logoPath = "/jasper/images/logo.png";
            File logoFile = new File(getClass().getResource(logoPath).getFile());
            log.info("Ruta logo: " + logoFile.getPath());
            if(logoFile.exists() == false)
                log.error("El archivo no existe: " + logoFile.getPath());
            
            reportNameJASPER = "/jasper/almacen/InventarioAlmacen.jrxml";
            
            
            //reportFile       = new File( getClass().getResource(reportNameJASPER).getFile() );
            reportFile = getClass().getClassLoader().getResourceAsStream(reportNameJASPER);
            
            jrParams = new HashMap<String, Object>();
            jrParams.put("REPORT_CONNECTION", conn);
            jrParams.put("imagen", logoPath);
            jrParams.put("idCliente", String.valueOf(idCliente));
            jrParams.put("fecha", fecha);
            jrParams.put("camara", null);
            jrParams.put("planta", idPlanta);
            jrParams.put("REPORT_LOCALE", new Locale("es", "MX"));
            
            //bytes = jasperBO.createPDF(jrParams, reportFile.getPath());
            bytes = jasperBO.createPDF(jrParams, reportFile);
            
        } catch(Exception ex) {
            throw new GestionException("Problema en el procesamiento del reporte de inventario (PDF)...", ex);
        }
        
        return bytes;
	}
	
	public byte[] getXLSReporteInventario(Integer idCliente, Integer idPlanta) throws GestionException {
		byte[] bytes = null;
		
		Date                fecha = new Date();
        String              reportNameJASPER = null;
        File                reportFile = null;
        Map<String, Object> jrParams = null;
        
        JasperBL jasperBO = new JasperBL();
        
        try {
            
            String logoPath = "/jasper/logo.png";
            File logoFile = new File(getClass().getResource(logoPath).getFile());
            log.info("Ruta logo: " + logoFile.getPath());
            if(logoFile.exists() == false)
                log.error("El archivo no existe: " + logoFile.getPath());
            
            reportNameJASPER = "/com/hoth/jasper/inventario/inventario.jasper";
            reportFile       = new File( getClass().getResource(reportNameJASPER).getFile() );
            
            jrParams = new HashMap<String, Object>();
            jrParams.put("REPORT_CONNECTION", conn);
            jrParams.put("imagen", logoPath);
            jrParams.put("idCliente", String.valueOf(idCliente));
            jrParams.put("fecha", fecha);
            jrParams.put("camara", null);
            jrParams.put("planta", idPlanta);
            jrParams.put("REPORT_LOCALE", new Locale("es", "MX"));
            
            bytes = jasperBO.createXLSX(jrParams, reportFile.getPath());
            
        } catch(Exception ex) {
            throw new GestionException("Problema en el procesamiento del reporte de inventario (PDF)...", ex);
        }
        
        return bytes;
	}

}
