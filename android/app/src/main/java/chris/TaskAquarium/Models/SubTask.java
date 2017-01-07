package chris.TaskAquarium.Models;

/**
 * Created by chris on 8/12/16.
 */

public class SubTask extends Task {
	public static final int IN_PROGRESS = -1000;

	private long completedAt = IN_PROGRESS;

	public SubTask(String title, String description) {
		super(title, description);
	}

	// Implementation of Parent Task methods
	public void setTitle(String title) {
		super.setTitle(title);
	}
	public void setDescription(String description) {
		super.setDescription(description);
	}
	public String getTitle() {
		return super.getTitle();
	}
	public String getDescription() {
		return super.getDescription();
	}

	public void setCompletedAt(long completedAt) {
		this.completedAt = completedAt;
	}

	public long getCompletedAt() {
		return completedAt;
	}
}
