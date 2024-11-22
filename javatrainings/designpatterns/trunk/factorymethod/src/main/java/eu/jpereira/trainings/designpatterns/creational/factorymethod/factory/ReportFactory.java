package eu.jpereira.trainings.designpatterns.creational.factorymethod.factory;

import eu.jpereira.trainings.designpatterns.creational.factorymethod.Report;
import eu.jpereira.trainings.designpatterns.creational.factorymethod.ReportData;

public interface ReportFactory {
    public Report createReport(String type, ReportData data);
} 
