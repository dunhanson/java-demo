package regex;

import lombok.extern.slf4j.Slf4j;
import java.util.regex.Pattern;

@Slf4j
public class RegexExample {
    public static void main(String[] args) {
        String str = "#sql{select date_format(date_add(now(), interval -30 day), '%Y%-%m-%d') from dual}";
        String pattern = "^#sql\\{([\\S|\\s]+)\\}$";
        boolean matches = Pattern.matches(pattern, str);
        log.info("matches:{}", matches);
    }
}
