import { ElMessage } from 'element-plus'

export const alerterror=(msg:string)=>{
    ElMessage({
        message: msg,
        type: 'error',
    })
}
export const alertsuccess=(msg:string)=>{
    ElMessage({
        message: msg,
        type: 'success',
    })
}

export const alertwarning=(msg:string)=>{
    ElMessage({
        message: msg,
        type: 'warning',
    })
}