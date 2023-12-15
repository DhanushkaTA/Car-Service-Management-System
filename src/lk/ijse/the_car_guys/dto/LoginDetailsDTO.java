package lk.ijse.the_car_guys.dto;

public class LoginDetailsDTO {
    private String l_ID;
    private String user_Id;
    private String login_date;
    private String login_time;
    private String logout_date;
    private String logout_time;

    public LoginDetailsDTO() {
    }

    public LoginDetailsDTO(String l_ID, String user_Id, String login_date,
                           String login_time, String logout_date, String logout_time) {
        this.l_ID = l_ID;
        this.user_Id = user_Id;
        this.login_date = login_date;
        this.login_time = login_time;
        this.logout_date = logout_date;
        this.logout_time = logout_time;
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

    @Override
    public String toString() {
        return "LoginDetails{" +
                "l_ID='" + l_ID + '\'' +
                ", user_Id='" + user_Id + '\'' +
                ", login_date='" + login_date + '\'' +
                ", login_time='" + login_time + '\'' +
                ", logout_date='" + logout_date + '\'' +
                ", logout_time='" + logout_time + '\'' +
                '}';
    }
}
