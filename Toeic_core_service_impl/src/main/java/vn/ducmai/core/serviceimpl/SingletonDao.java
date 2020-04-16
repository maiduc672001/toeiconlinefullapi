package vn.ducmai.core.serviceimpl;

import vn.ducmai.core.dao.impl.*;

public class SingletonDao {
    private static UserDaoImpl userDao;
    private static RoleDaoImpl roleDao;
    private static CommentDaoImpl commentDao;
    private static ListenGuidelineDaoImpl listenGuidelineDao;
    private static ExerciseListenDaoImpl exerciseListenDao;
    private static ExerciseListenQuestionDaoImpl exerciseListenQuestionDao;
    public static ExerciseListenDaoImpl getExerciseListenDaoIns(){
        if(exerciseListenDao==null){
            exerciseListenDao=new ExerciseListenDaoImpl();
        }
        return exerciseListenDao;
    }
    public static ExerciseListenQuestionDaoImpl getExerciseListenQuestionDaoIns(){
        if(exerciseListenQuestionDao==null){
            exerciseListenQuestionDao=new ExerciseListenQuestionDaoImpl();
        }
        return exerciseListenQuestionDao;
    }
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
