package com.thefieldpanama.utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.stereotype.Component;

/**
 * Utilizo esta clase para mapear el tipo de dato java.util.Date con jackson ya
 * que el formato de la fecha que esta enviando el servidor no es legible "fecha":1404795600000
 * Formato de fecha soportado dd-mm-yyyy
 * @author rospena
 * <p>Uso:
 * @JsonSerialize(using=JSONDateSerializer.class) 
 *		public Date getFecha() {
 *		return fecha;
 * }
 * </p>
 */
@Component
public class JSONDateSerializer extends JsonSerializer<Date> {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd-MM-yyyy");

	@Override
	public void serialize(Date date, JsonGenerator gen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		String formattedDate = dateFormat.format(date);
		gen.writeString(formattedDate);
	}
}
