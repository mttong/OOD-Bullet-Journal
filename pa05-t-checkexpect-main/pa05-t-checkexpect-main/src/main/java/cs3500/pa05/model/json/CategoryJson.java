package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Category json record
 *
 * @param name name of category
 */
public record CategoryJson(@JsonProperty("name") String name) {

}
