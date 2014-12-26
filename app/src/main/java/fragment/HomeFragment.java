package fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import adapter.HomeAdapter;
import adapter.ImageBannerPagerAdapter;
import contentprovider.ProviderUtils;
import entity.CourseDetailItem;
import entity.CourserItem;
import entity.HomeCategory;
import listener.OnFragmentInterfaceListener;
import loader.HomeCategoryLoader;
import task.IDataListener;
import task.LoadDataTask;
import view.ScrollGridView;
import wkb.apj.com.wkb_marco.R;


public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<HomeCategory>>, IDataListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInterfaceListener mListener;

    private RelativeLayout gllery_container;
    private RelativeLayout empty_view_for_you;
    private ViewPager mViewPager;
    private ProgressBar recommend_loading;
    private TextView recommend_no_data;
    private GridView grid_view_for_you;
    private TextView no_data_text_for_you;
    private ProgressBar  for_you_loading;
    private RelativeLayout recommend_empty_view;
    private TextView title_v1,title_v2,title_v3;
    private ScrollGridView grid_view_v1,grid_view_v2,grid_view_v3;

    private List<CourserItem> topData;
    private List<CourserItem> recommenData,recommendData1,recommendData2,recommendData3;
    private ImageBannerPagerAdapter topAdapter;
    private HomeAdapter recommendAdapter,recommendAdapter1,recommendAdapter2,recommendAdapter3;

    //pull-refresh
    private PullToRefreshScrollView scrollView;
    private Context context;
    private IDataListener dataListener;

    private String TAG = "HomeFragment";

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {


        super.onActivityCreated(savedInstanceState);
        if(getArguments() != null){
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mViewPager = (ViewPager)this.getView().findViewById(R.id.gallery);
        gllery_container=(RelativeLayout)this.getView().findViewById(R.id.gllery_container);
        recommend_loading = (ProgressBar)this.getView().findViewById(R.id.recommend_loading);
        recommend_no_data = (TextView)this.getView().findViewById(R.id.recommend_no_data);
        recommend_empty_view = (RelativeLayout)this.getView().findViewById(R.id.recommend_empty_view);

        //pull-refresh
        scrollView = (PullToRefreshScrollView)this.getView().findViewById(R.id.scrollView);
        context = this.getActivity();
        dataListener = this;

        grid_view_for_you = (GridView)this.getView().findViewById(R.id.grid_view_for_you);

        //标题
        title_v1  = (TextView)this.getView().findViewById(R.id.title_v1);
        //GridView
        grid_view_v1 = (ScrollGridView)this.getView().findViewById(R.id.grid_view_v1);
        title_v2  = (TextView)this.getView().findViewById(R.id.title_v2);
        grid_view_v2 = (ScrollGridView)this.getView().findViewById(R.id.grid_view_v2);
        title_v3  = (TextView)this.getView().findViewById(R.id.title_v3);
        grid_view_v3 = (ScrollGridView)this.getView().findViewById(R.id.grid_view_v3);
        grid_view_v1.setVisibility(View.GONE);
        title_v1.setVisibility(View.GONE);
        grid_view_v2.setVisibility(View.GONE);
        title_v2.setVisibility(View.GONE);
        grid_view_v3.setVisibility(View.GONE);
        title_v3.setVisibility(View.GONE);

        no_data_text_for_you = (TextView)this.getView().findViewById(R.id.no_data_text_for_you);
        for_you_loading = (ProgressBar)this.getView().findViewById(R.id.for_you_loading);
        empty_view_for_you =(RelativeLayout)this.getView().findViewById(R.id.empty_view_for_you);
        gllery_container.setVisibility(View.GONE);
        recommend_loading.setVisibility(View.VISIBLE);
        grid_view_for_you.setVisibility(View.GONE);
        empty_view_for_you.setVisibility(View.GONE);

        topData = new ArrayList<CourserItem>();
        topAdapter = new ImageBannerPagerAdapter(this.getActivity(),topData);
        mViewPager.setAdapter(topAdapter);

        recommenData = new ArrayList<CourserItem>();
        recommendAdapter = new HomeAdapter(this.getActivity(),this.recommenData);
        this.grid_view_for_you.setAdapter(recommendAdapter);

        recommendData1 = new ArrayList<CourserItem>();
        recommendAdapter1 = new HomeAdapter(this.getActivity(),recommendData1);
        this.grid_view_v1.setAdapter(recommendAdapter1);

        recommendData2 = new ArrayList<CourserItem>();
        recommendAdapter2 = new HomeAdapter(this.getActivity(),recommendData2);
        this.grid_view_v2.setAdapter(recommendAdapter2);

        recommendData3 = new ArrayList<CourserItem>();
        recommendAdapter3 = new HomeAdapter(this.getActivity(),recommendData3);
        this.grid_view_v3.setAdapter(recommendAdapter3);

        getLoaderManager().initLoader(0,null,this);

        scrollView.setOnRefreshListener(onRefreshListener);


    }

    //pull refresh execute
    PullToRefreshScrollView.OnRefreshListener onRefreshListener = new PullToRefreshBase.OnRefreshListener() {

        @Override
        public void onRefresh(PullToRefreshBase refreshView) {
            Log.i("HomeFragment","PullToRefreshScrollView");
            LoadDataTask loadTask = new LoadDataTask(context,"",dataListener);
            loadTask.execute();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_hm_main, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInterface(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInterfaceListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public Loader<List<HomeCategory>> onCreateLoader(int i, Bundle bundle) {
        return new HomeCategoryLoader(this.getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<HomeCategory>> listLoader, List<HomeCategory> homeCategories) {
        if(homeCategories != null && homeCategories.size() > 0){
            bindDataToUI(homeCategories);
        }else{
            recommend_loading.setVisibility(View.GONE);
            recommend_no_data.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<HomeCategory>> listLoader) {

    }

    @Override
    //IDataListener of method
    //pull refresh execute
    public void postData(List<HomeCategory> homeCategoryList) {

        if(homeCategoryList !=null && homeCategoryList.size()>0){
            scrollView.onRefreshComplete();

            bindDataToUI(homeCategoryList);

            //delete a table
            ProviderUtils utils = new ProviderUtils(this.getActivity());
            utils.removeCourseItem();

            for (HomeCategory category : homeCategoryList){
                for (CourserItem courserItem : category.getVos()){
                    courserItem.setTypeName((category.getName()));

                    //add to table
                    utils.addCourseItem(courserItem);
                }
            }


        }
    }

    @Override
    public void postDetailData(CourseDetailItem data) {

    }

    private void bindDataToUI(List<HomeCategory> homeCategories){

        gllery_container.setVisibility(View.VISIBLE);
        recommend_loading.setVisibility(View.GONE);
        grid_view_for_you.setVisibility(View.VISIBLE);
        empty_view_for_you.setVisibility(View.GONE);
        recommend_empty_view.setVisibility(View.GONE);

        recommenData.clear();
        topData.clear();
        recommendData1.clear();
        recommendData2.clear();
        recommendData3.clear();

        for (HomeCategory item:homeCategories){
            if(item.getType().equals("0")){
                //getVos是JSON对象
                topData.addAll(item.getVos());
            }else if(item.getType().equals("1")){
                recommendData1.addAll(item.getVos());
                title_v1.setText(item.getName());
            }else if(item.getType().equals("2")){
                recommendData2.addAll(item.getVos());
                title_v2.setText(item.getName());
            }else if(item.getType().equals("3")){
                recommendData3.addAll(item.getVos());
                title_v3.setText(item.getName());
            }else if(item.getType().equals("4")){
                recommenData.addAll(item.getVos());
            }
        }
        topAdapter.notifyDataSetChanged();
        recommendAdapter.notifyDataSetChanged();
        recommendAdapter1.notifyDataSetChanged();
        recommendAdapter2.notifyDataSetChanged();
        recommendAdapter3.notifyDataSetChanged();

        title_v1.setVisibility(View.VISIBLE);
        title_v2.setVisibility(View.VISIBLE);
        title_v3.setVisibility(View.VISIBLE);
        grid_view_v1.setVisibility(View.VISIBLE);
        grid_view_v2.setVisibility(View.VISIBLE);
        grid_view_v3.setVisibility(View.VISIBLE);
        gllery_container.setVisibility(View.VISIBLE);
        grid_view_for_you.setVisibility(View.VISIBLE);
    }
}
