const {contextBridge,ipcRenderer} = require('electron')

const closeWindow = async ()=>{
    await ipcRenderer.invoke('closeWindow')
}

const minWindow=async ()=>{
    await ipcRenderer.invoke('minWindow')
}

contextBridge.exposeInMainWorld('myApi',{
    closeWindow,minWindow
})