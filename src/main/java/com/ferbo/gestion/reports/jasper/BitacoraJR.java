package com.ferbo.gestion.reports.jasper;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ferbo.gestion.tools.GestionException;

public class BitacoraJR extends AbstractJR{

    private static final Logger log = LogManager.getLogger(BitacoraJR.class);

    public static final String reportNameJASPER = "/jasper/bitacora/Bitacora.jrxml";

    public BitacoraJR(Connection conn) {
        super(conn);
    }

    public BitacoraJR(Connection conn, String logoPath) {
        super(conn, logoPath);
    }

    public byte[] getPDF(Date fechaInicio, Date fechaFin, Integer idUsuario, String tipoPantalla, String nombrePantalla) throws GestionException {
        byte[] bytes = null;
		
        InputStream         jrxml = null;
        JasperBL jasperBO = new JasperBL();

        try {
            log.debug("Ruta logo: " + this.logoPath);
        	jrxml = this.fsTools.getResourceStream(reportNameJASPER);
            
            this.jrParams.put("P_FECHAINICIO", fechaInicio);
            this.jrParams.put("P_FECHAFIN", fechaFin);
            this.jrParams.put("P_CD_USUARIO", idUsuario);
            this.jrParams.put("P_NB_PANTA", tipoPantalla);
            this.jrParams.put("P_TP_PANTA", nombrePantalla);
            this.jrParams.put("imagen", this.logoPath);

            bytes = jasperBO.createPDF(jrParams, jrxml);

        } catch (Exception ex) {
            throw new GestionException("Problema en el procesamiento del reporte de bitácora (PDF)...", ex);
        }

        return bytes;
    }

    public byte[] getXLSX(Date fechaInicio, Date fechaFin, Integer idUsuario, String tipoPantalla, String nombrePantalla) throws GestionException {
        byte[] bytes = null;
		
        InputStream         jrxml = null;
        JasperBL jasperBO = new JasperBL();

        try {
            log.debug("Ruta logo: " + this.logoPath);
        	jrxml = this.fsTools.getResourceStream(reportNameJASPER);

            this.jrParams.put("P_FECHAINICIO", fechaInicio);
            this.jrParams.put("P_FECHAFIN", fechaFin);
            this.jrParams.put("P_CD_USUARIO", idUsuario);
            this.jrParams.put("P_NB_PANTA", tipoPantalla);
            this.jrParams.put("P_TP_PANTA", nombrePantalla);
            this.jrParams.put("imagen", this.logoPath);

            bytes = jasperBO.createXLSX(jrParams, jrxml);
        } catch (Exception ex) {
            throw new GestionException("Problema en el procesamiento del reporte de bitácora (XLSX)...", ex);
        }

        return bytes;
    }
 }
