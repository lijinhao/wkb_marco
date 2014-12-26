package entity;

import java.util.List;

/**
 * Created by student on 2014/12/13.
 */
public class CourseDetailItem {

    private String ccUrl;
    private String description;
    private String director;
    private int hits;
    private String imgpath;
    private String include_virtual;
    private String largeimgurl;
    private long ltime;
    private String outurl;
    private int playcount;
    private String plid;
    private String school;
    private String source;
    private String subtitle;
    private String tags;
    private String title;
    private String type;
    private int updated_playcount;

    public String getCcUrl() {
        return ccUrl;
    }

    public void setCcUrl(String ccUrl) {
        this.ccUrl = ccUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getInclude_virtual() {
        return include_virtual;
    }

    public void setInclude_virtual(String include_virtual) {
        this.include_virtual = include_virtual;
    }

    public String getLargeimgurl() {
        return largeimgurl;
    }

    public void setLargeimgurl(String largeimgurl) {
        this.largeimgurl = largeimgurl;
    }

    public long getLtime() {
        return ltime;
    }

    public void setLtime(long ltime) {
        this.ltime = ltime;
    }

    public String getOuturl() {
        return outurl;
    }

    public void setOuturl(String outurl) {
        this.outurl = outurl;
    }

    public int getPlaycount() {
        return playcount;
    }

    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }

    public String getPlid() {
        return plid;
    }

    public void setPlid(String plid) {
        this.plid = plid;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUpdated_playcount() {
        return updated_playcount;
    }

    public void setUpdated_playcount(int updated_playcount) {
        this.updated_playcount = updated_playcount;
    }

    private List<VideoListItem> videoList;

    public List<VideoListItem> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<VideoListItem> videoList) {
        this.videoList = videoList;
    }
}
