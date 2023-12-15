package lk.ijse.the_car_guys.dao.custom.impl;

import lk.ijse.the_car_guys.dao.custom.ServiceDAO;
import lk.ijse.the_car_guys.dao.exception.ConstraintViolationException;
import lk.ijse.the_car_guys.dao.util.CrudUtil;
import lk.ijse.the_car_guys.dto.ServiceDTO;
import lk.ijse.the_car_guys.entity.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ServiceDAOImpl implements ServiceDAO {

    @Override
    public ArrayList<Service> searchService(String searchType, String searchText) {
        if(searchType.equals("ser_description")){
            searchText="%"+searchText+"%";
        }
        String sql=setSql(searchType);

        ArrayList<Service> serviceList =new ArrayList<>();

        ResultSet resultSet= null;

        try {
            resultSet = CrudUtil.execute("SELECT * FROM Serviec WHERE "+sql,searchText);

            while (resultSet.next()){
                serviceList.add(new Service(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3)
                ));
            }
            return serviceList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private String setSql(String searchType) {
        String sql=(searchType.equals("ser_description")) ?
                "ser_description LIKE ?" : "ser_ID=?";
        return sql;
    }

    @Override
    public ArrayList<Service> getServiceList() {
        try {
            ResultSet resultSet=CrudUtil.execute("SELECT * FROM Serviec");

            ArrayList<Service> serviceList =new ArrayList<>();
            while (resultSet.next()){
                serviceList.add(new Service(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3)
                ));
            }
            return serviceList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getCount() {
        try {
            ResultSet resultSet=CrudUtil
                    .execute("SELECT COUNT(ser_ID) FROM  Serviec");
            if (resultSet.next()){
                return resultSet.getInt(1);
            }
            return 0;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean isExist(String id) {
        try {
            ResultSet rst=CrudUtil.execute("SELECT * FROM Serviec WHERE ser_ID=?",id);
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(Service service) throws ConstraintViolationException {

        try {
            boolean isAdded=CrudUtil
                    .execute(
                            "INSERT INTO Serviec VALUES(?,?,?)",
                            service.getSer_ID(),
                            service.getSer_description(),
                            service.getSer_price()
                    );
            if(isAdded){
                return true;
            }
            throw new SQLException("Failed to save Service");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public boolean updateService(Service service) {
        try {
            return CrudUtil
                    .execute(
                            "UPDATE Serviec SET ser_ID=?," +
                                    "ser_description=?,ser_price=? " +
                                    "WHERE ser_ID=?",
                            service.getSer_ID(),
                            service.getSer_description(),
                            service.getSer_price(),
                            service.getSer_ID());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getNextServiceID() {
        try {
            ResultSet resultSet = CrudUtil.
                    execute("SELECT ser_ID FROM Serviec ORDER BY ser_ID DESC LIMIT 1");

            String lastServiceID="";
            if (resultSet.next()){
                lastServiceID=resultSet.getString(1);
            }
            String nextPaymentId=generateNextServiceId(lastServiceID);
            return nextPaymentId;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextServiceId(String lastServiceID) {
        String date="";
        String newDate="";
        date=new SimpleDateFormat("yyyy/MM").format(new Date());
        newDate="Ser/"+date;

        if((lastServiceID.equals(""))==false) {
            String[] ids = lastServiceID.split("@");
            int id = Integer.parseInt(ids[1]);
            id += 1;


            boolean isEquals=isDateEquals(ids[0],newDate);
            if(!isEquals){
                ids[0]="Ser/"+date;
                id=1;
            }

            String newServiceId=String.format("@%04d",id);
            return ids[0] + newServiceId;
        }

        return newDate+"@0001";
    }

    private boolean isDateEquals(String id, String newDate) {
        if(id.equals(newDate)){
            return true;
        }
        return false;
    }
}
