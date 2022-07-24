package teamHTBP.vida.helper.guidebook;

import com.google.gson.annotations.Expose;

import java.util.LinkedList;
import java.util.List;

/**
 * 一篇Guide,
 * 每篇Guide都有对应的n页 {@link GuidebookSinglePage},
 *
 * */
public class Guide {
    /**guide所属的区域*/
    public String category;
    /**GuideId*/
    public String id;
    /**Guide名称*/
    public String name;
    /**总共页数*/
    public String totalPage;
    /**每页对应的page*/
    @Expose(serialize = false,deserialize = false)
    public List<GuidebookSinglePage> guidePages = new LinkedList<>();
    /**是否是公开信息,即不需要解锁的内容,默认为true*/
    public boolean isPublic = true;

}
