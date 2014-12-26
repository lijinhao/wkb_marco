package task;

import java.util.List;

import entity.CourseDetailItem;
import entity.HomeCategory;

/**
 * Created by lijinhao on 2014/12/9.
 */
public interface IDataListener {

    public void postData(List<HomeCategory> homeCategoryList);

    public void postDetailData(CourseDetailItem data);
}
