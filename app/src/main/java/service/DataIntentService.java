package service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

import java.util.List;

import contentprovider.ProviderUtils;
import entity.CourserItem;
import entity.HomeCategory;
import util.DataUtils;

/**
 * Created by lijinhao on 2014/12/11.
 */
public class DataIntentService extends IntentService {


    public DataIntentService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        List<HomeCategory> items = DataUtils.loadDate(this);
        ProviderUtils utils = new ProviderUtils(this);
        for(HomeCategory category:items){
            for(CourserItem item:category.getVos()){
                utils.addCourseItem(item);
            }
        }
    }
}
