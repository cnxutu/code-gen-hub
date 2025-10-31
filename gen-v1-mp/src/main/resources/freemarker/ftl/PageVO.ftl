/**
 * Copyright (c) 2025 Tu Personal Research
 * All rights reserved.
 */
package ${voUrl};

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**   
 * ${entityComment} 新增接口入参
 *
 * @author ${author}
 * @date ${createTime}
 */
@Data
public class ${entityName}PageVO {

    /**
     * 主键id
     */
    private Long id;

<#list cis as ci>
    /**
     * ${ci.comment}
     */
    <#-- 针对 LocalDate 查询字段 -->
    <#if ci.javaType=="LocalDate">
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    <#elseif ci.javaType=="LocalDateTime">
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    </#if>
	private ${ci.javaType} ${ci.property};
</#list>

}
	