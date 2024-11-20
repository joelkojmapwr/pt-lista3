package eu.jpereira.trainings.designpatterns.creational.builder.builder;

import eu.jpereira.trainings.designpatterns.creational.builder.model.Report;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SaleEntry;

public abstract class Builder {
    //public Report report;
    public SaleEntry saleEntry;
    public abstract Report buildReport(SaleEntry saleEntry);
    public Report report  = new Report();
}
