package com.github.p2g3.dataVisualization.io;

import java.util.ArrayList;

public class StorageVar {
    private float m_coeff;
    private float b_coeff;
    private float r_value;
    private ArrayList<String[]> cacheVals;
    private String tableName;
    private String col1Name;
    private String col2Name;

    public StorageVar(String c1Name, String c2Name){
        this.col1Name = c1Name;
        this.col2Name = c2Name;

        createSqlStmt();
    }

    public void insertVals(float m, float b, float r){
        this.m_coeff = m;
        this.b_coeff = b;
        this.r_value = r;
    }

    private void createSqlStmt(){
        this.tableName = this.col1Name
                        +"_over_"
                        +this.col2Name;
    }

    public String getTableName(){
        return this.tableName;
    }

    public String getCol1Name(){
        return this.col1Name;
    }

    public String getCol2Name(){
        return this.col2Name;
    }

    public ArrayList<String[]> getCache(){
        return this.cacheVals;
    }

    public float getM_coeff() {
        return m_coeff;
    }

    public float getB_coeff() {
        return b_coeff;
    }

    public float getR_value() {
        return r_value;
    }
   
}