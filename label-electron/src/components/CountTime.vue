<template>
  <el-header>
    <div class="demo-date-picker flex-grow" style="float: right;padding-top: 14px">
      <div class="block">
        <el-date-picker
            v-model.lazy="valueTime"
            type="daterange"
            unlink-panels
            range-separator="To"
            start-placeholder="Start date"
            end-placeholder="End date"
            :shortcuts="shortcuts"
            @change="getTableData"
        />
      </div>
    </div>
  </el-header>
  <el-table
      :data="tableData"
      style="width: 100%"
      stripe
      v-loading="isLoading"
  >
    <el-table-column prop="name" label="姓名" width="180" />
    <el-table-column prop="stuNum" label="学号" width="280" />
    <el-table-column prop="time" align="right" label="时间/h" />
  </el-table>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import {getTimeCount} from "../serve/api";
import {alerterror, alertwarning} from "../alert/alert";

interface User {
  name: string
  stuNum: string
  time:number
}

const tableData=ref<User[]>()

const valueTime = ref<Date[]>()
const isLoading=ref(false)

const getTableData=()=>{
  isLoading.value=true
  let req=getTimeCount(valueTime)
      if(req){
        req.then((res)=>{
          if (res.data.success){
            tableData.value=res.data.data
          }else {
            alerterror(res.data.message)
          }
        })
      }else alertwarning("请选择时间段")
  isLoading.value=false
}

const shortcuts = [
  {
    text: 'Last week',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
      return [start, end]
    },
  },
  {
    text: 'Last month',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
      return [start, end]
    },
  },
  {
    text: 'Last 3 months',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
      return [start, end]
    },
  },
]
</script>

<style>
.el-table .warning-row {
  --el-table-tr-bg-color: var(--el-color-warning-light-9);
}
.el-table .success-row {
  --el-table-tr-bg-color: var(--el-color-success-light-9);
}
.flex-grow {
  flex-grow: 1;
}
</style>
