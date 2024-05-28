package com.example.foodapp.model.DA;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class QueryParameter {
    private int index;
    private Object value;

    public QueryParameter(int index, Object value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public Object getValue() {
        return value;
    }


    public static void setStatementParameters(PreparedStatement statement, List<QueryParameter> queryParameters) throws SQLException {
        for (QueryParameter param : queryParameters) {
            if (param.getValue() instanceof String) {
                statement.setString(param.getIndex(), (String) param.getValue());
            } else if (param.getValue() instanceof Integer) {
                statement.setInt(param.getIndex(), (Integer) param.getValue());
            } else if (param.getValue() instanceof Long) {
                statement.setLong(param.getIndex(), (Long) param.getValue());
            } else if (param.getValue() instanceof Float) {
                statement.setFloat(param.getIndex(), (Float) param.getValue());
            } else if (param.getValue() instanceof Double) {
                statement.setDouble(param.getIndex(), (Double) param.getValue());
            } else if (param.getValue() instanceof Boolean) {
                statement.setBoolean(param.getIndex(), (Boolean) param.getValue());
            } else if (param.getValue() instanceof java.sql.Date) {
                statement.setDate(param.getIndex(), (java.sql.Date) param.getValue());
            } else if (param.getValue() instanceof java.sql.Time) {
                statement.setTime(param.getIndex(), (java.sql.Time) param.getValue());
            } else if (param.getValue() instanceof java.sql.Timestamp) {
                statement.setTimestamp(param.getIndex(), (java.sql.Timestamp) param.getValue());
            } else if (param.getValue() instanceof byte[]) {
                statement.setBytes(param.getIndex(), (byte[]) param.getValue());
            } else if (param.getValue() == null) {
                statement.setNull(param.getIndex(), java.sql.Types.NULL);
            }
        }
    }

}
