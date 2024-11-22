package eu.jpereira.trainings.designpatterns.creational.abstractfactory.factory;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.Report;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.json.JSONReportBody;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.json.JSONReportFooter;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.json.JSONReportHeader;

public class JSONReport extends Report {

    @Override
    public void createReport() {
        this.setReportType("JSON");
        this.setBody(new JSONReportBody());
        this.setFooter(new JSONReportFooter());
        this.setHeader(new JSONReportHeader());
    }
    
}
