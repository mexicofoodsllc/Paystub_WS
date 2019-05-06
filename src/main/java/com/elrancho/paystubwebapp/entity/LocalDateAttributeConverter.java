package com.elrancho.paystubwebapp.entity;



import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/*
 * to store a LocalDate attribute in a DATE column or a LocalDateTime in a TIMESTAMP column,
 *  you need to define the mapping to java.sql.Date or java.sql.Timestamp using the attribute converter,
 *   one of several new features in JPA 2.1, this can be achieved with just a few lines of code.
 */

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {
	
    @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
    	return (locDate == null ? null : Date.valueOf(locDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
    	return (sqlDate == null ? null : sqlDate.toLocalDate());
    }

	
}