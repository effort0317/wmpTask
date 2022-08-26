package hwi.wmpTaskHwi.service;

import hwi.wmpTaskHwi.domain.EffectVO;

import java.util.List;

public interface PrintService {
    // 몫&나머지 구하기
    public EffectVO getEffect(String url, String type, int unit);
    
    // 영어숫자만 출력 후 오름차순&교차출력(조건 2~4)까지 거친 결과 구하기
    public String getEngNo(String url);
    
    // 영어 대문자&소문자 교차 오름차순 구하기
    public List<String> engCrossedAsc(List<String> upperEngList, List<String> lowerEngList, List<String> resultList);
    
    // 영어 대문자&소문자 교차 오름차순한 것과 숫자 교차한 결과 구하기
    public String cross(List<String> engList, List<String> numberList);
}
