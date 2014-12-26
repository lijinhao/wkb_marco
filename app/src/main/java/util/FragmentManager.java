package util;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import fragment.CatalogFragment;
import fragment.HomeFragment;
import fragment.SettingFragment;
import fragment.VideoCommentsFragment;
import fragment.VideoCourseInfoFragment;
import fragment.VideoListFragment;
import fragment.VideoRelatedCourseFragment;

/**
 * Created by lijinhao on 2014/11/14.
 */
public class FragmentManager {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static Fragment getFragmentInstance(int sectionNumber) {
        if(sectionNumber == 0){
            HomeFragment fragment = new HomeFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }else if(sectionNumber == 1){
            CatalogFragment fragment = new CatalogFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }else{
            SettingFragment fragment = new SettingFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

    }

    public static Fragment getDetailActivityFragmentInstance(int sectionNumber){
        if(sectionNumber==1){
            VideoCourseInfoFragment fragment = new VideoCourseInfoFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ARG_SECTION_NUMBER,sectionNumber);
            fragment.setArguments(bundle);
            return fragment;
        }else if(sectionNumber == 2){
            VideoListFragment fragment = new VideoListFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ARG_SECTION_NUMBER,sectionNumber);
            fragment.setArguments(bundle);
            return fragment;
        }else if(sectionNumber == 3){
            VideoRelatedCourseFragment fragment = new VideoRelatedCourseFragment();
            Bundle bundle = new Bundle();
            fragment.setArguments(bundle);
            return fragment;
        }else{
            VideoCommentsFragment fragment = new VideoCommentsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ARG_SECTION_NUMBER,sectionNumber);
            fragment.setArguments(bundle);
            return fragment;
        }
    }
}
