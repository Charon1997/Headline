package com.nmid.headline.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.nmid.headline.R;
import com.nmid.headline.data.CourseDataSource;
import com.nmid.headline.data.CourseRepository;
import com.nmid.headline.data.bean.Course;

import java.util.List;

/**
 * Created by xwysu on 2017/5/13.
 */

public class CourseAppWidgetProvider extends AppWidgetProvider {

    int[] courseViews={R.id.course1,R.id.course2,R.id.course3,R.id.course4,R.id.course5,R.id.course6};
    int[] locationViews={R.id.location1,R.id.location2,R.id.location3,R.id.location4,R.id.location5,R.id.location6};
    CourseRepository repository=CourseRepository.getInstance();
    final String UPDATE_ACTION="android.appwidget.action.APPWIDGET_UPDATE";
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if(intent!=null&&intent.getAction().equals(UPDATE_ACTION)){
            updateView(context);
        }

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.appwidget_view);

        Intent intent = new Intent();
        intent.setClass(context, CourseAppWidgetProvider.class);
        intent.setAction(UPDATE_ACTION);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.fresh, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
    }

    private void updateView(Context context){
        RemoteViews remoteViews=new RemoteViews(context.getPackageName(),R.layout.appwidget_view);
        List<Course> courses=repository.getTodayCourse();
        for (Course c:courses
             ) {
            remoteViews.setTextViewText(courseViews[(c.getBeginLesson()-1)/2],c.getCourse());
            remoteViews.setTextViewText(locationViews[(c.getBeginLesson()-1)/2],c.getClassroom());
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName componentName = new ComponentName(context, CourseAppWidgetProvider.class);
            appWidgetManager.updateAppWidget(componentName, remoteViews);
        }
    }
    private void clearView(RemoteViews remoteViews){
        for (int i=0;i<7;i++){
            remoteViews.setTextViewText(courseViews[i],"");
            remoteViews.setTextViewText(locationViews[i],"");
        }
    }
}
