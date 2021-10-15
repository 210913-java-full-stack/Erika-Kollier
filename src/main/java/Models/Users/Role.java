package Models.Users;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
abstract public class Role{
    // Variable
    // 0 Passenger, 1 Admin
    private int roleID;
    private String userRole;

    public Role(){
    }

    public Role(int roleID) {
    }

    @Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getRoleID() {
        return roleID;
    }
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    @Column(name = "USER_ROLE")
    public String getUserRole() {
        switch (getRoleID()){
            case 0:
                setUserRole("Passenger");
            case 1:
                setUserRole("Admin");
        }
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    // FIXME Foreign Constraints
    //ROLE_ID WILL BE A FK ON USERINFO
}
