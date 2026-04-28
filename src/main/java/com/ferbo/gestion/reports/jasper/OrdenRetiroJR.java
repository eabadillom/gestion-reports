package com.ferbo.gestion.reports.jasper;

import java.io.InputStream;
import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ferbo.gestion.tools.GestionException;

public class OrdenRetiroJR extends AbstractJR {
	
	private static final Logger log = LogManager.getLogger(KardexJR.class);
	public static final String reportNameJASPER = "/jasper/almacen/OrdenRetiro.jrxml";

	public OrdenRetiroJR(Connection conn) {
		super(conn);
	}
	
	public OrdenRetiroJR(Connection conn, String logoAbsolutePath) {
		super(conn, logoAbsolutePath);
	}
	
	public byte[] getPDF(String folioSalida, Integer idPlanta, Boolean isHorarioEspecial)
	throws GestionException {
		byte[] bytes = null;
		
		InputStream jrxml = null;
        JasperBL jasperBO = new JasperBL();
        
        try {
        	log.debug("Ruta logo: " + this.logoPath);
        	jrxml = this.fsTools.getResourceStream(reportNameJASPER);
            this.jrParams.put("folioSalida", folioSalida);
			this.jrParams.put("idPlanta", idPlanta);
			this.jrParams.put("esHorarioEspecial", isHorarioEspecial);
			this.jrParams.put("logoPath", logoPath);
            
            bytes = jasperBO.createPDF(this.jrParams, jrxml);
            
        } catch(Exception ex) {
            throw new GestionException("Problema en el procesamiento del reporte de inventario (PDF)...", ex);
        }
		
		return bytes;
	}
	
	public byte[] getXLSX(String folioSalida, Integer idPlanta, Boolean isHorarioEspecial)
	throws GestionException {
		byte[] bytes = null;
		
		InputStream jrxml = null;
        JasperBL jasperBO = new JasperBL();
        
        try {
        	log.debug("Ruta logo: " + this.logoPath);
        	
        	jrxml = fsTools.getResourceStream(reportNameJASPER);
        	
            this.jrParams.put("folioSalida", folioSalida);
			this.jrParams.put("idPlanta", idPlanta);
			this.jrParams.put("esHorarioEspecial", isHorarioEspecial);
			this.jrParams.put("logoPath", this.logoPath);
            
            bytes = jasperBO.createXLSX(this.jrParams, jrxml);
            
        } catch(Exception ex) {
            throw new GestionException("Problema en el procesamiento del reporte de inventario (PDF)...", ex);
        }
		
		return bytes;
	}

}
