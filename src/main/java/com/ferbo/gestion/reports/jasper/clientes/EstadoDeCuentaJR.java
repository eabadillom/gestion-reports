package com.ferbo.gestion.reports.jasper.clientes;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ferbo.gestion.reports.jasper.AbstractJR;
import com.ferbo.gestion.reports.jasper.JasperBL;
import com.ferbo.gestion.tools.GestionException;

public class EstadoDeCuentaJR extends AbstractJR {
	
	private static Logger log = LogManager.getLogger(EstadoDeCuentaJR.class);
	public static final String reportNameJASPER = "/jasper/contabilidad/clientes/EstadoDeCuenta.jrxml";

	public EstadoDeCuentaJR(Connection conn) {
		super(conn);
	}

	public EstadoDeCuentaJR(Connection conn, String logoPath) {
		super(conn, logoPath);
	}

	public byte[] getPDF(Integer idCliente, Date fechaInicio, Date fechaFin)
	throws GestionException {
		byte[] bytes = null;
		
		InputStream jrxml = null;
        JasperBL jasperBO = new JasperBL();
        
        try {
        	log.debug("Ruta logo: " + this.logoPath);
        	jrxml = this.fsTools.getResourceStream(reportNameJASPER);
        	this.jrParams.put("idCliente", idCliente);
        	this.jrParams.put("fechaInicio", fechaInicio);
        	this.jrParams.put("fechaFin", fechaFin);
            this.jrParams.put("imagen", this.logoPath);
            
            bytes = jasperBO.createPDF(this.jrParams, jrxml);
            
        } catch(Exception ex) {
            throw new GestionException("Problema en el procesamiento del reporte de inventario (PDF)...", ex);
        }
		
		return bytes;
	}
	
	public byte[] getXLSX(Integer idCliente, Date fechaInicio, Date fechaFin)
	throws GestionException {
		byte[] bytes = null;
		
		InputStream jrxml = null;
        JasperBL jasperBO = new JasperBL();
        
        try {
        	log.debug("Ruta logo: " + this.logoPath);
        	jrxml = this.fsTools.getResourceStream(reportNameJASPER);
        	
        	this.jrParams.put("idCliente", idCliente);
        	this.jrParams.put("fechaInicio", fechaInicio);
        	this.jrParams.put("fechaFin", fechaFin);
            this.jrParams.put("imagen", this.logoPath);
            
            bytes = jasperBO.createXLSX(this.jrParams, jrxml);
            
        } catch(Exception ex) {
            throw new GestionException("Problema en el procesamiento del reporte de inventario (PDF)...", ex);
        }
		
		return bytes;
	}

}
