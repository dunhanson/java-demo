package common;

import lombok.extern.slf4j.Slf4j;
import java.util.Optional;

/**
 * Optional示例
 * @author dunhanson
 * @since 2023-0626
 * @version 1.0.0
 */
@Slf4j
public class OptionalExample {
    public static void main(String[] args) {
        testIfPresent();
    }

    /**
     * 测试ifPresent
     */
    public static void testIfPresent() {
        log.info("---------------testIfPresent---------------");
        Optional<String> optional1 = Optional.empty();
        optional1.map(String::toLowerCase).ifPresent(log::info);
        Optional<String> optional2 = Optional.of("abc");
        optional2.map(String::toUpperCase).ifPresent(log::info);
        log.info("finish.");
    }
}
