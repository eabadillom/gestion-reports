package com.ferbo.gestion.reports.jasper.ingresos;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ferbo.gestion.reports.jasper.AbstractJR;
import com.ferbo.gestion.reports.jasper.JasperBL;
import com.ferbo.gestion.tools.GestionException;

public class IngresosJR extends AbstractJR {
	
	private static Logger log = LogManager.getLogger(IngresosJR.class);
	public static final String reportNameJASPER = "/jasper/ingresos/Ingresos.jrxml";

	public IngresosJR(Connection conn) {
		super(conn);
	}
	
	public IngresosJR(Connection conn, String logoPath) {
		super(conn, logoPath);
	}
	
	public byte[] getPDF(Date fechaInicio, Date fechaFin, Integer idCliente, Integer idBanco) throws GestionException {
		byte[] bytes = null;
		InputStream jrxml = null;
		JasperBL jasperBO = new JasperBL();
		
		try {
			log.debug("Ruta logo: {}", this.logoPath);
			jrxml = this.fsTools.getResourceStream(reportNameJASPER);
        	this.jrParams.put("idCliente", idCliente);
        	this.jrParams.put("idBanco", idBanco);
        	this.jrParams.put("fechaInicio", fechaInicio);
        	this.jrParams.put("fechaFin", fechaFin);
            this.jrParams.put("imagen", this.logoPath);
            
            bytes = jasperBO.createPDF(this.jrParams, jrxml);
			
		} catch(Exception ex) {
			throw new GestionException("Problema en el procesamiento del reporte de ingresos (PDF)...", ex);
		}
		
		return bytes;
	}
	
	public byte[] getXLSX(Date fechaInicio, Date fechaFin, Integer idCliente, Integer idBanco) throws GestionException {
		byte[] bytes = null;
		InputStream jrxml = null;
		JasperBL jasperBO = new JasperBL();
		
		try {
			log.debug("Ruta logo: {}", this.logoPath);
			jrxml = this.fsTools.getResourceStream(reportNameJASPER);
        	this.jrParams.put("idCliente", idCliente);
        	this.jrParams.put("idBanco", idBanco);
        	this.jrParams.put("fechaInicio", fechaInicio);
        	this.jrParams.put("fechaFin", fechaFin);
            this.jrParams.put("imagen", this.logoPath);
            
            bytes = jasperBO.createXLSX(this.jrParams, jrxml);
			
		} catch(Exception ex) {
			throw new GestionException("Problema en el procesamiento del reporte de ingresos (XLSX)...", ex);
		}
		
		return bytes;
	}
}
