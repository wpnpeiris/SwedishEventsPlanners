/**
 * 
 */
package kth.id2207.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rafa
 *
 */
public enum EventType {
	WORKSHOP("evnet1", "Workshop"),
	SUMMER_SCHOOL("event2", "Summer School"),
	CONFERENCE("event3", "Conference");
	
	private final String id;
	
	private final String eventType;
	
	private static final Map<String, EventType> eventTypes = new HashMap<String, EventType>();

    static {
		for (EventType e : EnumSet.allOf(EventType.class)) {
			eventTypes.put(e.getId(), e);
		}
	}

	private EventType(String id, String eventType) {
		this.id = id;		
		this.eventType = eventType;
	}

	public String getId() {
		return id;
	}

	public String getEventType() {
		return eventType;
	}
	
	
}
