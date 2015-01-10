package fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.List;

import entity.CourseDetailItem;
import entity.HomeCategory;
import task.IDataListener;
import task.OnVideoFragmentInterfaceListener;
import wkb.apj.com.wkb_marco.R;
import wkb.apj.com.wkb_marco.VideoPlayerActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public  class PlayerFragment extends Fragment implements IDataListener {

    private VideoView videoView;
    private String url;
    private String title;
    private OnVideoFragmentInterfaceListener mListener;
    private CourseDetailItem mData;

    public PlayerFragment() {
    }

    //当Fragment所在的Activity被启动完成后回调该方法
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            Log.i("onActivityCreated","--------------------------------onActivityCreated------------------------------");
            url = getArguments().getString("url");
            title = getArguments().getString("title");

            postDetailData(mData);
            //LoadDetailTask task =new LoadDetailTask(this.getActivity(),url,this);
            //task.execute();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_player, container, false);
        videoView = (VideoView)rootView.findViewById(R.id.videoView);
        return rootView;
    }

    @Override
    public void postData(List<HomeCategory> data) {

    }

    @Override
    public void postDetailData(CourseDetailItem data) {
        if(data!=null){
            VideoPlayerActivity activity = (VideoPlayerActivity)this.getActivity();
            activity.setVideoDetail(data);
            String mp4Url = data.getVideoList().get(0).getRepovideourlmp4();
            MediaController mc = new MediaController(this.getActivity());
            videoView.setMediaController(mc);
            Log.i("PlayerFragment","mp4Url:"+mp4Url);

            videoView.setVideoURI(Uri.parse(
                    mp4Url));
            videoView.requestFocus();
            videoView.start();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public CourseDetailItem getData() {
        if (mListener != null) {
            return mListener.onFragmentInteraction();
        }
        return null;
    }

    //当该Fragment被添加到Activity时被回调。该方法只会调用一次。
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try{
            Log.i("onAttach","------------------------------onAttach---------------------------------");
            mListener = (OnVideoFragmentInterfaceListener) activity;
            mData = mListener.onFragmentInteraction();
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+ "must implement OnFragmentInteractionListener");

        }
    }

    //将该Fragment从Activity中被删除，被替换完成时回调该方法，
    // onDestroy()方法后一定会回调onDetach()方法。该方法只会被调用一次。
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}