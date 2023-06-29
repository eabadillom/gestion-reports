package com.ferbo.gestion.jasper;

import java.sql.Connection;

import com.ferbo.gestion.tools.FSTools;

public class AbstractJR {
	protected Connection conn = null;
	protected String logoPath = null;
	protected FSTools fsTools = null;
	
	public AbstractJR(Connection conn) {
		this.conn = conn;
		fsTools = new FSTools();
	}
	
	public AbstractJR(Connection conn, String logoPath) {
		this.conn = conn;
		this.logoPath = logoPath;
		fsTools = new FSTools();
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
}
