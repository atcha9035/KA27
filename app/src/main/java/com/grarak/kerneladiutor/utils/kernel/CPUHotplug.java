/*
 * Copyright (C) 2015 Willi Ye
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.grarak.kerneladiutor.utils.kernel;

import android.content.Context;

import com.grarak.kerneladiutor.R;
import com.grarak.kerneladiutor.utils.Constants;
import com.grarak.kerneladiutor.utils.Utils;
import com.grarak.kerneladiutor.utils.root.Control;
import com.kerneladiutor.library.root.RootUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willi on 06.02.15.
 */
public class CPUHotplug implements Constants {

    private enum INTELLIPLUG_TYPE {
        INTELLIPLUG,
        INTELLIPLUG_5,
        INSANITY
    }

    private static INTELLIPLUG_TYPE TYPE;

    private static String MSM_HOTPLUG_ENABLE_FILE;
    private static String MSM_HOTPLUG_UPDATE_RATE_FILE;
    private static String MSM_HOTPLUG_IO_IS_BUSY_FILE;
    private static String MSM_HOTPLUG_SUSPEND_FREQ_FILE;

    private static String MB_HOTPLUG_MIN_CPUS_FILE;
    private static String MB_HOTPLUG_MAX_CPUS_FILE;

    public static void activateAutoSmpScroffSingleCoreActive(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", HOTPLUG_AUTOSMP_SCROFF_SINGLE_CORE, Control.CommandType.GENERIC, context);
    }

    public static boolean isAutoSmpScroffSingleCoreActive() {
        return Utils.readFile(HOTPLUG_AUTOSMP_SCROFF_SINGLE_CORE).equals("1");
    }

    public static boolean hasAutoSmpScroffSingleCore() {
        return Utils.existFile(HOTPLUG_AUTOSMP_SCROFF_SINGLE_CORE);
    }

    public static void setAutoSmpMinCpus(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_AUTOSMP_MIN_CPUS, Control.CommandType.GENERIC, context);
    }

    public static int getAutoSmpMinCpus() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_AUTOSMP_MIN_CPUS));
    }

    public static boolean hasAutoSmpMinCpus() {
        return Utils.existFile(HOTPLUG_AUTOSMP_MIN_CPUS);
    }

    public static void setAutoSmpMaxCpus(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_AUTOSMP_MAX_CPUS, Control.CommandType.GENERIC, context);
    }

    public static int getAutoSmpMaxCpus() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_AUTOSMP_MAX_CPUS));
    }

    public static boolean hasAutoSmpMaxCpus() {
        return Utils.existFile(HOTPLUG_AUTOSMP_MAX_CPUS);
    }

    public static void setAutoSmpDelay(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_AUTOSMP_DELAY, Control.CommandType.GENERIC, context);
    }

    public static int getAutoSmpDelay() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_AUTOSMP_DELAY));
    }

    public static boolean hasAutoSmpDelay() {
        return Utils.existFile(HOTPLUG_AUTOSMP_DELAY);
    }

    public static void setAutoSmpCycleUp(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_AUTOSMP_CYCLE_UP, Control.CommandType.GENERIC, context);
    }

    public static int getAutoSmpCycleUp() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_AUTOSMP_CYCLE_UP));
    }

    public static boolean hasAutoSmpCycleUp() {
        return Utils.existFile(HOTPLUG_AUTOSMP_CYCLE_UP);
    }

    public static void setAutoSmpCycleDown(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_AUTOSMP_CYCLE_DOWN, Control.CommandType.GENERIC, context);
    }

    public static int getAutoSmpCycleDown() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_AUTOSMP_CYCLE_DOWN));
    }

    public static boolean hasAutoSmpCycleDown() {
        return Utils.existFile(HOTPLUG_AUTOSMP_CYCLE_DOWN);
    }

    public static void setAutoSmpCpufreqUp(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_AUTOSMP_CPUFREQ_UP, Control.CommandType.GENERIC, context);
    }

    public static int getAutoSmpCpufreqUp() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_AUTOSMP_CPUFREQ_UP));
    }

    public static boolean hasAutoSmpCpufreqUp() {
        return Utils.existFile(HOTPLUG_AUTOSMP_CPUFREQ_UP);
    }

    public static void setAutoSmpCpufreqDown(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_AUTOSMP_CPUFREQ_DOWN, Control.CommandType.GENERIC, context);
    }

    public static int getAutoSmpCpufreqDown() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_AUTOSMP_CPUFREQ_DOWN));
    }

    public static boolean hasAutoSmpCpufreqDown() {
        return Utils.existFile(HOTPLUG_AUTOSMP_CPUFREQ_DOWN);
    }

    public static void activateAutoSmp(boolean active, Context context) {
        Control.runCommand(active ? "Y" : "N", HOTPLUG_AUTOSMP_ENABLE, Control.CommandType.GENERIC, context);
        if (active) togglehotplugs("AutoSMP", context);
    }

    public static boolean isAutoSmpActive() {
        return Utils.readFile(HOTPLUG_AUTOSMP_ENABLE).equals("Y");
    }

    public static boolean hasAutoSmpEnable() {
        return Utils.existFile(HOTPLUG_AUTOSMP_ENABLE);
    }

    public static boolean hasAutoSmp() {
        for (String file: HOTPLUG_AUTOSMP_ARRAY)
            if (Utils.existFile(file)) return true;
        return false;
    }

    public static void activateLazyPlugTouchBoost(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", HOTPLUG_LAZYPLUG_TOUCH_BOOST_ACTIVE, Control.CommandType.GENERIC, context);
        if (active) togglehotplugs("LazyPlug", context);
    }

    public static boolean isLazyPlugTouchBoostActive() {
        return Utils.readFile(HOTPLUG_LAZYPLUG_TOUCH_BOOST_ACTIVE).equals("1");
    }

    public static boolean hasLazyPlugTouchBoostActive() {
        return Utils.existFile(HOTPLUG_LAZYPLUG_TOUCH_BOOST_ACTIVE);
    }

    public static void setLazyPlugProfile(int value, Context context) {
        String file = HOTPLUG_LAZYPLUG_NR_RUN_PROFILE_SET;
        Control.runCommand(String.valueOf(value), file, Control.CommandType.GENERIC, context);
    }

    public static int getLazyPlugProfile() {
        String file = HOTPLUG_LAZYPLUG_NR_RUN_PROFILE_SET;
        return Utils.stringToInt(Utils.readFile(file));
    }

    public static List < String > getLazyPlugProfileMenu(Context context) {
        List < String > list = new ArrayList < > ();
        list.add(context.getString(R.string.balanced));
        list.add(context.getString(R.string.performance));
        list.add(context.getString(R.string.conservative));
        list.add(context.getString(R.string.eco));
        list.add(context.getString(R.string.eco_extreme));
        list.add(context.getString(R.string.disabled));
        return list;
    }

    public static boolean hasLazyPlugProfile() {
        String file = HOTPLUG_LAZYPLUG_NR_RUN_PROFILE_SET;
        return Utils.existFile(file);
    }

    public static void setLazyPlugNrRunHysteresis(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_LAZYPLUG_NR_RUN_HYSTERESIS, Control.CommandType.GENERIC, context);
    }

    public static int getLazyPlugNrRunHysteresis() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_LAZYPLUG_NR_RUN_HYSTERESIS));
    }

    public static boolean hasLazyPlugNrRunHysteresis() {
        return Utils.existFile(HOTPLUG_LAZYPLUG_NR_RUN_HYSTERESIS);
    }

    public static void setLazyPlugNrPossibleCores(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_LAZYPLUG_NR_POSSIBLE_CORES, Control.CommandType.GENERIC, context);
    }

    public static int getLazyPlugNrPossibleCores() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_LAZYPLUG_NR_POSSIBLE_CORES));
    }

    public static boolean hasLazyPlugNrPossibleCores() {
        return Utils.existFile(HOTPLUG_LAZYPLUG_NR_POSSIBLE_CORES);
    }

    public static void setLazyPlugCpuNrRunTreshold(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_LAZYPLUG_CPU_NR_RUN_TRESHOLD, Control.CommandType.GENERIC, context);
    }

    public static int getLazyPlugCpuNrRunTreshold() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_LAZYPLUG_CPU_NR_RUN_TRESHOLD));
    }

    public static boolean hasLazyPlugCpuNrRunTreshold() {
        return Utils.existFile(HOTPLUG_LAZYPLUG_CPU_NR_RUN_TRESHOLD);
    }

    public static void activateLazyPlug(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", HOTPLUG_LAZYPLUG_ACTIVE, Control.CommandType.GENERIC, context);
        if (active) togglehotplugs("Lazyplug", context);
    }

    public static boolean isLazyPlugActive() {
        return Utils.readFile(HOTPLUG_LAZYPLUG_ACTIVE).equals("1");
    }

    public static boolean hasLazyPlugEnable() {
        return Utils.existFile(HOTPLUG_LAZYPLUG_ACTIVE);
    }

    public static boolean hasLazyPlug() {
        for (String file: HOTPLUG_LAZYPLUG_ARRAY)
            if (Utils.existFile(file)) return true;
        return false;
    }

    public static void setDynPlugDownTimerCnt(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_DYN_PLUG_DOWN_TIMER_CNT, Control.CommandType.GENERIC, context);
    }

    public static int getDynPlugDownTimerCnt() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_DYN_PLUG_DOWN_TIMER_CNT));
    }

    public static boolean hasDynPlugDownTimerCnt() {
        return Utils.existFile(HOTPLUG_DYN_PLUG_DOWN_TIMER_CNT);
    }

    public static void setDynPlugUpTimerCnt(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_DYN_PLUG_UP_TIMER_CNT, Control.CommandType.GENERIC, context);
    }

    public static int getDynPlugUpTimerCnt() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_DYN_PLUG_UP_TIMER_CNT));
    }

    public static boolean hasDynPlugUpTimerCnt() {
        return Utils.existFile(HOTPLUG_DYN_PLUG_UP_TIMER_CNT);
    }

    public static void setDynPlugUpThreshold(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_DYN_PLUG_UP_THRESHOLD, Control.CommandType.GENERIC, context);
    }

    public static int getDynPlugUpThreshold() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_DYN_PLUG_UP_THRESHOLD));
    }

    public static boolean hasDynPlugUpThreshold() {
        return Utils.existFile(HOTPLUG_DYN_PLUG_UP_THRESHOLD);
    }

    public static void setDynPlugMaxOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_DYN_PLUG_MAX_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getDynPlugMaxOnline() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_DYN_PLUG_MAX_ONLINE));
    }

    public static boolean hasDynPlugMaxOnline() {
        return Utils.existFile(HOTPLUG_DYN_PLUG_MAX_ONLINE);
    }

    public static void setDynPlugMinOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_DYN_PLUG_MIN_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getDynPlugMinOnline() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_DYN_PLUG_MIN_ONLINE));
    }

    public static boolean hasDynPlugMinOnline() {
        return Utils.existFile(HOTPLUG_DYN_PLUG_MIN_ONLINE);
    }

    public static void activateDynPlug(boolean active, Context context) {
        Control.runCommand(active ? "Y" : "N", HOTPLUG_DYN_PLUG_ENABLE, Control.CommandType.GENERIC, context);
        if (active) togglehotplugs("DynPlug", context);
    }

    public static boolean isDynPlugActive() {
        return Utils.readFile(HOTPLUG_DYN_PLUG_ENABLE).equals("Y");
    }

    public static boolean hasDynPlugEnable() {
        return Utils.existFile(HOTPLUG_DYN_PLUG_ENABLE);
    }

    public static boolean hasDynPlug() {
        return Utils.existFile(HOTPLUG_DYN_PLUG);
    }

    public static void setAutoHotplugDisableLoadTreshold(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_AUTO_HOTPLUG_DISABLE_LOAD_THRESHOLD, Control.CommandType.GENERIC, context);
    }

    public static int getAutoHotplugDisableLoadTreshold() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_AUTO_HOTPLUG_DISABLE_LOAD_THRESHOLD));
    }

    public static boolean hasAutoHotplugDisableLoadTreshold() {
        return Utils.existFile(HOTPLUG_AUTO_HOTPLUG_DISABLE_LOAD_THRESHOLD);
    }

    public static void setAutoHotplugEnableLoadTreshold(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_AUTO_HOTPLUG_ENABLE_LOAD_THRESHOLD, Control.CommandType.GENERIC, context);
    }

    public static int getAutoHotplugEnableLoadTreshold() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_AUTO_HOTPLUG_ENABLE_LOAD_THRESHOLD));
    }

    public static boolean hasAutoHotplugEnableLoadTreshold() {
        return Utils.existFile(HOTPLUG_AUTO_HOTPLUG_ENABLE_LOAD_THRESHOLD);
    }

    public static void setAutoHotplugEnableAllLoadTreshold(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_AUTO_HOTPLUG_ENABLE_ALL_LOAD_THRESHOLD, Control.CommandType.GENERIC, context);
    }

    public static int getAutoHotplugEnableAllLoadTreshold() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_AUTO_HOTPLUG_ENABLE_ALL_LOAD_THRESHOLD));
    }

    public static boolean hasAutoHotplugEnableAllLoadTreshold() {
        return Utils.existFile(HOTPLUG_AUTO_HOTPLUG_ENABLE_ALL_LOAD_THRESHOLD);
    }

    public static void setAutoHotplugMaxOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_AUTO_HOTPLUG_MAX_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getAutoHotplugMaxOnline() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_AUTO_HOTPLUG_MAX_ONLINE));
    }

    public static boolean hasAutoHotplugMaxOnline() {
        return Utils.existFile(HOTPLUG_AUTO_HOTPLUG_MAX_ONLINE);
    }

    public static void setAutoHotplugMinOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_AUTO_HOTPLUG_MIN_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getAutoHotplugMinOnline() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_AUTO_HOTPLUG_MIN_ONLINE));
    }

    public static boolean hasAutoHotplugMinOnline() {
        return Utils.existFile(HOTPLUG_AUTO_HOTPLUG_MIN_ONLINE);
    }

    public static void setAutoHotplugSamplingPeriods(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_AUTO_HOTPLUG_SAMPLING_PERIODS, Control.CommandType.GENERIC, context);
    }

    public static int getAutoHotplugSamplingPeriods() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_AUTO_HOTPLUG_SAMPLING_PERIODS));
    }

    public static boolean hasAutoHotplugSamplingPeriods() {
        return Utils.existFile(HOTPLUG_AUTO_HOTPLUG_SAMPLING_PERIODS);
    }

    public static void activateAutoHotplug(boolean active, Context context) {
        Control.runCommand(active ? "Y" : "N", HOTPLUG_AUTO_HOTPLUG_ENABLE, Control.CommandType.GENERIC, context);
        if (active) togglehotplugs("AutoHotplug", context);
    }

    public static boolean isAutoHotplugActive() {
        return Utils.readFile(HOTPLUG_AUTO_HOTPLUG_ENABLE).equals("Y");
    }

    public static boolean hasAutoHotplugEnable() {
        return Utils.existFile(HOTPLUG_AUTO_HOTPLUG_ENABLE);
    }

    public static boolean hasAutoHotplug() {
        return Utils.existFile(HOTPLUG_AUTO_HOTPLUG);
    }

    public static void setZenDecisionBatThresholdIgnore(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_ZEN_DECISION_BAT_THRESHOLD_IGNORE, Control.CommandType.GENERIC, context);
    }

    public static int getZenDecisionBatThresholdIgnore() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_ZEN_DECISION_BAT_THRESHOLD_IGNORE));
    }

    public static boolean hasZenDecisionBatThresholdIgnore() {
        return Utils.existFile(HOTPLUG_ZEN_DECISION_BAT_THRESHOLD_IGNORE);
    }

    public static void setZenDecisionWakeWaitTime(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_ZEN_DECISION_WAKE_WAIT_TIME, Control.CommandType.GENERIC, context);
    }

    public static int getZenDecisionWakeWaitTime() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_ZEN_DECISION_WAKE_WAIT_TIME));
    }

    public static boolean hasZenDecisionWakeWaitTime() {
        return Utils.existFile(HOTPLUG_ZEN_DECISION_WAKE_WAIT_TIME);
    }

    public static void activateZenDecision(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", HOTPLUG_ZEN_DECISION_ENABLE, Control.CommandType.GENERIC, context);
        if (active) togglehotplugs("ZenDecision", context);
    }

    public static boolean isZenDecisionActive() {
        return Utils.readFile(HOTPLUG_ZEN_DECISION_ENABLE).equals("1");
    }

    public static boolean hasZenDecisionEnable() {
        return Utils.existFile(HOTPLUG_ZEN_DECISION_ENABLE);
    }

    public static boolean hasZenDecision() {
        return Utils.existFile(HOTPLUG_ZEN_DECISION);
    }

    public static void activateThunderPlugTouchBoost(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", HOTPLUG_THUNDER_PLUG_TOUCH_BOOST, Control.CommandType.GENERIC, context);
    }

    public static boolean isThunderPlugTouchBoostActive() {
        return Utils.readFile(HOTPLUG_THUNDER_PLUG_TOUCH_BOOST).equals("1");
    }

    public static boolean hasThunderPlugTouchBoost() {
        return Utils.existFile(HOTPLUG_THUNDER_PLUG_TOUCH_BOOST);
    }

    public static void activateThunderPlugSchedBoost(boolean active, Context context) {
        Control.runCommand(active ? "2" : "1", HOTPLUG_THUNDER_PLUG_SCHED_BOOST, Control.CommandType.GENERIC, context);
    }

    public static boolean isThunderPlugSchedBoostActive() {
        return Utils.readFile(HOTPLUG_THUNDER_PLUG_SCHED_BOOST).equals("2");
    }

    public static boolean hasThunderPlugSchedBoost() {
        return Utils.existFile(HOTPLUG_THUNDER_PLUG_SCHED_BOOST);
    }

    public static void setThunderPlugLoadThreshold(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_THUNDER_PLUG_LOAD_THRESHOLD, Control.CommandType.GENERIC, context);
    }

    public static int getThunderPlugLoadThreshold() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_THUNDER_PLUG_LOAD_THRESHOLD));
    }

    public static boolean hasThunderPlugLoadThreshold() {
        return Utils.existFile(HOTPLUG_THUNDER_PLUG_LOAD_THRESHOLD);
    }

    public static void setThunderPlugSamplingRate(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_THUNDER_PLUG_SAMPLING_RATE, Control.CommandType.GENERIC, context);
    }

    public static int getThunderPlugSamplingRate() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_THUNDER_PLUG_SAMPLING_RATE));
    }

    public static boolean hasThunderPlugSamplingRate() {
        return Utils.existFile(HOTPLUG_THUNDER_PLUG_SAMPLING_RATE);
    }

    public static void setThunderPlugEnduranceLevel(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_THUNDER_PLUG_ENDURANCE_LEVEL, Control.CommandType.GENERIC, context);
    }

    public static int getThunderPlugEnduranceLevel() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_THUNDER_PLUG_ENDURANCE_LEVEL));
    }

    public static boolean hasThunderPlugEnduranceLevel() {
        return Utils.existFile(HOTPLUG_THUNDER_PLUG_ENDURANCE_LEVEL);
    }

    public static void setThunderPlughpstyle(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_THUNDER_HP_STYLE, Control.CommandType.GENERIC, context);
    }

    public static int getThunderPlughpstyle() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_THUNDER_HP_STYLE));
    }

    public static boolean hasThunderPlughpstyle() {
        return Utils.existFile(HOTPLUG_THUNDER_HP_STYLE);
    }

    public static void setThunderPlugSuspendCpus(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_THUNDER_PLUG_SUSPEND_CPUS, Control.CommandType.GENERIC, context);
    }

    public static int getThunderPlugSuspendCpus() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_THUNDER_PLUG_SUSPEND_CPUS));
    }

    public static boolean hasThunderPlugSuspendCpus() {
        return Utils.existFile(HOTPLUG_THUNDER_PLUG_SUSPEND_CPUS);
    }

    public static void activateThunderPlug(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", HOTPLUG_THUNDER_PLUG_ENABLE, Control.CommandType.GENERIC, context);
        if (active) togglehotplugs("ThunderPlug", context);
    }

    public static boolean isThunderPlugActive() {
        return Utils.readFile(HOTPLUG_THUNDER_PLUG_ENABLE).equals("1");
    }

    public static boolean hasThunderPlugEnable() {
        return Utils.existFile(HOTPLUG_THUNDER_PLUG_ENABLE);
    }

    public static boolean hasThunderPlug() {
        return Utils.existFile(HOTPLUG_THUNDER_PLUG);
    }

    public static void setAlucardHotplugCpuUpRate(int value, Context context) {
        Control.runCommand(String.valueOf(value), ALUCARD_HOTPLUG_CPU_UP_RATE, Control.CommandType.GENERIC, context);
    }

    public static int getAlucardHotplugCpuUpRate() {
        return Utils.stringToInt(Utils.readFile(ALUCARD_HOTPLUG_CPU_UP_RATE));
    }

    public static boolean hasAlucardHotplugCpuUpRate() {
        return Utils.existFile(ALUCARD_HOTPLUG_CPU_UP_RATE);
    }

    public static void setAlucardHotplugCpuDownRate(int value, Context context) {
        Control.runCommand(String.valueOf(value), ALUCARD_HOTPLUG_CPU_DOWN_RATE, Control.CommandType.GENERIC, context);
    }

    public static int getAlucardHotplugCpuDownRate() {
        return Utils.stringToInt(Utils.readFile(ALUCARD_HOTPLUG_CPU_DOWN_RATE));
    }

    public static boolean hasAlucardHotplugCpuDownRate() {
        return Utils.existFile(ALUCARD_HOTPLUG_CPU_DOWN_RATE);
    }

    public static void setAlucardHotplugMaxCoresLimitSleep(int value, Context context) {
        Control.runCommand(String.valueOf(value), ALUCARD_HOTPLUG_MAX_CORES_LIMIT_SLEEP, Control.CommandType.GENERIC, context);
    }

    public static int getAlucardHotplugMaxCoresLimitSleep() {
        return Utils.stringToInt(Utils.readFile(ALUCARD_HOTPLUG_MAX_CORES_LIMIT_SLEEP));
    }

    public static boolean hasAlucardHotplugMaxCoresLimitSleep() {
        return Utils.existFile(ALUCARD_HOTPLUG_MAX_CORES_LIMIT_SLEEP);
    }

    public static void setAlucardHotplugMaxCoresLimit(int value, Context context) {
        Control.runCommand(String.valueOf(value), ALUCARD_HOTPLUG_MAX_CORES_LIMIT, Control.CommandType.GENERIC, context);
    }

    public static int getAlucardHotplugMaxCoresLimit() {
        return Utils.stringToInt(Utils.readFile(ALUCARD_HOTPLUG_MAX_CORES_LIMIT));
    }

    public static boolean hasAlucardHotplugMaxCoresLimit() {
        return Utils.existFile(ALUCARD_HOTPLUG_MAX_CORES_LIMIT);
    }

    public static void setAlucardHotplugMinCpusOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), ALUCARD_HOTPLUG_MIN_CPUS_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getAlucardHotplugMinCpusOnline() {
        return Utils.stringToInt(Utils.readFile(ALUCARD_HOTPLUG_MIN_CPUS_ONLINE));
    }

    public static boolean hasAlucardHotplugMinCpusOnline() {
        return Utils.existFile(ALUCARD_HOTPLUG_MIN_CPUS_ONLINE);
    }

    public static void activateAlucardHotplugSuspend(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", ALUCARD_HOTPLUG_SUSPEND, Control.CommandType.GENERIC, context);
    }

    public static boolean isAlucardHotplugSuspendActive() {
        return Utils.readFile(ALUCARD_HOTPLUG_SUSPEND).equals("1");
    }

    public static boolean hasAlucardHotplugSuspend() {
        return Utils.existFile(ALUCARD_HOTPLUG_SUSPEND);
    }

    public static void setAlucardHotplugSamplingRate(int value, Context context) {
        Control.runCommand(String.valueOf(value), ALUCARD_HOTPLUG_SAMPLING_RATE, Control.CommandType.GENERIC, context);
    }

    public static int getAlucardHotplugSamplingRate() {
        return Utils.stringToInt(Utils.readFile(ALUCARD_HOTPLUG_SAMPLING_RATE));
    }

    public static boolean hasAlucardHotplugSamplingRate() {
        return Utils.existFile(ALUCARD_HOTPLUG_SAMPLING_RATE);
    }

    public static void activateAlucardHotplugHpIoIsBusy(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", ALUCARD_HOTPLUG_HP_IO_IS_BUSY, Control.CommandType.GENERIC, context);
    }

    public static boolean isAlucardHotplugHpIoIsBusyActive() {
        return Utils.readFile(ALUCARD_HOTPLUG_HP_IO_IS_BUSY).equals("1");
    }

    public static boolean hasAlucardHotplugHpIoIsBusy() {
        return Utils.existFile(ALUCARD_HOTPLUG_HP_IO_IS_BUSY);
    }

    public static void activateAlucardHotplug(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", ALUCARD_HOTPLUG_ENABLE, Control.CommandType.GENERIC, context);
        if (active) togglehotplugs("AlucardHotplug", context);
    }

    public static boolean isAlucardHotplugActive() {
        return Utils.readFile(ALUCARD_HOTPLUG_ENABLE).equals("1");
    }

    public static boolean hasAlucardHotplugEnable() {
        return Utils.existFile(ALUCARD_HOTPLUG_ENABLE);
    }

    public static boolean hasAlucardHotplug() {
        return Utils.existFile(ALUCARD_HOTPLUG);
    }

    public static void setAlucardHotplugHotplugRate(String core_hotplug, int value, Context context) {
        Control.runCommand(String.valueOf(value), String.format(ALUCARD_HOTPLUG_HOTPLUG_RATE, core_hotplug), Control.CommandType.GENERIC, context);
    }

    public static int getAlucardHotplugHotplugRate(String core_hotplug) {
        return Utils.stringToInt(Utils.readFile(String.format(ALUCARD_HOTPLUG_HOTPLUG_RATE, core_hotplug)));
    }

    public static boolean hasAlucardHotplugHotplugRate(String core_hotplug) {
        return Utils.existFile(String.format(ALUCARD_HOTPLUG_HOTPLUG_RATE, core_hotplug));
    }

    public static void setAlucardHotplugLoad(String core_hotplug, int value, Context context) {
        Control.runCommand(String.valueOf(value), String.format(ALUCARD_HOTPLUG_LOAD, core_hotplug), Control.CommandType.GENERIC, context);
    }

    public static int getAlucardHotplugLoad(String core_hotplug) {
        return Utils.stringToInt(Utils.readFile(String.format(ALUCARD_HOTPLUG_LOAD, core_hotplug)));
    }

    public static boolean hasAlucardHotplugLoad(String core_hotplug) {
        return Utils.existFile(String.format(ALUCARD_HOTPLUG_LOAD, core_hotplug));
    }

    public static void setAlucardHotplugRq(String core_hotplug, int value, Context context) {
        Control.runCommand(String.valueOf(value), String.format(ALUCARD_HOTPLUG_RQ, core_hotplug), Control.CommandType.GENERIC, context);
    }

    public static int getAlucardHotplugRq(String core_hotplug) {
        return Utils.stringToInt(Utils.readFile(String.format(ALUCARD_HOTPLUG_RQ, core_hotplug)));
    }

    public static boolean hasAlucardHotplugRq(String core_hotplug) {
        return Utils.existFile(String.format(ALUCARD_HOTPLUG_RQ, core_hotplug));
    }

    public static void setAlucardHotplugFreq(String core_hotplug, int value, Context context) {
        Control.runCommand(String.valueOf(value), String.format(ALUCARD_HOTPLUG_FREQ, core_hotplug), Control.CommandType.GENERIC, context);
    }

    public static int getAlucardHotplugFreq(String core_hotplug) {
        return Utils.stringToInt(Utils.readFile(String.format(ALUCARD_HOTPLUG_FREQ, core_hotplug)));
    }

    public static boolean hasAlucardHotplugFreq(String core_hotplug) {
        return Utils.existFile(String.format(ALUCARD_HOTPLUG_FREQ, core_hotplug));
    }

    public static void setMBHotplugPause(int value, Context context) {
        Control.runCommand(String.valueOf(value), Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_PAUSE, Control.CommandType.GENERIC, context);
    }

    public static int getMBHotplugPause() {
        return Utils.stringToInt(Utils.readFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_PAUSE));
    }

    public static boolean hasMBHotplugPause() {
        return Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_PAUSE);
    }

    public static void setMBHotplugDelay(int value, Context context) {
        Control.runCommand(String.valueOf(value), Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_DELAY, Control.CommandType.GENERIC, context);
    }

    public static int getMBHotplugDelay() {
        return Utils.stringToInt(Utils.readFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_DELAY));
    }

    public static boolean hasMBHotplugDelay() {
        return Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_DELAY);
    }

    public static void setMBHotplugStartDelay(int value, Context context) {
        Control.runCommand(String.valueOf(value), Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_STARTDELAY, Control.CommandType.GENERIC, context);
    }

    public static int getMBHotplugStartDelay() {
        return Utils.stringToInt(Utils.readFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_STARTDELAY));
    }

    public static boolean hasMBHotplugStartDelay() {
        return Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_STARTDELAY);
    }

    public static void setBrickedDownLockDuration(int value, Context context) {
        Control.runCommand(String.valueOf(value), Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + BRICKED_DOWN_LOCK_DURATION, Control.CommandType.GENERIC, context);
    }

    public static int getBrickedDownLockDuration() {
        return Utils.stringToInt(Utils.readFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + BRICKED_DOWN_LOCK_DURATION));
    }

    public static boolean hasBrickedDownLockDuration() {
        return Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + BRICKED_DOWN_LOCK_DURATION);
    }

    public static void setMBHotplugBoostFreqs(int core, int value, Context context) {
        Control.runCommand(core + " " + value, Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_BOOST_FREQS, Control.CommandType.GENERIC, context);
    }

    public static List < Integer > getMBHotplugBoostFreqs() {
        List < Integer > list = new ArrayList < > ();
        for (String freq: Utils.readFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_BOOST_FREQS).split(" "))
            list.add(Utils.stringToInt(freq));
        return list;
    }

    public static boolean hasMBHotplugBoostFreqs() {
        return Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_BOOST_FREQS);
    }

    public static void setMBHotplugCpusBoosted(int value, Context context) {
        Control.runCommand(String.valueOf(value), Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_CPUS_BOOSTED, Control.CommandType.GENERIC, context);
    }

    public static int getMBHotplugCpusBoosted() {
        return Utils.stringToInt(Utils.readFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_CPUS_BOOSTED));
    }

    public static boolean hasMBHotplugCpusBoosted() {
        return Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_CPUS_BOOSTED);
    }

    public static void setMBHotplugBoostTime(int value, Context context) {
        Control.runCommand(String.valueOf(value), Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_BOOST_TIME, Control.CommandType.GENERIC, context);
    }

    public static int getMBHotplugBoostTime() {
        return Utils.stringToInt(Utils.readFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_BOOST_TIME));
    }

    public static boolean hasMBHotplugBoostTime() {
        return Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_BOOST_TIME);
    }

    public static void activateMBHotplugBoost(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_BOOST_ENABLED, Control.CommandType.GENERIC, context);
    }

    public static boolean isMBHotplugBoostActive() {
        return Utils.readFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_BOOST_ENABLED).equals("1");
    }

    public static boolean hasMBHotplugBoostEnable() {
        return Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_BOOST_ENABLED);
    }

    public static void setMBHotplugIdleFreq(int value, Context context) {
        Control.runCommand(String.valueOf(value), Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_IDLE_FREQ, Control.CommandType.GENERIC, context);
    }

    public static int getMBHotplugIdleFreq() {
        return Utils.stringToInt(Utils.readFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_IDLE_FREQ));
    }

    public static boolean hasMBHotplugIdleFreq() {
        return Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_IDLE_FREQ);
    }

    public static void setMBHotplugMaxCpusOnlineSusp(int value, Context context) {
        Control.runCommand(String.valueOf(value), Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_CPUS_ONLINE_SUSP, Control.CommandType.GENERIC, context);
    }

    public static int getMBHotplugMaxCpusOnlineSusp() {
        return Utils.stringToInt(Utils.readFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_CPUS_ONLINE_SUSP));
    }

    public static boolean hasMBHotplugMaxCpusOnlineSusp() {
        return Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_CPUS_ONLINE_SUSP);
    }

    public static void setMBHotplugMaxCpus(int value, Context context) {
        Control.runCommand(String.valueOf(value), MB_HOTPLUG_MAX_CPUS_FILE, Control.CommandType.GENERIC, context);
    }

    public static int getMBHotplugMaxCpus() {
        return Utils.stringToInt(Utils.readFile(MB_HOTPLUG_MAX_CPUS_FILE));
    }

    public static boolean hasMBHotplugMaxCpus() {
        if (Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_MAX_CPUS))
            MB_HOTPLUG_MAX_CPUS_FILE = Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_MAX_CPUS;
        else if (Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_MAX_CPUS_ONLINE))
            MB_HOTPLUG_MAX_CPUS_FILE = Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_MAX_CPUS_ONLINE;
        return MB_HOTPLUG_MAX_CPUS_FILE != null;
    }

    public static void setMBHotplugMinCpus(int value, Context context) {
        Control.runCommand(String.valueOf(value), MB_HOTPLUG_MIN_CPUS_FILE, Control.CommandType.GENERIC, context);
    }

    public static int getMBHotplugMinCpus() {
        return Utils.stringToInt(Utils.readFile(MB_HOTPLUG_MIN_CPUS_FILE));
    }

    public static boolean hasMBHotplugMinCpus() {
        if (Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_MIN_CPUS))
            MB_HOTPLUG_MIN_CPUS_FILE = Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_MIN_CPUS;
        else if (Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_MIN_CPUS_ONLINE))
            MB_HOTPLUG_MIN_CPUS_FILE = Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_MIN_CPUS_ONLINE;
        return MB_HOTPLUG_MIN_CPUS_FILE != null;
    }

    public static void activateMBHotplugScroffSingleCore(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_SCROFF_SINGLE_CORE, Control.CommandType.GENERIC, context);
    }

    public static boolean isMBHotplugScroffSingleCoreActive() {
        return Utils.readFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_SCROFF_SINGLE_CORE).equals("1");
    }

    public static boolean hasMBHotplugScroffSingleCore() {
        return Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_SCROFF_SINGLE_CORE);
    }

    public static void activateMBHotplug(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_ENABLED, Control.CommandType.GENERIC, context);
        if (active) togglehotplugs("MBHotplug", context);
    }

    public static boolean isMBHotplugActive() {
        return Utils.readFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_ENABLED).equals("1");
    }

    public static boolean hasMBHotplugEnable() {
        return Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_ENABLED);
    }

    public static boolean hasMBHotplug() {
        return Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY));
    }

    public static boolean hasBrickedNWNS() {
        return Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + BRICKED_NWNS + "_0");
    }

    public static String getBrickedNWNS(int num, String item, Context context) {
        if (item != null) {
            if ((num & 1) == 0) {
                if (item.equals("title"))
                    return String.format(context.getString(R.string.bricked_nwns), num / 2);
                if (item.equals("description"))
                    return String.format(context.getString(R.string.bricked_nwns_hotplug), num / 2);
                if (item.equals("value") && Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + BRICKED_NWNS + "_" + num))
                    return Utils.readFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + BRICKED_NWNS + "_" + num);
            } else {
                if (item.equals("title"))
                    return String.format(context.getString(R.string.bricked_nwns), (num - 1) / 2);
                if (item.equals("description"))
                    return String.format(context.getString(R.string.bricked_nwns_unplug), (num - 1) / 2);
                if (item.equals("value") && Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + BRICKED_NWNS + "_" + num))
                    return Utils.readFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + BRICKED_NWNS + "_" + num);
            }
        }
        return "error";
    }

    public static void setBrickedNWNS(int num, int value, Context context) {
        if (Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + BRICKED_NWNS + "_" + num)) {
            Control.runCommand(String.valueOf(value), Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + BRICKED_NWNS + "_" + num, Control.CommandType.GENERIC, context);
        }
    }

    public static boolean hasBrickedTWTS() {
        return Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + BRICKED_TWTS + "_0");
    }

    public static String getBrickedTWTS(int num, String item, Context context) {
        if ((num & 1) == 0) {
            if (item.equals("title")) return String.format(context.getString(R.string.bricked_twts), num / 2);
            if (item.equals("description")) return String.format(context.getString(R.string.bricked_twts_hotplug), num / 2);
            if (item.equals("value") && Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + BRICKED_TWTS + "_" + num)) return Utils.readFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + BRICKED_TWTS + "_" + num);
        } else {
            if (item.equals("title")) return String.format(context.getString(R.string.bricked_twts), (num - 1) / 2);
            if (item.equals("description")) return String.format(context.getString(R.string.bricked_twts_unplug), (num - 1) / 2);
            if (item.equals("value") && Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + BRICKED_TWTS + "_" + num)) return Utils.readFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + BRICKED_TWTS + "_" + num);
        }
        return "error";
    }

    public static void setBrickedTWTS(int num, int value, Context context) {
        if (Utils.existFile(Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + BRICKED_TWTS + "_" + num)) {
            Control.runCommand(String.valueOf(value), Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + BRICKED_TWTS + "_" + num, Control.CommandType.GENERIC, context);
        }
    }

    public static void setMakoHotplugSuspendFreq(int value, Context context) {
        Control.runCommand(String.valueOf(value), MAKO_HOTPLUG_SUSPEND_FREQ, Control.CommandType.GENERIC, context);
    }

    public static int getMakoHotplugSuspendFreq() {
        return Utils.stringToInt(Utils.readFile(MAKO_HOTPLUG_SUSPEND_FREQ));
    }

    public static boolean hasMakoHotplugSuspendFreq() {
        return !Utils.existFile(CPU_MAX_SCREEN_OFF_FREQ) && Utils.existFile(MAKO_HOTPLUG_SUSPEND_FREQ);
    }

    public static void setMakoHotplugTimer(int value, Context context) {
        Control.runCommand(String.valueOf(value), MAKO_HOTPLUG_TIMER, Control.CommandType.GENERIC, context);
    }

    public static int getMakoHotplugTimer() {
        return Utils.stringToInt(Utils.readFile(MAKO_HOTPLUG_TIMER));
    }

    public static boolean hasMakoHotplugTimer() {
        return Utils.existFile(MAKO_HOTPLUG_TIMER);
    }

    public static void setMakoHotplugMinCoresOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), MAKO_HOTPLUG_MIN_CORES_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getMakoHotplugMinCoresOnline() {
        return Utils.stringToInt(Utils.readFile(MAKO_HOTPLUG_MIN_CORES_ONLINE));
    }

    public static boolean hasMakoHotplugMinCoresOnline() {
        return Utils.existFile(MAKO_HOTPLUG_MIN_CORES_ONLINE);
    }

    public static void setMakoHotplugMinTimeCpuOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), MAKO_HOTPLUG_MIN_TIME_CPU_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getMakoHotplugMinTimeCpuOnline() {
        return Utils.stringToInt(Utils.readFile(MAKO_HOTPLUG_MIN_TIME_CPU_ONLINE));
    }

    public static boolean hasMakoHotplugMinTimeCpuOnline() {
        return Utils.existFile(MAKO_HOTPLUG_MIN_TIME_CPU_ONLINE);
    }

    public static void setMakoHotplugMaxLoadCounter(int value, Context context) {
        Control.runCommand(String.valueOf(value), MAKO_HOTPLUG_MAX_LOAD_COUNTER, Control.CommandType.GENERIC, context);
    }

    public static int getMakoHotplugMaxLoadCounter() {
        return Utils.stringToInt(Utils.readFile(MAKO_HOTPLUG_MAX_LOAD_COUNTER));
    }

    public static boolean hasMakoHotplugMaxLoadCounter() {
        return Utils.existFile(MAKO_HOTPLUG_MAX_LOAD_COUNTER);
    }

    public static void setMakoHotplugLoadThreshold(int value, Context context) {
        Control.runCommand(String.valueOf(value), MAKO_HOTPLUG_LOAD_THRESHOLD, Control.CommandType.GENERIC, context);
    }

    public static int getMakoHotplugLoadThreshold() {
        return Utils.stringToInt(Utils.readFile(MAKO_HOTPLUG_LOAD_THRESHOLD));
    }

    public static boolean hasMakoHotplugLoadThreshold() {
        return Utils.existFile(MAKO_HOTPLUG_LOAD_THRESHOLD);
    }

    public static void setMakoHotplugHighLoadCounter(int value, Context context) {
        Control.runCommand(String.valueOf(value), MAKO_HOTPLUG_HIGH_LOAD_COUNTER, Control.CommandType.GENERIC, context);
    }

    public static int getMakoHotplugHighLoadCounter() {
        return Utils.stringToInt(Utils.readFile(MAKO_HOTPLUG_HIGH_LOAD_COUNTER));
    }

    public static boolean hasMakoHotplugHighLoadCounter() {
        return Utils.existFile(MAKO_HOTPLUG_HIGH_LOAD_COUNTER);
    }

    public static void setMakoHotplugFirstLevel(int value, Context context) {
        Control.runCommand(String.valueOf(value), MAKO_HOTPLUG_FIRST_LEVEL, Control.CommandType.GENERIC, context);
    }

    public static int getMakoHotplugFirstLevel() {
        return Utils.stringToInt(Utils.readFile(MAKO_HOTPLUG_FIRST_LEVEL));
    }

    public static boolean hasMakoHotplugFirstLevel() {
        return Utils.existFile(MAKO_HOTPLUG_FIRST_LEVEL);
    }

    public static void setMakoHotplugCpuFreqUnplugLimit(int value, Context context) {
        Control.runCommand(String.valueOf(value), MAKO_HOTPLUG_CPUFREQ_UNPLUG_LIMIT, Control.CommandType.GENERIC, context);
    }

    public static int getMakoHotplugCpuFreqUnplugLimit() {
        return Utils.stringToInt(Utils.readFile(MAKO_HOTPLUG_CPUFREQ_UNPLUG_LIMIT));
    }

    public static boolean hasMakoHotplugCpuFreqUnplugLimit() {
        return Utils.existFile(MAKO_HOTPLUG_CPUFREQ_UNPLUG_LIMIT);
    }

    public static void setMakoHotplugCoresOnTouch(int value, Context context) {
        Control.runCommand(String.valueOf(value), MAKO_HOTPLUG_CORES_ON_TOUCH, Control.CommandType.GENERIC, context);
    }

    public static int getMakoHotplugCoresOnTouch() {
        return Utils.stringToInt(Utils.readFile(MAKO_HOTPLUG_CORES_ON_TOUCH));
    }

    public static boolean hasMakoHotplugCoresOnTouch() {
        return Utils.existFile(MAKO_HOTPLUG_CORES_ON_TOUCH);
    }

    public static void activateMakoHotplug(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", MAKO_HOTPLUG_ENABLED, Control.CommandType.GENERIC, context);
        if (active) togglehotplugs("MakoHotPlug", context);
    }

    public static boolean isMakoHotplugActive() {
        return Utils.readFile(MAKO_HOTPLUG_ENABLED).equals("1");
    }

    public static boolean hasMakoHotplugEnable() {
        return Utils.existFile(MAKO_HOTPLUG_ENABLED);
    }

    public static boolean hasMakoHotplug() {
        return Utils.existFile(MAKO_HOTPLUG);
    }

    public static void setMsmHotplugSuspendDeferTime(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_SUSPEND_DEFER_TIME, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugSuspendDeferTime() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_SUSPEND_DEFER_TIME));
    }

    public static boolean hasMsmHotplugSuspendDeferTime() {
        return Utils.existFile(HOTPLUG_MSM_SUSPEND_DEFER_TIME);
    }

    public static void setMsmHotplugSuspendFreq(int value, Context context) {
        Control.runCommand(String.valueOf(value), MSM_HOTPLUG_SUSPEND_FREQ_FILE, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugSuspendFreq() {
        return Utils.stringToInt(Utils.readFile(MSM_HOTPLUG_SUSPEND_FREQ_FILE));
    }

    public static boolean hasMsmHotplugSuspendFreq() {
        if (!Utils.existFile(CPU_MAX_SCREEN_OFF_FREQ))
            if (Utils.existFile(HOTPLUG_MSM_SUSPEND_FREQ)) {
                MSM_HOTPLUG_SUSPEND_FREQ_FILE = HOTPLUG_MSM_SUSPEND_FREQ;
            } else if (Utils.existFile(HOTPLUG_MSM_SUSPEND_MAX_FREQ))
            MSM_HOTPLUG_SUSPEND_FREQ_FILE = HOTPLUG_MSM_SUSPEND_MAX_FREQ;
        return MSM_HOTPLUG_SUSPEND_FREQ_FILE != null;
    }

    public static void setMsmHotplugSuspendMaxCpus(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_SUSPEND_MAX_CPUS, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugSuspendMaxCpus() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_SUSPEND_MAX_CPUS));
    }

    public static boolean hasMsmHotplugSuspendMaxCpus() {
        return Utils.existFile(HOTPLUG_MSM_SUSPEND_MAX_CPUS);
    }

    public static void activateMsmHotplugIoIsBusy(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", MSM_HOTPLUG_IO_IS_BUSY_FILE, Control.CommandType.GENERIC, context);
    }

    public static boolean isMsmHotplugIoIsBusyActive() {
        return Utils.readFile(MSM_HOTPLUG_IO_IS_BUSY_FILE).equals("1");
    }

    public static boolean hasMsmHotplugIoIsBusy() {
        if (Utils.existFile(HOTPLUG_MSM_IO_IS_BUSY))
            MSM_HOTPLUG_IO_IS_BUSY_FILE = HOTPLUG_MSM_IO_IS_BUSY;
        else if (Utils.existFile(HOTPLUG_MSM_HP_IO_IS_BUSY))
            MSM_HOTPLUG_IO_IS_BUSY_FILE = HOTPLUG_MSM_HP_IO_IS_BUSY;
        return MSM_HOTPLUG_IO_IS_BUSY_FILE != null;
    }

    public static void setMsmHotplugOfflineLoad(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_OFFLINE_LOAD, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugOfflineLoad() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_OFFLINE_LOAD));
    }

    public static boolean hasMsmHotplugOfflineLoad() {
        return Utils.existFile(HOTPLUG_MSM_OFFLINE_LOAD);
    }

    public static void setMsmHotplugFastLaneMinFreq(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_FAST_LANE_MIN_FREQ, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugFastLaneMinFreq() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_FAST_LANE_MIN_FREQ));
    }

    public static boolean hasMsmHotplugFastLaneMinFreq() {
        return Utils.existFile(HOTPLUG_MSM_FAST_LANE_MIN_FREQ);
    }

    public static void setMsmHotplugFastLaneLoad(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_FAST_LANE_LOAD, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugFastLaneLoad() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_FAST_LANE_LOAD));
    }

    public static boolean hasMsmHotplugFastLaneLoad() {
        return Utils.existFile(HOTPLUG_MSM_FAST_LANE_LOAD);
    }

    public static void setMsmHotplugUpdateRate(int value, Context context) {
        Control.runCommand(String.valueOf(value), MSM_HOTPLUG_UPDATE_RATE_FILE, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugUpdateRate() {
        return Utils.stringToInt(Utils.readFile(MSM_HOTPLUG_UPDATE_RATE_FILE));
    }

    public static boolean hasMsmHotplugUpdateRate() {
        if (Utils.existFile(HOTPLUG_MSM_UPDATE_RATE))
            MSM_HOTPLUG_UPDATE_RATE_FILE = HOTPLUG_MSM_UPDATE_RATE;
        else if (Utils.existFile(HOTPLUG_MSM_UPDATE_RATES))
            MSM_HOTPLUG_UPDATE_RATE_FILE = HOTPLUG_MSM_UPDATE_RATES;
        return MSM_HOTPLUG_UPDATE_RATE_FILE != null;
    }

    public static void setMsmHotplugHistorySize(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_HISTORY_SIZE, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugHistorySize() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_HISTORY_SIZE));
    }

    public static boolean hasMsmHotplugHistorySize() {
        return Utils.existFile(HOTPLUG_MSM_HISTORY_SIZE);
    }

    public static void setMsmHotplugDownLockDuration(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_DOWN_LOCK_DURATION, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugDownLockDuration() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_DOWN_LOCK_DURATION));
    }

    public static boolean hasMsmHotplugDownLockDuration() {
        return Utils.existFile(HOTPLUG_MSM_DOWN_LOCK_DURATION);
    }

    public static void setMsmHotplugBoostLockDuration(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_BOOST_LOCK_DURATION, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugBoostLockDuration() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_BOOST_LOCK_DURATION));
    }

    public static boolean hasMsmHotplugBoostLockDuration() {
        return Utils.existFile(HOTPLUG_MSM_BOOST_LOCK_DURATION);
    }

    public static void setMsmHotplugMaxCpusOnlineSusp(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_MAX_CPUS_ONLINE_SUSP, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugMaxCpusOnlineSusp() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_MAX_CPUS_ONLINE_SUSP));
    }

    public static boolean hasMsmHotplugMaxCpusOnlineSusp() {
        return Utils.existFile(HOTPLUG_MSM_MAX_CPUS_ONLINE_SUSP);
    }

    public static void setMsmHotplugCpusBoosted(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_CPUS_BOOSTED, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugCpusBoosted() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_CPUS_BOOSTED));
    }

    public static boolean hasMsmHotplugCpusBoosted() {
        return Utils.existFile(HOTPLUG_MSM_CPUS_BOOSTED);
    }

    public static void setMsmHotplugMaxCpusOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_MAX_CPUS_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugMaxCpusOnline() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_MAX_CPUS_ONLINE));
    }

    public static boolean hasMsmHotplugMaxCpusOnline() {
        return Utils.existFile(HOTPLUG_MSM_MAX_CPUS_ONLINE);
    }

    public static void setMsmHotplugMinCpusOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_MIN_CPUS_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugMinCpusOnline() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_MIN_CPUS_ONLINE));
    }

    public static boolean hasMsmHotplugMinCpusOnline() {
        return Utils.existFile(HOTPLUG_MSM_MIN_CPUS_ONLINE);
    }

    public static void activateMsmHotplugDebugMask(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", HOTPLUG_MSM_DEBUG_MASK, Control.CommandType.GENERIC, context);
    }

    public static boolean isMsmHotplugDebugMaskActive() {
        return Utils.readFile(HOTPLUG_MSM_DEBUG_MASK).equals("1");
    }

    public static boolean hasMsmHotplugDebugMask() {
        return Utils.existFile(HOTPLUG_MSM_DEBUG_MASK);
    }

    public static void activateMsmHotplug(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", MSM_HOTPLUG_ENABLE_FILE, Control.CommandType.GENERIC, context);
        if (active) togglehotplugs("MSMHotPlug", context);
    }

    public static boolean isMsmHotplugActive() {
        return Utils.readFile(MSM_HOTPLUG_ENABLE_FILE).equals("1");
    }

    public static boolean hasMsmHotplugEnable() {
        if (Utils.existFile(Utils.getsysfspath(HOTPLUG_MSM_ENABLE_ARRAY))) {
            MSM_HOTPLUG_ENABLE_FILE = Utils.getsysfspath(HOTPLUG_MSM_ENABLE_ARRAY);
        }
        return MSM_HOTPLUG_ENABLE_FILE != null;
    }

    public static boolean hasMsmHotplug() {
        return Utils.existFile(HOTPLUG_MSM);
    }

    public static void setBluPlugDownTimerCnt(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_BLU_PLUG_DOWN_TIMER_CNT, Control.CommandType.GENERIC, context);
    }

    public static int getBluPlugDownTimerCnt() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_BLU_PLUG_DOWN_TIMER_CNT));
    }

    public static boolean hasBluPlugDownTimerCnt() {
        return Utils.existFile(HOTPLUG_BLU_PLUG_DOWN_TIMER_CNT);
    }

    public static void setBluPlugUpTimerCnt(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_BLU_PLUG_UP_TIMER_CNT, Control.CommandType.GENERIC, context);
    }

    public static int getBluPlugUpTimerCnt() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_BLU_PLUG_UP_TIMER_CNT));
    }

    public static boolean hasBluPlugUpTimerCnt() {
        return Utils.existFile(HOTPLUG_BLU_PLUG_UP_TIMER_CNT);
    }

    public static void setBluPlugUpThreshold(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_BLU_PLUG_UP_THRESHOLD, Control.CommandType.GENERIC, context);
    }

    public static int getBluPlugUpThreshold() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_BLU_PLUG_UP_THRESHOLD));
    }

    public static boolean hasBluPlugUpThreshold() {
        return Utils.existFile(HOTPLUG_BLU_PLUG_UP_THRESHOLD);
    }

    public static void setBluPlugMaxFreqScreenOff(int position, Context context) {
        String command = position == 0 ? "0" : String.valueOf(CPU.getFreqs().get(position - 1));
        Control.runCommand(command, HOTPLUG_BLU_PLUG_MAX_FREQ_SCREEN_OFF, Control.CommandType.GENERIC, context);
    }

    public static int getBluPlugMaxFreqScreenOff() {
        String value = Utils.readFile(HOTPLUG_BLU_PLUG_MAX_FREQ_SCREEN_OFF);
        if (value.equals("0")) return 0;
        return CPU.getFreqs().indexOf(Utils.stringToInt(value)) + 1;
    }

    public static boolean hasBluPlugMaxFreqScreenOff() {
        return !Utils.existFile(CPU_MAX_SCREEN_OFF_FREQ) && Utils.existFile(HOTPLUG_BLU_PLUG_MAX_FREQ_SCREEN_OFF);
    }

    public static void setBluPlugMaxCoresScreenOff(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_BLU_PLUG_MAX_CORES_SCREEN_OFF, Control.CommandType.GENERIC, context);
    }

    public static int getBluPlugMaxCoresScreenOff() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_BLU_PLUG_MAX_CORES_SCREEN_OFF));
    }

    public static boolean hasBluPlugMaxCoresScreenOff() {
        return Utils.existFile(HOTPLUG_BLU_PLUG_MAX_CORES_SCREEN_OFF);
    }

    public static void setBluPlugMaxOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_BLU_PLUG_MAX_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getBluPlugMaxOnline() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_BLU_PLUG_MAX_ONLINE));
    }

    public static boolean hasBluPlugMaxOnline() {
        return Utils.existFile(HOTPLUG_BLU_PLUG_MAX_ONLINE);
    }

    public static void setBluPlugMinOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_BLU_PLUG_MIN_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getBluPlugMinOnline() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_BLU_PLUG_MIN_ONLINE));
    }

    public static boolean hasBluPlugMinOnline() {
        return Utils.existFile(HOTPLUG_BLU_PLUG_MIN_ONLINE);
    }

    public static void activateBluPlugPowersaverMode(boolean active, Context context) {
        Control.runCommand(active ? "Y" : "N", HOTPLUG_BLU_PLUG_POWERSAVER_MODE, Control.CommandType.GENERIC, context);
    }

    public static boolean isBluPlugPowersaverModeActive() {
        return Utils.readFile(HOTPLUG_BLU_PLUG_POWERSAVER_MODE).equals("Y");
    }

    public static boolean hasBluPlugPowersaverMode() {
        return Utils.existFile(HOTPLUG_BLU_PLUG_POWERSAVER_MODE);
    }

    public static void activateBluPlug(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", HOTPLUG_BLU_PLUG_ENABLE, Control.CommandType.GENERIC, context);
        if (active) togglehotplugs("BluPlug", context);
    }

    public static boolean isBluPlugActive() {
        return Utils.readFile(HOTPLUG_BLU_PLUG_ENABLE).equals("1");
    }

    public static boolean hasBluPlugEnable() {
        return Utils.existFile(HOTPLUG_BLU_PLUG_ENABLE);
    }

    public static boolean hasBluPlug() {
        return Utils.existFile(HOTPLUG_BLU_PLUG);
    }

    public static void setIntelliPlugFShift(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_INTELLI_PLUG_5_FSHIFT, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugFShift() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_INTELLI_PLUG_5_FSHIFT));
    }

    public static boolean hasIntelliPlugFShift() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_FSHIFT);
    }

    public static void setIntelliPlugDownLockDuration(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_INTELLI_PLUG_5_DOWN_LOCK_DURATION, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugDownLockDuration() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_INTELLI_PLUG_5_DOWN_LOCK_DURATION));
    }

    public static boolean hasIntelliPlugDownLockDuration() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_DOWN_LOCK_DURATION);
    }

    public static void setIntelliPlugBoostLockDuration(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_INTELLI_PLUG_5_BOOST_LOCK_DURATION, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugBoostLockDuration() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_INTELLI_PLUG_5_BOOST_LOCK_DURATION));
    }

    public static boolean hasIntelliPlugBoostLockDuration() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_BOOST_LOCK_DURATION);
    }

    public static void setIntelliPlugDeferSampling(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_INTELLI_PLUG_5_DEFER_SAMPLING, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugDeferSampling() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_INTELLI_PLUG_5_DEFER_SAMPLING));
    }

    public static boolean hasIntelliPlugDeferSampling() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_DEFER_SAMPLING);
    }

    public static void setIntelliPlugSuspendDeferTime(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_INTELLI_PLUG_5_SUSPEND_DEFER_TIME, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugSuspendDeferTime() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_INTELLI_PLUG_5_SUSPEND_DEFER_TIME));
    }

    public static boolean hasIntelliPlugSuspendDeferTime() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_SUSPEND_DEFER_TIME);
    }

    public static void setIntelliPlugMaxCpusOnlineSusp(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_INTELLI_PLUG_5_MAX_CPUS_ONLINE_SUSP, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugMaxCpusOnlineSusp() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_INTELLI_PLUG_5_MAX_CPUS_ONLINE_SUSP));
    }

    public static boolean hasIntelliPlugMaxCpusOnlineSusp() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_MAX_CPUS_ONLINE_SUSP);
    }

    public static void setIntelliPlugMaxCpusOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_INTELLI_PLUG_5_MAX_CPUS_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugMaxCpusOnline() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_INTELLI_PLUG_5_MAX_CPUS_ONLINE));
    }

    public static boolean hasIntelliPlugMaxCpusOnline() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_MAX_CPUS_ONLINE);
    }

    public static void setIntelliPlugMinCpusOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_INTELLI_PLUG_5_MIN_CPUS_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugMinCpusOnline() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_INTELLI_PLUG_5_MIN_CPUS_ONLINE));
    }

    public static boolean hasIntelliPlugMinCpusOnline() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_MIN_CPUS_ONLINE);
    }

    public static void setIntelliPlugCpusBoosted(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_INTELLI_PLUG_5_CPUS_BOOSTED, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugCpusBoosted() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_INTELLI_PLUG_5_CPUS_BOOSTED));
    }

    public static boolean hasIntelliPlugCpusBoosted() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_CPUS_BOOSTED);
    }

    public static void activateIntelliPlugSuspend(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", HOTPLUG_INTELLI_PLUG_5_SUSPEND, Control.CommandType.GENERIC, context);
    }

    public static boolean isIntelliPlugSuspendActive() {
        return Utils.readFile(HOTPLUG_INTELLI_PLUG_5_SUSPEND).equals("1");
    }

    public static boolean hasIntelliPlugSuspend() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_SUSPEND);
    }

    public static void activateIntelliPlugDebug(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", HOTPLUG_INTELLI_PLUG_5_DEBUG, Control.CommandType.GENERIC, context);
    }

    public static boolean isIntelliPlugDebugActive() {
        return Utils.readFile(HOTPLUG_INTELLI_PLUG_5_DEBUG).equals("1");
    }

    public static boolean hasIntelliPlugDebug() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_DEBUG);
    }

    public static void setIntelliPlugScreenOffMax(int position, Context context) {
        String file = HOTPLUG_INTELLI_PLUG_SCREEN_OFF_MAX;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_SCREEN_OFF_MAX;

        String command = position == 0 ? "4294967295" : String.valueOf(CPU.getFreqs().get(position - 1));
        Control.runCommand(command, file, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugScreenOffMax() {
        String file = HOTPLUG_INTELLI_PLUG_SCREEN_OFF_MAX;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_SCREEN_OFF_MAX;

        String value = Utils.readFile(file);
        if (value.equals("4294967295") // 4294967295 is 32-Bit max unsigned integer
            ||
            value.equals("0")) return 0;
        return CPU.getFreqs().indexOf(Utils.stringToInt(value)) + 1;
    }

    public static boolean hasIntelliPlugScreenOffMax() {
        if (Utils.existFile(CPU_MAX_SCREEN_OFF_FREQ)) return false;
        String file = HOTPLUG_INTELLI_PLUG_SCREEN_OFF_MAX;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_SCREEN_OFF_MAX;
        return Utils.existFile(file);
    }

    public static void setIntelliPlugThresold(int value, Context context) {
        String file = HOTPLUG_INTELLI_PLUG_THRESHOLD;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_THRESHOLD;
        Control.runCommand(String.valueOf(value), file, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugThresold() {
        String file = HOTPLUG_INTELLI_PLUG_THRESHOLD;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_THRESHOLD;
        return Utils.stringToInt(Utils.readFile(file));
    }

    public static boolean hasIntelliPlugThresold() {
        String file = HOTPLUG_INTELLI_PLUG_THRESHOLD;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_THRESHOLD;
        return Utils.existFile(file);
    }

    public static void setIntelliPlugHysteresis(int value, Context context) {
        String file = HOTPLUG_INTELLI_PLUG_HYSTERESIS;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_HYSTERESIS;
        Control.runCommand(String.valueOf(value), file, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugHysteresis() {
        String file = HOTPLUG_INTELLI_PLUG_HYSTERESIS;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_HYSTERESIS;
        return Utils.stringToInt(Utils.readFile(file));
    }

    public static boolean hasIntelliPlugHysteresis() {
        String file = HOTPLUG_INTELLI_PLUG_HYSTERESIS;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_HYSTERESIS;
        return Utils.existFile(file);
    }

    public static void activateIntelliPlugTouchBoost(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", HOTPLUG_INTELLI_PLUG_TOUCH_BOOST, Control.CommandType.GENERIC, context);
    }

    public static boolean isIntelliPlugTouchBoostActive() {
        return Utils.readFile(HOTPLUG_INTELLI_PLUG_TOUCH_BOOST).equals("1");
    }

    public static boolean hasIntelliPlugTouchBoost() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_TOUCH_BOOST);
    }

    public static void activateIntelliPlugEco(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", HOTPLUG_INTELLI_PLUG_ECO, Control.CommandType.GENERIC, context);
    }

    public static boolean isIntelliPlugEcoActive() {
        return Utils.readFile(HOTPLUG_INTELLI_PLUG_ECO).equals("1");
    }

    public static boolean hasIntelliPlugEco() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_ECO);
    }

    public static void setIntelliPlugProfile(int value, Context context) {
        String file = HOTPLUG_INTELLI_PLUG_PROFILE;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_PROFILE;
        Control.runCommand(String.valueOf(value), file, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugProfile() {
        String file = HOTPLUG_INTELLI_PLUG_PROFILE;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_PROFILE;
        return Utils.stringToInt(Utils.readFile(file));
    }

    public static List < String > getIntelliPlugProfileMenu(Context context) {
        List < String > list = new ArrayList < > ();
        if (TYPE == INTELLIPLUG_TYPE.INSANITY)
            list.add(context.getString(R.string.insanity));
        list.add(context.getString(R.string.balanced));
        list.add(context.getString(R.string.performance));
        list.add(context.getString(R.string.conservative));
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) {
            list.add(context.getString(R.string.disabled));
            list.add(context.getString(R.string.tri));
            list.add(context.getString(R.string.eco));
            list.add(context.getString(R.string.strict));
        } else {
            if (TYPE == INTELLIPLUG_TYPE.INSANITY)
                list.add(context.getString(R.string.eco_insanity));
            list.add(context.getString(R.string.eco_performance));
            list.add(context.getString(R.string.eco_conservative));
        }
        return list;
    }

    public static boolean hasIntelliPlugProfile() {
        String file = HOTPLUG_INTELLI_PLUG_PROFILE;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_PROFILE;
        return Utils.existFile(file);
    }

    public static void activateIntelliPlug(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", Utils.getsysfspath(INTELLIPLUG_ARRAY) + HOTPLUG_INTELLI_PLUG_ENABLE, Control.CommandType.GENERIC, context);
        if (active) togglehotplugs("IntelliPlug", context);
    }

    public static boolean isIntelliPlugActive() {
        return Utils.readFile(Utils.getsysfspath(INTELLIPLUG_ARRAY) + HOTPLUG_INTELLI_PLUG_ENABLE).equals("1");
    }

    public static boolean hasIntelliPlugEnable() {
        return Utils.existFile(Utils.getsysfspath(INTELLIPLUG_ARRAY) + HOTPLUG_INTELLI_PLUG_ENABLE);
    }

    public static boolean hasIntelliPlug() {
        if (Utils.existFile(HOTPLUG_INTELLI_PLUG)) TYPE = INTELLIPLUG_TYPE.INTELLIPLUG;
        else if (Utils.existFile(HOTPLUG_INTELLI_PLUG_5)) TYPE = INTELLIPLUG_TYPE.INTELLIPLUG_5;
        if (Utils.existFile(HOTPLUG_INTELLI_PLUG_INSANITY)) TYPE = INTELLIPLUG_TYPE.INSANITY;
        return TYPE != null;
    }

    public static void activateMpdecision(boolean active, Context context) {
        if (active) {
            Control.startService(HOTPLUG_MPDEC, context);
            togglehotplugs("MPDecision", context);
        } else {
            Control.stopService(HOTPLUG_MPDEC, context);
            CPU.onlineAllCores(context);
        }
    }

    public static boolean isMpdecisionActive() {
        // Doing this here instead of the utils.ispropactive function because it can show either of these statuses
        try {
            String result = RootUtils.runCommand("getprop | grep " + HOTPLUG_MPDEC).split("]: ")[1];
            if (result.equals("[running]") || result.equals("[restarting]")) {
                return true;
            }
        } catch (Exception ignored) {
            return false;
        }
        return false;
    }
    public static boolean hasMpdecision() {
        return Utils.hasProp(HOTPLUG_MPDEC);
    }

    public static boolean hasCpuHotplug() {
        if (hasMpdecision()) return true;
        for (String[] array: CPU_HOTPLUG_ARRAY)
            for (String file: array)
                if (Utils.existFile(file)) return true;
        return false;
    }

    public static void togglehotplugs(String activehotplug, Context context) {
        if (CPUHotplug.isMpdecisionActive() && !activehotplug.equals("MPDecision")) {
            if (isMpdecisionActive()) {
                Control.stopService(HOTPLUG_MPDEC, context);
                CPU.onlineAllCores(context);
            }
        }
        if (CPUHotplug.isAutoSmpActive() && hasAutoSmpEnable() && !activehotplug.equals("AutoSMP")) Control.runCommand("N", HOTPLUG_AUTOSMP_ENABLE, Control.CommandType.GENERIC, context);
        if (CPUHotplug.isThunderPlugActive() && hasThunderPlugEnable() && !activehotplug.equals("ThunderPlug")) Control.runCommand("0", HOTPLUG_THUNDER_PLUG_ENABLE, Control.CommandType.GENERIC, context);
        if (CPUHotplug.isAlucardHotplugActive() && hasAlucardHotplugEnable() && !activehotplug.equals("AlucardHotplug")) Control.runCommand("0", ALUCARD_HOTPLUG_ENABLE, Control.CommandType.GENERIC, context);
        if (CPUHotplug.isMakoHotplugActive() && hasMakoHotplugEnable() && !activehotplug.equals("MakoHotPlug")) Control.runCommand("0", MAKO_HOTPLUG_ENABLED, Control.CommandType.GENERIC, context);
        if (CPUHotplug.isBluPlugActive() && CPUHotplug.hasBluPlugEnable() && !activehotplug.equals("BluPlug")) Control.runCommand("0", HOTPLUG_BLU_PLUG_ENABLE, Control.CommandType.GENERIC, context);
        if (CPUHotplug.isIntelliPlugActive() && hasIntelliPlugEnable() && !activehotplug.equals("IntelliPlug")) {
            if (Utils.existFile(HOTPLUG_INTELLI_PLUG_ENABLE)) Control.runCommand("0", HOTPLUG_INTELLI_PLUG_ENABLE, Control.CommandType.GENERIC, context);
            if (Utils.existFile(HOTPLUG_INTELLI_PLUG_5_ENABLE)) Control.runCommand("0", HOTPLUG_INTELLI_PLUG_5_ENABLE, Control.CommandType.GENERIC, context);
        }
        if (CPUHotplug.isZenDecisionActive() && hasZenDecisionEnable() && !activehotplug.equals("ZenDecision")) Control.runCommand("0", HOTPLUG_ZEN_DECISION_ENABLE, Control.CommandType.GENERIC, context);
        if (CPUHotplug.isMSMSleeperActive() && hasMSMSleeperEnable() && !activehotplug.equals("MSM_Sleeper")) Control.runCommand("0", MSM_SLEEPER_ENABLE, Control.CommandType.GENERIC, context);
        if (CPUHotplug.isMBHotplugActive() && hasMBHotplugEnable() && !activehotplug.equals("MBHotplug")) Control.runCommand("0", Utils.getsysfspath(MB_HOTPLUG_ARRAY) + "/" + MB_ENABLED, Control.CommandType.GENERIC, context);
        if (CPUHotplug.isMsmHotplugActive() && hasMsmHotplugEnable() && !activehotplug.equals("MSMHotPlug")) Control.runCommand("0", MSM_HOTPLUG_ENABLE_FILE, Control.CommandType.GENERIC, context);
        if (CPUHotplug.isStateHelperActive() && hasStateHelperEnable() && !activehotplug.equals("State_Helper")) Control.runCommand("0", STATE_HELPER_ENABLE, Control.CommandType.GENERIC, context);
        if (CPUHotplug.isLazyPlugActive() && hasLazyPlugEnable() && !activehotplug.equals("LazyPlug")) Control.runCommand("0", HOTPLUG_LAZYPLUG_TOUCH_BOOST_ACTIVE, Control.CommandType.GENERIC, context);
        if (CPUHotplug.isDynPlugActive() && hasDynPlugEnable() && !activehotplug.equals("DynPlug")) Control.runCommand("N", HOTPLUG_DYN_PLUG_ENABLE, Control.CommandType.GENERIC, context);
        if (CPUHotplug.isAutoHotplugActive() && hasAutoHotplugEnable() && !activehotplug.equals("AutoHotplug")) Control.runCommand("N", HOTPLUG_AUTO_HOTPLUG_ENABLE, Control.CommandType.GENERIC, context);
        if (CPUHotplug.isHimaActive() && hasHimaEnable() && !activehotplug.equals("Hima")) Control.runCommand("N", HIMA_HOTPLUG_ENABLE, Control.CommandType.GENERIC, context);
    }

    public static boolean hasMSMSleeper() {
        return Utils.existFile(MSM_SLEEPER);
    }

    public static boolean hasMSMSleeperEnable() {
        return Utils.existFile(MSM_SLEEPER_ENABLE);
    }

    public static void activateMSMSleeper(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", MSM_SLEEPER_ENABLE, Control.CommandType.GENERIC, context);
        if (active) togglehotplugs("MSM_Sleeper", context);
    }

    public static boolean isMSMSleeperActive() {
        return Utils.readFile(MSM_SLEEPER_ENABLE).equals("1");
    }

    public static void setMSMSleeperMaxOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), MSM_SLEEPER_MAX_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getMSMSleeperMaxOnline() {
        return Utils.stringToInt(Utils.readFile(MSM_SLEEPER_MAX_ONLINE));
    }

    public static boolean hasMSMSleeperMaxOnline() {
        return Utils.existFile(MSM_SLEEPER_MAX_ONLINE);
    }

    public static void setMSMSleeperSuspendMaxOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), MSM_SLEEPER_SUSPEND_MAX_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getMSMSleeperSuspendMaxOnline() {
        return Utils.stringToInt(Utils.readFile(MSM_SLEEPER_SUSPEND_MAX_ONLINE));
    }

    public static boolean hasMSMSleeperSuspendMaxOnline() {
        return Utils.existFile(MSM_SLEEPER_SUSPEND_MAX_ONLINE);
    }

    public static void setMSMSleeperUpThresh(int value, Context context) {
        Control.runCommand(String.valueOf(value), MSM_SLEEPER_UP_THRESHOLD, Control.CommandType.GENERIC, context);
    }

    public static int getMSMSleeperUpThresh() {
        return Utils.stringToInt(Utils.readFile(MSM_SLEEPER_UP_THRESHOLD));
    }

    public static boolean hasMSMSleeperUpThresh() {
        return Utils.existFile(MSM_SLEEPER_UP_THRESHOLD);
    }

    public static void setMSMSleeperUpCountMax(int value, Context context) {
        Control.runCommand(String.valueOf(value), MSM_SLEEPER_UP_COUNT_MAX, Control.CommandType.GENERIC, context);
    }

    public static int getMSMSleeperUpCountMax() {
        return Utils.stringToInt(Utils.readFile(MSM_SLEEPER_UP_COUNT_MAX));
    }

    public static boolean hasMSMSleeperUpCountMax() {
        return Utils.existFile(MSM_SLEEPER_UP_COUNT_MAX);
    }

    public static void setMSMSleeperDownCountMax(int value, Context context) {
        Control.runCommand(String.valueOf(value), MSM_SLEEPER_DOWN_COUNT_MAX, Control.CommandType.GENERIC, context);
    }

    public static int getMSMSleeperDownCountMax() {
        return Utils.stringToInt(Utils.readFile(MSM_SLEEPER_DOWN_COUNT_MAX));
    }

    public static boolean hasMSMSleeperDownCountMax() {
        return Utils.existFile(MSM_SLEEPER_DOWN_COUNT_MAX);
    }

    public static boolean hasStateHelper() {
        return Utils.existFile(STATE_HELPER);
    }

    public static boolean hasStateHelperEnable() {
        return Utils.existFile(STATE_HELPER_ENABLE);
    }

    public static boolean isStateHelperActive() {
        return Utils.readFile(STATE_HELPER_ENABLE).equals("1");
    }

    public static void activateStateHelper(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", STATE_HELPER_ENABLE, Control.CommandType.GENERIC, context);
        if (active) togglehotplugs("State_Helper", context);
    }

    public static void setStateHelperMaxCpusOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), STATE_HELPER_MAX_CPUS_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getStateHelperMaxCpusOnline() {
        return Utils.stringToInt(Utils.readFile(STATE_HELPER_MAX_CPUS_ONLINE));
    }

    public static boolean hasStateHelperMaxCpusOnline() {
        return Utils.existFile(STATE_HELPER_MAX_CPUS_ONLINE);
    }

    public static void setStateHelperMaxCpusSuspend(int value, Context context) {
        Control.runCommand(String.valueOf(value), STATE_HELPER_MAX_CPUS_SUSPEND, Control.CommandType.GENERIC, context);
    }

    public static int getStateHelperMaxCpusSuspend() {
        return Utils.stringToInt(Utils.readFile(STATE_HELPER_MAX_CPUS_SUSPEND));
    }

    public static boolean hasStateHelperMaxCpusSuspend() {
        return Utils.existFile(STATE_HELPER_MAX_CPUS_SUSPEND);
    }

    public static boolean hasStateHelperBattLevelEco() {
        return Utils.existFile(STATE_HELPER_BATT_LEVEL_ECO);
    }

    public static int getStateHelperBattLevelEco() {
        return Utils.stringToInt(Utils.readFile(STATE_HELPER_BATT_LEVEL_ECO));
    }

    public static void setStateHelperBattLevelEco(int value, Context context) {
        Control.runCommand(String.valueOf(value), STATE_HELPER_BATT_LEVEL_ECO, Control.CommandType.GENERIC, context);
    }

    public static boolean hasStateHelperBattLevelCri() {
        return Utils.existFile(STATE_HELPER_BATT_LEVEL_CRI);
    }

    public static int getStateHelperBattLevelCri() {
        return Utils.stringToInt(Utils.readFile(STATE_HELPER_BATT_LEVEL_CRI));
    }

    public static void setStateHelperBattLevelCri(int value, Context context) {
        Control.runCommand(String.valueOf(value), STATE_HELPER_BATT_LEVEL_CRI, Control.CommandType.GENERIC, context);
    }

    public static void setStateHelperMaxCpusEco(int value, Context context) {
        Control.runCommand(String.valueOf(value), STATE_HELPER_MAX_CPU_ECO, Control.CommandType.GENERIC, context);
    }

    public static int getStateHelperMaxCpusEco() {
        return Utils.stringToInt(Utils.readFile(STATE_HELPER_MAX_CPU_ECO));
    }

    public static boolean hasStateHelperMaxCpusEco() {
        return Utils.existFile(STATE_HELPER_MAX_CPU_ECO);
    }

    public static void setStateHelperMaxCpusCri(int value, Context context) {
        Control.runCommand(String.valueOf(value), STATE_HELPER_MAX_CPU_CRI, Control.CommandType.GENERIC, context);
    }

    public static int getStateHelperMaxCpusCri() {
        return Utils.stringToInt(Utils.readFile(STATE_HELPER_MAX_CPU_CRI));
    }

    public static boolean hasStateHelperMaxCpusCri() {
        return Utils.existFile(STATE_HELPER_MAX_CPU_CRI);
    }

    public static void activateStateHelperDebug(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", STATE_HELPER_DEBUG_MASK, Control.CommandType.GENERIC, context);
    }

    public static boolean isStateHelperPlugDebugActive() {
        return Utils.readFile(STATE_HELPER_DEBUG_MASK).equals("1");
    }

    public static boolean hasStateHelperDebug() {
        return Utils.existFile(STATE_HELPER_DEBUG_MASK);
    }

    public static void activateStateHelperDynamic(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", STATE_HELPER_DYNAMIC, Control.CommandType.GENERIC, context);
    }

    public static boolean isStateHelperPlugDynamicActive() {
        return Utils.readFile(STATE_HELPER_DYNAMIC).equals("1");
    }

    public static boolean hasStateHelperDynamic() {
        return Utils.existFile(STATE_HELPER_DYNAMIC);
    }

    public static void setStateHelperDynamicInterval(int value, Context context) {
        Control.runCommand(String.valueOf(value), STATE_HELPER_DYNAMIC_INTERVAL, Control.CommandType.GENERIC, context);
    }

    public static int getStateHelperDynamicInterval() {
        return Utils.stringToInt(Utils.readFile(STATE_HELPER_DYNAMIC_INTERVAL));
    }

    public static boolean hasStateHelperDynamicInterval() {
        return Utils.existFile(STATE_HELPER_DYNAMIC_INTERVAL);
    }

    public static void setStateHelperMinOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), STATE_HELPER_MIN_CPU_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getStateHelperMinOnline() {
        return Utils.stringToInt(Utils.readFile(STATE_HELPER_MIN_CPU_ONLINE));
    }

    public static boolean hasStateHelperMinOnline() {
        return Utils.existFile(STATE_HELPER_MIN_CPU_ONLINE);
    }

    public static void setStateHelperUpThreshold(int value, Context context) {
        Control.runCommand(String.valueOf(value), STATE_HELPER_DYNAMIC_UP_THRESHOLD, Control.CommandType.GENERIC, context);
    }

    public static int getStateHelperUpThreshold() {
        return Utils.stringToInt(Utils.readFile(STATE_HELPER_DYNAMIC_UP_THRESHOLD));
    }

    public static boolean hasStateHelperUpThreshold() {
        return Utils.existFile(STATE_HELPER_DYNAMIC_UP_THRESHOLD);
    }

    public static void setStateHelperDownThreshold(int value, Context context) {
        Control.runCommand(String.valueOf(value), STATE_HELPER_DYNAMIC_DOWN_THRESHOLD, Control.CommandType.GENERIC, context);
    }

    public static int getStateHelperDownThreshold() {
        return Utils.stringToInt(Utils.readFile(STATE_HELPER_DYNAMIC_DOWN_THRESHOLD));
    }

    public static boolean hasStateHelperDownThreshold() {
        return Utils.existFile(STATE_HELPER_DYNAMIC_DOWN_THRESHOLD);
    }

    public static void activatebch(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", BCH, Control.CommandType.GENERIC, context);
    }

    public static boolean isbchActive() {
        return Utils.readFile(BCH).equals("1");
    }
    public static boolean hasbch() {
        return Utils.existFile(BCH);
    }

    public static boolean hasmsmperformance() {
        return Utils.existFile(MSMPERFORMANCE);
    }

    public static int getmsmperformance() {
        return Utils.stringToInt(Utils.readFile(MSMPERFORMANCE));
    }

    public static void setmsmperformance(int value, Context context) {
        Control.runCommand(Integer.toString(value), MSMPERFORMANCE, Control.CommandType.GENERIC, context);
    }

    public static boolean hasHima() {
        return Utils.existFile(HIMA_HOTPLUG);
    }

    public static boolean hasHimaEnable() {
        return Utils.existFile(HIMA_HOTPLUG_ENABLE);
    }

    public static boolean isHimaActive() {
        return Utils.readFile(HIMA_HOTPLUG_ENABLE).equals("1");
    }

    public static void activateHima(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", HIMA_HOTPLUG_ENABLE, Control.CommandType.GENERIC, context);
        if (active) togglehotplugs("Hima", context);
    }

    public static boolean hasHimaProfile() {
        return Utils.existFile(HIMA_HOTPLUG_PROFILE);
    }

    public static void setHimaProfile(int value, Context context) {
        Control.runCommand(String.valueOf(value), HIMA_HOTPLUG_PROFILE, Control.CommandType.GENERIC, context);
    }

    public static int getHimaProfile() {
        return Utils.stringToInt(Utils.readFile(HIMA_HOTPLUG_PROFILE));
    }

    public static List < String > getHimaProfileMenu(Context context) {
        List < String > list = new ArrayList < > ();
        list.add(context.getString(R.string.balanced));
        list.add(context.getString(R.string.powersave));
        list.add(context.getString(R.string.performance));
        return list;
    }

    public static boolean hasHimaMinOnline() {
        return Utils.existFile(HIMA_HOTPLUG_MIN_CPUS_ONLINE);
    }

    public static int getHimaMinOnline() {
        return Utils.stringToInt(Utils.readFile(HIMA_HOTPLUG_MIN_CPUS_ONLINE));
    }

    public static void setHimaMinOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), HIMA_HOTPLUG_MIN_CPUS_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static boolean hasHimaMaxOnline() {
        return Utils.existFile(HIMA_HOTPLUG_MAX_CPUS_ONLINE);
    }

    public static int getHimaMaxOnline() {
        return Utils.stringToInt(Utils.readFile(HIMA_HOTPLUG_MAX_CPUS_ONLINE));
    }

    public static void setHimaMaxOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), HIMA_HOTPLUG_MAX_CPUS_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static boolean hasHimaSamplingRate() {
        return Utils.existFile(HIMA_HOTPLUG_DEF_SAMPLING_MS);
    }

    public static int getHimaSamplingRate() {
        return Utils.stringToInt(Utils.readFile(HIMA_HOTPLUG_DEF_SAMPLING_MS));
    }

    public static void setHimaSamplingRate(int value, Context context) {
        Control.runCommand(String.valueOf(value), HIMA_HOTPLUG_DEF_SAMPLING_MS, Control.CommandType.GENERIC, context);
    }

}
