package lk.ijse.the_car_guys.dao;

import lk.ijse.the_car_guys.dao.exception.ConstraintViolationException;
import lk.ijse.the_car_guys.entity.SuperEntity;

import java.sql.SQLException;

public interface CrudDAO<T extends SuperEntity,ID> extends SuperDAO{

    boolean add(T entity) throws ConstraintViolationException;

    boolean delete(ID id) ;

    //--------------------

    //boolean deleteFromTable(String id) throws SQLException, ClassNotFoundException;
    //boolean deleteFromTable(String v_id) throws SQLException, ClassNotFoundException;
    //public boolean deleteFromTable(String u_id) throws SQLException, ClassNotFoundException;
    //boolean deleteFromTable(String s_id) throws SQLException, ClassNotFoundException;
    //boolean deleteFromTable(String cus_id) throws SQLException, ClassNotFoundException;
}
