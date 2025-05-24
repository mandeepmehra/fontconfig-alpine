package com.reports.JasperDemo.service;



import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.reports.JasperDemo.model.Person;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    public byte[] generateReport() throws Exception {
        // Load JRXML file from resources
        File file = ResourceUtils.getFile("classpath:reports/sample_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        // Sample data
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("John Doe", "USA"));
        persons.add(new Person("Jane Smith", "Canada"));
        persons.add(new Person("Alice Brown", "UK"));

        // Create data source
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(persons);

        // Parameters (optional)
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Spring Boot Jasper Demo");

        // Fill report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export to PDF as byte array
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}