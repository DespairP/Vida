package teamHTBP.vida.api.core.blueprint;

/**挑战*/
public interface IChallenge {

    /**是否完成挑战*/
    boolean isComplete();

    /**challenge开始时callout*/
    void startChallenge();

    /**challenge结束时callout*/
    void endChallenge();
}
