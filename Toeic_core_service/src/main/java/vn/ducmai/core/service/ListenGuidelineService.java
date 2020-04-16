package vn.ducmai.core.service;

import vn.ducmai.core.dto.ListenGuidelineDTO;

import java.util.List;
import java.util.Map;

public interface ListenGuidelineService {
    ListenGuidelineDTO findListenGuidelineById(Integer id);
    void saveListenGuideline(ListenGuidelineDTO dto);
    ListenGuidelineDTO updateListenGuideline(ListenGuidelineDTO dto);
    Integer deleteListenGuideline(List<Integer> ids);
    Object[] findListenGuidelineByProperties(Map<String, Object> mapProperties, String sortDirection, String sortExpression, Integer first, Integer limit);
    ListenGuidelineDTO findListenGuidelineByExciseId(Integer id);
}
