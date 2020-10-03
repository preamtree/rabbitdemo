package com.preamtree.rabbitdemo.pubsub;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Objects;

// publisher와 subscriber가 주고받는 class.
public class EventPayload {
    @JsonProperty("event_name")
    private String eventName;
    private Map<String, Object> data;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventPayload that = (EventPayload) o;
        return Objects.equals(eventName, that.eventName) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, data);
    }
}
