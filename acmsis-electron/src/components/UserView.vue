<template>
  <el-table v-loading="isLoading" :data="filterTableData" style="width: 100%">
    <el-table-column fixed label="姓名" prop="name" width="100"/>
    <el-table-column label="学号" prop="stuNum" width="180"/>
    <el-table-column label="年级" prop="grade" width="80"/>
    <el-table-column label="专业" prop="major" width="150"/>
    <el-table-column label="卡号" prop="stuCardNum" width="180"/>
    <el-table-column fixed="right" align="right" width="150">
      <template #header>
        <el-input v-model.lazy="search" style="width: 120px;height: 35px" placeholder="Type to search" />
        <el-button @click="reTableData" type="primary" :loading="isLoading" round :icon="Refresh"/>
        <el-button type="primary" style="margin: 0" round @click="UserAdd" :icon="Plus"/>
      </template>
      <template #default="scope">
        <el-button type="primary" @click="UserEdit(scope.row)" :icon="Edit" circle />
        <el-popconfirm title="确认删除?" @confirm="UserDelete(scope.row.id)">
          <template #reference>
            <el-button type="danger" :icon="Delete" circle />
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>
  <el-dialog
      v-model="dialogVisible"
      :title="SubOrUp?'添加用户':'修改用户'"
      width="50%"
  >

    <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        :rules="rules"
        label-width="50px"
        class="demo-ruleForm"
        :size="formSize"
        status-icon
    >
      <el-form-item label="姓名" prop="name">
        <el-input v-model="ruleForm.name" />
      </el-form-item>
      <el-form-item label="学号" prop="stuNum">
        <el-input v-model="ruleForm.stuNum" />
      </el-form-item>
      <el-form-item label="年级" prop="grade">
        <el-input v-model="ruleForm.grade" />
      </el-form-item>
      <el-form-item label="专业" prop="major">
        <el-select v-model="ruleForm.major" placeholder="请选择">
          <el-option label="计算机类" value="计算机类" />
          <el-option label="计算机科学" value="计算机科学" />
          <el-option label="软件工程" value="软件工程" />
          <el-option label="网络工程" value="网络工程" />
          <el-option label="人工智能" value="人工智能" />
        </el-select>
      </el-form-item>
      <el-form-item label="卡号" prop="stuCardNum">
        <el-input v-model="ruleForm.stuCardNum" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="SubOrUp?submitForm(ruleFormRef):updateForm(ruleFormRef)" :loading="isLoading2"
        >确定</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {computed, reactive, ref} from 'vue'
import {addUser, deleteUser, getUsers, updateUser} from "../serve/api"
import {alerterror, alertsuccess} from "../alert/alert";
import {
  Edit,Delete,Plus,Refresh
} from '@element-plus/icons-vue'
interface User {
  id:number
  name: string
  stuNum: string
  grade:string
  major:string
  stuCardNum:string
}
const search = ref('')
const tableData=ref<User[]>([])
const isLoading=ref(false)
const filterTableData = computed(() =>
    tableData.value.filter(
        (data) =>
            !search.value ||
            data.name.toLowerCase().includes(search.value.toLowerCase())
    )
)

const getTableData = ()=>{
  getUsers().then((res:any)=>{
    if (res.data.success){
      tableData.value=res.data.data
    }else {
      alerterror(res.data.message)
    }
  })
}
getTableData()
const reTableData = () => {
  isLoading.value=true
  getTableData()
  isLoading.value=false
  alertsuccess("刷新成功")
}

const UserDelete = (id:number) => {
  deleteUser(id).then((res:any)=>{
    if (res.data.success){
      alertsuccess("删除成功")
      getTableData()
    }else {
      alerterror("删除失败")
    }
  })
}

const UserAdd = () => {
  dialogVisible.value = true
  SubOrUp.value=true
}

const updateId=ref(0)
const UserEdit = (user:User) => {
  SubOrUp.value=false
  ruleForm.name=user.name
  ruleForm.stuNum=user.stuNum
  ruleForm.grade=user.grade
  ruleForm.major=user.major
  ruleForm.stuCardNum=user.stuCardNum
  updateId.value=user.id
  dialogVisible.value = true
}

const dialogVisible = ref(false)
import type { FormInstance, FormRules } from 'element-plus'

const formSize = ref('default')
const ruleFormRef = ref<FormInstance>()
const ruleForm = reactive({
  name: '',
  stuNum:'',
  grade:'',
  major:'',
  stuCardNum:''
})

const rules = reactive<FormRules>({
  name: [
    { required: true, message: '请输入姓名', trigger: 'blur' }
  ],
  stuNum: [
    {
      required: true,
      message: '请输入学号',
      trigger: 'blur',
    },
    {
      len:12,
      message: '请输入正确的学号',
      trigger: 'blur',
    }
  ],
  grade: [
    {
      required: true,
      message: '请输入年级',
      trigger: 'blur',
    },
    {
      len:4,
      message: '请输入正确的年级',
      trigger: 'blur',
    }
  ],
  major: [
    {
      required: true,
      message: '请选择专业',
      trigger: 'change',
    },
  ],
  stuCardNum: [
    {
      required: true,
      message: '请输入卡号',
      trigger: 'blur',
    },{
      len:10,
      message: '请输入正确的卡号',
      trigger: 'blur',
    }
  ],
})

const isLoading2=ref(false)
const SubOrUp=ref(true)

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      isLoading2.value=true
      addUser(ruleForm).then((res:any)=>{
        if (res.data.success){
          alertsuccess("添加成功")
          getTableData()
          dialogVisible.value = false
          formEl.resetFields()
        }else {
          alerterror(res.data.message)
        }
      })

      isLoading2.value=false
    } else {
      if (fields)
      alerterror(fields.toString())
    }
  })
}
const updateForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      isLoading2.value=true
      updateUser(updateId.value,ruleForm).then((res:any)=>{
        if (res.data.success){
          alertsuccess("修改成功")
          getTableData()
          dialogVisible.value = false
        }else {
          alerterror(res.data.message)
        }
      })
      formEl.resetFields()
      isLoading2.value=false
    } else {
      if (fields)
        alerterror(fields.toString())
    }
  })
}

</script>
<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>