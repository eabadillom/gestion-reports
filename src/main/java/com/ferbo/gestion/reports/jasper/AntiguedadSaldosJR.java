package com.ferbo.gestion.reports.jasper;

import com.ferbo.gestion.reports.util.TipoAntiguedadSaldos;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ferbo.gestion.tools.GestionException;

public class AntiguedadSaldosJR extends AbstractJR
{
    private static Logger log = LogManager.getLogger(AntiguedadSaldosJR.class);
	
    private final TipoAntiguedadSaldos tipoSaldos;
    private final static String PDF = "PDF";
    private final static String EXCEL = "XLSX";
    
    public AntiguedadSaldosJR(Connection conn, TipoAntiguedadSaldos tipoSaldos){
        super(conn);
        this.tipoSaldos = tipoSaldos;
    }
    
    public AntiguedadSaldosJR(Connection conn, String logoAbsolutePath, TipoAntiguedadSaldos tipoSaldos){
        super(conn, logoAbsolutePath);
        this.tipoSaldos = tipoSaldos;
    }
    
    public byte[] getPDF(Integer idCliente, String emisorRFC, Date fecha) throws GestionException {
        return generarReporte(idCliente, emisorRFC, fecha, PDF);
    }
    
    public byte[] getPDF(List<Integer> idCliente, String emisorRFC, Date fecha) throws GestionException {
        return generarReporte(idCliente, emisorRFC, fecha, PDF);
    }
    
    public byte[] getXLSX(Integer idCliente, String emisorRFC, Date fecha) throws GestionException {
        return generarReporte(idCliente, emisorRFC, fecha, EXCEL);
    }
    
    public byte[] getXLSX(List<Integer> idCliente, String emisorRFC, Date fecha) throws GestionException {
        return generarReporte(idCliente, emisorRFC, fecha, EXCEL);
    }
    
    private byte[] generarReporte(Object clienteCve, String emisorRFC, Date fecha, String formato) throws GestionException 
    {
        byte[] bytes = null;
        JasperBL jasperBO = new JasperBL();
        
        try {
            log.debug("Ruta logo: " + this.logoPath);
            InputStream jrxml = fsTools.getResourceStream(this.tipoSaldos.getReportPath());
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
            throw new GestionException(String.format("Problema en el procesamiento del reporte de antiguedad de saldos (%s)...", formato), ex);
        }
        
        return bytes;
    }
    
}
