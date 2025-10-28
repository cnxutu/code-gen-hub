/**
 * Copyright (c) 2025 Tu Personal Research
 * All rights reserved.
 */
package ${convertUrl};

import ${paramUrl}.${entityName}AddParam;
import ${entityUrl}.${entityName}PO;
import ${dtoUrl}.${entityName}DTO;
import ${voUrl}.${entityName}VO;
import org.mapstruct.*;
import java.util.List;

/**
 * ${entityName} 对象转换器
 *
 * - 简单链路-生成层级：PO ⇄ VO
 * - 复杂链路-生成层级：PO ⇄ DTO ⇄ VO
 * - 跨系统调用请使用 DTO, VO 仅用于本系统调用展示
 * - 生产标准：MapStruct + Spring 组件模型
 */
@Mapper(componentModel = "spring")
public interface ${entityName}Convert {

    // ==========================
    //        Param -> PO
    // ==========================
    ${entityName}PO addParamToPO(${entityName}AddParam param);

    // ==========================
    //        PO ⇄ DTO
    // ==========================
    // @Mapping(source = "createTime", target = "createdAt", ignore = true) // 可选字段映射
    ${entityName}DTO poToDTO(${entityName}PO po);

    ${entityName}PO dtoToPO(${entityName}DTO dto);

    List<${entityName}DTO> poListToDTOList(List<${entityName}PO> list);
    List<${entityName}PO> dtoListToPOList(List<${entityName}DTO> list);

    // ==========================
    //        DTO ⇄ VO
    // ==========================
    ${entityName}VO dtoToVO(${entityName}DTO dto);
    ${entityName}DTO voToDTO(${entityName}VO vo);

    List<${entityName}VO> dtoListToVOList(List<${entityName}DTO> list);
    List<${entityName}DTO> voListToDTOList(List<${entityName}VO> list);

    // ==========================
    //        PO -> VO 直接转换（灵活）
    // ==========================

    /**
     * PO -> VO（默认通过 DTO 中转，保证标准逻辑）
     */
    @Named("poToVO")
    default ${entityName}VO poToVO(${entityName}PO po) {
        if (po == null) return null;
        return dtoToVO(poToDTO(po));
    }

    /**
     * PO -> VO（直接转换，不经过 DTO，可用于性能敏感场景）
     */
    ${entityName}VO poToVODirect(${entityName}PO po);

    @Named("poListToVOList")
    default List<${entityName}VO> poListToVOList(List<${entityName}PO> list) {
        if (list == null) return null;
        return dtoListToVOList(poListToDTOList(list));
    }

    /**
     * PO 列表 -> VO 列表（直接转换版本）
     */
    List<${entityName}VO> poListToVOListDirect(List<${entityName}PO> list);
}