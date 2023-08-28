package site.dunhanson.design.pattern.demo.strategy.strategy1;

public class StrategyStart {
    public static void main(String[] args) {
        PromotionActivity activity1 = new PromotionActivity(new CouponStrategy());
        activity1.execute();

        PromotionActivity activity2 = new PromotionActivity(new CashbackStrategy());
        activity2.execute();

        PromotionActivity activity3 = new PromotionActivity(new GroupBuyStrategy());
        activity3.execute();

        PromotionActivity activity4 = new PromotionActivity(new EmptyStrategy());
        activity4.execute();
    }
}
