package chris.TaskAquarium.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import chris.TaskAquarium.Models.Task;
import chris.TaskAquarium.R;
import chris.TaskAquarium.ReminderSettingEnum;

/**
 * Created by chris on 8/11/16.
 */

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskViewHolder>{
	// TODO: adapter tied to each instance of a TaskList (recyclerview) used for population/drawing

	private List<Task> tasksList;

	public TasksAdapter() {
		this.tasksList = new ArrayList<>();
	}

	public TasksAdapter(List<Task> tasksList) {
		this.tasksList = tasksList;
	}

	@Override
	public int getItemCount() {
		return this.tasksList.size();
	}

	@Override
	public void onBindViewHolder(TaskViewHolder taskViewHolder, int i) {
		/*ContactInfo ci = contactList.get(i);
		contactViewHolder.vName.setText(ci.name);
		contactViewHolder.vSurname.setText(ci.surname);
		contactViewHolder.vEmail.setText(ci.email);
		contactViewHolder.vTitle.setText(ci.name + " " + ci.surname);*/

		Task task = this.tasksList.get(i);
	}

	@Override
	public TaskViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View itemView = LayoutInflater.
				from(viewGroup.getContext()).
				inflate(R.layout.task_card, viewGroup, false);

		return new TaskViewHolder(itemView);
	}



	public static class TaskViewHolder extends RecyclerView.ViewHolder {
		// Task abstract object variables
		private TextView title, description;

		// Sub-task object variables
		private long completedAt;

		// Main-task object variables
		private ReminderSettingEnum reminderSetting;
		private long remindAtTime, reminderInterval; // ms?

		// TODO: just use SubTasks instead of abstract Task?
		private Task currentTask;
		private List<Task> subTasks = new ArrayList<Task>();
		private List<Task> completedTasks = new ArrayList<Task>();

		public TaskViewHolder(View v) {
			super(v);
			this.title = (TextView) v.findViewById(R.id.title);
		}
	}
}
