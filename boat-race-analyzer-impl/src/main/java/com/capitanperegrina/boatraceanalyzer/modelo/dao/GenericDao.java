package com.capitanperegrina.boatraceanalyzer.modelo.dao;

import org.apache.commons.lang3.StringUtils;

public abstract class GenericDao {

	protected static final String INSERT_INTO = "INSERT INTO ";
	protected static final String UPDATE = "UPDATE ";
	protected static final String SELECT = "SELECT ";
	protected static final String DELETE = "DELETE ";
	
	protected static final String FROM = " FROM ";
	protected static final String SET = " SET ";
	protected static final String WHERE = " WHERE ";
	
	protected static final String HISTORY_SUFIX = "_his ";
	
	protected static final String KEY_TABLE = " {table} ";
	protected static final String KEY_FIELDS = " {fields} ";
	protected static final String KEY_VALUES = " {values} ";
	protected static final String KEY_CONDITION = " {condition} ";
	protected static final String KEY_USER = " {user} ";

	protected static final String INSERT_SENTENCE = INSERT_INTO + KEY_TABLE + " (" + KEY_FIELDS + ") VALUES (" + KEY_VALUES + ")";
	protected static final String EXISTS_SENTENCE = SELECT + "COUNT(*)" + FROM + KEY_TABLE + WHERE + KEY_CONDITION;
	protected static final String SELECT_SENTENCE = SELECT + KEY_FIELDS + FROM  + KEY_TABLE + WHERE + KEY_CONDITION;
	protected static final String UPDATE_SENTENCE = UPDATE + KEY_TABLE + SET + KEY_FIELDS + WHERE + KEY_CONDITION;
	protected static final String DELETE_SENTENCE = DELETE + FROM + KEY_TABLE + WHERE + KEY_CONDITION;
	protected static final String HISTORY_SENTENCE = INSERT_INTO + KEY_TABLE + HISTORY_SUFIX + SELECT + " NOW(), " + KEY_USER + KEY_FIELDS + WHERE + KEY_CONDITION; 

	protected abstract String getTable();
	protected abstract String getFields();
	protected abstract String getInsertQuestionMarks();
	protected abstract String getWhereByKey();
	protected abstract String getFieldsUpdate();

	protected final String generateInsertQuery() {
		return INSERT_SENTENCE.replace(KEY_TABLE, getTable()).replace(KEY_FIELDS, getFields()).replace(KEY_VALUES,
				getInsertQuestionMarks());
	}

	protected final String generateExistsQuery() {
		return generateExistsQuery(null);
	}

	protected final String generateExistsQuery(String condition) {
		String _condition = condition;
		if (StringUtils.isEmpty(_condition)) {
			_condition = getWhereByKey();
		}
		return EXISTS_SENTENCE.replace(KEY_TABLE, getTable()).replace(KEY_CONDITION, _condition);
	}

	protected final String generateSelectQuery() {
		return generateSelectQuery(null);
	}

	protected final String generateSelectQuery(String condition) {
		String _condition = condition;
		if (StringUtils.isEmpty(_condition)) {
			_condition = getWhereByKey();
		}
		return SELECT_SENTENCE.replace(KEY_FIELDS, getFields()).replace(KEY_TABLE, getTable()).replace(KEY_CONDITION,
				_condition);
	}

	protected final String generateUpdateQuery() {
		return generateUpdateQuery(null);
	}

	protected final String generateUpdateQuery(String condition) {
		String _condition = condition;
		if (StringUtils.isEmpty(_condition)) {
			_condition = getWhereByKey();
		}
		return UPDATE_SENTENCE.replace(KEY_FIELDS, getFieldsUpdate()).replace(KEY_TABLE, getTable()).replace(KEY_CONDITION,
				_condition);
	}

	protected final String generateDeleteQuery() {
		return generateDeleteQuery(null);
	}

	protected final String generateDeleteQuery(String condition) {
		String _condition = condition;
		if (StringUtils.isEmpty(_condition)) {
			_condition = getWhereByKey();
		}
		return DELETE_SENTENCE.replace(KEY_TABLE, getTable()).replace(KEY_CONDITION,_condition);
	}

	protected final String generateHistoryQuery(Integer idUser) {
		return generateHistoryQuery(idUser, null);
	}

	protected final String generateHistoryQuery(Integer idUser, String condition) {
		if ( idUser == null ) {
			throw new RuntimeException("Cannot create history query withou user id");
		}
		String _condition = condition;
		if (StringUtils.isEmpty(_condition)) {
			_condition = getWhereByKey();
		}
		return HISTORY_SENTENCE.replace(KEY_TABLE, getTable()+HISTORY_SUFIX ).replace(KEY_CONDITION,_condition);
	}
}
