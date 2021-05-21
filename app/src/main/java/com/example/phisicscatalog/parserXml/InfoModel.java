package com.example.phisicscatalog.parserXml;

public class InfoModel {
    private String theme;
    private String formula;
    private String description;

    public InfoModel(String theme, String formula, String description) {
        this.theme = theme;
        this.formula = formula;
        this.description = description;
    }

    public String getTheme() {
        return theme;
    }

    public String getFormula() {
        return formula;
    }

    public String getDescription() {
        return description;
    }
}
