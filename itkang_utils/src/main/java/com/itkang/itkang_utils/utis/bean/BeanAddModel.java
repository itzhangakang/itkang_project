package com.itkang.itkang_utils.utis.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BeanAddModel implements Serializable {

    public BeanAddModel() {
    }

    public BeanAddModel(String ptId, String orgId, String sysId, Integer pessimisticLock, String createEId, String createEName, Date createDate, String updateEId, String updateEName, Date updateDate) {
        this.ptId = ptId;
        this.orgId = orgId;
        this.sysId = sysId;
        this.pessimisticLock = pessimisticLock;
        this.createEId = createEId;
        this.createEName = createEName;
        this.createDate = createDate;
        this.updateEId = updateEId;
        this.updateEName = updateEName;
        this.updateDate = updateDate;
    }

    /**
     * 平台代码
     */
    String ptId = "1";

    /**
     * 账套代码
     */
    //String orgId = UserUtils.getuUserOrgId();
    String orgId = "1";

    /**
     * 系统代码
     */
    String sysId = "1";

    /**
     * 悲观锁
     */
    Integer pessimisticLock = 1;

    /**
     * 创建人代码
     */
    //String createEId = UserUtils.getOperator().getOperateId();
    String createEId = "1";

    /**
     * 创建人姓名
     */
    //String createEName = UserUtils.getOperator().getOperateName();
    String createEName = "1";

    /**
     * 创建时间
     */
    Date createDate = new Date();

    /**
     * 修改人代码
     */
    //String updateEId = UserUtils.getOperator().getOperateId();
    String updateEId = "1";

    /**
     * 修改人姓名
     */
    //String updateEName = UserUtils.getOperator().getOperateName();
    String updateEName = "1";

    /**
     * 修改时间
     */
    Date updateDate = new Date();
}
