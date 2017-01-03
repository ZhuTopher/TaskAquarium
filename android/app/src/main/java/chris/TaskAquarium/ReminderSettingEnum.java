package chris.TaskAquarium;

/**
 * Created by chris on 8/12/16.
 */

public enum ReminderSettingEnum {
	// LOUD/QUIET Setting codes correspond to the varying settings of Alarms for AlarmManager
	//    - LOUD will use the WAKEUP notification that will wake the device if sleeping
	// AUDIBLE will also send an audio 'ping' when the notification is received
	LOUD_AUDIBLE, LOUD_SILENT, QUIET_AUDIBLE, QUIET_SILENT
}
