package vn.ducmai.core.serviceimpl;

import vn.ducmai.core.dao.impl.CommentDaoImpl;
import vn.ducmai.core.dao.impl.ListenGuidelineDaoImpl;
import vn.ducmai.core.dao.impl.RoleDaoImpl;
import vn.ducmai.core.dao.impl.UserDaoImpl;

public class SingletonDao {
    private static UserDaoImpl userDao;
    private static RoleDaoImpl roleDao;
    private static CommentDaoImpl commentDao;
    private static ListenGuidelineDaoImpl listenGuidelineDao;
    public static UserDaoImpl getUserDaoInstance(){
        if(userDao==null){
            userDao=new UserDaoImpl();
        }
        return userDao;
    }
    public static RoleDaoImpl getRoleDaoInstance(){
        if(roleDao==null){
            roleDao=new RoleDaoImpl();
        }
        return roleDao;
    }
    public static CommentDaoImpl getCommentDaoInstance(){
        if(commentDao==null){
            commentDao=new CommentDaoImpl();
        }
        return commentDao;
    }
    public static ListenGuidelineDaoImpl getListenGuidelineDaoInstance(){
        if(listenGuidelineDao==null){
            listenGuidelineDao=new ListenGuidelineDaoImpl();
        }
        return listenGuidelineDao;
    }
}
