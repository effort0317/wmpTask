package hwi.wmpTaskHwi.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoVO {
    private String url; // url
    private String type; // "all":Text전체, "noTag" : HTML태그 제외
    private int unit; // 출력 묶음 단위

    public InfoVO(){
    }

    public InfoVO(String url, String type, int unit){
        this.url = url;
        this.type = type;
        this.unit = unit;
    }
}
