/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import annotations.AnnotationClass;
import annotations.AnnotationField;
import connexion.Connexion;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import outil.Attribut;
import outil.Outil;
import static outil.Outil.getAttributsNotNull;

/**
 *
 * @author nyamp
 */
public class Dao {
    private static Dao instance=null;
    
    /**
     * 
     * @return  retourne l' instance de BaseDAO
     */
    public static Dao dao() {
        if(instance==null)
            instance=new Dao();
        return instance; 
    }
    
    private Dao(){}
    
    
    /** 
     * Function Encrypt
     * @param objet
     * @param connection
     * @return 
     * 
     * 
     * @throws java.sql.SQLException 
     */
    public String Encrypt(String objet, Connection connection) throws SQLException {
        String pass = null;
        String requette = "select encrypt('password','?','bf'";
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        try {
            preparedStatement = connection.prepareStatement(requette);
            preparedStatement.setObject(1 ,objet);
            resultset = preparedStatement.executeQuery();
            while(resultset.next()) {
                pass = resultset.getString(1);
            }
        }
        catch(SQLException exception) {
            throw exception;
        }
        return pass;
    }
    
    
    private String verifierTable(Object object,String table) throws Exception {
        if(table==null) {
            Class classe = object.getClass();
            AnnotationClass annotationClass = (AnnotationClass) classe.getAnnotation(AnnotationClass.class);
            if(annotationClass!=null) {
                table = annotationClass.table();
            }
            else {
                throw new Exception("Il faut definir la table");
            }
        }
        return table;
    }
    
    private String getWhere(List<Attribut> condition, String apresWhere, boolean separateurIsAnd) throws Exception{
        String separateur = "and";
        if(!separateurIsAnd) {
            separateur = "or";
        }
        String where = "";
        int taille;
        int i=0;
        try {
            if(condition!=null) {
                taille = condition.size();
                for(i=0; i<taille; i++){
                    if(i==0)
                        where+=" where ";
                    else 
                        where+=" "+separateur+" ";
                    
                    where+=condition.get(i).getRequette();
                }
            }
            if(apresWhere!=null){
                if(where.trim().equals("") && apresWhere.trim().startsWith("and")) {
                    where+= " where 1>0 ";
                }
                where+=" "+apresWhere;
            }
        }
        catch(Exception exception) {
            throw exception;
        }
        return where;
    }
    
    private void invokeResultat(String typecoloumn, String namecoloumn, Outil outil, Field fields, Object objet, Object temporaire, ResultSet resultset, Method method, int j) 
            throws ClassNotFoundException, SQLException, IllegalAccessException, IllegalArgumentException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object result = null;
        try {
            if(typecoloumn.equalsIgnoreCase("varchar") == true && namecoloumn.equalsIgnoreCase(fields.getName().toUpperCase()) == true) {
                result = (String) resultset.getString(j+1);
                method = outil.getMethodSet(objet, fields);
                method.invoke(temporaire, result);
            }
            else if(typecoloumn.equalsIgnoreCase("serial") == true && namecoloumn.equalsIgnoreCase(fields.getName().toUpperCase()) == true) {
                result = (int) resultset.getInt(j+1);
                method = outil.getMethodSet(objet, fields);
                method.invoke(temporaire, result);
            }
            else if(typecoloumn.equalsIgnoreCase("number") == true && namecoloumn.equalsIgnoreCase(fields.getName().toUpperCase()) == true) {
                result = (int) resultset.getInt(j+1);
                method = outil.getMethodSet(objet, fields);
                method.invoke(temporaire, result);
            }
            else if(typecoloumn.equalsIgnoreCase("int4") == true && namecoloumn.equalsIgnoreCase(fields.getName().toUpperCase()) == true) {
                result = (int) resultset.getInt(j+1);
                method = outil.getMethodSet(objet, fields);
                method.invoke(temporaire, result);
            }
            else if(typecoloumn.equalsIgnoreCase("date") == true && namecoloumn.equalsIgnoreCase(fields.getName().toUpperCase()) == true) {
                result = (java.util.Date) resultset.getDate(j+1);
                method = outil.getMethodSet(objet, fields);
                method.invoke(temporaire, result);
            }
            else if(typecoloumn.equalsIgnoreCase("Object") == true && namecoloumn.equalsIgnoreCase(fields.getName().toUpperCase()) == true) {
                result = resultset.getObject(j+1);
                method = outil.getMethodSet(objet, fields);
                try { method.invoke(temporaire, result); }
                catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) { throw exception; }
            }
            else if(typecoloumn.equalsIgnoreCase("Timestamp") == true && namecoloumn.equalsIgnoreCase(fields.getName().toUpperCase()) == true) {
                result = resultset.getTimestamp(j+1);
                method = outil.getMethodSet(objet, fields);
                try { method.invoke(temporaire, result); }
                catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) { throw exception; }
            }
            else if(typecoloumn.equalsIgnoreCase("float") == true && namecoloumn.equalsIgnoreCase(fields.getName().toUpperCase()) == true) {
                result = resultset.getFloat(j+1);
                method = outil.getMethodSet(objet, fields);
                try { method.invoke(temporaire, result); }
                catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) { throw exception; }
            }
            else if(typecoloumn.equalsIgnoreCase("double") == true && namecoloumn.equalsIgnoreCase(fields.getName().toUpperCase()) == true) {
                result = resultset.getDouble(j+1);
                method = outil.getMethodSet(objet, fields);
                try { method.invoke(temporaire, result); }
                catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) { throw exception; }
            }
            else if(typecoloumn.equalsIgnoreCase("numeric") == true && namecoloumn.equalsIgnoreCase(fields.getName().toUpperCase()) == true) {
                result = resultset.getDouble(j+1);
                method = outil.getMethodSet(objet, fields);
                try { method.invoke(temporaire, result); }
                catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) { throw exception; }
            }
        }
        catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException | SQLException exception) {
            throw  exception;
        }
    }
    
    private boolean isFieldAnnoted(Object object, Field field) {
        boolean resultat = false;
        Class classe = object.getClass();
        AnnotationField annotationField = null;
        annotationField = field.getAnnotation(AnnotationField.class);
        if(annotationField!=null) {
            resultat = true;
        }
        else
            resultat=false;
        return resultat;
    }
    
    
    /**
     * 
     * @param objet  les attributs non null sont les valeurs à inserer
     * @param connection connection à la base de donné
     * @throws  Exception nom de table non specifier ou qui n'existe pas dans la base, objectCondition null,
     * colonne  pas definie dans la table,
     * nom de table qui n'est pas definie dans la classe corespondant
     */
    public void insert(Object objet,Connection connection) throws  Exception{
        getinsert(null,objet, connection);
    }
    
    /**
     * 
     * @param table nom de la table pour faire l'action de modifier
     * @param object  les attributs non null sont les valeurs à inserer  ,
     * nom de table definie dans la classe corespondant
     * @param connection connection à la base de donné
     * @throws java.lang.ClassNotFoundException le champs n'a pas la méthode get ou set 
     * @throws  Exception nom de table non specifier ou qui n'existe pas dans la base, objectCondition null, colonne 
     * pas definie dans la table
     */
    public void getinsert(String table, Object object,Connection connection) throws Exception {
        table=verifierTable(object,table);
        List<Attribut> listInsert;
        String requette = "insert into "+ table;
        String colomn = "(";
        String value = "(";
        PreparedStatement preparedstatement = null;
        boolean verifieConnection = false;
        if(connection==null) {
            verifieConnection = true;
            connection = new Connexion().getConnection();
        }
        int insert;
        try {
            listInsert = getAttributsNotNull(object);
            if(listInsert==null)
                throw new Exception("Il faut des valeurs à inserer");
            int taille = listInsert.size();
            for(int i=0;i<taille;i++) {
                if(i>0) {
                    colomn+=",";
                    value+=",";
                }
                colomn+=listInsert.get(i).getName();
                value+="?";
            }
            colomn+=")";
            value+=")";
       
            requette+=colomn+" values "+value;
            preparedstatement=connection.prepareStatement(requette);
            for(int i=0;i<taille;i++) {
                preparedstatement.setObject(i+1 ,listInsert.get(i).getValue());
            }
            insert=preparedstatement.executeUpdate();
           
        }
        catch(Exception e) {
            System.out.println(e);
            throw e;
        }
        finally {
            if(preparedstatement!=null) 
                preparedstatement.close();
            if(verifieConnection==true) {
                if(connection!=null) 
                    connection.close();
            }
        }
    }
    
    
    /**
     * 
     * @param objectCondition les attributs non null sont les conditions
     * @param objectModifier  les attributs non null sont les valeur à modifier 
     * @param connection connection à la base de donné
     * @throws Exception nom de table non specifier ou qui n'existe pas dans la base, objectCondition null, objectCondition null,
     * colonne  pas definie dans la table,
     * nom de table qui n'est pas definie dans la classe corespondant
     */
    public void update(Object objectCondition, Object objectModifier, Connection connection) throws Exception {
        try {
            getupdate(null, objectCondition, objectModifier, connection);
        }
        catch(Exception exception) {
            throw exception;
        }
    }
   
    /**
     * 
     * @param table nom de la table pour faire l'action de modifier
     * @param objectCondition les attributs non null sont les conditions 
     * @param objectModifier  les attributs non null sont les valeur à modifier 
     * @param connection connection à la base de donné
     * @throws Exception nom de table non specifier ou qui n'existe pas dans la base, objectCondition null, objectCondition null, colonne 
     * pas definie dans la table
     */
    public void getupdate(String table, Object objectCondition, Object objectModifier, Connection connection) throws Exception{
        table = verifierTable(objectCondition, table);
        String requette = "update "+table+" set ";
        List<Attribut> modifier;
        List<Attribut> condition;
        PreparedStatement preparedStatement = null;
        int update;
        int i=0;
        int j=0;
        if(objectModifier==null)
            throw new Exception("Il faut precicer l'objet à modifier");
        boolean verifieConnection = false;
        if(connection==null) {
            verifieConnection = true;
            connection = new Connexion().getConnection();
        }
        try {
            modifier = getAttributsNotNull(objectModifier);
            int tailleModifier = modifier.size();
            for(i=0; i<tailleModifier; i++) {
                if(i!=0)
                    requette+=",";
                
                requette+=String.format(" %s = ? ", modifier.get(i).getName());
            }
            
            condition = getAttributsNotNull(objectCondition);
            int tailleCondition = 0;
            if(condition!=null ) {
                tailleCondition = condition.size();
                for(i=0; i<tailleCondition; i++) {
                    if(i!=0)
                        requette+=" and ";
                    else
                        requette+=" where ";
                    
                    requette+=String.format(" %s = ? ", condition.get(i).getName());
                }
            }
            preparedStatement = connection.prepareStatement(requette);
            for(i=0; i<tailleModifier; i++,j++) {
                preparedStatement.setObject(j+1, modifier.get(i).getValue());
            }
            for(i=0; i<tailleCondition; i++,j++) {
                preparedStatement.setObject(j+1, condition.get(i).getValue());
            }
            update = preparedStatement.executeUpdate();
        }
        catch(Exception exception) {
            throw exception;
        }
        finally {
            if(preparedStatement !=null)
                preparedStatement.close();
            if(verifieConnection==true) {
                if(connection!=null)
                    connection.close();
            }
        }  
    }
    
    
    /**
     * 
     * @param objectCondition les attributs non null sont les conditions
     * @param connection connection à la base de donné
     * @throws Exception nom de table nom specifier ou qui n'existe pas, objectCondition null, objectCondition null, 
     * colonne  pas definie dans la table,
     * nom de table qui n'est pas definie dans la classe corespondant
     */   
    public void delete(Object objectCondition, Connection connection) throws Exception {
        getdelete(null, objectCondition, connection) ;
    }
    
    /**
     * 
     * @param table nom de la table pour faire l'action d'effacer
     * @param objectCondition les attributs non null sont les conditions 
     * @param connection connection à la base de donné
     * @throws Exception nom de table non specifier ou qui n'existe pas dans la base, objectCondition null, objectCondition null, colonne 
     * pas definie dans la table
     */
    public void getdelete(String table, Object objectCondition, Connection connection) throws Exception {
        table = verifierTable(objectCondition,table);
        String requette = "delete from "+table;
        List<Attribut> condition;
        PreparedStatement preparedStatement = null;
        boolean verifieConnection = false;
        if(connection==null) {
            verifieConnection = true;
            connection = new Connexion().getConnection();
        }
        int delete;
        try {
            condition = getAttributsNotNull(objectCondition);
            int tailleCondition = condition.size();
            requette+=getWhere(condition, null,true);
            preparedStatement = connection.prepareStatement(requette);
            for(int i=0; i<tailleCondition; i++){
                preparedStatement.setObject(i+1, condition.get(i).getValue());
            }
            delete = preparedStatement.executeUpdate();
        }
        catch(Exception exception) {
            throw exception;
        }
        finally {
            if(preparedStatement!=null)
                preparedStatement.close();
            if(verifieConnection==true) {
                if(connection!=null)
                    connection.close();
            }
        }
    }
    
    
    /**
     * 
     * @param object les attributs non null sont les conditions
     * @param connection connection à la base de donné
     * @throws  Exception nom de table non specifier ou qui n'existe pas dans la base, objectCondition null,
     * colonne pas definie dans la table
     * @return list d'objet , dont les objets sont de meme instance que l' object condition
     */
    public Object[] select(Object object,Connection connection) throws Exception {
        return select(null,object,null,connection);
    }
    
    /**
     * 
     * @param object les attributs non null sont les conditions 
     * @param apresWhere se sont condition tel que : order by ...  
     * @param connection connection à la base de donné
     * @throws  Exception nom de table non specifier ou qui n'existe pas dans la base, objectCondition null, colonne 
     * pas definie dans la table
     * @return list d'objet , dont les objets sont de meme instance que l' object condition
     */
    public Object[] select(Object object,String apresWhere,Connection connection) throws Exception {
        return select(null,object,apresWhere,connection);
    }
    
    public Object[] select(String table,Object object,Connection connection) throws Exception{
        return select(table,object,null, connection);
    }
    
    /**
     * 
     * @param table nom de la table pour faire l'action d'effacer
     * @param object les attributs non null sont les conditions
     * @param afterwhere se sont condition tel que : order by ...  
     * @param connection connection à la base de donné
     * @throws  Exception nom de table non specifier ou qui n'existe pas dans la base, objectCondition null, colonne 
     * pas definie dans la table
     * @return list d'objet , dont les objets sont de meme instance que l' object condition
     */
    public Object[] select(String table, Object object, String afterwhere, Connection connection) throws  Exception {
        Object[] resultat;
        try {
            List<Attribut>condition = getAttributsNotNull(object);
            resultat = getselect(table, object, condition, afterwhere, true, connection);
        }
        catch(Exception exception) {
            throw exception;
        }
        return resultat;
    }
    
    private Object[] getselect(String table, Object object, List<Attribut> objetcondition, String afterwhere, boolean separateurIsAnd, Connection connection) throws SQLException, Exception {
        if(object==null) {
            throw new Exception("objet ne doit pas etre null");
        }
        table = verifierTable(object, table);
        Object[] objectreturn = null;
        String where;
        PreparedStatement preparedstatement = null;
        ResultSet resultset = null;
        String requette = "select * from "+table;
        boolean verifieConnection = false;
        if(connection==null) {
            verifieConnection = true;
            connection = new Connexion().getConnection();
        }
        int i=0;
        try {
            where = this.getWhere(objetcondition, afterwhere, separateurIsAnd);
            requette+=where;
            preparedstatement = connection.prepareStatement(requette, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            System.out.println("requette : "+requette);
            int taille = objetcondition.size();
            for(i=0; i<taille; i++){
                preparedstatement.setObject(i+1, objetcondition.get(i).getValue());
            }
            resultset = preparedstatement.executeQuery();
            objectreturn = getResultat(table, object, resultset, preparedstatement, connection);
        }
        catch(Exception exception) {
            throw exception;
        }
        finally {
            if(resultset!=null)
                resultset.close();
            if(preparedstatement!=null)
                preparedstatement.close();
            if(verifieConnection==true) {
                if(connection!=null)
                    connection.close();
            }
        }
        return objectreturn;
    }
    
    private Object[] getResultat(String table, Object objet, ResultSet resultset, PreparedStatement preparedstatement, Connection connection) throws Exception {
        int i=0;
        int j=0;
        Outil outil = new Outil();
        Method method = null;
        Object[] objetreturn = null;
        Field[] fields = null;
        try {
            int taillecoloumn = outil.getTailleColoumn(table, connection, resultset, preparedstatement, objet);
            String[] namecoloumn = new String[taillecoloumn];
            String[] typecoloumn = new String[taillecoloumn];
            for(i=0; i<taillecoloumn; i++) {
                namecoloumn[i] = resultset.getMetaData().getColumnName(i+1);
                typecoloumn[i] = resultset.getMetaData().getColumnTypeName(i+1);
            }
            Class classe = objet.getClass();
            while(!classe.getSimpleName().equals("Object")) {
                fields = classe.getDeclaredFields();
                classe = classe.getSuperclass();
            }
            
            int nombreresultat = outil.getNombreResultat(resultset);
            objetreturn = new Object[nombreresultat];
            int indice=0;
            int taillefield = fields.length;
            while(resultset.next()) {
                Object temporaire=objet.getClass().newInstance();
                for(j=0; j<taillecoloumn; j++) {
                    for(i=0; i<taillefield; i++) {
                        if(isFieldAnnoted(objet, fields[i])==true) {
                            invokeResultat(typecoloumn[j], namecoloumn[j], outil, fields[i], objet, temporaire, resultset, method, j);
                        }
                    }   
                }
                objetreturn[indice] = temporaire;
                indice++;
            }
        }
        catch(Exception e) {
            System.out.println(e);
            throw e;
        }
        return objetreturn;
    }
}
