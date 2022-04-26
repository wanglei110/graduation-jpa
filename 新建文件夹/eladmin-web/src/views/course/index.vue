<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
          <el-form-item label="id">
            <el-input v-model="form.id" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="ifDeleted">
            <el-input v-model="form.ifDeleted" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="createTime">
            <el-input v-model="form.createTime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="课程名">
            <el-input v-model="form.courseName" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="课程代码">
            <el-input v-model="form.courseCode" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="课程类别">
            <el-input v-model="form.courseType" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="课程性质(必修,选修等)">
            <el-input v-model="form.courseNature" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="学分">
            <el-input v-model="form.credit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="总学时数">
            <el-input v-model="form.totalHours" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="开课学院">
            <el-input v-model="form.academy" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="开课基层教学组织">
            <el-input v-model="form.teachingGroup" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="面向专业">
            <el-input v-model="form.forProfessional" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="开课学期">
            <el-input v-model="form.semester" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="userId">
            <el-input v-model="form.userId" style="width: 370px;" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="text" @click="crud.cancelCU">取消</el-button>
          <el-button :loading="crud.status.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
        </div>
      </el-dialog>
      <!--表格渲染-->
      <el-table ref="table" v-loading="crud.loading" :data="crud.data" size="small" style="width: 100%;" @selection-change="crud.selectionChangeHandler">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="id" label="id" />
        <el-table-column prop="ifDeleted" label="ifDeleted" />
        <el-table-column prop="createTime" label="createTime" />
        <el-table-column prop="courseName" label="课程名" />
        <el-table-column prop="courseCode" label="课程代码" />
        <el-table-column prop="courseType" label="课程类别" />
        <el-table-column prop="courseNature" label="课程性质(必修,选修等)" />
        <el-table-column prop="credit" label="学分" />
        <el-table-column prop="totalHours" label="总学时数" />
        <el-table-column prop="academy" label="开课学院" />
        <el-table-column prop="teachingGroup" label="开课基层教学组织" />
        <el-table-column prop="forProfessional" label="面向专业" />
        <el-table-column prop="semester" label="开课学期" />
        <el-table-column prop="userId" label="userId" />
        <el-table-column v-if="checkPer(['admin','course:edit','course:del'])" label="操作" width="150px" align="center">
          <template slot-scope="scope">
            <udOperation
              :data="scope.row"
              :permission="permission"
            />
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <pagination />
    </div>
  </div>
</template>

<script>
import crudCourse from '@/api/course'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { id: null, ifDeleted: null, createTime: null, courseName: null, courseCode: null, courseType: null, courseNature: null, credit: null, totalHours: null, academy: null, teachingGroup: null, forProfessional: null, semester: null, userId: null }
export default {
  name: 'Course',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({ title: '课程管理', url: 'api/course', idField: 'id', sort: 'id,desc', crudMethod: { ...crudCourse }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'course:add'],
        edit: ['admin', 'course:edit'],
        del: ['admin', 'course:del']
      },
      rules: {
      }    }
  },
  methods: {
    // 钩子：在获取表格数据之前执行，false 则代表不获取数据
    [CRUD.HOOK.beforeRefresh]() {
      return true
    }
  }
}
</script>

<style scoped>

</style>
