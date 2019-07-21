/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuonghn.DTO;

import java.util.List;

/**
 *
 * @author nhatc
 */
public class UserDTO {

    private String username;
    private String password;
    private String name;
    private String roleName;
    private String history;

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDTO(String username, String name, String roleName) {
        this.username = username;
        this.name = name;
        this.roleName = roleName;
    }

    public UserDTO(String username, String name, String roleName, String history) {
        this.username = username;
        this.name = name;
        this.roleName = roleName;
        this.history = history;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        String tmp = "";
        for (int i = 0; i < history.size(); i++) {
            String t = history.get(i) + ",";
            tmp += t;
        }
        this.history = tmp;
    }

    public UserDTO() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
