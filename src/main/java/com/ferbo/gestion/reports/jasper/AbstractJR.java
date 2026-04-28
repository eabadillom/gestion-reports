package com.ferbo.gestion.reports.jasper;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import com.ferbo.gestion.tools.FSTools;

public class AbstractJR {
	protected Connection conn = null;
	protected String logoPath = null;
	protected FSTools fsTools = null;
	protected Map<String, Object> jrParams = null;
	
	public AbstractJR(Connection conn) {
		this.conn = conn;
		this.fsTools = new FSTools();
		this.jrParams = this.initJRParameters();
	}
	
	public AbstractJR(Connection conn, String logoPath) {
		this(conn);
		this.logoPath = logoPath;
	}
	
	protected Map<String, Object> initJRParameters() {
		Map<String, Object> parameters = null;
		parameters = new HashMap<String, Object>();
		parameters.put("REPORT_CONNECTION", this.conn);
		parameters.put("REPORT_LOCALE", new Locale("es", "MX"));
		parameters.put("REPORT_TIME_ZONE", TimeZone.getTimeZone("GMT-6"));
		return parameters;
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
