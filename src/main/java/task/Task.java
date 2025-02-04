package task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }
    
    /**
     * Converts the task to a formatted string for saving to a file.
     * Subclasses should override this method to add specific task details.
     */
    public String toDataString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
