/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package outil;

import annotations.AnnotationField;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author nyamp
 */
public class Outil {
    public int getTailleColoumn(String table, Connection connection, ResultSet resultset, PreparedStatement preparedstatement, Object objet) 
            throws Exception {
        int taillecoloumn = 0;
        try {
            String sql = "select * from "+table;
            preparedstatement = connection.prepareStatement(sql);
            resultset = preparedstatement.executeQuery();
            taillecoloumn = resultset.getMetaData().getColumnCount();
        }      
        catch(Exception e) {
            System.out.println(e);
            throw e;
        }
        return taillecoloumn;
    }

    public static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }
    
    public static String upperCaseSecond(String val) {
        char[] arr = val.toCharArray();
        arr[2] = Character.toUpperCase(arr[0]);
        return new String(arr);
   }
    
    public int getNombreResultat(ResultSet resultset) throws Exception {
        int size = 0;
        resultset.last();
        size = resultset.getRow();
        resultset.beforeFirst();
        return size;
    }
    
    static Method getMethodeGet(Object objet, Field field) throws NoSuchMethodException, Exception{
        Method methodget = null;
        Class classe = objet.getClass();
        try {
            String fieldname = new Outil().upperCaseFirst((field.getName()));
            methodget = classe.getMethod("get" + fieldname);
        }
        catch(SecurityException exception1) {
            try {
                String fieldname = field.getName();
                methodget = classe.getMethod("get" + fieldname);
            }
            catch(NoSuchMethodException | SecurityException exception2) {
                try{
                    String fieldname = field.getName().toLowerCase();
                    methodget = classe.getMethod("get" + fieldname);
                }
                catch(SecurityException exception3){
                    throw exception3;
                }
            }
        }
        return methodget;
    }
    
    public Method getMethodSet(Object objet,Field field) throws NoSuchMethodException {
        Method methodset = null;
        Class classe = objet.getClass();
        try {
            String fieldname = new Outil().upperCaseFirst((field.getName()));
            methodset = classe.getMethod("set" + fieldname, field.getType());
        }
        catch(SecurityException exception1) {
            try {
                String fieldname = field.getName();
                methodset = classe.getMethod("set" + fieldname, field.getType());            
            }
            catch(NoSuchMethodException | SecurityException exception2) {
                try{
                    String fieldname = field.getName().toLowerCase();
                    methodset = classe.getMethod("set" + fieldname, field.getType());
                }
                catch(SecurityException exception3){
                    throw exception3;
                }
            }
        }
        return methodset;
    }
    
    public static ArrayList<Attribut> getAttributsNotNull(Object object) throws NoSuchMethodException, IllegalAccessException, Exception{
        ArrayList<Attribut> attributs = new ArrayList();
        Class classe = object.getClass();
        Field[] fields = null;
        Method method = null;
        Object attribut = null;
        String column = null;
        AnnotationField annotationField = null;
        while(!classe.getSimpleName().equals("Object")) {
            fields = classe.getDeclaredFields();
            for(Field field : fields) {
                annotationField = field.getAnnotation(AnnotationField.class);
                if(annotationField!=null) {
                    column=annotationField.attribut();
                    try {
                        method = getMethodeGet(object, field);
                        attribut = method.invoke(object);
                        if(attribut!=null){
                            if((attribut.getClass().isPrimitive() ||  attribut instanceof Number)) {
                                if(((Number)attribut).intValue()!=0) {
                                    attributs.add(new Attribut(attribut,column));
                                }
                            }
                            else {
                                attributs.add(new Attribut(attribut,column));
                            }
                        }
                    }
                    catch(Exception exception) {
                        throw exception;
                    }
                }
            }
            classe=classe.getSuperclass();
        }
        return attributs;
    }
    
    
    
    
    
    
    
    
    
    
    public boolean isCharacter(String fieldtypename) {
        boolean result;
        if(fieldtypename.equalsIgnoreCase("java.lang.String") == true) {result=true; }
        else if(fieldtypename.equalsIgnoreCase("java.sql.Date") == true) { result=true; }
        else if(fieldtypename.equalsIgnoreCase("java.util.Date") == true) { result=true; }
        else if(fieldtypename.equalsIgnoreCase("java.sql.Time") == true) { result=true; }
        else { result=false; }
        return result;
    }
}
