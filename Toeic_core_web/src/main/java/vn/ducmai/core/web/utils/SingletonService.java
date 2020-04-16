package vn.ducmai.core.web.utils;

import vn.ducmai.core.serviceimpl.*;

public class SingletonService {
    private static UserServiceImpl userService=null;
    private static RoleServiceImpl roleService=null;
    private static CommentServiceImpl commentService=null;
    private static ListenGuidelineServiceImpl listenGuidelineServiceơ=null;
    private static ExerciseListenServiceImpl listenService=null;
    private static ExerciseListenQuestionServiceImpl exerciseListenQuestionService=null;
    public static ExerciseListenServiceImpl getExerciseListenService(){
        if(listenService==null){
            listenService=new ExerciseListenServiceImpl();
        }
        return listenService;
    }
    public static ExerciseListenQuestionServiceImpl getExerciseListenQuestionService(){
        if(exerciseListenQuestionService==null){
            exerciseListenQuestionService=new ExerciseListenQuestionServiceImpl();
        }
        return exerciseListenQuestionService;
    }
    public static UserServiceImpl getUserServiceIns(){
        if(userService==null){
            userService=new UserServiceImpl();
        }
        return userService;
    }
    public static RoleServiceImpl getRoleServiceIns(){
        if(roleService==null){
            roleService=new RoleServiceImpl();
        }
        return roleService;
    }
    public static CommentServiceImpl getCommentServiceIns(){
        if(commentService==null){
            commentService=new CommentServiceImpl();
        }
        return commentService;
    }
    public static ListenGuidelineServiceImpl getListenGuidelineServiceIns(){
        if(listenGuidelineServiceơ==null){
            listenGuidelineServiceơ=new ListenGuidelineServiceImpl();
        }
        return listenGuidelineServiceơ;
    }
}
