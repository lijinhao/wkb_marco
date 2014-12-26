package loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

import contentprovider.ProviderUtils;
import entity.CourserItem;
import entity.HomeCategory;
import util.DataUtils;

/**
 * Created by lijinhao on 2014/11/27.
 */
public class HomeCategoryLoader extends AsyncTaskLoader<List<HomeCategory>> {

    private Context context;
    private List<HomeCategory> mData;

    public HomeCategoryLoader(Context context) {
        super(context);
        this.context = context;
    }

    /*Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Log.i("111111", "22222222222222222222222222");
                    break;

                default:
            }
        }
    };*/
    @Override
    public List<HomeCategory> loadInBackground() {

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(30000);
                    handler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/

        ProviderUtils utils = new ProviderUtils(this.context);
        //if local contain data..
        if(utils.getCount()>0){
            return loadDataFromLocal();
        }else{//else remote load..
            return DataUtils.loadDate(this.context);
        }
    }

    //Local getData
    private List<HomeCategory> loadDataFromLocal(){
        List<HomeCategory> homeCategoryList = new ArrayList<HomeCategory>();

        ProviderUtils utils = new ProviderUtils(this.context);

        HomeCategory category1 = new HomeCategory();
        category1.setType("0");
        List<CourserItem> courserItems1 = utils.showCourseItems("0");
        category1.setVos(courserItems1);
        category1.setName(courserItems1.size()>0?courserItems1.get(0).getTypeName():"");
        homeCategoryList.add(category1);

        HomeCategory category2 = new HomeCategory();
        category2.setType("1");
        List<CourserItem> courserItems2 = utils.showCourseItems("1");
        category2.setVos(courserItems2);
        category2.setName(courserItems2.size()>0?courserItems2.get(0).getTypeName():"");
        homeCategoryList.add(category2);

        HomeCategory category3 = new HomeCategory();
        category3.setType("2");
        List<CourserItem> courserItems3 = utils.showCourseItems("2");
        category3.setVos(courserItems3);
        category3.setName(courserItems3.size()>0?courserItems3.get(0).getTypeName():"");
        homeCategoryList.add(category3);

        HomeCategory category4 = new HomeCategory();
        category4.setType("3");
        List<CourserItem> courserItems4 = utils.showCourseItems("3");
        category4.setVos(courserItems4);
        category4.setName(courserItems4.size()>0?courserItems4.get(0).getTypeName():"");
        homeCategoryList.add(category4);

        HomeCategory category5 = new HomeCategory();
        category5.setType("4");
        List<CourserItem> courserItems5 = utils.showCourseItems("4");
        category5.setVos(courserItems5);
        category5.setName(courserItems5.size()>0?courserItems5.get(0).getTypeName():"");
        homeCategoryList.add(category5);

        return homeCategoryList;
    }

    /**
     * Called when there is new data to deliver to the client.  The
     * super class will take care of delivering it; the implementation
     * here just adds a little more logic.
     */
    @Override
    public void deliverResult(List<HomeCategory> data) {
        if (isReset()) {
            // An async query came in while the loader is stopped.  We
            // don't need the result.
            if (data != null) {
                onReleaseResources(data);
            }
        }
        List<HomeCategory> oldData = mData;
        mData = data;
        if (isStarted()) {
            // If the Loader is currently started, we can immediately
            // deliver its results.
            super.deliverResult(data);
        }


        // At this point we can release the resources associated with
        // 'oldApps' if needed; now that the new result is delivered we
        // know that it is no longer in use.
        if (oldData != null) {
            onReleaseResources(oldData);
        }
    }


    /**
     * Handles a request to start the Loader.
     */
    @Override
    protected void onStartLoading() {
        if (mData != null) {
            // If we currently have a result available, deliver it
            // immediately.
            deliverResult(mData);
        }
        if (takeContentChanged() || mData == null) {
            // If the data has changed since the last time it was loaded
            // or is not currently available, start a load.
            forceLoad();
        }
    }

    /**
     * Handles a request to stop the Loader.
     */
    @Override
    protected void onStopLoading() {
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }


    /**
     * Handles a request to cancel a load.
     */
    @Override
    public void onCanceled(List<HomeCategory> apps) {
        super.onCanceled(apps);

        // At this point we can release the resources associated with 'apps'
        // if needed.
        onReleaseResources(apps);
    }


    /**
     * Handles a request to completely reset the Loader.
     */
    @Override
    protected void onReset() {
        super.onReset();


        // Ensure the loader is stopped
        onStopLoading();


        // At this point we can release the resources associated with 'apps'
        // if needed.
        if (mData != null) {
            onReleaseResources(mData);
            mData = null;
        }
    }

    /**
     * Helper function to take care of releasing resources associated
     * with an actively loaded data set.
     */
    protected void onReleaseResources(List<HomeCategory> apps) {
        // For a simple List<> there is nothing to do.  For something
        // like a Cursor, we would close it here.
    }
}
