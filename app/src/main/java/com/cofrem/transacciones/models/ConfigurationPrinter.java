package com.cofrem.transacciones.models;

/**
 * Created by luispineda on 8/08/17.
 */

public class ConfigurationPrinter {

    private int gray_level;
    private int font_size;

    public ConfigurationPrinter() {
    }

    public ConfigurationPrinter(int gray_level, int font_size) {
        this.gray_level = gray_level;
        this.font_size = font_size;
    }

    public int getGray_level() {
        return gray_level;
    }

    public void setGray_level(int gray_level) {
        this.gray_level = gray_level;
    }

    public int getFont_size() {
        return font_size;
    }

    public void setFont_size(int font_size) {
        this.font_size = font_size;
    }
}
