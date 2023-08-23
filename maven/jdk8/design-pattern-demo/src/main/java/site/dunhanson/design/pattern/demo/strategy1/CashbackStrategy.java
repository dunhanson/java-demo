package site.dunhanson.design.pattern.demo.strategy1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CashbackStrategy implements PromotionStrategy{
    @Override
    public void doPromotion() {
        log.info("返现促销，返回的金额转到支付宝账号");
    }
}
