package io.pivotal.pal.tracker;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TimeEntryRepository {
    private InMemoryTimeEntryRepository inMemoryTimeEntryRepository;

    public TimeEntryRepository(InMemoryTimeEntryRepository inMemoryTimeEntryRepository){
        this.inMemoryTimeEntryRepository = inMemoryTimeEntryRepository;
    }

    public TimeEntry create(TimeEntry timeEntry) {
        return this.inMemoryTimeEntryRepository.create(timeEntry);
    }

    public TimeEntry find(long timeEntryId) {
        return this.inMemoryTimeEntryRepository.find(timeEntryId);
    }

    public List<TimeEntry> list() {
        return this.inMemoryTimeEntryRepository.list();
    }

    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {
        return this.inMemoryTimeEntryRepository.update(timeEntryId, timeEntry);
    }

    public void delete(long timeEntryId) {
        this.inMemoryTimeEntryRepository.delete(timeEntryId);
    }
}
