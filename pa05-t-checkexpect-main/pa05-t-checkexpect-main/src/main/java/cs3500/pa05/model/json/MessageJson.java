package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Message json
 *
 * @param messageName key
 * @param arguments   value
 */
public record MessageJson(@JsonProperty("key") String messageName,
                          @JsonProperty("value") JsonNode arguments) {

}
