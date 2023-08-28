package site.dunhanson.design.pattern.demo.strategy.strategy2;

import site.dunhanson.design.pattern.demo.strategy.strategy1.PromotionActivity;
import site.dunhanson.design.pattern.demo.strategy.strategy1.PromotionStrategy;

public class Strategy2Start {

    public static void main(String[] args) {
        String promotionKey = "GROUP_BUY";
        PromotionStrategy promotionStrategy = PromotionStrategyFactory.getPromotionStrategy(promotionKey);
        PromotionActivity activity = new PromotionActivity(promotionStrategy);
        activity.execute();
    }
}
