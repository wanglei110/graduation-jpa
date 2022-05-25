
package me.wang.ideo.repository;

import me.wang.ideo.domain.Ideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @website
* @author wang
* @date 2022-04-26
**/
public interface IdeoRepository extends JpaRepository<Ideo, Long>, JpaSpecificationExecutor<Ideo> {
}