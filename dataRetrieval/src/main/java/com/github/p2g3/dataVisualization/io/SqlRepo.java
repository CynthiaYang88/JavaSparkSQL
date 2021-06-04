package com.github.p2g3.dataVisualization.io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlRepo implements Dao<String[]> {
    private SqlDataSource dataSource;
    private List<String[]> cache;

    public SqlRepo(SqlDataSource source){
        this.dataSource = source;
        this.cache = new ArrayList<>();
    }


    @Override
    public void insertAll(StorageVar dataVars) {
        String sql = "insert into coeffs(tablename, m_coeff, b_coeff, r_value) values(?,?,?,?)";
        try(Connection connection = this.dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);){
        //for (int i =0; i< dataVars.size();i++){
                statement.setString(1, dataVars.getTableName());
                statement.setFloat(2, dataVars.getM_coeff());
                statement.setFloat(3, dataVars.getB_coeff());
                statement.setFloat(4, dataVars.getR_value());
                statement.addBatch();
                statement.executeBatch();
            }
        //}
        catch (SQLException e) {
            System.err.println(e.getMessage());
            }

    }

    @Override
    public List<String[]> readAll(StorageVar tableNames) {
        if (cache.isEmpty()){
            String sql = "select * from coeffs";

            try (Connection connection = this.dataSource.getConnection();
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);){
                
                while(resultSet.next()){
                    String tNames = resultSet.getString("tablename");
                    String mcoeff = resultSet.getString("m_coeff");
                    String bcoeff = resultSet.getString("b_coeff");
                    String rvalue = resultSet.getString("r_value");
                    String[] outResult = new String[4];
                    outResult[0] = tNames;
                    outResult[1] = mcoeff;
                    outResult[2] = bcoeff;
                    outResult[3] = rvalue;
                    cache.add(outResult);
                }
            } catch(SQLException e){
                System.err.println(e.getMessage());
                }   
            return cache;} 
        else {
            return cache;}
    }   
    }