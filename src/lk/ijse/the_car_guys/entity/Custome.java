package lk.ijse.the_car_guys.entity;

public class Custome implements SuperEntity{
    private String cus_ID;
    private String cus_Name;
    private String cus_NIC;
    private int cus_telephone;
    private String cus_Address;
    private String cus_Email;
    private String cus_regDate;

    private String l_ID;
    private String user_Id;
    private String login_date;
    private String login_time;
    private String logout_date;
    private String logout_time;

    private String o_ID ;
    private String o_Date;
    private String o_Time;
    private double o_Value ;

    private String sparePart_Id;
    private double qty;
    private double unitPrice;

    private String p_ID;
    private String payment_type;
    private String payment_date;
    private String payment_time;
    private double payment;

    private String repair_Id;


    private String r_Date ;
    private String r_Time ;
    private double r_Value ;

    private String service_Id;
    private double price;

    private String ser_ID ;
    private String ser_description;
    private double ser_price ;

    private String s_description;
    private String s_Type ;
    private double s_price;
    private int qtyOnHand;

    private String u_ID ;
    private String u_FullName;
    private String username ;
    private String password ;
    private String u_tele ;
    private String u_email ;
    private String u_NIC ;
    private String role;

    private String v_ID;
    private String v_Number;
    private String v_Engine_Type;
    private String v_Owner;
    private String v_Color;
    private String v_Type;
    private String v_regDate;

    private String w_ID;
    private String customer_ID;
    private String vehicle_Number;
    private String date;
    private String status;

    public Custome() {
    }

    public Custome(String cus_ID, String cus_Name, String cus_NIC, int cus_telephone, String cus_Address, String cus_Email, String cus_regDate, String l_ID, String user_Id, String login_date, String login_time, String logout_date, String logout_time, String o_ID, String o_Date, String o_Time, double o_Value, String sparePart_Id, double qty, double unitPrice, String p_ID, String payment_type, String payment_date, String payment_time, double payment, String repair_Id, String r_Date, String r_Time, double r_Value, String service_Id, double price, String ser_ID, String ser_description, double ser_price, String s_description, String s_Type, double s_price, int qtyOnHand, String u_ID, String u_FullName, String username, String password, String u_tele, String u_email, String u_NIC, String role, String v_ID, String v_Number, String v_Engine_Type, String v_Owner, String v_Color, String v_Type, String v_regDate, String w_ID, String customer_ID, String vehicle_Number, String date, String status) {
        this.cus_ID = cus_ID;
        this.cus_Name = cus_Name;
        this.cus_NIC = cus_NIC;
        this.cus_telephone = cus_telephone;
        this.cus_Address = cus_Address;
        this.cus_Email = cus_Email;
        this.cus_regDate = cus_regDate;
        this.l_ID = l_ID;
        this.user_Id = user_Id;
        this.login_date = login_date;
        this.login_time = login_time;
        this.logout_date = logout_date;
        this.logout_time = logout_time;
        this.o_ID = o_ID;
        this.o_Date = o_Date;
        this.o_Time = o_Time;
        this.o_Value = o_Value;
        this.sparePart_Id = sparePart_Id;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.p_ID = p_ID;
        this.payment_type = payment_type;
        this.payment_date = payment_date;
        this.payment_time = payment_time;
        this.payment = payment;
        this.repair_Id = repair_Id;
        this.r_Date = r_Date;
        this.r_Time = r_Time;
        this.r_Value = r_Value;
        this.service_Id = service_Id;
        this.price = price;
        this.ser_ID = ser_ID;
        this.ser_description = ser_description;
        this.ser_price = ser_price;
        this.s_description = s_description;
        this.s_Type = s_Type;
        this.s_price = s_price;
        this.qtyOnHand = qtyOnHand;
        this.u_ID = u_ID;
        this.u_FullName = u_FullName;
        this.username = username;
        this.password = password;
        this.u_tele = u_tele;
        this.u_email = u_email;
        this.u_NIC = u_NIC;
        this.role = role;
        this.v_ID = v_ID;
        this.v_Number = v_Number;
        this.v_Engine_Type = v_Engine_Type;
        this.v_Owner = v_Owner;
        this.v_Color = v_Color;
        this.v_Type = v_Type;
        this.v_regDate = v_regDate;
        this.w_ID = w_ID;
        this.customer_ID = customer_ID;
        this.vehicle_Number = vehicle_Number;
        this.date = date;
        this.status = status;
    }

    public String getCus_ID() {
        return cus_ID;
    }

    public void setCus_ID(String cus_ID) {
        this.cus_ID = cus_ID;
    }

    public String getCus_Name() {
        return cus_Name;
    }

    public void setCus_Name(String cus_Name) {
        this.cus_Name = cus_Name;
    }

    public String getCus_NIC() {
        return cus_NIC;
    }

    public void setCus_NIC(String cus_NIC) {
        this.cus_NIC = cus_NIC;
    }

    public int getCus_telephone() {
        return cus_telephone;
    }

    public void setCus_telephone(int cus_telephone) {
        this.cus_telephone = cus_telephone;
    }

    public String getCus_Address() {
        return cus_Address;
    }

    public void setCus_Address(String cus_Address) {
        this.cus_Address = cus_Address;
    }

    public String getCus_Email() {
        return cus_Email;
    }

    public void setCus_Email(String cus_Email) {
        this.cus_Email = cus_Email;
    }

    public String getCus_regDate() {
        return cus_regDate;
    }

    public void setCus_regDate(String cus_regDate) {
        this.cus_regDate = cus_regDate;
    }

    public String getL_ID() {
        return l_ID;
    }

    public void setL_ID(String l_ID) {
        this.l_ID = l_ID;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public String getLogin_date() {
        return login_date;
    }

    public void setLogin_date(String login_date) {
        this.login_date = login_date;
    }

    public String getLogin_time() {
        return login_time;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }

    public String getLogout_date() {
        return logout_date;
    }

    public void setLogout_date(String logout_date) {
        this.logout_date = logout_date;
    }

    public String getLogout_time() {
        return logout_time;
    }

    public void setLogout_time(String logout_time) {
        this.logout_time = logout_time;
    }

    public String getO_ID() {
        return o_ID;
    }

    public void setO_ID(String o_ID) {
        this.o_ID = o_ID;
    }

    public String getO_Date() {
        return o_Date;
    }

    public void setO_Date(String o_Date) {
        this.o_Date = o_Date;
    }

    public String getO_Time() {
        return o_Time;
    }

    public void setO_Time(String o_Time) {
        this.o_Time = o_Time;
    }

    public double getO_Value() {
        return o_Value;
    }

    public void setO_Value(double o_Value) {
        this.o_Value = o_Value;
    }

    public String getSparePart_Id() {
        return sparePart_Id;
    }

    public void setSparePart_Id(String sparePart_Id) {
        this.sparePart_Id = sparePart_Id;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getP_ID() {
        return p_ID;
    }

    public void setP_ID(String p_ID) {
        this.p_ID = p_ID;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(String payment_time) {
        this.payment_time = payment_time;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getRepair_Id() {
        return repair_Id;
    }

    public void setRepair_Id(String repair_Id) {
        this.repair_Id = repair_Id;
    }

    public String getR_Date() {
        return r_Date;
    }

    public void setR_Date(String r_Date) {
        this.r_Date = r_Date;
    }

    public String getR_Time() {
        return r_Time;
    }

    public void setR_Time(String r_Time) {
        this.r_Time = r_Time;
    }

    public double getR_Value() {
        return r_Value;
    }

    public void setR_Value(double r_Value) {
        this.r_Value = r_Value;
    }

    public String getService_Id() {
        return service_Id;
    }

    public void setService_Id(String service_Id) {
        this.service_Id = service_Id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSer_ID() {
        return ser_ID;
    }

    public void setSer_ID(String ser_ID) {
        this.ser_ID = ser_ID;
    }

    public String getSer_description() {
        return ser_description;
    }

    public void setSer_description(String ser_description) {
        this.ser_description = ser_description;
    }

    public double getSer_price() {
        return ser_price;
    }

    public void setSer_price(double ser_price) {
        this.ser_price = ser_price;
    }

    public String getS_description() {
        return s_description;
    }

    public void setS_description(String s_description) {
        this.s_description = s_description;
    }

    public String getS_Type() {
        return s_Type;
    }

    public void setS_Type(String s_Type) {
        this.s_Type = s_Type;
    }

    public double getS_price() {
        return s_price;
    }

    public void setS_price(double s_price) {
        this.s_price = s_price;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public String getU_ID() {
        return u_ID;
    }

    public void setU_ID(String u_ID) {
        this.u_ID = u_ID;
    }

    public String getU_FullName() {
        return u_FullName;
    }

    public void setU_FullName(String u_FullName) {
        this.u_FullName = u_FullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getU_tele() {
        return u_tele;
    }

    public void setU_tele(String u_tele) {
        this.u_tele = u_tele;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_NIC() {
        return u_NIC;
    }

    public void setU_NIC(String u_NIC) {
        this.u_NIC = u_NIC;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getV_ID() {
        return v_ID;
    }

    public void setV_ID(String v_ID) {
        this.v_ID = v_ID;
    }

    public String getV_Number() {
        return v_Number;
    }

    public void setV_Number(String v_Number) {
        this.v_Number = v_Number;
    }

    public String getV_Engine_Type() {
        return v_Engine_Type;
    }

    public void setV_Engine_Type(String v_Engine_Type) {
        this.v_Engine_Type = v_Engine_Type;
    }

    public String getV_Owner() {
        return v_Owner;
    }

    public void setV_Owner(String v_Owner) {
        this.v_Owner = v_Owner;
    }

    public String getV_Color() {
        return v_Color;
    }

    public void setV_Color(String v_Color) {
        this.v_Color = v_Color;
    }

    public String getV_Type() {
        return v_Type;
    }

    public void setV_Type(String v_Type) {
        this.v_Type = v_Type;
    }

    public String getV_regDate() {
        return v_regDate;
    }

    public void setV_regDate(String v_regDate) {
        this.v_regDate = v_regDate;
    }

    public String getW_ID() {
        return w_ID;
    }

    public void setW_ID(String w_ID) {
        this.w_ID = w_ID;
    }

    public String getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(String customer_ID) {
        this.customer_ID = customer_ID;
    }

    public String getVehicle_Number() {
        return vehicle_Number;
    }

    public void setVehicle_Number(String vehicle_Number) {
        this.vehicle_Number = vehicle_Number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Custome{" +
                "cus_ID='" + cus_ID + '\'' +
                ", cus_Name='" + cus_Name + '\'' +
                ", cus_NIC='" + cus_NIC + '\'' +
                ", cus_telephone=" + cus_telephone +
                ", cus_Address='" + cus_Address + '\'' +
                ", cus_Email='" + cus_Email + '\'' +
                ", cus_regDate='" + cus_regDate + '\'' +
                ", l_ID='" + l_ID + '\'' +
                ", user_Id='" + user_Id + '\'' +
                ", login_date='" + login_date + '\'' +
                ", login_time='" + login_time + '\'' +
                ", logout_date='" + logout_date + '\'' +
                ", logout_time='" + logout_time + '\'' +
                ", o_ID='" + o_ID + '\'' +
                ", o_Date='" + o_Date + '\'' +
                ", o_Time='" + o_Time + '\'' +
                ", o_Value=" + o_Value +
                ", sparePart_Id='" + sparePart_Id + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", p_ID='" + p_ID + '\'' +
                ", payment_type='" + payment_type + '\'' +
                ", payment_date='" + payment_date + '\'' +
                ", payment_time='" + payment_time + '\'' +
                ", payment=" + payment +
                ", repair_Id='" + repair_Id + '\'' +
                ", r_Date='" + r_Date + '\'' +
                ", r_Time='" + r_Time + '\'' +
                ", r_Value=" + r_Value +
                ", service_Id='" + service_Id + '\'' +
                ", price=" + price +
                ", ser_ID='" + ser_ID + '\'' +
                ", ser_description='" + ser_description + '\'' +
                ", ser_price=" + ser_price +
                ", s_description='" + s_description + '\'' +
                ", s_Type='" + s_Type + '\'' +
                ", s_price=" + s_price +
                ", qtyOnHand=" + qtyOnHand +
                ", u_ID='" + u_ID + '\'' +
                ", u_FullName='" + u_FullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", u_tele='" + u_tele + '\'' +
                ", u_email='" + u_email + '\'' +
                ", u_NIC='" + u_NIC + '\'' +
                ", role='" + role + '\'' +
                ", v_ID='" + v_ID + '\'' +
                ", v_Number='" + v_Number + '\'' +
                ", v_Engine_Type='" + v_Engine_Type + '\'' +
                ", v_Owner='" + v_Owner + '\'' +
                ", v_Color='" + v_Color + '\'' +
                ", v_Type='" + v_Type + '\'' +
                ", v_regDate='" + v_regDate + '\'' +
                ", w_ID='" + w_ID + '\'' +
                ", customer_ID='" + customer_ID + '\'' +
                ", vehicle_Number='" + vehicle_Number + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
