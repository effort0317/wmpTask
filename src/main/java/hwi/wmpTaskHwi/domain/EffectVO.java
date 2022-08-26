package hwi.wmpTaskHwi.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EffectVO {
    private String share; // 몫
    private String rest; // 나머지

    public EffectVO(){
    }

    public EffectVO(String share, String rest){
        this.share = share;
        this.rest = rest;
    }
}
