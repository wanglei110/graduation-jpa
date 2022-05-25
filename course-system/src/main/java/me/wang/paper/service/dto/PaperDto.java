
package me.wang.paper.service.dto;

import lombok.Data;
import java.io.Serializable;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

/**
* @website https://el-admin.vip
* @description /
* @author wang
* @date 2022-05-18
**/
@Data
public class PaperDto implements Serializable {

    /** id */
    /** 防止精度丢失 */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /** 序号 */
    private Integer orderNo;

    /** 名称 */
    private String name;
}