package com.amateur.repository;
//2新增
import com.amateur.entity.Account;

import java.util.List;
public interface AccountRepository {
    public int save(Account account);
    public int update(Account account);
    public int deleteById(long id);
    public List<Account> findAll();
    public Account findById(long id);
    public Account findByName(String name);
    public Account findById2(long id);//包装类Long
    public Account findByNameAndAge(String name,int age);
    public int count();
    public Integer count2();
    public String findNameById(long id);
}
