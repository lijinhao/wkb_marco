package fragment;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import entity.CourseDetailItem;
import entity.HomeCategory;
import task.IDataListener;
import util.DataUtils;
import wkb.apj.com.wkb_marco.R;
import wkb.apj.com.wkb_marco.VideoPlayerActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public  class PlayerFragment extends Fragment implements IDataListener {

    private VideoView videoView;
    private String url;
    private String title;

    public PlayerFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString("url");
            title = getArguments().getString("title");
            LoadDetailTask task =new LoadDetailTask(this.getActivity(),url,this);
            task.execute();
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

    public class LoadDetailTask extends AsyncTask<Void,Void,String> {

        private String contentId;
        private Context context;
        private IDataListener listener;

        public LoadDetailTask(Context context,String id,IDataListener listener) {
            super();
            contentId = id;
            this.context =context;
            this.listener = listener;
        }

        @Override
        protected String doInBackground(Void... params) {
            String jsonString = "";
            try{
                jsonString  =  HttpRequest.get(String.format(DataUtils.URL_DETAIL, contentId)).accept("application/json").body();
            }catch (Exception ex){
                Log.e("DataUtils", "loadDate", ex);
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
            }catch (Exception ex){
                listener.postDetailData(null);
            }
        }
    }
}