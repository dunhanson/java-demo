package site.dunhanson.design.pattern.demo.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GroupBuyStrategy implements PromotionStrategy{
    @Override
    public void doPromotion() {
        log.info("拼团，满20人成团，全团享受团购价");
    }
}
