package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Activitiy record
 *
 * @param name                   name of activity
 * @param description            activity description
 * @param weekdayString          activity weekday
 * @param categoryJson           activity category
 * @param typeString             type of activity
 * @param completionStatusString completion
 * @param startTime              begin time
 * @param endTime                end time
 */
public record ActivityJson(@JsonProperty("name") String name,
                           @JsonProperty("description") String description,
                           @JsonProperty("weekday") String weekdayString,
                           @JsonProperty("category") CategoryJson categoryJson,
                           @JsonProperty("type") String typeString,
                           @JsonProperty("completion_status") String completionStatusString,
                           @JsonProperty("start_time") TimeJson startTime,
                           @JsonProperty("end_time") TimeJson endTime) {

}
