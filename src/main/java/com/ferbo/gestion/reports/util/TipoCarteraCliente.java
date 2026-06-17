package com.ferbo.gestion.reports.util;

public enum TipoCarteraCliente 
{
    DESGLOSADO("/jasper/facturacion/Desglosada.jrxml"),
    CONCENTRADO("/jasper/facturacion/Concentrada.jrxml");

    private final String reportPath;

    TipoCarteraCliente(String reportPath) {
        this.reportPath = reportPath;
    }

    public String getReportPath() {
        return reportPath;
    }
    
}
