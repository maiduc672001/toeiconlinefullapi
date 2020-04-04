package vn.ducmai.core.web.utils;

import org.apache.commons.lang.StringUtils;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import vn.ducmai.core.web.command.AbstractCommand;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
    public static void initSearchBean(HttpServletRequest request, AbstractCommand command){
        String sortExpression=request.getParameter( new ParamEncoder(command.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_SORT));
        String sortDirection=request.getParameter( new ParamEncoder(command.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_ORDER));
        String pageSr=request.getParameter( new ParamEncoder(command.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_PAGE));
        Integer page=1;
        if(StringUtils.isNotBlank(pageSr)){
            page=Integer.valueOf(pageSr);
        }
        command.setPage(page);
        command.setSortDirection(sortDirection);
        command.setSortExpression(sortExpression);
        command.setFirstItem((command.getPage()-1)*command.getMaxPageItems());
    }
}
