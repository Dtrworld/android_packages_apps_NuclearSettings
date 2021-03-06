/*
 * Copyright (C) 2017 AICP
 * Copyright (C) 2018 NR
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nr.settings.preferences;

import android.content.ContentResolver;
import android.widget.Switch;

import com.nr.settings.BaseSettingsFragment;
import com.nr.settings.widget.SwitchBar;

public class SystemSettingSwitchBarController implements SwitchBar.OnSwitchChangeListener {

    private String mKey;
    private SystemSettingsStore mPreferenceDataStore;
    private BaseSettingsFragment mSettingsFragment;

    public SystemSettingSwitchBarController(SwitchBar switchBar, String key, boolean defaultValue,
                                            ContentResolver resolver,
                                            BaseSettingsFragment settingsFragment) {
        mKey = key;
        mPreferenceDataStore = new SystemSettingsStore(resolver);
        mSettingsFragment = settingsFragment;
        switchBar.addOnSwitchChangeListener(this);

        // Init
        boolean initialValue = mPreferenceDataStore.getBoolean(mKey, defaultValue);
        switchBar.setChecked(initialValue);
        if (mSettingsFragment != null) {
            mSettingsFragment.setMasterDependencyState(initialValue);
        }
    }

    @Override
    public void onSwitchChanged(Switch switchView, boolean isChecked) {
        mPreferenceDataStore.putBoolean(mKey, isChecked);
        if (mSettingsFragment != null) {
            mSettingsFragment.setMasterDependencyState(isChecked);
        }
    }

}
