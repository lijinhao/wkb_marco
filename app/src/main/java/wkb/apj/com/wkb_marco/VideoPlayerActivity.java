package wkb.apj.com.wkb_marco;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import entity.CourseDetailItem;
import entity.HomeCategory;
import fragment.PlayerFragment;
import task.IDataListener;
import task.OnVideoFragmentInterfaceListener;
import util.DataUtils;


public class VideoPlayerActivity extends ActionBarActivity implements OnVideoFragmentInterfaceListener{

    private String url;
    private String title;
    private ActionBar actionBar;
    public CourseDetailItem mData;
    TabPageIndicator tabs;
    ViewPager mViewPagers;
    SectionsPagerAdapter mSectionsPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_video_player);
        setContentView(R.layout.activity_vdetail);
        actionBar= this.getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);

        url = this.getIntent().getStringExtra("url");
        title = this.getIntent().getStringExtra("title");
        actionBar.setTitle(title);

        tabs = (TabPageIndicator)findViewById(R.id.v_detail_tabs);
        mViewPagers = (ViewPager)findViewById(R.id.v_detail_pager);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        //设置数据
        mViewPagers.setAdapter(mSectionsPagerAdapter);
        //设置页码
        tabs.setViewPager(mViewPagers);

        //初始化异步
        if (savedInstanceState == null) {
            LoadDetailTask task = new LoadDetailTask(this,url,new IDataListener() {
                @Override
                public void postData(List<HomeCategory> homeCategoryList) {

                }

                @Override
                public void postDetailData(CourseDetailItem data) {
                    mData = data;
                    //播放电影
                    PlayerFragment fragment = new PlayerFragment();
                    Bundle args = new Bundle();
                    args.putString("url", url);
                    args.putString("title", title);
                    fragment.setArguments(args);

                    //电影视图 v_detail_player
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.v_detail_player, fragment)
                            .commit();

                    //用来总计数量,如果不加上这4句代码，最下角就不会显示视图
                    pagerData.add("简介");
                    pagerData.add("目录");
                    pagerData.add("相关课程");
                    pagerData.add("跟帖");
                    //SectionsPager Adapter
                    mSectionsPagerAdapter.notifyDataSetChanged();
                    //Tab Adapter
                    tabs.notifyDataSetChanged();

                }
            });
            task.execute();



        }
    }

    public void setVideoDetail(CourseDetailItem data){
        this.mData = data;
        this.actionBar.setTitle(data.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.video_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<String> pagerData = new ArrayList<String>();

    @Override
    public void OnFragmentInterface(CourseDetailItem item) {
        this.mData = item;
    }

    @Override
    public CourseDetailItem onFragmentInteraction() {
        return mData;
    }

    //最下方的切换界面(简介，目录，相关推荐，跟帖)
    public class SectionsPagerAdapter extends FragmentPagerAdapter{

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return util.FragmentManager.getDetailActivityFragmentInstance(position + 1);
        }

        @Override
        public int getCount() {
            return pagerData.size();
        }

        @Override
        public CharSequence getPageTitle(int position){
            Locale l = Locale.getDefault();

            switch (position){
                case 0:
                    return "简介1";
                case 1:
                    return "目录2";
                case 2:
                    return "相关推荐3";
                case 3:
                    return "跟帖4";
            }
            return null;
        }
    }

    public class LoadDetailTask extends AsyncTask<Void,Void,String>{
        private String contentId;
        private Context context;
        private IDataListener listener;

        public LoadDetailTask(Context context,String id,IDataListener listener){
            super();
            contentId = id;
            this.context = context;
            this.listener = listener;
        }

        @Override
        protected String doInBackground(Void... params) {
            String jsonString = "";
            try{
                jsonString = HttpRequest.get(String.format(DataUtils.URL_DETAIL, contentId)).accept("application/json").body();
            }catch (Exception e){
                Log.e("DataUtils", "loadDate", e);
            }

            return jsonString;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String jsonString) {
            Gson gson = new Gson();
            try{
                CourseDetailItem data = gson.fromJson(jsonString, new TypeToken<CourseDetailItem>(){}.getType());
                listener.postDetailData(data);
            }catch (Exception e){
                listener.postDetailData(null);
            }
        }
    }

}
