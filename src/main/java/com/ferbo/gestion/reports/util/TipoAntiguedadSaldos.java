package com.ferbo.gestion.reports.util;

public enum TipoAntiguedadSaldos 
{
    DESGLOSADO("/jasper/facturacion/AntiguedadSaldosDesglosado.jrxml"),
    CONDENSADO("/jasper/facturacion/AntiguedadSaldosCondensado.jrxml");

    private final String reportPath;
    
    TipoAntiguedadSaldos(String reportPath) {
        this.reportPath = reportPath;
    }
    
    public String getReportPath() {
        return reportPath;
    }
    
}
