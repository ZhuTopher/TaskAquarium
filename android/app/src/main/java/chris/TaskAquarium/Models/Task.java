package chris.TaskAquarium.Models;

/**
 * Created by chris on 8/11/16.
 */

public abstract class Task {
	public static final String DEFAULT_NO_TITLE = "Missing title";

	// TODO: properly adjust accessibility of Task objects
	private String title;

	protected Task() {
		this(Task.DEFAULT_NO_TITLE);
	}

	protected Task(String title) {
		this.title = title;
	}

	protected void setTitle(String title) {
		this.title = title;
	}

	protected String getTitle() {
		return this.title;
	}
}
