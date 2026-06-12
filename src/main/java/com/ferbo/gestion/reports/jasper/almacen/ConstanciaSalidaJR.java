package com.ferbo.gestion.reports.jasper.almacen;

import java.io.InputStream;
import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ferbo.gestion.reports.jasper.AbstractJR;
import com.ferbo.gestion.reports.jasper.JasperBL;
import com.ferbo.gestion.tools.GestionException;

/**Clase para generar la constancia de salida (ticket). Sólo se implementa la conversión a PDF,
 * ya que no requiere exportación a excel.
 */
public class ConstanciaSalidaJR extends AbstractJR {
	
	private static final Logger log = LogManager.getLogger(ConstanciaSalidaJR.class);
	public static final String reportNameJASPER = "/jasper/almacen/ConstanciaSalida.jrxml";

	public ConstanciaSalidaJR(Connection conn) {
		super(conn);
	}
	
	public ConstanciaSalidaJR(Connection conn, String logoAbsolutePath) {
		super(conn, logoAbsolutePath);
	}
	
	public byte[] getPDF(String folio)
	throws GestionException{
		byte[] bytes = null;
		
		InputStream jrxml = null;
        JasperBL jasperBO = new JasperBL();
        
        try {
        	log.debug("Ruta logo: " + this.logoPath);
        	jrxml = this.fsTools.getResourceStream(reportNameJASPER);
			this.jrParams.put("folioCliente", folio);
			this.jrParams.put("logoPath", logoPath);
            
            bytes = jasperBO.createPDF(this.jrParams, jrxml);
            
        } catch(Exception ex) {
            throw new GestionException("Problema en el procesamiento del reporte de inventario (PDF)...", ex);
        }
		
		return bytes;
	}

}
