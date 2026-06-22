package com.ferbo.gestion.reports.util;

public enum TipoCarteraCliente 
{
    DESGLOSADO("/jasper/facturacion/CarteraClientesDesglosada.jrxml"),
    CONCENTRADO("/jasper/facturacion/CarteraClientesConcentrada.jrxml");

    private final String reportPath;

    TipoCarteraCliente(String reportPath) {
        this.reportPath = reportPath;
    }

    public String getReportPath() {
        return reportPath;
    }
    
}
