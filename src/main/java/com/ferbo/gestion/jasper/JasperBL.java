package com.ferbo.gestion.jasper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.ferbo.gestion.tools.IOTools;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

public class JasperBL {
	
	public byte[] createPDF(Map<String, Object> jrParams, String jrxmlPath) throws IOException {
		byte[] bytes = null;
		ByteArrayOutputStream output = null;
		JasperDesign design = null;
		JasperReport report = null;
		JasperPrint jasperPrint = null;
		try {
			output = new ByteArrayOutputStream();
			design = JRXmlLoader.load(jrxmlPath);
			report = JasperCompileManager.compileReport(design);
			jasperPrint = JasperFillManager.fillReport(report, jrParams);
			JasperExportManager.exportReportToPdfStream(jasperPrint, output);
			bytes = output.toByteArray();
		} catch (JRException ex) {
			ex.printStackTrace();
		}finally {
			IOTools.close(output);
		}
		
		return bytes;
	}
	
	public byte[] createPDF(Map<String, Object> jrParams, InputStream jasperIS) throws IOException {
		byte[] bytes = null;
		ByteArrayOutputStream output = null;
		JasperDesign design = null;
		JasperReport report = null;
		JasperPrint jasperPrint = null;
		try {
			output = new ByteArrayOutputStream();
			design = JRXmlLoader.load(jasperIS);
			report = JasperCompileManager.compileReport(design);
			jasperPrint = JasperFillManager.fillReport(report, jrParams);
			JasperExportManager.exportReportToPdfStream(jasperPrint, output);
			bytes = output.toByteArray();
		} catch (JRException ex) {
			ex.printStackTrace();
		}finally {
			IOTools.close(output);
		}
		
		return bytes;
	}
	
	public byte[] createXLSX(Map<String, Object> jrParams, String jrxmlPath) throws IOException {
		byte[] bytes = null;
		ByteArrayOutputStream output = null;
		JasperDesign design = null;
		JasperReport report = null;
		JasperPrint jasperPrint = null;
		JRXlsxExporter exporter = null;
		SimpleXlsxReportConfiguration configuration = null;
		OutputStreamExporterOutput outputExporter = null;
		
		try {
			output = new ByteArrayOutputStream();
			design = JRXmlLoader.load(jrxmlPath);
			report = JasperCompileManager.compileReport(design);
			jasperPrint = JasperFillManager.fillReport(report, jrParams);
			outputExporter = new SimpleOutputStreamExporterOutput(output);
			exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(outputExporter);
			configuration = new SimpleXlsxReportConfiguration();
			configuration.setRemoveEmptySpaceBetweenColumns(true);
			configuration.setRemoveEmptySpaceBetweenRows(true);
			configuration.setDetectCellType(true);
			configuration.setIgnoreGraphics(true);
			configuration.setIgnorePageMargins(true);
			configuration.setIgnoreCellBorder(true);
			configuration.setWhitePageBackground(false);
			exporter.setConfiguration(configuration);
			exporter.exportReport();
			
			bytes = output.toByteArray();
		} catch (JRException ex) {
			ex.printStackTrace();
		} finally {
			IOTools.close(output);
		}
		
		return bytes;
	}
	
	public byte[] createXLSX(Map<String, Object> jrParams, InputStream jasperIS) throws IOException {
		byte[] bytes = null;
		ByteArrayOutputStream output = null;
		JasperDesign design = null;
		JasperReport report = null;
		JasperPrint jasperPrint = null;
		JRXlsxExporter exporter = null;
		SimpleXlsxReportConfiguration configuration = null;
		OutputStreamExporterOutput outputExporter = null;
		
		try {
			output = new ByteArrayOutputStream();
			design = JRXmlLoader.load(jasperIS);
			report = JasperCompileManager.compileReport(design);
			jasperPrint = JasperFillManager.fillReport(report, jrParams);
			outputExporter = new SimpleOutputStreamExporterOutput(output);
			exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(outputExporter);
			configuration = new SimpleXlsxReportConfiguration();
			configuration.setRemoveEmptySpaceBetweenColumns(true);
			configuration.setRemoveEmptySpaceBetweenRows(true);
			configuration.setDetectCellType(true);
			configuration.setIgnoreGraphics(true);
			configuration.setIgnorePageMargins(true);
			configuration.setIgnoreCellBorder(true);
			configuration.setWhitePageBackground(false);
			exporter.setConfiguration(configuration);
			exporter.exportReport();
			
			bytes = output.toByteArray();
		} catch (JRException ex) {
			ex.printStackTrace();
		} finally {
			IOTools.close(output);
		}
		
		return bytes;
	}
}
