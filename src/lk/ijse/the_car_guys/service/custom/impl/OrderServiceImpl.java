package lk.ijse.the_car_guys.service.custom.impl;

import lk.ijse.the_car_guys.dao.DaoFactory;
import lk.ijse.the_car_guys.dao.DaoTypes;
import lk.ijse.the_car_guys.dao.custom.OrderDAO;
import lk.ijse.the_car_guys.dao.custom.OrderDetailsDAO;
import lk.ijse.the_car_guys.dao.custom.SparePartDAO;
import lk.ijse.the_car_guys.db.DBConnection;
import lk.ijse.the_car_guys.dto.OrderDTO;
import lk.ijse.the_car_guys.dto.OrderDetailsDTO;
import lk.ijse.the_car_guys.entity.Order;
import lk.ijse.the_car_guys.entity.OrderDetails;
import lk.ijse.the_car_guys.entity.SparePart;
import lk.ijse.the_car_guys.service.custom.OrderService;
import lk.ijse.the_car_guys.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO= (OrderDAO) DaoFactory.getDaoFactory().getDAO(DaoTypes.ORDER);
    private final OrderDetailsDAO orderDetailsDAO= (OrderDetailsDAO) DaoFactory.getDaoFactory().getDAO(DaoTypes.ORDER_DETAIL);
    private final SparePartDAO sparePartDAO= (SparePartDAO) DaoFactory.getDaoFactory().getDAO(DaoTypes.SPARE_PART);

    @Override
    public boolean addOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);

            boolean isAdded=orderDAO.add(Convertor.toOrder(orderDTO));

            if(isAdded){
                //for convert orderDetailsDTO ArrayList to orderDetails ArrayList
                ArrayList<OrderDetails> orderDetailsList=new ArrayList<>();

                for (OrderDetailsDTO dto:orderDTO.getOrderDetailList()){
                    orderDetailsList.add(Convertor.toOrderDetails(dto));
                }

                boolean isDetailsAdded=
                        orderDetailsDAO.addDetails(orderDetailsList);

                if(isDetailsAdded){
                    ArrayList<SparePart>list=new ArrayList<>();

                    for(OrderDetailsDTO dto:orderDTO.getOrderDetailList()){
                        ArrayList<SparePart> sparePart =
                                sparePartDAO.searchItem("s_ID", dto.getSparePart_Id());
                        SparePart sp = sparePart.get(0);
                        sp.setQtyOnHand((int) (sp.getQtyOnHand()-dto.getQty()));
                        list.add(sp);
                    }

                    boolean isUpdated=
                            sparePartDAO.updatedAsNegative(list);
                    if (isUpdated){
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }

    @Override
    public String getNextOrderID() {
        return orderDAO.getNextOrderID();
    }

    @Override
    public OrderDTO getLastOrder() {
        return Convertor.toOrderDTO(orderDAO.getLastOrder());
    }

    @Override
    public int getTodayCount(String date) {
        return orderDAO.getTodayCount(date);
    }

    @Override
    public double getTodayOrderValue(String date) {
        return orderDAO.getTodayOrderValue(date);
    }

    @Override
    public int getOrderCount() {
        return orderDAO.getOrderCount();
    }

    @Override
    public double getYearOrderValue(String year) {
        return orderDAO.getYearOrderValue(year);
    }
}
