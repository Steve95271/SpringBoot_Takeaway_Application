package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO
     */
    void addEmployee(EmployeeDTO employeeDTO);

    /**
     * 分页查询员工
     * @return
     */
    PageResult pagingQueryEmployee(EmployeePageQueryDTO employeePageQueryDTO);

    void changeEmployeeState(Integer state, Long id);


    /**
     * 根据id获取员工
     * @param id 员工ID
     * @return 员工对象
     */
    Employee getById(Long id);

    /**
     * 修改员工信息
     * @param employeeDTO 需要修改的信息段
     */
    void updateEmployee(EmployeeDTO employeeDTO);
}
