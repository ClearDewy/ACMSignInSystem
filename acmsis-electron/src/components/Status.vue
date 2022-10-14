<template>
  <el-button @click="reTableData" type="primary" style="float: right" :loading="isLoading" round :icon="Refresh"/>
  <el-table
      :data="tableData"
      style="width: 100%"
      :row-class-name="tableRowClassName"
      v-loading="isLoading"
  >
    <el-table-column prop="name" label="姓名" width="180" />
    <el-table-column prop="stuNum" label="学号" width="180" />
    <el-table-column prop="startTime" label="签到时间" width="280" />
    <el-table-column prop="isAlive" align="right" label="状态" />
  </el-table>
</template>

<script lang="ts" setup>
import {getUserStutas} from "../serve/api"
import {alerterror, alertsuccess} from "../alert/alert"
import {ref} from "vue";
import {Refresh} from '@element-plus/icons-vue'
interface User {
  userId:number
  name: string
  stuNum: string
  isAlive:boolean
  startTime:string
}

const tableRowClassName = ({row, rowIndex,}: {
  row: User
  rowIndex: number
}) => {
  if (row.isAlive) {
    return 'success-row'
  }
  return ''
}


const isLoading=ref(false)
const getTableData=()=>{
  getUserStutas().then((res:any)=>{
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
const tableData=ref<User[]>([])

</script>

<style>
.el-table .warning-row {
  --el-table-tr-bg-color: var(--el-color-warning-light-9);
}
.el-table .success-row {
  --el-table-tr-bg-color: var(--el-color-success-light-9);
}
</style>
