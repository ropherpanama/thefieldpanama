package com.thefieldpanama.utilities;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Author: Rosendo Peña Hernandez
 * ropherpanama@gmail.com
 * Date: 23/06/2014
 */

/**
 * Clase de utilidades varias, procesos comunes
 */
public class Utilities {
	/**
	 * Convierte un java.util.Date a un java.sql.Date
	 * 
	 * @return java.sql.Date
	 */
	public static java.sql.Date utilDateToSQLDate(java.util.Date d) {
		java.sql.Date sqlDate = new java.sql.Date(d.getTime());
		return sqlDate;
	}

	/**
	 * Retorna la fecha de hoy en java.sql.Date
	 * 
	 * @return java.sql.Date now
	 */
	public static java.sql.Date fechahoy() {
		java.util.Date now = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(now.getTime());
		return sqlDate;
	}

	/**
	 * Este metodo retorna en un string la salida del printStackTrace para las
	 * excepciones
	 * 
	 * @param exception
	 * @return String con la excepcion completa
	 */
	public static String stringStackTrace(Exception exception) {
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		exception.printStackTrace(printWriter);
		return writer.toString();
	}
}
