package entity;

import java.util.List;

/**
 * Created by student on 2014/12/13.
 */
public class VideoListItem {

    private String adv;
    private String advlink;
    private String commentid;
    private String imgpath;
    private String mid;
    private int mlength;
    private int mp4size;
    private String repovideourl;
    private String repovideourlOrigin;
    private String repovideourlmp4Origin;
    private String repovideourlmp4;
    private List<SubVideoListItem> subList;

    public String getAdv() {
        return adv;
    }

    public void setAdv(String adv) {
        this.adv = adv;
    }

    public String getAdvlink() {
        return advlink;
    }

    public void setAdvlink(String advlink) {
        this.advlink = advlink;
    }

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public int getMlength() {
        return mlength;
    }

    public void setMlength(int mlength) {
        this.mlength = mlength;
    }

    public int getMp4size() {
        return mp4size;
    }

    public void setMp4size(int mp4size) {
        this.mp4size = mp4size;
    }

    public String getRepovideourl() {
        return repovideourl;
    }

    public void setRepovideourl(String repovideourl) {
        this.repovideourl = repovideourl;
    }

    public String getRepovideourlOrigin() {
        return repovideourlOrigin;
    }

    public void setRepovideourlOrigin(String repovideourlOrigin) {
        this.repovideourlOrigin = repovideourlOrigin;
    }

    public String getRepovideourlmp4Origin() {
        return repovideourlmp4Origin;
    }

    public void setRepovideourlmp4Origin(String repovideourlmp4Origin) {
        this.repovideourlmp4Origin = repovideourlmp4Origin;
    }

    public List<SubVideoListItem> getSubList() {
        return subList;
    }

    public void setSubList(List<SubVideoListItem> subList) {
        this.subList = subList;
    }

    public String getRepovideourlmp4() {
        return repovideourlmp4;
    }

    public void setRepovideourlmp4(String repovideourlmp4) {
        this.repovideourlmp4 = repovideourlmp4;
    }
}
