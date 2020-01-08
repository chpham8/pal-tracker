package io.pivotal.pal.tracker;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryTimeEntryRepository {

    private Map<Long, TimeEntry> timeEntryMap = new ConcurrentHashMap<>();
    private final AtomicLong count = new AtomicLong(1);

    public TimeEntry create(TimeEntry timeEntry) {
        long id = timeEntry.getId();
        if (id <= 0) {
            id = count.getAndIncrement();
            timeEntryMap.put(id, new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours()));
            return timeEntryMap.get(id);
        }
        timeEntryMap.put(timeEntry.getId(), new TimeEntry(timeEntry.getId(), timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours()));
        return timeEntryMap.get(timeEntry.getId());
    }

    public TimeEntry find(long id) {
        return timeEntryMap.get(id);
    }

    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntryMap.values());
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (timeEntryMap.containsKey(id)) {
            timeEntryMap.put(id, new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours()));
        }
        return timeEntryMap.get(id);
    }

    public void delete(long id) {
        timeEntryMap.remove(id);
    }
}
