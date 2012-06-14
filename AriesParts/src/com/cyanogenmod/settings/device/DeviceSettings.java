package com.cyanogenmod.settings.device;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;

public class DeviceSettings extends PreferenceActivity  {

    public static final String KEY_COLOR_TUNING = "color_tuning";
    public static final String KEY_MDNIE = "mdnie";
    public static final String KEY_BACKLIGHT_TIMEOUT = "backlight_timeout";
    public static final String KEY_HSPA = "hspa";
    public static final String KEY_VOLUME_BOOST = "volume_boost";
    public static final String KEY_VOLUME_CATEGORY = "category_volume_boost";

    private ColorTuningPreference mColorTuning;
    private ListPreference mMdnie;
    private ListPreference mBacklightTimeout;
    private ListPreference mHspa;
    private VolumeBoostPreference mVolumeBoost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.main);

        mColorTuning = (ColorTuningPreference) findPreference(KEY_COLOR_TUNING);
        mColorTuning.setEnabled(ColorTuningPreference.isSupported());

        mMdnie = (ListPreference) findPreference(KEY_MDNIE);
        mMdnie.setEnabled(Mdnie.isSupported());
        mMdnie.setOnPreferenceChangeListener(new Mdnie());

        mBacklightTimeout = (ListPreference) findPreference(KEY_BACKLIGHT_TIMEOUT);
        mBacklightTimeout.setEnabled(TouchKeyBacklightTimeout.isSupported());
        mBacklightTimeout.setOnPreferenceChangeListener(new TouchKeyBacklightTimeout());

        mHspa = (ListPreference) findPreference(KEY_HSPA);
        mHspa.setEnabled(Hspa.isSupported());
        mHspa.setOnPreferenceChangeListener(new Hspa(this));
        mVolumeBoost = (VolumeBoostPreference) findPreference(KEY_VOLUME_BOOST);
        if (!VolumeBoostPreference.isSupported()) {
            PreferenceCategory category = (PreferenceCategory) getPreferenceScreen().findPreference(KEY_VOLUME_CATEGORY);
            category.removePreference(mVolumeBoost);
            getPreferenceScreen().removePreference(category);
    }

}
