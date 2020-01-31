package com.capitanperegrina.boatraceanalyzer.util.sql;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class QueryUtils {

    /**
     * Instantiates a new query utils.
     */
    private QueryUtils() {

    }

    /**
     * Pinta query.
     *
     * @param query
     *            the query
     * @param parametros
     *            the parametros
     * @return the string
     */
    public static String pintaQuery(final String query, final List<Object> parametros) {
        return pintaQuery(query, parametros.toArray());
    }

    /**
     * Pinta query.
     *
     * @param query
     *            the query
     * @param parametros
     *            the parametros
     * @return the string
     */
    public static String pintaQuery(final StringBuilder query, final List<Object> parametros) {
        return pintaQuery(query.toString(), parametros.toArray());
    }

    /**
     * Pinta query.
     *
     * @param query
     *            the query
     * @param parametros
     *            the parametros
     * @return the string
     */
    public static String pintaQuery(final String query, final Object[] parametros) {
        List<Object> objs = Arrays.asList(parametros);
        String tmp = query;
        if (CollectionUtils.isNotEmpty(objs)) {
            for (final Object obj : objs) {
                tmp = tmp.replaceFirst("\\?", parameterToSqlString(obj));
            }
        }
        return tmp;
    }

    /**
     * Pinta query.
     *
     * @param query
     *            the query
     * @param parametros
     *            the parametros
     * @return the string
     */
    public static String pintaQuery(final StringBuilder query, final Object[] parametros) {
        return pintaQuery(query.toString(), parametros);
    }

    /**
     * Parameter to sql string.
     *
     * @param o
     *            the o
     * @return the string
     */
    private static String parameterToSqlString(final Object o) {
        if (o instanceof String) {
            return toSqlString((String) o);
        } else if (o instanceof Integer) {
            return toSqlInteger((Integer) o);
        } else if (o instanceof Calendar) {
            return toSqlDate(((Calendar) o).getTime());
        } else if (o instanceof Date) {
            return toSqlDate((Date) o);
        } else if (o instanceof BigDecimal) {
            return ((BigDecimal) o).toString();
        } else if (o == null) {
            return "null";
        }
        return "<ERR>";
    }

    /**
     * To sql string.
     *
     * @param s
     *            the s
     * @return the string
     */
    private static String toSqlString(final String s) {
        if (s == null) {
            return "null";
        }
        return "'" + s + "'";
    }

    /**
     * To sql integer.
     *
     * @param i
     *            the i
     * @return the string
     */
    private static String toSqlInteger(final Integer i) {
        if (i == null) {
            return "null";
        }
        return i.toString();
    }

    /**
     * To sql date.
     *
     * @param d
     *            the d
     * @return the string
     */
    private static String toSqlDate(final Date d) {
        if (d == null) {
            return "null";
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
    }
}
