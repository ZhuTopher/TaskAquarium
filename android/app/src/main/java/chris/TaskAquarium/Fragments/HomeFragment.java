package chris.TaskAquarium.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import chris.TaskAquarium.Adapters.TasksAdapter;
import chris.TaskAquarium.Models.MainTask;
import chris.TaskAquarium.R;

/**
 * Created by chris on 8/4/16.
 */

public class HomeFragment extends Fragment {

	private RelativeLayout homeFragRoot;

	private LinearLayout alertsContainer;
	private RecyclerView alertsRV;

	private FrameLayout taskListContainer;
	private RecyclerView taskListRV;
	private TasksAdapter tasksAdapter;

	private FloatingActionButton fab;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		// Inflates the fragment_home into the containing FrameLayout (already has parent)
		this.homeFragRoot = (RelativeLayout) inflater.inflate(R.layout.fragment_home, container, false);

		this.alertsContainer = (LinearLayout) this.homeFragRoot.findViewById(R.id.alerts_container);
		/*this.alertsRV = (RecyclerView) this.alertsContainer.findViewById(R.id.tasks_list);
		this.alertsRV.setHasFixedSize(false);
		this.alertsRV.setNestedScrollingEnabled(false); // allows for smooth scrollview scrolling
		LinearLayoutManager upcomingTaskLlm = new LinearLayoutManager(getContext());
		upcomingTaskLlm.setOrientation(LinearLayoutManager.VERTICAL);
		this.alertsRV.setLayoutManager(upcomingTaskLlm);*/

		this.taskListContainer = (FrameLayout) this.homeFragRoot.findViewById(R.id.task_list_container);
		this.taskListRV = (RecyclerView) this.taskListContainer.findViewById(R.id.tasks_list);
		this.taskListRV.setHasFixedSize(false);
		// this.taskListRV.setNestedScrollingEnabled(false); // allows for smooth scrollview scrolling
		LinearLayoutManager taskListLlm = new LinearLayoutManager(getContext());
		taskListLlm.setOrientation(LinearLayoutManager.VERTICAL);
		this.taskListRV.setLayoutManager(taskListLlm);

		// TODO: add onClickListeners() to the adapter, calls TasksManager -> opens task overview
		// -> Debug for now, have click listener add 'fish' (branches) to check horizontal scroll functionality
		this.tasksAdapter = new TasksAdapter();
		this.taskListRV.setAdapter(tasksAdapter);

		//setup floating action button
		fab = (FloatingActionButton) this.homeFragRoot.findViewById(R.id.home_fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				showTaskCreateDialog();
			}
		});

		return this.homeFragRoot;
	}

	// TODO: should create a new fragment/activity and pass results across
	private void showTaskCreateDialog() {
		AlertDialog.Builder b = new AlertDialog.Builder(getContext());
		b.setTitle("Enter a Task title");
		final EditText input = new EditText(getContext());
		b.setView(input);
		b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				addTask(input.getText().toString());
			}
		});
		b.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				Toast.makeText(HomeFragment.this.getContext(),
						"Cancelled task creation", Toast.LENGTH_SHORT).show();
			}
		});
		b.show();
	}

	private void addTask(String taskTitle) {
		if (taskTitle.isEmpty()) {
			Toast.makeText(getContext(), "Discarding empty titled task", Toast.LENGTH_SHORT).show();
		} else if (this.tasksAdapter.isTaskTitleAvailable(taskTitle)) {
			this.tasksAdapter.addNewTask(new MainTask(taskTitle));
		} else {
			Toast.makeText(getContext(), String.format("Task with title %s already exists",
					taskTitle), Toast.LENGTH_SHORT).show();
		}
	}
}