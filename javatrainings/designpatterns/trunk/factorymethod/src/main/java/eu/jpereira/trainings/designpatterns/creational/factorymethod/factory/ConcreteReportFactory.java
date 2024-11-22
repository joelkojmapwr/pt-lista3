package eu.jpereira.trainings.designpatterns.creational.factorymethod.factory;

import eu.jpereira.trainings.designpatterns.creational.factorymethod.Report;
import eu.jpereira.trainings.designpatterns.creational.factorymethod.ReportData;
import eu.jpereira.trainings.designpatterns.creational.factorymethod.HTMLReport;
import eu.jpereira.trainings.designpatterns.creational.factorymethod.JSONReport;
import eu.jpereira.trainings.designpatterns.creational.factorymethod.PDFReport;
import eu.jpereira.trainings.designpatterns.creational.factorymethod.XMLReport;

public class ConcreteReportFactory implements ReportFactory {
    @Override
    public Report createReport(String type, ReportData data) {
        Report newReport = null;

        if (type.equals("JSON")) {
            newReport = new JSONReport();
        } else if (type.equals("XML")) {
            newReport = new XMLReport();
        } else if (type.equals("HTML")) {
            newReport = new HTMLReport();
        } else if (type.equals("PDF")) {
            newReport = new PDFReport();
        }
        if (newReport != null) {
            newReport.generateReport(data);
        }
        return newReport;
    }
}