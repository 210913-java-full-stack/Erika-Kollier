package Models;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
abstract public class Role{
    // Variable
    // 0 Passenger, 1 Admin
    private int roleID;

    public Role(){

    }

    @Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getRoleID() {
        return roleID;
    }
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
}
