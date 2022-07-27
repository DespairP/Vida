package teamHTBP.vida.client.screen.guidebook;

public interface IPageableGuidebook {
    /**
     * 获得当前页
     * @return 页码
     */
    int getCurrentPage();

    /**
     * 获得总页面
     * @return 页数
     */
    int getTotalPage();
}
