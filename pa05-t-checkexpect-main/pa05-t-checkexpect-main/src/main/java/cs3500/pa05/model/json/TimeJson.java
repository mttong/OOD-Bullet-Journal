package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * time json
 *
 * @param hour   hour
 * @param minute minute
 */
public record TimeJson(@JsonProperty("hour") int hour, @JsonProperty("minute") int minute) {

}
