package site.dunhanson.design.pattern.demo.strategy2;

import site.dunhanson.design.pattern.demo.strategy1.*;
import java.util.HashMap;
import java.util.Map;

public class PromotionStrategyFactory {
    private static final Map<String, PromotionStrategy> PROMOTION_STRATEGY_MAP = new HashMap<>();

    static {
        PROMOTION_STRATEGY_MAP.put(PromotionKey.COUPON, new CouponStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.CASH_BACK, new CashbackStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.GROUP_BUY, new GroupBuyStrategy());
    }

    private interface PromotionKey {
        String COUPON = "COUPON";
        String CASH_BACK = "CASH_BACK";
        String GROUP_BUY = "GROUP_BUY";
    }

    /**
     * 获取促销策略
     * @param promotionKey PromotionStrategy
     * @return PromotionStrategy
     */
    public static PromotionStrategy getPromotionStrategy(String promotionKey) {
        PromotionStrategy promotionStrategy = PROMOTION_STRATEGY_MAP.get(promotionKey);
        return promotionStrategy == null ? new EmptyStrategy() : promotionStrategy;
    }

}
