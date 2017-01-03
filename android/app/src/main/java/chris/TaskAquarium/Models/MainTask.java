package chris.TaskAquarium.Models;

import java.util.ArrayList;
import java.util.List;

import chris.TaskAquarium.ReminderSettingEnum;

/**
 * Created by chris on 8/12/16.
 */

public class MainTask extends Task {
	private ReminderSettingEnum reminderSetting;
	private long remindAtTime, reminderInterval; // ms?

	// TODO: just use SubTasks instead of abstract Task?
	private Task currentTask;
	private List<Task> subTasks = new ArrayList<>();
	private List<Task> completedTasks = new ArrayList<>();

	public MainTask(String title, String description) {
		super(title, description);

		// always use currentTask for displaying info, not MainTask.getTitle() and etc.
		this.currentTask = new SubTask(title, description);
	}

	public void setReminderSetting(ReminderSettingEnum reminderSetting) {
		this.reminderSetting = reminderSetting;
	}

	protected void setRemindAtTime(long remindAtTime) {
		this.remindAtTime = remindAtTime;
	}

	public void setReminderInterval(long reminderInterval) {
		this.reminderInterval = reminderInterval;
	}

	public void setCurrentTask(Task currentTask) {
		this.currentTask = currentTask;
	}

	public void setSubTasks(List<Task> subTasks) {
		this.subTasks = subTasks;
	}

	public void setCompletedTasks(List<Task> completedTasks) {
		this.completedTasks = completedTasks;
	}

	public ReminderSettingEnum getReminderSetting() {
		return this.reminderSetting;
	}

	protected long getRemindAtTime() {
		return this.remindAtTime;
	}

	public long getReminderInterval() {
		return this.reminderInterval;
	}

	public Task setCurrentTask() {
		return this.currentTask;
	}

	public List<Task> setSubTasks() {
		return this.subTasks;
	}

	public List<Task> getCompletedTasks() {
		return this.completedTasks;
	}

	// Non setter/getter methods

	// TODO: move to TasksManager?
	// NOTE: TasksList is a RecyclerView of MainTask,
	//       each MainTask will need a RecyclerView for its subTasks list
	public void addSubTask(Task newTask) {
		this.subTasks.add(newTask);
	}

	// TODO: maybe use index instead (RecyclerView.getView() gives position)?
	public void removeSubTask(Task removedTask) {
		this.subTasks.remove(removedTask);
	}

	public void completeCurrentTask() {
		Task nextTask = null;
		if (this.subTasks.get(0) != null) {
			nextTask = this.subTasks.get(0);
			this.subTasks.remove(0);
		}

		((SubTask) this.currentTask).setCompletedAt(System.currentTimeMillis());
		this.completedTasks.add(currentTask);
		this.currentTask = nextTask;

		// TODO: if currentTask is now null, alert completion
	}
}
