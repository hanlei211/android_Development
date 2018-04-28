package com.rjyx.quickdevelopment.base;


import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.SeekBar;

import com.rjyx.quickdevelopment.R;
import com.rjyx.quickdevelopment.util.ColorPaletteUtils;
import com.rjyx.quickdevelopment.util.PreferenceUtils;
import com.rjyx.quickdevelopment.util.ThemeUtils;
import com.rjyx.quickdevelopment.util.UIUtils;

import java.util.ArrayList;

/**
 *
 *   实现换肤 基类
 */
public class BaseThemeActivity  extends BaseActivity {

    public PreferenceUtils sp;
    public ThemeUtils themeUtils;
    private boolean coloredNavBar;
    private boolean obscuredStatusBar;
    private boolean applyThemeImgAct;

    @Override
    public void setActivityPre() {
          sp = PreferenceUtils.getInstance(this.getApplicationContext());
          themeUtils = new ThemeUtils(this.getApplicationContext());

    }

    @Override
    public  void onResume(){
        super.onResume();
        updateTheme();
    }


    public void updateTheme() {
        themeUtils.updateTheme();
        coloredNavBar = sp.getBoolean(getString(R.string.preference_colored_nav_bar), false);
        obscuredStatusBar = sp.getBoolean(getString(R.string.preference_translucent_status_bar), true);
        applyThemeImgAct = sp.getBoolean(getString(R.string.preference_apply_theme_pager), true);
    }


    /**
     * 设置 底部导航栏
     *       颜色
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public  void  setNavBarColor(){
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP)
        {
            if(isNavigationBarColored())
                getWindow().setNavigationBarColor(getPrimaryColor());
             else
                 getWindow().setNavigationBarColor(UIUtils.getColor(R.color.md_black_1000));
        }
    }


    /**
     * 设置 状态栏 颜色
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void  setStatusBarColor(){
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP)
        {
            if(isTranslucentStatusBar())
            {
                getWindow().setStatusBarColor(ColorPaletteUtils.getObscuredColor(getPrimaryColor()));
            }else{
                getWindow().setStatusBarColor(getPrimaryColor());
            }
        }
    }


    protected void setScrollViewColor(ScrollView scr) {
        themeUtils.setScrollViewColor(scr);
    }

    public void setCursorDrawableColor(EditText editText, int color) {
        // TODO: 02/08/16 remove this
        ThemeUtils.setCursorDrawableColor(editText, color);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setRecentApp(String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BitmapDrawable drawable = ((BitmapDrawable) getDrawable(R.mipmap.ic_launcher));
            setTaskDescription(new ActivityManager.TaskDescription(text, drawable.getBitmap(), getPrimaryColor()));
        }
    }


    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }


    public boolean isNavigationBarColored() {
        return coloredNavBar;
    }

    public boolean isTranslucentStatusBar() {
        return obscuredStatusBar;
    }

    protected boolean isApplyThemeOnImgAct() {
        return applyThemeImgAct;
    }

    protected boolean isTransparencyZero() {
        return 255 - sp.getInt(getString(R.string.preference_transparency), 0) == 255;
    }

    public int getTransparency() {
        return 255 - sp.getInt(getString(R.string.preference_transparency), 0);
    }

    public void setBaseTheme(int baseTheme, boolean permanent) {
        themeUtils.setBaseTheme(baseTheme, permanent);
    }

    public void themeSeekBar(SeekBar bar) {
        themeUtils.themeSeekBar(bar);
    }


    public int getPrimaryColor() {
        return themeUtils.getPrimaryColor();
    }

    public int getAccentColor() {
        return themeUtils.getAccentColor();
    }

    public int getBaseTheme() {
        return themeUtils.getBaseTheme();
    }


    protected int getBackgroundColor() {
        return themeUtils.getBackgroundColor();
    }

    protected Drawable getPlaceHolder() {
        return themeUtils.getPlaceHolder();
    }

    protected int getInvertedBackgroundColor() {
        return themeUtils.getInvertedBackgroundColor();
    }

    public int getTextColor() {
        return themeUtils.getTextColor();
    }

    public int getSubTextColor() {
        return themeUtils.getSubTextColor();
    }

    public int getCardBackgroundColor() {
        return themeUtils.getCardBackgroundColor();
    }

    public int getIconColor() {
        return themeUtils.getIconColor();
    }

    protected int getDrawerBackground() {
        return themeUtils.getDrawerBackground();
    }

    public int getDialogStyle() {
        return themeUtils.getDialogStyle();
    }

    protected int getPopupToolbarStyle() {
        return themeUtils.getPopupToolbarStyle();
    }

    protected ArrayAdapter<String> getSpinnerAdapter(ArrayList<String> items) {
        return themeUtils.getSpinnerAdapter(items);
    }

    protected int getDefaultThemeToolbarColor3th() {
        return themeUtils.getDefaultThemeToolbarColor3th();
    }

    protected void updateRadioButtonColor(RadioButton radioButton) {
        themeUtils.updateRadioButtonColor(radioButton);
    }

    protected void setRadioTextButtonColor(RadioButton radioButton, int color) {
        themeUtils.setRadioTextButtonColor(radioButton, color);
    }

    public void updateSwitchColor(SwitchCompat sw, int color) {
        themeUtils.updateSwitchColor(sw, color);
    }

}
