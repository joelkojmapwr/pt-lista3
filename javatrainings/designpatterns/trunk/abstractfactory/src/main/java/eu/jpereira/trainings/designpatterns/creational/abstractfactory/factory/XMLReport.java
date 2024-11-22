package eu.jpereira.trainings.designpatterns.creational.abstractfactory.factory;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.Report;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.xml.XMLReportBody;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.xml.XMLReportFooter;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.xml.XMLReportHeader;

public class XMLReport extends Report {

    @Override
    public void createReport() {
        this.setReportType("XML");
        this.setBody(new XMLReportBody());
        this.setFooter(new XMLReportFooter());
        this.setHeader(new XMLReportHeader());
    }
    
}