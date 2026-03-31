package io.hextree.attacksurface.receivers;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;
import io.hextree.attacksurface.MainActivity;
import io.hextree.attacksurface.R;
import io.hextree.attacksurface.SolvedPreferences;
import io.hextree.attacksurface.Utils;
import io.hextree.attacksurface.activities.Flag19Activity;

/* loaded from: classes.dex */
public class Flag19Widget extends AppWidgetProvider {
    @Override // android.appwidget.AppWidgetProvider
    public void onDisabled(Context context) {
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onEnabled(Context context) {
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int i) {
        new Intent(context, (Class<?>) MainActivity.class);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.flag_home_widget);
        remoteViews.setOnClickPendingIntent(R.id.btn_widget1, PendingIntent.getBroadcast(context, 0, refreshIntent(context), 201326592));
        SolvedPreferences.initialize(context);
        remoteViews.setTextViewText(R.id.btn_widget1, SolvedPreferences.countSolved() + " Flags");
        appWidgetManager.updateAppWidget(i, remoteViews);
    }

    @Override // android.appwidget.AppWidgetProvider, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Bundle bundleExtra;
        Log.i("Flag19Widget.onReceive", Utils.dumpIntent(context, intent));
        super.onReceive(context, intent);
        String action = intent.getAction();
        if (action == null || !action.contains("APPWIDGET_UPDATE") || (bundleExtra = intent.getBundleExtra("appWidgetOptions")) == null) {
            return;
        }
        int i = bundleExtra.getInt("appWidgetMaxHeight", -1);
        int i2 = bundleExtra.getInt("appWidgetMinHeight", -1);
        if (i == 1094795585 && i2 == 322376503) {
            success(context);
        }
    }

    private void success(Context context) {
        Intent intent = new Intent();
        intent.addFlags(268468224);
        intent.putExtra("appWidgetMaxHeight", 1094795585);
        intent.putExtra("appWidgetMinHeight", 322376503);
        intent.setComponent(new ComponentName(context, (Class<?>) Flag19Activity.class));
        Toast.makeText(context, "Success! Open the app to trigger the flag activity", 0).show();
        context.startActivity(intent);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        for (int i : iArr) {
            updateAppWidget(context, appWidgetManager, i);
        }
    }

    public static Intent refreshIntent(Context context) {
        int[] appWidgetIds = AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, (Class<?>) Flag19Widget.class));
        Intent intent = new Intent();
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        intent.putExtra("appWidgetIds", appWidgetIds);
        intent.addFlags(8);
        return intent;
    }
}