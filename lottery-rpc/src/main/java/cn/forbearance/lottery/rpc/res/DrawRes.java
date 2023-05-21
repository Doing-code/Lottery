package cn.forbearance.lottery.rpc.res;

import cn.forbearance.lottery.common.Result;
import cn.forbearance.lottery.rpc.dto.AwardDto;

import java.io.Serializable;

/**
 * 抽奖结果
 *
 * @author cristina
 */
public class DrawRes extends Result implements Serializable {

    private static final long serialVersionUID = 967411026242519368L;

    private AwardDto awardDto;

    public DrawRes(String code, String info) {
        super(code, info);
    }

    public AwardDto getAwardDTO() {
        return awardDto;
    }

    public void setAwardDTO(AwardDto awardDto) {
        this.awardDto = awardDto;
    }
}
