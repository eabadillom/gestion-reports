package com.ferbo.gestion.jasper;

import java.sql.Connection;

public class AbstractJR {
	protected Connection conn = null;
	protected String logoPath = null;
	
	public AbstractJR(Connection conn) {
		this.conn = conn;
	}
	
	public AbstractJR(Connection conn, String logoPath) {
		this.conn = conn;
		this.logoPath = logoPath;
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
