/*************************************
*
* Student Name: Andre Barbosa
* Assingment: #4
* Class: CIS 4160
*
*
************************************/
package com.webapp.hw.andre.model;

//Data Access Object plus List with all the users from the Database
public class Admin {
    private String username;
    private String password;
    private int dept;
    //private final Connection connection;

    /*public Admin() {
      try{
          this.connection = new ConnectionFactory().getConnection();
      }
      catch(SQLException e){
          throw new RuntimeException(e);
      }

    }*/

    public void setUser(String username) {
        this.username = username;
    }
    public String getUser(){
        return this.username;
    }

    public void setPass(String password) {
        this.password = password;
    }

    public String getPass() {
        return this.password;
    }
    public void setDept(int dept) {
        this.dept = dept;
    }
    public int getDept() {
        return this.dept;
    }

    /*public List<Admin> getUserPass() {
        try {
            Connection con = new ConnectionFactory().getConnection();
            List<Admin> usernames = new ArrayList<Admin>();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT dept, password, user_name FROM admin_user_sr");
            ResultSet rs =  stmt.executeQuery();
            while(rs.next()) {
                Admin ad = new Admin();
                String user = rs.getString("user_name");
                String pass = rs.getString("password");
                int dept = rs.getInt("dept");
                ad.setUser(user);
                ad.setPass(pass);
                ad.setDept(dept);
                usernames.add(ad);
            }
            rs.close();
            stmt.close();
            return usernames;

        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }*/



}
