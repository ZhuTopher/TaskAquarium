package chris.TaskAquarium.Models;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chris on 1/7/17.
 */

public class BranchTask extends Task {
    private static final String LOG_TAG = BranchTask.class.getSimpleName();

    private String description = "";

    // TODO: display completedTasks sorted by SubTask.completedAt
    private Map<String, SubTask> subTasks = new HashMap<>(), completedTasks = new HashMap<>();
    // private SubTask currentTask;

    public BranchTask() {
        this(Task.DEFAULT_NO_TITLE);
    }

    public BranchTask(String title) {
        super(title);

        /*// always use currentTask for displaying info, not MainTask.getTitle() and etc.
        this.currentTask = new SubTask(title);*/
    }

    // Implementation of Parent Task methods
    public void setTitle(String title) {
        super.setTitle(title);
    }
    public String getTitle() {
        return super.getTitle();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public Map<String, SubTask> getSubTasks() {
        return this.subTasks;
    }

    public Map<String, SubTask> getCompletedTasks() {
        return this.completedTasks;
    }


    // Implementation Methods

    public boolean addSubTask(String subTaskTitle) {
        subTaskTitle = subTaskTitle.trim();

        if (this.subTasks.containsKey(subTaskTitle) ||
                this.completedTasks.containsKey(subTaskTitle)) {
            Log.w(LOG_TAG, String.format("Didn't add subtask with title %s: already exists", subTaskTitle));
            return false;
        } else {
            this.subTasks.put(subTaskTitle, new SubTask(subTaskTitle));
            return true;
        }
    }

    public boolean removeSubTask(String subTaskTitle) {
        subTaskTitle = subTaskTitle.trim();

        if (this.subTasks.containsKey(subTaskTitle)) {
            /*if (this.currentTask.getTitle().equals(subTaskTitle)) {
                Log.d(LOG_TAG, String.format("Removing current subtask with title %s", subTaskTitle));
                this.currentTask = null;
            }*/

            this.subTasks.remove(subTaskTitle);
            return true;
        } else { // subtask DNE
            Log.w(LOG_TAG, String.format("Didn't remove subtask with title %s: doesn't exist", subTaskTitle));
            return false; // this.currentTask unchanged
        }
    }

    public boolean removeCompletedTask(String subTaskTitle) {
        subTaskTitle = subTaskTitle.trim();

        if (this.completedTasks.containsKey(subTaskTitle)) {
            this.completedTasks.remove(subTaskTitle);
            return true;
        } else { // subtask DNE
            Log.w(LOG_TAG, String.format("Didn't remove completed subtask with title %s: doesn't exist", subTaskTitle));
            return false;
        }
    }

    public boolean completeSubTask(String subTaskTitle) {
        subTaskTitle = subTaskTitle.trim();

        if (this.subTasks.containsKey(subTaskTitle)) {
            SubTask completedTask = subTasks.get(subTaskTitle);
            completedTask.setCompletedAt(System.currentTimeMillis());

            this.completedTasks.put(completedTask.getTitle(), completedTask);
            removeSubTask(subTaskTitle);

            // TODO: if subTasks is empty, alert completion (?)

            return true;
        } else { // task with that title didn't exist in subtasks
            Log.e(LOG_TAG, String.format("Unable to complete subtask with title %s", subTaskTitle));
            return false;
        }
    }

    /*public boolean setCurrentTask(String subTaskTitle) {
        subTaskTitle = subTaskTitle.trim();

        if (this.subTasks.containsKey(subTaskTitle)) {
            this.currentTask = this.subTasks.get(subTaskTitle);
            return true;
        } else {
            Log.w(LOG_TAG, String.format("Didn't change currentTask: subtask with title %s DNE", subTaskTitle));
            return false; // this.currentTask unchanged
        }
    }

    public SubTask getCurrentTask() {
        return this.currentTask;
    }*/


    public int getNumCompletedTasks() {
        return this.completedTasks.size();
    }

    public int getNumTotalTasks() {
        return this.subTasks.size() + this.completedTasks.size();
    }
}
