package com.play001.cloud.cms.entity;



import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Admin {

    private Integer id;

    @NotNull(message = "请选择用户组")
    private Role role; //角色,用户组
    @Size(min = 5, max= 18, message = "用户名长度在5-18以内")
    private String username;
    @Size(min = 6, max= 18, message = "密码长度在6-18以内")
    private String password;
    @Size(min = 2, max= 12, message = "真实姓名长度在2-12以内")
    private String realName;
    @Max(value = 200, message = "年龄必须在0-200内")
    @Min(value = 0, message = "年龄必须在0-200内")
    private Integer age;
    @Size(min = 1, message = "邮箱长度错误")
    private String email;

    private Byte status;  //状态 0=冻结,1=正常
    private String avatar;//头像
    private Byte sex;
    private String telephone;
    private String createTime;
    private Admin creator; //创建者

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Admin getCreator() {
        return creator;
    }

    public void setCreator(Admin creator) {
        this.creator = creator;
    }
}
