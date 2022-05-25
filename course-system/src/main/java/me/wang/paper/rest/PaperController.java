
package me.wang.paper.rest;


import me.wang.annotation.Log;
import me.wang.paper.domain.Paper;
import me.wang.paper.service.PaperService;
import me.wang.paper.service.dto.PaperQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;


/**
* @website https://el-admin.vip
* @author wang
* @date 2022-05-18
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "思政改革教学改革论文管理")
@RequestMapping("/api/paper")
public class PaperController {

    private final PaperService paperService;

    @GetMapping
    @Log("查询思政改革教学改革论文")
    @ApiOperation("查询思政改革教学改革论文")
    @PreAuthorize("@el.check('paper:list')")
    public ResponseEntity<Object> queryPaper(PaperQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(paperService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增思政改革教学改革论文")
    @ApiOperation("新增思政改革教学改革论文")
    @PreAuthorize("@el.check('paper:add')")
    public ResponseEntity<Object> createPaper(@Validated @RequestBody Paper resources){
        return new ResponseEntity<>(paperService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改思政改革教学改革论文")
    @ApiOperation("修改思政改革教学改革论文")
    @PreAuthorize("@el.check('paper:edit')")
    public ResponseEntity<Object> updatePaper(@Validated @RequestBody Paper resources){
        paperService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除思政改革教学改革论文")
    @ApiOperation("删除思政改革教学改革论文")
    @PreAuthorize("@el.check('paper:del')")
    public ResponseEntity<Object> deletePaper(@RequestBody Long[] ids) {
        paperService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}