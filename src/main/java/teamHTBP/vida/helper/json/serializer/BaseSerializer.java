package teamHTBP.vida.helper.json.serializer;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

/**
 * @author DustW
 **/
public interface BaseSerializer<T> extends JsonSerializer<T>, JsonDeserializer<T> {
}
