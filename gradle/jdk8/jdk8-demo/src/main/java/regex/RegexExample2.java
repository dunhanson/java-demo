package regex;

import lombok.extern.slf4j.Slf4j;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class RegexExample2 {
    public static void main(String[] args) {
        String str = "call data_analysis.project_weibu_industry_forjava()";
        String pattern = "((\\w+)\\.)";
        Matcher matcher = Pattern.compile(pattern).matcher(str);
        if(matcher.find()) {
            System.out.println(matcher.group(2));
        }
    }
}
