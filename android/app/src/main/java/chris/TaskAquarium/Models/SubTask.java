package chris.TaskAquarium.Models;

/**
 * Created by chris on 8/12/16.
 */

public class SubTask extends Task {
	public static final int TASK_IN_PROGRESS = -1000;

	private long completedAt = TASK_IN_PROGRESS;

	public SubTask() {
		this(Task.DEFAULT_NO_TITLE);
	}

	public SubTask(String title) {
		super(title);
	}

	// Implementation of Parent Task methods
	public void setTitle(String title) {
		super.setTitle(title);
	}
	public String getTitle() {
		return super.getTitle();
	}

	public void setCompletedAt(long completedAt) {
		this.completedAt = completedAt;
	}

	public long getCompletedAt() {
		return completedAt;
	}
}
