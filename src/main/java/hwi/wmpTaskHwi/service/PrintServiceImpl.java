package hwi.wmpTaskHwi.service;

import hwi.wmpTaskHwi.domain.EffectVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PrintServiceImpl implements PrintService{

    @Override
    public EffectVO getEffect(String url, String type, int unit) {
        String engNo = getEngNo(url); // 영어&숫자 오름차순 후 교차한 결과
        String share = ""; // 몫
        String rest = ""; // 나머지

        // 몫과 나머지 구하기 방법 1
        int idx = (engNo.length()/unit)*unit;
        for(int i=0;i<idx;i++){
            share +=engNo.substring(i,i+1);
        }
        rest = engNo.substring(idx, engNo.length());

        // 몫과 나머지 구하기 방법 2
        /*
        int restIdxStart = engNo.length()-(engNo.length()%unit);
        share = engNo.substring(0, restIdxStart);
        rest = engNo.substring(restIdxStart, engNo.length());
        */

        EffectVO effectVO = new EffectVO(share, rest);
        return effectVO;
    }
    
    // 영어숫자만 출력 후 오름차순&교차출력(조건 2~4)까지 거친 결과를 return
    @Override
    public String getEngNo(String url){
        String engNo = "";
        List<String> upperEngList = new ArrayList<>(); // 대문자
        List<String> lowerEngList = new ArrayList<>(); // 소문자
        List<String> numberList = new ArrayList<>(); // 숫자

        if(url.length() != 0) {
            for (int i = 0; i < url.length(); i++) {
                char c = url.charAt(i);
                if (Character.isUpperCase(c)) { // 대문자일 경우
                    upperEngList.add(Character.toString(c));
                } else if (Character.isLowerCase(c)) { // 소문자일 경우
                    lowerEngList.add(Character.toString(c));
                } else if (Character.isDigit(c)) { // 숫자일 경우
                    numberList.add(Character.toString(c));
                }
            }
            upperEngList.sort(Comparator.naturalOrder()); // 영어 대문자 오름차순
            lowerEngList.sort(Comparator.naturalOrder()); // 영어 소문자 오름차순
            numberList.sort(Comparator.naturalOrder()); // 숫자 오름차순

            List<String> engNoList = new ArrayList<>();
            engNoList = engCrossedAsc(upperEngList, lowerEngList, engNoList);
            if(engNoList.size()>0){
                engNo = cross(engNoList, numberList);
            }else if((engNoList.size()==0)&&(numberList.size() !=0)){
                for(String s : numberList){
                    engNo += s;
                }
            }
        }
        return engNo;
    }

    // 영어 대문자&소문자 교차 오름차순 return
    public List<String> engCrossedAsc(List<String> upperEngList, List<String> lowerEngList, List<String> resultList){
        if((upperEngList.size()==0)&&(lowerEngList.size()==0)){ // 오름차순 된 대문자 소문자 다 교차 했을 시 
            return resultList;
        }else{ // 오름차순 된 대문자 소문자 다 교차 안 됐을 시
            String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            int shorterLen = Math.min(upperEngList.size(), lowerEngList.size());

            for(int i=0;i<shorterLen;i++){
                if(alphabet.indexOf(upperEngList.get(0)) <= alphabet.indexOf(lowerEngList.get(0).toUpperCase())){
                    resultList.add(upperEngList.get(0));
                    upperEngList.remove(0);
                }else{
                    resultList.add(lowerEngList.get(0));
                    lowerEngList.remove(0);
                }
            }
        }

        if((upperEngList.size()>0)&&(lowerEngList.size()>0)){ // 교차 후 아직 더 교차해야 할 대문자와 소문자가 남았을 시(재귀 처리)
            return engCrossedAsc(upperEngList, lowerEngList, resultList);
        }else if(upperEngList.size() >0){ // 교차 후 아직 교차 안 된 대문자 남았을 시 교차한 결과 뒤에 나머지 대문자 다 붙힘
            for(String s : upperEngList){
                resultList.add(s);
            }
        }else if(lowerEngList.size()>0){ // 교차 후 아직 교차 안 된 소문자 남았을 시 교차한 결과 뒤에 나머지 소문자 다 붙힘
            for(String s : lowerEngList){
                resultList.add(s);
            }
        }
        return resultList;
    }

    // 영어 대문자&소문자 교차 오름차순한 것과 숫자 교차한 결과 return
    public String cross(List<String> engList, List<String> numberList){
        String upperLower = "";

        if((engList.size() != 0)||(numberList.size() !=0)){ // 영어와 숫자 둘 다 빈 값이 아닐 경우
            int shorterLen = Math.min(engList.size(), numberList.size());
            List<String> longerList = engList.size()>numberList.size()?engList:numberList;

            if(shorterLen == 0){ // 영어와 숫자 둘 중 하나가 빈 값일 경우 나머지 값 String 처리 후 return
                for(String s : longerList){
                    upperLower +=s;
                }
            }else{ // 영어와 숫자 둘 다 빈 값이 아닐 경우 교차
                for(int i=0;i<shorterLen;i++){
                    upperLower += engList.get(i)+numberList.get(i);
                }
                if(engList.size() != numberList.size()){ // 교차 후 한 쪽의 양이 더 많으면 양이 더 많은 쪽 나머지 뒤에 붙힘
                    for(int i=shorterLen;i<longerList.size();i++){
                        upperLower += longerList.get(i);
                    }
                }

            }
        }
        return upperLower;
    }
}
