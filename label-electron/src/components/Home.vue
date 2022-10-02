<template>
  <el-button type="primary" style="float: right" @click="dialogVisible=true" :icon="Location" circle />

  <p style="margin: 0; line-height: 70px ;background: linear-gradient(to right, red, green,  purple, orange);-webkit-background-clip: text;color: transparent;font-weight: bold;font-size: xxx-large">ACM实验室</p>
  <el-image :src="catuicpc" style="width: 500px"></el-image>
  <el-dialog
      v-model="dialogVisible"
      :title="'手动签到'"
      width="50%"
  >
    <el-form
        ref="ruleFormRef"
        label-width="50px"
        class="demo-ruleForm"
        :size="formSize"
        status-icon
        @submit="ClockInOut"
        :rules="rules"
    >
      <el-form-item label="学号" prop="stuNum">
        <el-input v-model="rule" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="ClockInOut(ruleFormRef)" :loading="isLoading"
        >确定</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {addUser, Clockin} from "../serve/api"
import {alerterror, alertsuccess} from "../alert/alert";
import {ref} from "vue"
import catuicpc from "../assets/katuicpc.jpg"
import {Location,} from '@element-plus/icons-vue'
import {FormInstance, FormRules} from "element-plus";
import { useRoute } from 'vue-router'
const isLoading=ref(false)
const dialogVisible=ref(false)
const rule=ref('')

const rules = {
  stuNum: [
    { required: true, message: '请输入学号', trigger: 'blur' }
  ],}
const ruleFormRef = ref<FormInstance>()

const myclockin=(value:string)=>{
  isLoading.value=true
  Clockin(value).then((res)=>{
    if (res.data.success){
      alertsuccess(res.data.message)
      rule.value=''
      dialogVisible.value=false
    }else {
      alerterror(res.data.message)
    }
  })
  isLoading.value=false
}

const ClockInOut=async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      myclockin(rule.value)
    } else {
      if (fields)
        alerterror(fields.toString())
    }
  })
}
const stunum=ref('')
const route = useRoute()
document.onkeydown=(e)=>{
  // console.log("key:",e.key)
  if (e.key>='0'&&e.key<='9'){
    stunum.value+=e.key
    if (stunum.value.length>100){
      stunum.value=stunum.value.slice(-10)
    }
  }else if (e.key==="Enter"){
    if (stunum.value.length>=10&&route.path!="/userview"){
      stunum.value=stunum.value.slice(-10)
      // console.log(stunum.value)
      myclockin(stunum.value)
    }
  }
}
</script>

<style scoped>

</style>