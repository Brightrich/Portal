package com.brightrich.dao.expression;


import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.hibernate.criterion.Criterion;
import org.hibernate.engine.spi.TypedValue;
import org.hibernate.type.IntegerType;

public class CustomSQLExpression implements Criterion {

    private final String propertyName;
    private final Object val;
    private final String modifier;
    private final String matchMode;

    public CustomSQLExpression(String propertyName, Object val, String modifier, String matchMode) {
        this.propertyName = propertyName;
        this.val = val;
        this.modifier = modifier;
        this.matchMode = matchMode;
    }


    @Override
    public String toString() {
        return modifier+"(" + propertyName + ") = " + val;
    }

	public String toSqlString(Criteria criteria,
			org.hibernate.criterion.CriteriaQuery criteriaQuery)
			throws HibernateException {
		String[] columns = criteriaQuery.findColumns(propertyName, criteria);
        if (columns.length!=1) {
            throw new HibernateException("criteria may only be used with single-column properties");
        }
        
        String result="";
        if(matchMode.equalsIgnoreCase("exact")){
        	result = modifier+"(" + columns[0] + ") = ?";
        } else {
        	result = modifier+"(" + columns[0] + ") like '%?%'";
        }
        return result;
	}

	public TypedValue[] getTypedValues(Criteria criteria,
			org.hibernate.criterion.CriteriaQuery criteriaQuery)
			throws HibernateException {
		Type type = null;
		if(val instanceof Integer){
			type = IntegerType.INSTANCE;
		} else {
			type = StringType.INSTANCE;
		}
		return new TypedValue[] {new TypedValue(type, val, EntityMode.POJO)};
	}
}

