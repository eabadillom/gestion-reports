package com.ferbo.gestion.reports.jasper;

import com.ferbo.gestion.reports.util.TipoCarteraCliente;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ferbo.gestion.tools.GestionException;

public class CarteraClienteJR extends AbstractJR
{
    private static Logger log = LogManager.getLogger(CarteraClienteJR.class);
	
    private final TipoCarteraCliente tipoCartera;
    private final static String PDF = "PDF";
    private final static String EXCEL = "XLSX";
    
    public CarteraClienteJR(Connection conn, TipoCarteraCliente tipoCartera) {
        super(conn);
        this.tipoCartera = tipoCartera;
    }
    
    public CarteraClienteJR(Connection conn, String logoAbsolutePath, TipoCarteraCliente tipoCartera) {
        super(conn, logoAbsolutePath);
        this.tipoCartera = tipoCartera;
    }
    
    public byte[] getPDF(Integer clienteCve, String emisorRFC, Date fecha) throws GestionException {
        return generarReporte(clienteCve, emisorRFC, fecha, PDF);
    }
    
    public byte[] getPDF(List<Integer> clienteCve, String emisorRFC, Date fecha) throws GestionException {
        return generarReporte(clienteCve, emisorRFC, fecha, PDF);
    }
    
    public byte[] getXLSX(Integer clienteCve, String emisorRFC, Date fecha) throws GestionException {
        return generarReporte(clienteCve, emisorRFC, fecha, EXCEL);
    }
    
    public byte[] getXLSX(List<Integer> clienteCve, String emisorRFC, Date fecha) throws GestionException {
        return generarReporte(clienteCve, emisorRFC, fecha, EXCEL);
    }
    
    private byte[] generarReporte(Object clienteCve, String emisorRFC, Date fecha, String formato) throws GestionException 
    {
        byte[] bytes = null;
        JasperBL jasperBO = new JasperBL();
        
        try {
            log.debug("Ruta logo: " + this.logoPath);
            InputStream jrxml = fsTools.getResourceStream(this.tipoCartera.getReportPath());
            this.jrParams.put("idCliente", clienteCve);
            this.jrParams.put("emisorRFC", emisorRFC);
            this.jrParams.put("fecha", fecha);
            this.jrParams.put("imagen", this.logoPath);
            
            if ("PDF".equalsIgnoreCase(formato)) {
                bytes = jasperBO.createPDF(jrParams, jrxml);
            } else {
                bytes = jasperBO.createXLSX(jrParams, jrxml);
            }
            
        } catch(Exception ex) {
            throw new GestionException(String.format("Problema en el procesamiento del reporte de cartera de clientes (%s)...", formato), ex);
        }
        
        return bytes;
    }
    
}
