package com.thefieldpanama.utilities;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * Author: Rosendo Peña Hernandez
 * ropherpanama@gmail.com
 * Date: 23/06/2014
 */

/**
 * Clase de utilidades varias, procesos comunes
 */
public class Utilities {
	
	private static Logger log = Logger.getLogger(Utilities.class);
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
		log.info("FECHA DE SYSTEMA SQL TYPE ::: " + sqlDate); 
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

	@SuppressWarnings("deprecation")
	public static int compareHours(String str) {
		try {
			int answer = 0;
			SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
			Date systemTime = parser.parse(nowTime());
			Date inputTime = parser.parse(str);
			
			int sysHour = systemTime.getHours();
			int matchHour = inputTime.getHours();
			
			int diferencia = matchHour - sysHour;
			
			log.info("DIFERENCIA DE HORAS ::: " + diferencia);
			
			if (diferencia >= 1)//aun no inicia
				answer = 1;
			else if (diferencia <= 0 && diferencia >= -2)//en curso
				answer = 2;
			else if (diferencia <= -3)//ya paso
				answer = 3;
			
			return answer;
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * Retorna solo la hora y minutos de la fecha actual
	 * 
	 * @return horas/minutos
	 */
	public static String nowTime() {
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		Date today = Calendar.getInstance().getTime();
		return df.format(today);
	}
}
