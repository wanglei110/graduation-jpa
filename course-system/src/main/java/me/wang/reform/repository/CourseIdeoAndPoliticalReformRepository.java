
package me.wang.reform.repository;

import me.wang.reform.domain.CourseIdeoAndPoliticalReform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @website https://el-admin.vip
* @author wang
* @date 2022-05-18
**/
public interface CourseIdeoAndPoliticalReformRepository extends JpaRepository<CourseIdeoAndPoliticalReform, Long>, JpaSpecificationExecutor<CourseIdeoAndPoliticalReform> {
}