package regex;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
public class RegexExample1 {
    public static void main(String[] args) {
        String str = "call data_analysis.project_weibu_industry_forjava()";
        String pattern = "^call (\\w+)\\.\\w+\\(\\)$";
        System.out.println(str.replaceAll(pattern, "$1"));
    }
}
