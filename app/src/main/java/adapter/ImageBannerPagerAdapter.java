package adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import entity.CourserItem;
import wkb.apj.com.wkb_marco.R;
import wkb.apj.com.wkb_marco.VideoPlayerActivity;

/**
 * Created by lijinhao on 2014/11/26.
 */
public class ImageBannerPagerAdapter extends PagerAdapter {
    private Activity context;
    private List<CourserItem> topBannerData;
    private boolean isDownloadImageIn3G;
    int w = 0;//500
    int h = 0;//220
    private DisplayImageOptions options;

    public ImageBannerPagerAdapter(Activity context,List<CourserItem> imageList){
        this.context = context;
        this.topBannerData = imageList;

        options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.thumb)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
    }

    public void addData(List<CourserItem> topBannerData){
        topBannerData.clear();
        this.topBannerData.addAll(topBannerData);
    }

    public int getCount(){
        return topBannerData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        final CourserItem entity = topBannerData.get(position);
        View view = context.getLayoutInflater().inflate(R.layout.top_image_layout,null);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        String imgUrl = entity.getPicUrl();
        imageView.setTag(imgUrl);

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
        ImageLoader.getInstance().displayImage(imgUrl,imageView,options);
        //Picasso.with(context).load(imgUrl).fit().into(imageView);
        ((ViewPager) container).addView(view, 0);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, VideoPlayerActivity.class);
                intent.putExtra("url",entity.getContentId());
                intent.putExtra("title",entity.getTitle());
                context.startActivityForResult(intent,0);
            }
        });
        return view;
    }

    public void destroyItem(ViewGroup container,int position,Object object){
        ((ViewPager)container).removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view.equals(o);
    }
}
