package cn.forbearance.lottery.application.process.res;

import cn.forbearance.lottery.common.Result;
import cn.forbearance.lottery.domain.strategy.model.vo.DrawAwardVo;

/**
 * 活动抽奖结果
 *
 * @author cristina
 */
public class DrawProcessResult extends Result {

    private DrawAwardVo drawAwardVo;

    public DrawProcessResult(String code, String info) {
        super(code, info);
    }

    public DrawProcessResult(String code, String info, DrawAwardVo drawAwardVo) {
        super(code, info);
        this.drawAwardVo = drawAwardVo;
    }

    public DrawAwardVo getDrawAwardVo() {
        return drawAwardVo;
    }

    public void setDrawAwardVo(DrawAwardVo drawAwardVo) {
        this.drawAwardVo = drawAwardVo;
    }

}
