package site.dunhanson.design.pattern.demo.strategy1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmptyStrategy implements PromotionStrategy{
    @Override
    public void doPromotion() {
        log.info("无促销活动");
    }
}
