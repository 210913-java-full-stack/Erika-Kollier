package Models;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Table(name = "ROLES")
@Entity(name = "ROLE")
public class Role{
    public Role(){
    }

    public Role(int roleID) {
    }

    // 0 Passenger, 1 Admin
    @Id
    @Column(name = "ROLE_ID", columnDefinition = "int default 0")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleID;
    public int getRoleID() {
        return roleID;
    }
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    @Column(name = "USER_ROLE")
    private String userRole;
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

    // ROLE_ID WILL BE A FK ON USERINFO
    @OneToMany (cascade = CascadeType.ALL)
    private List<UserInfo> userInfo;
}