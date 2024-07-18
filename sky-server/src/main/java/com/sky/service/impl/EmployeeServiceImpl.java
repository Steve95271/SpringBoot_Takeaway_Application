package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.EmployeeMapper;
import com.sky.result.PageResult;
import com.sky.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        //把传进来的密码换成md5再进行比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    /**
     * 添加新员工
     * @param employeeDTO
     */
    @Override
    public void addEmployee(EmployeeDTO employeeDTO) {
        //创建员工对象
        Employee employee = new Employee();

        //对象属性拷贝
        //拷贝的属性名要对上
        BeanUtils.copyProperties(employeeDTO, employee);

        //设置账号状态
        employee.setStatus(StatusConstant.ENABLE);

        //设置密码，把默认密码转成md5加密
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        //设置时间
        //employee.setCreateTime(LocalDateTime.now());
        //employee.setUpdateTime(LocalDateTime.now());

        //设置当前记录创建人id和修改人id
        //通过线程的设置和获取线程id的方法获得当前操作人的id
        //employee.setCreateUser(BaseContext.getCurrentId());
        //employee.setUpdateUser(BaseContext.getCurrentId());

        employeeMapper.insert(employee);
    }

    /**
     * 分页查询员工
     * @param employeePageQueryDTO
     * @return
     */
    @Override
    public PageResult pagingQueryEmployee(EmployeePageQueryDTO employeePageQueryDTO) {
        //分页查询
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());

        Page<Employee> employees = employeeMapper.pageQuery(employeePageQueryDTO);

        long total = employees.getTotal();
        List<Employee> result = employees.getResult();

        return new PageResult(total, result);
    }

    /**
     * 修改员工状态
     * @param state
     * @param id
     */
    @Override
    public void changeEmployeeState(Integer state, Long id) {
        Employee employee = new Employee();
        employee.setStatus(state);
        employee.setId(id);

        employeeMapper.update(employee);
    }

    /**
     * 根据id获取员工
     * @param id 员工ID
     * @return 员工对象
     */
    @Override
    public Employee getById(Long id) {
       return employeeMapper.getById(id);
    }

    /**
     * 修改员工信息
     * @param employeeDTO 需要修改的信息段
     */
    @Override
    public void updateEmployee(EmployeeDTO employeeDTO) {
        //创建员工对象用于Mapper
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);

        //用ThreadLocal获得当前操作人的id
        //employee.setUpdateUser(BaseContext.getCurrentId());
        //employee.setUpdateTime(LocalDateTime.now());
        employeeMapper.update(employee);
    }
}
