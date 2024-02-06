package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Setting Json
 *
 * @param name            name of user
 * @param email           user email
 * @param eventMax        user event max
 * @param taskMax         user task max
 * @param startDateString user start date
 */
public record SettingsJson(@JsonProperty("name") String name, @JsonProperty("email") String email,
                           @JsonProperty("eventMax") int eventMax,
                           @JsonProperty("taskMax") int taskMax,
                           @JsonProperty("start-date") String startDateString) {

}
