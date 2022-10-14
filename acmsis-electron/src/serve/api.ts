import axios from 'axios'
import formData from 'form-data'
import {alerterror} from "../alert/alert"
import { ref } from 'vue'
axios.defaults.baseURL="/spring"

// axios.defaults.baseURL="https://scmzu-acm.com:1107/api"
const GET=(url:string)=>{
    return new Promise((resolve)=>{
        axios.get(url).then((res)=>{
            if (res.status!=200){
                alerterror(res.statusText)
            }
            resolve(res)
        }).catch((error)=>{
            alerterror(error.message)
        })
    })
}

const POST=(url:string,data:object)=>{
    return new Promise((resolve)=>{
        axios({
            method: 'post',
            url:url,
            data:data
        }).then((res)=>{
            if (res.status!=200){
                alerterror(res.statusText)
            }
            resolve(res)
        }).catch((error)=>{
            alerterror(error.message)
        })
    })
}

const DELETE=(url:string,data:object)=>{
    return new Promise((resolve)=>{
        axios({
            method: 'delete',
            url:url,
            data:data
        }).then((res)=>{
            if (res.status!=200){
                alerterror(res.statusText)
            }
            resolve(res)
        }).catch((error)=>{
            alerterror(error.message)
        })
    })
}
const PUT=(url:string,data:object)=>{
    return new Promise((resolve)=>{
        axios({
            method: 'put',
            url:url,
            data:data
        }).then((res)=>{
            if (res.status!=200){
                alerterror(res.statusText)
            }
            resolve(res)
        }).catch((error)=>{
            alerterror(error.message)
        })
    })
}


export const getUserStutas=()=>{return GET("/getalluserstatus")}
export const getUsers=()=>{return GET("/getalluser")}
export const getTimeCount=(date:Date[]|undefined)=>{
    if (date){
        const data = new formData();
        data.append("StartDate",date[0].toLocaleDateString().replaceAll('/','-'))
        data.append("EndDate",date[1].toLocaleDateString().replaceAll('/','-'))
        return POST("/counttime",data)}
}

export const addUser=(user:any)=>{
    const data = new formData();
    data.append("Name",user.name)
    data.append("StuNum",user.stuNum)
    data.append("Grade",user.grade)
    data.append("Major",user.major)
    data.append("StuCardNum",user.stuCardNum)
    return POST("/adduer",data)
}

export const deleteUser=(id:number)=>{
    const data = new formData();
    data.append("Id",id)
    return DELETE("/deleteuser",data)
}
export const updateUser=(id:number,user:any)=>{
    const data = new formData();
    data.append("Id",id)
    data.append("Name",user.name)
    data.append("StuNum",user.stuNum)
    data.append("Grade",user.grade)
    data.append("Major",user.major)
    data.append("StuCardNum",user.stuCardNum)
    return PUT("/updateuser",data)
}

export const Clockin=(rule:string)=>{
    const data = new formData();
    data.append("rule",rule)
    return POST("/clockin",data)
}