package chris.TaskAquarium.Activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import chris.TaskAquarium.Fragments.HomeFragment;
import chris.TaskAquarium.R;

public class MainActivity extends AppCompatActivity {

	private RelativeLayout rootView;
	private DrawerLayout navDrawerLayout;
	private ActionBarDrawerToggle actionBarDrawerToggle;

	private FrameLayout mainFragContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); // TODO: change layout based on device screen

		this.rootView = (RelativeLayout) findViewById(R.id.home_root);
		this.navDrawerLayout = (DrawerLayout) this.rootView.findViewById(R.id.nav_drawer_layout);

		// setup toolbar
		Toolbar toolbar = (Toolbar) this.rootView.findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
//		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_first_page_white_24px);
//		getSupportActionBar().setHomeActionContentDescription(R.string.app_name);

		this.actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,
				this.navDrawerLayout, R.string.app_name, R.string.app_name);
//		this.actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
//		this.actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_first_page_white_24px);
		this.actionBarDrawerToggle.syncState();
		this.navDrawerLayout.addDrawerListener(this.actionBarDrawerToggle);


		// Check that the activity is using the layout version with
		// the fragment_container FrameLayout (for tablet support using different layout)
		if (this.rootView.findViewById(R.id.main_frag_container) != null) {
			// However, if we're being restored from a previous state,
			// then we don't need to do anything and should return or else
			// we could end up with overlapping fragments.
			if (savedInstanceState != null) {
				return;
			}

//			this.mainFragContainer = (FrameLayout) this.rootDrawerLayout.findViewById(R.id.main_frag_container);

			// In case this activity was started with special instructions from an
			// Intent, pass the Intent's extras to the fragment as arguments
			HomeFragment homeFragment = new HomeFragment();
			homeFragment.setArguments(getIntent().getExtras());

			getSupportFragmentManager().beginTransaction()
					.add(R.id.main_frag_container, homeFragment, "home_fragment").commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
			Toast.makeText(MainActivity.this, "Pressed Actionbar Toggle", Toast.LENGTH_SHORT).show();
			return true;
		}

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			Toast.makeText(MainActivity.this, "Pressed Settings", Toast.LENGTH_SHORT).show();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
