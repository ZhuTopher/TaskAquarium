package chris.TaskAquarium.Models;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import chris.TaskAquarium.ReminderSettingEnum;

/**
 * Created by chris on 8/12/16.
 */

public class MainTask extends Task {
    // TODO: Future consideration of having unbounded branches,
    //       i.e MainTask is the root node, BranchTask are children nodes?
	public static final String DEFAULT_BRANCH_NAME = "Main";
    private static final String LOG_TAG = MainTask.class.getSimpleName();

//	private ReminderSettingEnum reminderSetting;
//	private long remindAtTime, reminderInterval; // ms?

	private Map<String, BranchTask> branches = new HashMap<>();
    private BranchTask currentBranch;

	public MainTask() {
		this(Task.DEFAULT_NO_TITLE);
	}

	public MainTask(String title) {
		super(title);

		this.branches.put(MainTask.DEFAULT_BRANCH_NAME, new BranchTask(MainTask.DEFAULT_BRANCH_NAME));
        this.currentBranch = this.branches.get(MainTask.DEFAULT_BRANCH_NAME);
	}

	// Implementation of Parent Task methods
	public void setTitle(String title) {
		super.setTitle(title);
	}
	public String getTitle() {
		return super.getTitle();
	}

//	public void setReminderSetting(ReminderSettingEnum reminderSetting) {
//		this.reminderSetting = reminderSetting;
//	}
//
//	protected void setRemindAtTime(long remindAtTime) {
//		this.remindAtTime = remindAtTime;
//	}
//
//	public void setReminderInterval(long reminderInterval) {
//		this.reminderInterval = reminderInterval;
//	}

//	public ReminderSettingEnum getReminderSetting() {
//		return this.reminderSetting;
//	}
//
//	protected long getRemindAtTime() {
//		return this.remindAtTime;
//	}
//
//	public long getReminderInterval() {
//		return this.reminderInterval;
//	}

    // Implementation methods

    public boolean addBranch(String branchTitle) {
        branchTitle = branchTitle.trim();

        if (this.branches.containsKey(branchTitle)) {
            Log.w(LOG_TAG, String.format("Didn't add branch with title %s: already exists", branchTitle));
            return false;
        } else {
            this.branches.put(branchTitle, new BranchTask(branchTitle));
            return true;
        }
    }

    public boolean removeBranch(String branchTitle) {
        branchTitle = branchTitle.trim();

        if (this.branches.containsKey(branchTitle)) {
            if (this.currentBranch.getTitle().equals(branchTitle)) {
                Log.d(LOG_TAG, String.format("Removing current branch with title %s", branchTitle));
                this.currentBranch = null;
            }

            this.branches.remove(branchTitle);
            return true;
        } else { // branch DNE
            Log.w(LOG_TAG, String.format("Didn't remove branch with title %s: doesn't exist", branchTitle));
            return false; // this.currentBranch unchanged
        }
    }

    public boolean setCurrentBranch(String branchTitle) {
        branchTitle = branchTitle.trim();

        if (this.branches.containsKey(branchTitle)) {
            this.currentBranch = this.branches.get(branchTitle);
            return true;
        } else {
            Log.w(LOG_TAG, String.format("Didn't change currentBranch: branch with title %s DNE", branchTitle));
            return false; // this.currentBranch unchanged
        }
    }

    public BranchTask getCurrentBranch() {
        return this.currentBranch;
    }
}
