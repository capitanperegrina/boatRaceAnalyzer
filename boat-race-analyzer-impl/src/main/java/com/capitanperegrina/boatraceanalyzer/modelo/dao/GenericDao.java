package com.capitanperegrina.boatraceanalyzer.modelo.dao;

public abstract class GenericDao {
    
    protected static final String SELECT = "SELECT ";
    protected static final String FROM = " FROM ";
    
    protected static final String KEY_TABLE = "{table}";
    protected static final String KEY_FIELDS = "{fields}";
    protected static final String KEY_VALUES = "{values}";
    protected static final String KEY_CONDITION = "{condition}";
    
    protected static final String INSERT_SENTENCE = "INSERT INTO {table} ( {fields} ) VALUES ( {values} )";
    protected static final String EXISTS_SENTENCE = "SELECT COUNT(*) FROM  {table} WHERE {condition)";

    protected abstract String getTable();
    protected abstract String getFields();
    protected abstract String getInsertQuestionMarks();

    protected final String generateInsertQuery() {
        return INSERT_SENTENCE.replace(KEY_TABLE, getTable()).replace(KEY_FIELDS, getFields()).replace(KEY_VALUES, getInsertQuestionMarks() );    
    }
    
    protected final String generateInsertQuery( String condition ) {
        return EXISTS_SENTENCE.replace(KEY_TABLE, getTable()).replace(KEY_CONDITION, condition);
    }

}

