package com.example.common.util

import com.example.common.data.local.preference.SettingRepository

public expect object PreferencesUtil {
    public var settingsRepository: SettingRepository?
}