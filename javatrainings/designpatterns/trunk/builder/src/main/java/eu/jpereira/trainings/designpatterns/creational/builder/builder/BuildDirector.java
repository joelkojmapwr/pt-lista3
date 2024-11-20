package eu.jpereira.trainings.designpatterns.creational.builder.builder;

import eu.jpereira.trainings.designpatterns.creational.builder.model.SaleEntry;
import eu.jpereira.trainings.designpatterns.creational.builder.model.Report;

public class BuildDirector {
    public Report buildReport(String type, SaleEntry saleEntry) {
        Builder builder = null;
        if (type.equals("HTML")) {
            builder = new HTMLBuilder();
        } else if (type.equals("XML")) {
            builder = new XMLBuilder();
        } else if (type.equals("JSON")) {
            builder = new JSONBuilder();
        }
        return builder.buildReport(saleEntry);
    }
}
