package site.dunhanson.design.pattern.demo.strategy.strategy1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PromotionActivity {
    private final PromotionStrategy promotionStrategy;

    public PromotionActivity(PromotionStrategy promotionStrategy) {
        this.promotionStrategy = promotionStrategy;
    }

    public void execute() {
        promotionStrategy.doPromotion();
    }
}
