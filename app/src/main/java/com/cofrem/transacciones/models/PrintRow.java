package com.cofrem.transacciones.models;

/**
 * Created by luispineda on 12/07/17.
 */

public class PrintRow {

    private String msg1,msg2;
    private int fonzise1, fonzise2;
    private int position1, position2;
    private int walkPaper;

    public PrintRow() {
    }

    public PrintRow(String msg1, int fonzise1, int position1,int walkPaper) {
        this.msg1 = msg1;
        this.fonzise1 = fonzise1;
        this.position1 = position1;
        this.walkPaper = walkPaper;
    }

    public PrintRow(String msg1, int fonzise1, int position1,String msg2, int fonzise2, int position2, int walkPaper) {
        this.msg1 = msg1;
        this.msg2 = msg2;
        this.fonzise1 = fonzise1;
        this.fonzise2 = fonzise2;
        this.position1 = position1;
        this.position2 = position2;
        this.walkPaper = walkPaper;
    }

    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

    public String getMsg2() {
        return msg2;
    }

    public void setMsg2(String msg2) {
        this.msg2 = msg2;
    }

    public int getFonzise1() {
        return fonzise1;
    }

    public void setFonzise1(int fonzise1) {
        this.fonzise1 = fonzise1;
    }

    public int getFonzise2() {
        return fonzise2;
    }

    public void setFonzise2(int fonzise2) {
        this.fonzise2 = fonzise2;
    }

    public int getPosition1() {
        return position1;
    }

    public void setPosition1(int position1) {
        this.position1 = position1;
    }

    public int getPosition2() {
        return position2;
    }

    public void setPosition2(int position2) {
        this.position2 = position2;
    }

    public int getWalkPaper() {
        return walkPaper;
    }

    public void setWalkPaper(int walkPaper) {
        this.walkPaper = walkPaper;
    }
}
