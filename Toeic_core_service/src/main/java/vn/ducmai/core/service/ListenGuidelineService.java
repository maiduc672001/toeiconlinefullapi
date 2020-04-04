package vn.ducmai.core.service;

import java.util.Map;

public interface ListenGuidelineService {
    Object[] findListenGuidelineByProperties(Map<String, Object> mapProperties, String sortDirection, String sortExpression, Integer first, Integer limit);
}
