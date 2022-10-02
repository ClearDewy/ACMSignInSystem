import axios from 'axios'
import formData from 'form-data'
import {alerterror} from "../alert/alert"
import { ref } from 'vue'
// axios.defaults.baseURL="/spring"
axios.defaults.baseURL="http://localhost:8080/api"

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


export const getUserStutas=()=>{return GET("/getalluserstatus")}
export const getUsers=()=>{return GET("/getalluser")}
export const getTimeCount=(date:Date[]|undefined)=>{
    if (date){
        var data=new formData()
        data.append("StartDate",date.value[0].toLocaleDateString().replaceAll('/','-'))
        data.append("EndDate",date.value[1].toLocaleDateString().replaceAll('/','-'))
        return POST("/counttime",data)}
}
import ruleForm from "../components/UserView.vue"
export const addUser=(user:typeof ruleForm)=>{
    var data=new formData()
    data.append("Name",user.name)
    data.append("StuNum",user.stuNum)
    data.append("Grade",user.grade)
    data.append("Major",user.major)
    data.append("StuCardNum",user.stuCardNum)
    return POST("/adduer",data)
}

export const deleteUser=(id:number)=>{
    var data=new formData()
    data.append("Id",id)
    return POST("/deleteuser",data)
}
export const updateUser=(id:number,user:typeof ruleForm)=>{
    var data=new formData()
    data.append("Id",id)
    data.append("Name",user.name)
    data.append("StuNum",user.stuNum)
    data.append("Grade",user.grade)
    data.append("Major",user.major)
    data.append("StuCardNum",user.stuCardNum)
    return POST("updateuser",data)
}

export const Clockin=(rule:string)=>{
    var data=new formData()
    data.append("rule",rule)
    return POST("/clockin",data)
}


