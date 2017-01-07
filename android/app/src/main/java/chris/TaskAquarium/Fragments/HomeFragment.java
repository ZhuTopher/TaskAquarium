package chris.TaskAquarium.Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import chris.TaskAquarium.R;

/**
 * Created by chris on 8/4/16.
 */

public class HomeFragment extends Fragment {

	private RelativeLayout homeFragRoot;
	private ScrollView homeScrollView;

	private LinearLayout alertsContainer, pinnedTasksContainer;
	private RecyclerView alertsRV, pinnedTasksRV;

	private FloatingActionButton fab;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		// Inflates the fragment_home into the containing FrameLayout (already has parent)
		this.homeFragRoot = (RelativeLayout) inflater.inflate(R.layout.fragment_home, container, false);
		this.homeScrollView = (ScrollView) this.homeFragRoot.findViewById(R.id.home_scroll_view);

		this.alertsContainer = (LinearLayout) this.homeFragRoot.findViewById(R.id.alerts_container);
		this.alertsRV = (RecyclerView) this.alertsContainer.findViewById(R.id.tasks_list);
		this.alertsRV.setHasFixedSize(false);
		this.alertsRV.setNestedScrollingEnabled(false); // allows for smooth scrollview scrolling
		LinearLayoutManager upcomingTaskLlm = new LinearLayoutManager(getContext());
		upcomingTaskLlm.setOrientation(LinearLayoutManager.VERTICAL);
		this.alertsRV.setLayoutManager(upcomingTaskLlm);

		this.pinnedTasksContainer = (LinearLayout) this.homeFragRoot.findViewById(R.id.pinned_tasks_container);
		this.pinnedTasksRV = (RecyclerView) this.pinnedTasksContainer.findViewById(R.id.tasks_list);
		this.pinnedTasksRV.setHasFixedSize(false);
		this.pinnedTasksRV.setNestedScrollingEnabled(false); // allows for smooth scrollview scrolling
		LinearLayoutManager taskListLlm = new LinearLayoutManager(getContext());
		taskListLlm.setOrientation(LinearLayoutManager.VERTICAL);
		this.pinnedTasksRV.setLayoutManager(taskListLlm);



		//setup floating action button
		fab = (FloatingActionButton) this.homeFragRoot.findViewById(R.id.home_fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(getContext(), "Pressed fab", Toast.LENGTH_SHORT).show();
			}
		});

		return this.homeFragRoot;
	}
}