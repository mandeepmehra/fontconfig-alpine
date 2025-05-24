package com.reports.JasperDemo.service;



import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.reports.JasperDemo.model.Person;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    public byte[] generateReport() throws Exception {
        // Load JRXML file from resources

        ClassPathResource resource = new ClassPathResource("reports/sample_report.jrxml");
        InputStream reportInputStream = resource.getInputStream();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportInputStream);

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