package site.dunhanson.design.pattern.demo.strategy.strategy1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CouponStrategy implements PromotionStrategy{
    @Override
    public void doPromotion() {
        log.info("领取优惠券，课程的价格直减优惠券面值抵扣");
    }
}
