package com.example.tsmpc47.catalogmovie.ui.setting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.tsmpc47.catalogmovie.R;
import com.example.tsmpc47.catalogmovie.ui.home.nowplaying.reminder.SchedulerTask;

public class SettingActivity extends AppCompatActivity {

    private AlarmReceiver alarmReceiver = new AlarmReceiver();
    private SchedulerTask schedulerTask;
    private static final String TAG = "SettingActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: Setting");
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingPreferenceFragment()).commit();
    }

    @SuppressLint("ValidFragment")
    public class SettingPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

        String reminder_daily;

        String reminder_upcoming;

        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            reminder_daily = getResources().getString(R.string.daily_reminder);
            reminder_upcoming = getResources().getString(R.string.upcoming_reminder);

            findPreference(reminder_daily).setOnPreferenceChangeListener(this);
            findPreference(reminder_upcoming).setOnPreferenceChangeListener(this);

            schedulerTask = new SchedulerTask(getActivity());
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object o) {
            String key = preference.getKey();
            boolean isOn = (boolean) o;

            if (key.equals(reminder_daily)) {
                if (isOn) {
                    alarmReceiver.setRepeatingAlarm(getActivity(), alarmReceiver.TYPE_REPEATING, "07:00", "Selamat Pagi, Ada Film Bagus untuk mu.");
                } else {
                    alarmReceiver.cancelAlarm(getActivity(), alarmReceiver.TYPE_REPEATING);
                }

                Toast.makeText(SettingActivity.this, "Daily reminder is on" + " " + (isOn ? "Active" : "Deactivated"), Toast.LENGTH_SHORT).show();
                return true;
            }

            if (key.equals(reminder_upcoming)) {
                if (isOn) {
                    schedulerTask.createPeriodicTask();
                } else schedulerTask.cancelPeriodicTask();

                Toast.makeText(SettingActivity.this, "Upcoming reminder is on" + " " + (isOn ? "Active" : "Deactivated"), Toast.LENGTH_SHORT).show();
                return true;
            }

            return false;
        }
    }
}
