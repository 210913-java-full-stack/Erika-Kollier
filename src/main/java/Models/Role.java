package Models;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
abstract public class Role{
    // Variable
    // 0 Passenger, 1 Admin
    protected int roleID;

    public Role(){
    }

    public Role(int roleID) {
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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "role")
    private UserInfo userInfo;
    public void setUser(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
    public UserInfo getUser() {
        return userInfo;
    }
}
